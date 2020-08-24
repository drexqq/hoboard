package Reserve;

import java.io.IOException;
import java.util.ArrayList;
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
import review.Review_Dao;

@WebServlet("/reserve")
public class ReserveController extends HttpServlet {

	
	public static ArrayList<String> Arraylist = new ArrayList<String>();
	
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
			int score = dao.getScore_avg(id);
			String homepage = dao.getHomepage(id);
			
			
			List<Member_Dto> m_list = dao.getMember_list(id);
			List<BUSI_Time_Dto> t_list = dao.getTime_list(id);
			Map<String, Integer> busi_cate = dao.getCate_list(id);
			Map<String, Integer> busi_amenity = dao.getAmetiny_list(id);
			
			req.setAttribute("busi_id", id);
			req.setAttribute("score",score);
			req.setAttribute("homepage",homepage);
			req.setAttribute("memberlist", m_list);
			req.setAttribute("timelist", t_list);
			req.setAttribute("catelist", busi_cate);
			req.setAttribute("amenitylist", busi_amenity);
			UtilEx.forward("reserve_detail.jsp", req, resp);
			
			
		} else if(key.equals("select")) {
			
			String date = req.getParameter("date");
			String id = req.getParameter("id");
			
			int idx = date.indexOf("(");
			String date2 = date.substring(idx+1);
			String date3 = date2.substring(0,1);
			
			switch (date3) {
			case "월":
				date3 = "MON";
				break;
			case "화":
				date3 = "TUE";
				break;
			case "수":
				date3 = "WED";
				break;
			case "목":
				date3 = "THU";
				break;
			case "금":
				date3 = "FRI";
				break;
			case "토":
				date3 = "SAT";
				break;
			case "일":
				date3 = "SUN";
				break;
			}
			System.out.println(date);	
			System.out.println(id);
			System.out.println(date3);
			
			String lunch = dao.getLunch_Time(id, date3);
			
			if(lunch.equals("휴무")) {
				
				String breaktime = "휴무일입니다.";
				
				req.setAttribute("lunch", breaktime);
			
			} else {
				
				
				
				
			}
			
			
			
			//String lunch = dao.getLunch_Time();
			
			
			Arraylist.add("09:00~10:00");
			Arraylist.add("10:00~11:00");
			Arraylist.add("11:00~12:00");
			Arraylist.add("12:00~13:00");
			Arraylist.add("13:00~14:00");
			Arraylist.add("14:00~15:00");
			Arraylist.add("16:00~17:00");
			Arraylist.add("17:00~18:00");

			
			
			
			
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
