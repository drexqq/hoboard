package Reserve;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;
import member.Member_Dto;
import review.Review_Dao;

@WebServlet("/reserve")
public class ReserveController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		Reserve_Dao dao = Reserve_Dao.getInstance();
		
		String cate = req.getParameter("cate");
		String key = req.getParameter("key");
		
		//key == null || "".equals(key)
		if(key == null || "".equals(key)) {
			
			List<Member_Dto> S_list = null;
			
			String loc = req.getParameter("loc");
			String amt = req.getParameter("amt");
			String searchWord =  req.getParameter("searchWord");
			
			System.out.println(loc);
			System.out.println(amt);
			System.out.println(searchWord);
			
			int limit = 5;
			int pageNumber = 0;

			if (req.getParameter("page") == null)
				pageNumber = 0;
			else
				pageNumber = Integer.parseInt((String) req.getParameter("page"));
			
			int len = dao.getsearch_allcount(loc, amt, searchWord);
			
			int page = len / limit; // 예: 12개 -> 2page
			if (len % limit > 0)
				page = page + 1; // -> 2
			
			System.out.println("총 게시물 숫자 len : " + len);
			System.out.println("페이지 갯수 page : " + page);
			System.out.println("페이지 넘버 pageNum : " + pageNumber);
			
			// 처음 들어왔을때
			if (loc == null && amt == null && searchWord == null && pageNumber == 0) {
				System.out.println("처음");
				S_list = dao.getSearch_list("", "", "", limit, pageNumber);
			// 페이지만 바뀔때
			}else if (loc == null && amt == null && searchWord == null && req.getParameter("page") != null) {
				System.out.println("페이지만");
				S_list = dao.getSearch_list("", "", "", limit, pageNumber);
			// 검색후 페이지 바뀔때
			}else {
				System.out.println("검색");
				S_list = dao.getSearch_list(loc, amt, searchWord, limit, pageNumber);
				req.setAttribute("loc", loc);
				req.setAttribute("amt", amt);
				req.setAttribute("searchWord", searchWord);
			}
			
			
			req.setAttribute("len", len); // 총 개수
			req.setAttribute("pageNumber", pageNumber); // 현재 페이지 넘버
			req.setAttribute("page", page - 1); // 총 페이지수
			req.setAttribute("res_search_list", S_list); // 실제 데이터 
			UtilEx.forward("reserve.jsp", req, resp);
			
		
		
		}else if(key.equals("category")) {
			
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getCate_list(cate, seq);
			
			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);
			
		}else if(key.equals("detail")) {
			
			String id = req.getParameter("id");
			
			
			
			
			
			
			req.setAttribute(name, o);
			UtilEx.forward("reserve_detail", req, resp);
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
