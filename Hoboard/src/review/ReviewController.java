package review;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Reserve.Reserve_Dao;
import Reserve.Reserve_Dto;
import Util.UtilEx;
import net.sf.json.JSONObject;

@WebServlet("/review")
public class ReviewController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Review_Dao dao = Review_Dao.getInstance();
		Reserve_Dao reserveDao = Reserve_Dao.getInstance();
		String d = req.getParameter("d");
		JSONObject jsonData = new JSONObject();
		
		int seq = 0;
		if (d != null) seq = UtilEx.numCheck(d) ? Integer.parseInt(req.getParameter("d")) : -1;
		
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
			req.setAttribute("pageNumber", pageNumber); // 현재 페이지 넘버
			req.setAttribute("page", page - 1); // 총 페이지수
			req.setAttribute("reviewlist", list); // 실제 데이터
			UtilEx.forward("review.jsp", req, resp);
		}
		// review_detail.jsp
		else if ((seq+"").equals(d)) {
			System.out.println("detail.jsp");
			HashMap<String, Review_Dto> dto = dao.getReviewDetail(seq);
			dao.updateViewCount(seq);
			Review_COMM_Dao commDao = Review_COMM_Dao.getInstance();
			
			// getCommentList
			List<Review_COMM_Dto> commList = commDao.getComments(seq);
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
		// review_delete
		else if ("d".equals(d)) {
			System.out.println("review_delete.jsp");
			seq = Integer.parseInt(req.getParameter("seq"));
			boolean delete = dao.deleteReview(seq);
			jsonData.put("del", delete);
			resp.setContentType("application/json; charset=UTF-8");
			resp.getWriter().print(jsonData);
		}
		// review_write
		else if ("w".equals(d)) {
			System.out.println("review write.jsp");
			seq = Integer.parseInt(req.getParameter("seq"));
			HashMap<String, Reserve_Dto> dto = reserveDao.getReserve(seq);
			req.setAttribute("reserveDto", dto);
			UtilEx.forward("review_write.jsp", req, resp);
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
		HttpSession sessoin = req.getSession();
		Review_Dao dao = Review_Dao.getInstance();
		JSONObject json = new JSONObject();
		
		
		
		if("write".equals(req.getParameter("hidden"))) {
			System.out.println("write post");
			String formData = URLDecoder.decode(req.getParameter("data"));
			String userId = (String)sessoin.getAttribute("sessionID");
			String busiId = formData.split("&")[0].split("=")[1];
			String cate = formData.split("&")[1].split("=")[1];
			String title = formData.split("&")[2].split("=")[1];
			int score = Integer.parseInt(formData.split("&")[3].split("=")[1]);
			String content = formData.split("&")[4].split("=")[1];
			Review_Dto dto = new Review_Dto(busiId, userId, title, content, score, "파일은추후", cate);
			boolean done =dao.addReview(dto);
			json.put("done", done);
			resp.setContentType("application/x-json; charset=UTF-8");
			resp.getWriter().print(json);
		} else {
			int seq = Integer.parseInt(req.getParameter("seq"));
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			boolean isUpdate = dao.updateReview(seq, title, content);
			if (isUpdate) {
				json.put("update", isUpdate);
				resp.setContentType("application/json; charset=UTF-8");
				resp.getWriter().print(json);
			};
		}
	}
}
