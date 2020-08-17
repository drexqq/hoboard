package review;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Reserve.Reserve_Dto;
import Util.UtilEx;
import member.Member_Dto;
import net.sf.json.JSONObject;

@WebServlet("/review")
public class ReviewController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		Review_Dao dao = Review_Dao.getInstance();
		String key = req.getParameter("key");

		if (key.equals("main")) {
			List<Review_Dto> list = null;
			
			String sW = (String) req.getParameter("searchWord");
			String c = (String) req.getParameter("choice");

			int limit = 5;
			int pageNumber = 0;

			if (req.getParameter("page") == null)
				pageNumber = 0;
			else
				pageNumber = Integer.parseInt((String) req.getParameter("page"));

			int len = dao.getsearch(c, sW);
			int page = len / limit; // 예: 12개 -> 2page
			if (len % limit > 0)
				page = page + 1; // -> 2
			System.out.println("page = " + page);
			// 처음 들어왔을때
			if (sW == null && c == null && pageNumber == 0)
				list = dao.getReview_PagingList("", "", limit, pageNumber);
			// 페이지만 바뀔때
			else if (sW == null && c == null && req.getParameter("page") != null)
				list = dao.getReview_PagingList("", "", limit, pageNumber);
			// 다 바뀔때
			else {
				if (sW == null)
					sW = "";
				list = dao.getReview_PagingList(c, sW, limit, pageNumber);
				req.setAttribute("choice", c);
				req.setAttribute("searchWord", sW);
			}
			req.setAttribute("len", len);
			req.setAttribute("pageNumber", pageNumber); // 현재 페이지 넘버
			req.setAttribute("page", page - 1); // 총 페이지수
			req.setAttribute("reviewlist", list); // 실제 데이터

			UtilEx.forward("review.jsp", req, resp);

			// -> review_ list
		}else if (key.equals("writeview")) {
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

				}

			} else if (key.equals("detail")) {

				int seq = Integer.parseInt(req.getParameter("seq"));
				 //ArrayList<Review_COMM_Dto> comments = null;
				// seq check
				System.out.println("seq:" + seq);

				Review_Dto dto = dao.getDetail_list(seq);
						
				// view count
				dao.viewcount(seq);
				
				
				req.setAttribute("detaillist", dto);
				UtilEx.forward("review_detail.jsp", req, resp);
					
			} else if (key.equals("updateview")) {

				int seq = Integer.parseInt(req.getParameter("seq"));

				Review_Dto dto = dao.getDetail_list(seq);
				
				req.setAttribute("detaillist", dto);
				UtilEx.forward("review_update.jsp", req, resp);


			} else if (key.equals("delete")) {
				int seq = Integer.parseInt(req.getParameter("seq"));

				// seq check
				System.out.println("seq:" + seq);

				boolean delete = dao.Review_delete(seq);

				if (delete) {
					System.out.println("글 삭제 성공");
					resp.sendRedirect("review.jsp");

				} else {
					System.out.println("글 삭제 실패");
					resp.sendRedirect("review.jsp");
				}

			} else if (key.equals("commentwrite")) {
					
					Review_COMM_Dao cdao = Review_COMM_Dao.getInstance();
					Review_COMM_Dto comment = new Review_COMM_Dto();
					
					String comment_seq = req.getParameter("comment_seq");
					String comment_id = req.getParameter("comment_id");
					String comment_content = req.getParameter("comment_content");
					
					
					comment.setId("comment_id");
					comment.setContent("comment_content");
					
					boolean result = cdao.insertComment(comment);

					if (result) {
						resp.setContentType("text/html; charset=utf-8");
						PrintWriter out = resp.getWriter();
						out.print("complete reply");
						out.close();
					}
					
				}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String key = req.getParameter("key");
		Review_Dao dao = Review_Dao.getInstance();

		if (key.equals("writecomplete")) {
			String indvd_id = req.getParameter("indvd_id");
			String busi_id = req.getParameter("busi_id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String filename = req.getParameter("filename");
			int score = Integer.parseInt(req.getParameter("score"));
			String fileupload = req.getParameter("fileupload");
			String busi_cate = req.getParameter("cate");

			// Parameter check
			System.out.println("indvd_id: " + indvd_id);
			System.out.println("busi_id: " + busi_id);
			System.out.println("title: " + title);
			System.out.println("content: " + content);

			System.out.println("score: " + content);
			System.out.println("fileupload: " + fileupload);
			System.out.println("cate: " + busi_cate);

			boolean write = dao
					.writeReview(new Review_Dto(busi_id, indvd_id, title, content, score, filename, busi_cate));

			if (write) {
				System.out.println("글 작성 성공");
				resp.sendRedirect("review?key=main");

			} else {
				System.out.println("글 작성 실패");
				resp.sendRedirect("review?key=main");
			}
		} else if (key.equals("update")) {

			int seq = Integer.parseInt(req.getParameter("seq"));
			String title = req.getParameter("title");
			String content = req.getParameter("content");

			System.out.println("seq: " + seq);
			System.out.println("title: " + title);
			System.out.println("content: " + content);

			boolean update = dao.getReview_update(seq, title, content);

			if (update) {
				System.out.println("글 수정 성공");
				resp.sendRedirect("review?key=main");

			} else {
				System.out.println("글 수정 실패");
				resp.sendRedirect("review?key=main");
			}

		}
	}
}
