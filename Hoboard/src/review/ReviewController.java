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

	Review_Dao dao = Review_Dao.getInstance();
	Reserve_Dao reserveDao = Reserve_Dao.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
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
			System.out.println(len);
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
			dao.updateViewCount(seq);
			Review_COMM_Dao commDao = Review_COMM_Dao.getInstance();
			
			// getCommentList
			List<Review_COMM_Dto> commList = commDao.getComments(seq);
			System.out.println(commList.toString());
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
			System.out.println(formData);
			int seq = Integer.parseInt(formData.split("&")[0].split("=")[1]);
			String userId = (String)sessoin.getAttribute("sessionID");
			String busiId = formData.split("&")[1].split("=")[1];
			String cate = formData.split("&")[2].split("=")[1];
			String title = formData.split("&")[3].split("=")[1];
			int score = Integer.parseInt(formData.split("&")[4].split("=")[1]);
			String content = formData.split("&")[5].split("=")[1];
			
			Review_Dto dto = new Review_Dto(busiId, userId, title, content, score, "파일은추후", cate);
			boolean done =dao.addReview(dto);
			
			if(done) reserveDao.doneReserve(seq);
			
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
