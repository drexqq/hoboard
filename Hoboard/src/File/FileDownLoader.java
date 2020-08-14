package File;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/filename")
public class FileDownLoader extends HttpServlet {
	
	ServletConfig mConfig = null;
	final int BUFFER_SIZE = 8192;
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		mConfig = config;		// file 업로드 경로를 취득하기 위함
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("FileDownLoader doGet()");
	
		String filename = req.getParameter("filename");
		String sseq = req.getParameter("seq");
		int seq = Integer.parseInt(sseq);
		System.out.println("seq:" + seq);
		
		
		//System.out.println(filename);
		//System.out.println(seq);
		BufferedOutputStream out = new BufferedOutputStream(resp.getOutputStream());
		

		String filePath = "";
		
		// tomcat(server)
		filePath = mConfig.getServletContext().getRealPath("/upload");
		
		
		// 폴더(client)
		// filePath = "d:\\tmp";
		
		filePath = filePath + "\\" + filename;
		System.out.println("filePath: " + filePath);
		
		File f = new File(filePath);
		
		if( f.exists() && f.canRead() ) {
			
			// download window set
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
			resp.setHeader("Content-Transfer-Encoding", "binary;");
			resp.setHeader("Content-Length", "" + f.length());
			resp.setHeader("Pragma", "no-cache;"); 
			resp.setHeader("Expires", "-1;");
			
			// 파일 생성, 기입
			BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));
			
			byte buffer[] = new byte[BUFFER_SIZE];
			
			int read = 0;
			
			while((read = fileInput.read(buffer)) != -1) {
				out.write(buffer, 0, read);		// 실제 다운로드
			}
			//꼭 기입할 것
			fileInput.close();
			out.flush();
			
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
