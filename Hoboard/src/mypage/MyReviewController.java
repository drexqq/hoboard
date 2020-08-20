package mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.UtilEx;
import review.Review_Dao;
import review.Review_Dto;

@WebServlet("/myreview")
public class MyReviewController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Review_Dao dao = Review_Dao.getInstance();
		List<Review_Dto> list = null;
		String id = (String)session.getAttribute("sessionID");
		String sW = (String) req.getParameter("searchWord");
		String c = (String) req.getParameter("choice");
		
		int limit = 5;
		int pageNumber = 0;

		if (req.getParameter("page") == null)
			pageNumber = 0;
		else
			pageNumber = Integer.parseInt((String) req.getParameter("page"));
		
		int len = UtilEx.getAllCountTable("review", c, sW);
//		int len = dao.getsearch(c, sW);
		int page = len / limit; // 예: 12개 -> 2page
		if (len % limit > 0)
			page = page + 1; // -> 2
		// 처음 들어왔을때
		if (sW == null && c == null && pageNumber == 0)
			list = dao.getMyPageReviewPagingList(id, "", "", limit, pageNumber);
		// 페이지만 바뀔때
		else if (sW == null && c == null && req.getParameter("page") != null)
			list = dao.getMyPageReviewPagingList(id, "", "", limit, pageNumber);
		// 검색후 페이지 바뀔때
		else {
			list = dao.getMyPageReviewPagingList(id, c, sW, limit, pageNumber);
			req.setAttribute("choice", c);
			req.setAttribute("searchWord", sW);
		}
		req.setAttribute("len", len);
		req.setAttribute("pageNumber", pageNumber); // 현재 페이지 넘버
		req.setAttribute("page", page - 1); // 총 페이지수
		req.setAttribute("list", list); // 실제 데이터
		UtilEx.forward("my_review.jsp", req, resp);
	}
}
