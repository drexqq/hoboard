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

import org.apache.commons.fileupload.FileItem;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Reserve.Reserve_Dto;
import Util.UtilEx;
import member.Member_Dto;

@WebServlet("/REVIEW")
public class ReviewController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		String review = req.getParameter("review");
		Review_Dao dao = Review_Dao.getInstance();

		// TODO review_Main not using
		if(review.equals("reviewlist")) {
			System.out.println("---review list---");

			List<Review_Dto> list = dao.getReviewList();

			req.setAttribute("reviewlist", list);
			UtilEx.forward("	", req, resp);

			// review_WriteView
		} else if (review.equals("writeview")) {
			System.out.println("---review writeView ---");

			// dummy RESERVE DATA -> REVIEW
			// getSession ID
			String id = req.getParameter("id");
			// getBusi_ID
			String busi = req.getParameter("busi");
			// getSeq
			int seq = Integer.parseInt(req.getParameter("seq"));

			// check
			System.out.println("id check :" + id);
			System.out.println("busi check :" + busi);
			System.out.println("seq check :" + seq);

			// status lookup method
			String check = dao.checkStatus(id, seq);

			// status check
			System.out.println(check);

			// reserve MEMBER - NAME
			Member_Dto N_list = dao.getBusi_Name(busi, seq);

			// reserve MEMBER - NAME check
			System.out.println(N_list.toString());

			// reserve CATE
			Reserve_Dto C_list = dao.getReserve_Cate(id, seq);

			// reserve CATE check
			System.out.println(C_list.toString());

			// reserve all data Transfer
			Reserve_Dto R_list = dao.getReserve_Data(id, seq);

			// reserve list check
			System.out.println(R_list.toString());
			
			if (check.equals("3")) {

				req.setAttribute("businame", N_list);
				req.setAttribute("reservecate", C_list);
				req.setAttribute("reservelist", R_list);

				UtilEx.forward("review_write.jsp", req, resp);
				
			} else {
				resp.sendRedirect("review.jsp");
			}
			
		// -> detail list
		}else if (review.equals("detail")) {
			
			int seq = Integer.parseInt(req.getParameter("seq"));
			
			// seq check
			System.out.println("seq:" + seq);
			
			Review_Dto dto = dao.getDetail_list(seq);
			
			//view count
			dao.viewcount(seq);
			
			req.setAttribute("detaillist", dto);
			UtilEx.forward("review_detail.jsp", req, resp);
			
		}else if (review.equals("updateview")) {
			
			int seq = Integer.parseInt(req.getParameter("seq"));
			
			Review_Dto dto = dao.getDetail_list(seq);
			
			
			
			req.setAttribute("detaillist", dto);
			UtilEx.forward("review_update.jsp", req, resp);
			
		}else if(review.equals("delete")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			
			// seq check
			System.out.println("seq:" + seq);
			
			boolean delete = dao.Review_delete(seq);
			
			if(delete) {
				System.out.println("글 삭제 성공");
				resp.sendRedirect("REVIEW?review=main");
				
			}else {
				System.out.println("글 삭제 실패");
				resp.sendRedirect("REVIEW?review=main");
			}
			
		}else if(review.equals("answer")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			
			Review_Dto dto = dao.getDetail_list(seq);
			
			req.setAttribute("detaillist", dto);
			UtilEx.forward("review_answer.jsp", req, resp);
		
		//TODO paging main now using
		}else if(review.equals("main")) {
			//검색어
			String searchWord = req.getParameter("searchWord");
			//셀렉트박스
			String choice = req.getParameter("choice");
			//페이지넘버
			String spageNumber = req.getParameter("pageNumber");
			
			if(choice == null || choice.equals("")){
				choice = "sel";
			}

			if(choice.equals("sel")){
				searchWord = "";
			}
			if(searchWord == null){
				searchWord = "";
				choice = "sel";
			}
			
			//현재 페이지
			int pageNumber = 0; 
			if(spageNumber != null && !spageNumber.equals("")){
				pageNumber = Integer.parseInt(spageNumber);
			}
			
			int len = dao.getsearch(choice, searchWord);
			
			System.out.println("-전체 컬럼 숫자-len:" + len);
			
	
			int Page = len / 10;		// 예: 12개 -> 2page
			if(len % 10 > 0){
				Page = Page + 1;	// -> 2
			}
		
			
			List<Review_Dto> list = dao.getReview_PagingList(choice, searchWord, pageNumber);
			
			int P_pageNumber = pageNumber;
			int P_paging = Page;
			//String P_pageNumber = Integer.toString(pageNumber);
			//String P_paging =  Integer.toString(Page);
			
			
			System.out.println("-카테고리선택- choice :" + choice);
			System.out.println("-검색어- searchWord :" + searchWord);
			System.out.println("-페이지번호- pageNumber :" + P_pageNumber);
			System.out.println("-페이지- page :" + P_paging);
			
			
			
			
			req.setAttribute("choice", choice);
			req.setAttribute("searchWord", searchWord);
			req.setAttribute("pageNumber", P_pageNumber);
			req.setAttribute("page", P_paging);
			req.setAttribute("paginglist", list);
			UtilEx.forward("review.jsp", req, resp);
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
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String filename = req.getParameter("filename");
			int score = Integer.parseInt(req.getParameter("score"));
			String fileupload = req.getParameter("fileupload");
			String busi_cate = req.getParameter("cate");
			
			//Parameter check
			System.out.println("indvd_id: " + indvd_id);
			System.out.println("busi_id: " + busi_id);
			System.out.println("title: " + title);
			System.out.println("content: " + content);
			
			System.out.println("score: " + content);
			System.out.println("fileupload: " + fileupload);	
			System.out.println("cate: " + busi_cate);
			
			boolean write = dao.writeReview(new Review_Dto(busi_id,indvd_id,title,content,score,filename,busi_cate));
			
			if(write) {
				System.out.println("글 작성 성공");
				resp.sendRedirect("REVIEW?review=main");
				
			}else {
				System.out.println("글 작성 실패");
				resp.sendRedirect("REVIEW?review=main");
			}
		}else if(review.equals("update")) {
			
			int seq = Integer.parseInt(req.getParameter("seq"));
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			System.out.println("seq: " + seq);
			System.out.println("title: " + title);
			System.out.println("content: " + content);
			
			boolean update = dao.getReview_update(seq, title, content);
			
			if(update) {
				System.out.println("글 수정 성공");
				resp.sendRedirect("review.jsp");
				
			}else {
				System.out.println("글 수정 실패");
				resp.sendRedirect("review.jsp");
			}
			
		}
	}
}
