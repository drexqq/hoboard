package Reserve;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;
import member.BUSI_Cate_Dto;
import member.BUSI_Member_Dao;
import member.BUSI_Time_Dto;
import member.Member_Dao;
import member.Member_Dto;
import net.sf.json.JSONObject;
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
		
		if(key == null || "".equals(key)) {
			List<Member_Dto> S_list = null;
			
			String loc = req.getParameter("loc");
			String amt = req.getParameter("amt");
			String searchWord =  req.getParameter("searchWord");
			
			int limit = 2;
			int pageNumber = 0;

			if (req.getParameter("page") == null) pageNumber = 0;
			else pageNumber = Integer.parseInt((String) req.getParameter("page"));
			
			int len = dao.getsearch_allcount(loc, amt, searchWord);
			System.out.println(len);
			int page = len / limit; // 예: 12개 -> 2page
			if (len % limit > 0) page = page + 1; // -> 2
			
			// 처음 들어왔을때
			if (loc == null && amt == null && searchWord == null && pageNumber == 0) {
				S_list = dao.getSearch_list("", "", "", limit, pageNumber);
			}
			else {
				S_list = dao.getSearch_list(loc, amt, searchWord, limit, pageNumber);
			}
			req.setAttribute("loc", loc);
			req.setAttribute("amt", amt);
			req.setAttribute("searchWord", searchWord);
			req.setAttribute("len", len); // 총 개수
			req.setAttribute("pageNumber", pageNumber); // 현재 페이지 넘버
			req.setAttribute("page", page - 1); // 총 페이지수
			req.setAttribute("res_search_list", S_list); // 실제 데이터 
			UtilEx.forward("reserve.jsp", req, resp);
		
		}
		else if(key.equals("detail")) {
			String id = req.getParameter("id");
			String time[] = BUSI_Member_Dao.time;
			String cate_k[] = BUSI_Member_Dao.cate;
			String amenity_k[] = BUSI_Member_Dao.amenity;
			Map<String, Object> map = dao.getBusiUserDetail(id);
			
			req.setAttribute("busi_id", id);
			req.setAttribute("time", time);
			req.setAttribute("cate_k", cate_k);
			req.setAttribute("amenity_k", amenity_k);
			req.setAttribute("map", map);
			UtilEx.forward("reserve_detail.jsp", req, resp);
		}
		/*
		 	else if(key.equals("category")) {
			
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getCate_list(cate, seq);
			
			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);
			
		}
		 */
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
