package File;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import review.Review_Dao;
import review.Review_Dto;

@WebServlet("/file")
public class Review_File_Controller extends HttpServlet {

	ServletConfig mConfig = null;
	final int BUFFER_SIZE = 8192;	
	
	public String processUploadFile(FileItem fileItem, String dir) throws IOException {
		// ..abc.txt d:\tmp\
		String filename = fileItem.getName(); // load + filename
		System.out.println(filename);

		long sizeInBytes = fileItem.getSize();
		System.out.println(sizeInBytes);
		
		// file
		if (sizeInBytes > 0) { // d:\\tmp\\abc.txt d:/tmp/abc.txt \" \\

			int idx = filename.lastIndexOf("\\");
			if (idx == -1) {
				idx = filename.lastIndexOf("/");
			}

			filename = filename.substring(idx + 1); // -> abc.txt
			File uploadFile = new File(dir, filename);

			try {
				fileItem.write(uploadFile); // real upload 
			} catch (Exception e) {
			}
		}
		return filename; // DB save return
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		mConfig = config;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("--FileDownLoad start--");

		String filename = req.getParameter("filename");
		String sseq = req.getParameter("seq");

		// download(DB)
		BufferedOutputStream out = new BufferedOutputStream(resp.getOutputStream());

		String filePath = "";

		// tomcat(server)
		filePath = mConfig.getServletContext().getRealPath("/upload");

		// filePath = "d:\\tmp";

		filePath = filePath + "\\" + filename;
		System.out.println("filePath:" + filePath);

		File f = new File(filePath);

		if (f.exists() && f.canRead()) {

			// download window set
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
			resp.setHeader("Content-Transfer-Encoding", "binary;");
			resp.setHeader("Content-Length", "" + f.length());
			resp.setHeader("Pragma", "no-cache;");
			resp.setHeader("Expires", "-1;");

			BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));

			byte buffer[] = new byte[BUFFER_SIZE];

			int read = 0;

			while ((read = fileInput.read(buffer)) != -1) {
				out.write(buffer, 0, read); 
			}
			fileInput.close();
			out.flush();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

//		String key = req.getParameter("key");
		Review_Dao dao = Review_Dao.getInstance();

//		if (key.equals("writecomplete")) {
		// tomcat (server)
		String fupload = req.getRealPath("/upload");
		// String fupload = req.getSession().getServletContext().getRealPath("/");

		// 지정 폴더(client)
		System.out.println("업로드 폴더:" + fupload);

		String yourTempDir = fupload;

		int yourMaxRequestSize = 100 * 1024 * 1024; // 1 Mbyte
		int yourMaxMemorySize = 100 * 1024; // 1 Kbyte

		// form field variable
		String busi_id = "";
		String indvd_id = "";
		String title = "";
		String content = "";
		int score = 0;
		// file
		String filename = "";
		String busi_cate = "";

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart == true) {

			// Fileitem
			DiskFileItemFactory factory = new DiskFileItemFactory();

			factory.setSizeThreshold(yourMaxMemorySize);
			factory.setRepository(new File(yourTempDir));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(yourMaxRequestSize);

			List<FileItem> items;

			try {
				items = upload.parseRequest(req);
				Iterator<FileItem> it = items.iterator();

				// 구분
				while (it.hasNext()) {
					FileItem item = it.next();

					if (item.isFormField()) { // id, title, content
						if (item.getFieldName().equals("busi_id")) {
							busi_id = item.getString("utf-8");
							System.out.println(busi_id);
						} else if (item.getFieldName().equals("indvd_id")) {
							indvd_id = item.getString("utf-8");
							System.out.println(indvd_id);
						} else if (item.getFieldName().equals("title")) {
							title = item.getString("utf-8");
							System.out.println(title);
						} else if (item.getFieldName().equals("content")) {
							content = item.getString("utf-8");
							System.out.println(content);
						} else if (item.getFieldName().equals("score")) {
							score = Integer.parseInt(item.getString("utf-8"));
							System.out.println(score);
						} else if (item.getFieldName().equals("filename")) {
							filename = item.getString("utf-8");
							System.out.println(filename);
						} else if (item.getFieldName().equals("cate")) {
							busi_cate = item.getString("utf-8");
							System.out.println(busi_cate);
						}
					} else { // fileload ??
						if (item.getFieldName().equals("filename")) {
							filename = processUploadFile(item, fupload);
						}
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
		// DB에 저장
		boolean isS = dao.writeReview(new Review_Dto(busi_id, indvd_id, title, content, score, filename, busi_cate));
		if (isS) {
			System.out.println("파일 업로드 성공");
			resp.sendRedirect("reviewn");

		} else {
			System.out.println("파일 업로드 실패");
			resp.sendRedirect("review");

		}
	}
//	}

}
