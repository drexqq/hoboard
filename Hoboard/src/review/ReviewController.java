package review;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import Reserve.Reserve_Dto;
import Util.UtilEx;
import member.Member_Dto;

@WebServlet("/REVIEW")
public class ReviewController extends HttpServlet {
	
	ServletConfig mConfig = null;
	final int BUFFER_SIZE = 8192;
	
	//TODO file download
	/*
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		mConfig = config;		// file upload load
	}*/

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String review = req.getParameter("review");
		Review_Dao dao = Review_Dao.getInstance(); 
		
		//review_Main
		if(review.equals("reviewlist")) {
			System.out.println("---review list---");
			
			List<Review_Dto> list = dao.getReviewList();
			
			req.setAttribute("reviewlist", list);
			UtilEx.forward("review.jsp", req, resp);
		
		
		//review_WriteView
		}else if(review.equals("writeView")) {
			System.out.println("---review writeView ---");
			
			
			//dummy RESERVE DATA -> REVIEW 
			//getSession ID
			String id = req.getParameter("id");
			//getBusi_ID
			String busi = req.getParameter("busi");
			//getSeq
			int seq = Integer.parseInt(req.getParameter("seq"));
			
			//check
			System.out.println("id check :" + id);
			System.out.println("busi check :" + busi);
			System.out.println("seq check :" + seq);
			
			//status lookup method
			String check = dao.checkStatus(id ,seq);
			
			//status check
			System.out.println(check);
			
			
			//reserve MEMBER - NAME
			Member_Dto N_list = dao.getBusi_Name(busi, seq);
			
			//reserve MEMBER - NAME check
			System.out.println(N_list.toString());
			
			//reserve CATE
			Reserve_Dto C_list = dao.getReserve_Cate(id, seq);
			
			//reserve CATE check
			System.out.println(C_list.toString());
			
			//reserve all data Transfer
			Reserve_Dto R_list = dao.getReserve_Data(id, seq);
			
			//reserve list check
			System.out.println(R_list.toString());
			

			
			if(check.equals("3")) {
				
				
				req.setAttribute("businame", N_list);
				req.setAttribute("reservecate", C_list);
				req.setAttribute("reservelist", R_list);
				
				UtilEx.forward("review_write.jsp", req, resp);
				
			}else {
				resp.sendRedirect("review.jsp");
			}
	    }
		else if(review.equals("업데이트")) {
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String review = req.getParameter("review");
		
		
		Review_Dao dao = Review_Dao.getInstance();
		

	
		
		if(review.equals("writecomplete")) {
			
			String indvd_id = req.getParameter("indvd_id");
			String busi_id = req.getParameter("busi_id");
			//TODO LIST 병원 이름
			String cate = req.getParameter("cate");
			
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String score = req.getParameter("score");
			String fileupload = req.getParameter("fileupload");
			
			//Parameter check
			System.out.println("indvd_id: " + indvd_id);
			System.out.println("busi_id: " + busi_id);
			System.out.println("cate: " + cate);
			System.out.println("id: " + id);
			System.out.println("title: " + title);
			System.out.println("content: " + content);
			System.out.println("score: " + content);
			System.out.println("fileupload: " + fileupload);
			
			
			
			//dao.processUploadFile(fileItem, dir);
			
			
			
			
			
			//TODO FileDownload
			/*
			String filename = req.getParameter("filename");
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			System.out.println("seq:" + seq);
			
			// download 횟수 증가(DB)
			Review_Dao dao = Review_Dao.getInstance();
			dao.downcount(seq);
			
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
			
		//getParameter session ID
		String id = req.getParameter("id");
		
		*/
		
		}
	
	}
}
