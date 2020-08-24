package review;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;
import member.BUSI_Member_Dao;
import member.Member_Dao;
import member.Member_Dto;
import net.sf.json.JSONObject;

@WebServlet("/review")
public class ReviewController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Review_Dao dao = Review_Dao.getInstance();
		String d = req.getParameter("d");
		int seq = 0;
		if (d != null) {
			seq = UtilEx.numCheck(d) ? Integer.parseInt(req.getParameter("d")) : -1;
		}
		
		// String key = req.getParameter("key");
		// review.jsp
		if (d == null || "".equals(d)) {
			System.out.println("review.jsp");
			
			List<LinkedHashMap<Review_Dto, String>> list = null;
			String sW = (String) req.getParameter("searchWord");
			String c = (String) req.getParameter("choice");
			int limit = 5;
			int pageNumber = (req.getParameter("page") != null) ? Integer.parseInt((String) req.getParameter("page")) : 0;
			int len = UtilEx.getAllCountTable("REVIEW", c, sW);
			int page = len / limit;
			if (len % limit > 0)
				page = page + 1; // -> 2

			// 처음 들어왔을때
			if (sW == null && c == null && pageNumber == 0)
				list = dao.getReviewPagingList("", "", limit, pageNumber);
			// 페이지만 바뀔때
			else if (sW == null && c == null && req.getParameter("page") != null)
				list = dao.getReviewPagingList("", "", limit, pageNumber);
			// 검색후 페이지 바뀔때
			else {
				list = dao.getReviewPagingList(c, sW, limit, pageNumber);
				req.setAttribute("choice", c);
				req.setAttribute("searchWord", sW);
			}
			System.out.println(list.toString());
			req.setAttribute("pageNumber", pageNumber); // 현재 페이지 넘버
			req.setAttribute("page", page - 1); // 총 페이지수
			req.setAttribute("reviewlist", list); // 실제 데이터
			UtilEx.forward("review.jsp", req, resp);
		}
		// review_detail.jsp
		else if ((seq+"").equals(d)) {
			System.out.println("detail.jsp");
			HashMap<String, Review_Dto> dto = dao.getReviewDetail(seq);
			// updateViewCount;
			dao.updateViewCount(seq);
			Review_COMM_Dao commDao = Review_COMM_Dao.getInstance();
			// getCommentList
			List<Review_COMM_Dto> commList = commDao.getComments(seq);
			System.out.println(dto.toString());
			req.setAttribute("seq", seq);
			req.setAttribute("reviewDto", dto);
			req.setAttribute("commList", commList);
			UtilEx.forward("review_detail.jsp", req, resp);
		}
		// review_update.jsp
		else if ("u".equals(d)) {
			System.out.println("review_update.jsp");
			seq = Integer.parseInt(req.getParameter("seq"));
			HashMap<String, Review_Dto> dto = dao.getReviewDetail(seq);
			
			req.setAttribute("reviewDto", dto);
			UtilEx.forward("review_update.jsp", req, resp);
		}

		/*
		 * else if (key.equals("writeview")) {
		 * System.out.println("---review writeView ---");
		 * 
		 * // dummy RESERVE DATA -> REVIEW String id = req.getParameter("id"); String
		 * busi = req.getParameter("busi"); // getSeq int seq =
		 * Integer.parseInt(req.getParameter("seq"));
		 * 
		 * // check System.out.println("id check :" + id);
		 * System.out.println("busi check :" + busi); System.out.println("seq check :" +
		 * seq);
		 * 
		 * // status lookup method String check = dao.checkStatus(id, seq);
		 * 
		 * // status check System.out.println(check);
		 * 
		 * // reserve MEMBER - NAME Member_Dto N_list = dao.getBusi_Name(busi, seq);
		 * 
		 * // reserve MEMBER - NAME check System.out.println(N_list.toString());
		 * 
		 * // reserve CATE Reserve_Dto C_list = dao.getReserve_Cate(id, seq);
		 * 
		 * // reserve CATE check System.out.println(C_list.toString());
		 * 
		 * // reserve all data Transfer Reserve_Dto R_list = dao.getReserve_Data(id,
		 * seq);
		 * 
		 * // reserve list check System.out.println(R_list.toString());
		 * 
		 * if (check.equals("3")) { req.setAttribute("businame", N_list);
		 * req.setAttribute("reservecate", C_list); req.setAttribute("reservelist",
		 * R_list);
		 * 
		 * UtilEx.forward("review_write.jsp", req, resp);
		 * 
		 * }
		 * 
		 * } else if (key.equals("detail")) {
		 * 
		 * int seq = Integer.parseInt(req.getParameter("seq")); // seq check
		 * System.out.println("seq:" + seq);
		 * 
		 * Review_Dto dto = dao.getReviewDetail(seq);
		 * 
		 * // view count dao.updateViewCount(seq);
		 * 
		 * Review_COMM_Dao Cdao = Review_COMM_Dao.getInstance();
		 * 
		 * // comment List<Review_COMM_Dto> Clist = Cdao.getComments(seq);
		 * 
		 * req.setAttribute("seq", seq); req.setAttribute("detaillist", dto);
		 * req.setAttribute("commentlist", Clist); UtilEx.forward("review_detail.jsp",
		 * req, resp);
		 * 
		 * } else if (key.equals("updateview")) {
		 * 
		 * int seq = Integer.parseInt(req.getParameter("seq"));
		 * 
		 * 
		 * 
		 *  UtilEx.forward("review_update.jsp", req,
		 * resp);
		 * 
		 * } else if (key.equals("delete")) { int seq =
		 * Integer.parseInt(req.getParameter("seq"));
		 * 
		 * // seq check System.out.println("seq:" + seq);
		 * 
		 * boolean delete = dao.deleteReview(seq);
		 * 
		 * if (delete) { System.out.println("글 삭제 성공"); req.setAttribute("delete",
		 * delete); UtilEx.forward("myreview", req, resp);
		 * 
		 * } else { System.out.println("글 삭제 실패"); req.setAttribute("delete", delete);
		 * UtilEx.forward("review?key=detail&seq="+seq+"", req, resp); }
		 * 
		 * } else if (key.equals("commentwrite")) {
		 * 
		 * Review_COMM_Dao cdao = Review_COMM_Dao.getInstance(); Review_COMM_Dto comment
		 * = new Review_COMM_Dto();
		 * 
		 * String comment_seq = req.getParameter("comment_seq"); String comment_id =
		 * req.getParameter("comment_id"); String comment_content =
		 * req.getParameter("comment_content");
		 * 
		 * comment.setId("comment_id"); comment.setContent("comment_content");
		 * 
		 * boolean result = cdao.insertComment(comment);
		 * 
		 * if (result) { resp.setContentType("text/html; charset=utf-8"); PrintWriter
		 * out = resp.getWriter(); out.print("complete reply"); out.close(); }
		 * 
		 * }
		 */
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println("post");
		
		Review_Dao dao = Review_Dao.getInstance();
		JSONObject jsonData = new JSONObject();
		
		int seq = Integer.parseInt(req.getParameter("seq"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		System.out.println(title);
		System.out.println(content);
		boolean isUpdate = dao.updateReview(seq, title, content);
		if(isUpdate) {
			jsonData.put("update", isUpdate);
			System.out.println(jsonData);
			resp.setContentType("application/json; charset=UTF-8");
			resp.getWriter().print(jsonData);
		};
	}
}
