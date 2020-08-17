package mypage;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.UtilEx;
import member.BUSI_Member_Dao;
import member.INDVD_Member_Dao;
import member.Member_Dao;
import member.Member_Dto;

@WebServlet("/mypage")
public class MypageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String id = (String) session.getAttribute("sessionID");
		
		Member_Dao dao = Member_Dao.getInstance();
		Member_Dto dto = dao.getUser(id);
		int auth = dto.getAuth();
		
		LinkedHashMap<String, String> cate = null;
		LinkedHashMap<String, String> time = null;
		LinkedHashMap<String, String> amenity = null;
		
		
		if(auth == 1) {
			System.out.println("개인");
		}
		else if (auth == 2) {
			System.out.println("병원");
			String cate_e[] = dao.getBusiCateList();
			String cate_k[] = BUSI_Member_Dao.cate;
			cate = new LinkedHashMap<String, String>();
			for (int i = 0; i < cate_e.length; i++) cate.put(cate_e[i].toLowerCase(), cate_k[i]);
			System.out.println(java.util.Arrays.toString(cate_e));
			System.out.println(java.util.Arrays.toString(cate_k));
			System.out.println(cate.toString());
			
			String time_e[] = dao.getBusiTimeList();
			String time_k[] = BUSI_Member_Dao.time;
			time = new LinkedHashMap<String, String>();
			for (int i = 0; i < time_e.length; i++) time.put(time_e[i].toLowerCase(), time_k[i]);
			
			String amenity_e[] = dao.getAmenityList();
			String amenity_k[] = BUSI_Member_Dao.amenity;
			amenity = new LinkedHashMap<String, String>();
			for (int i = 0; i < amenity_e.length; i++) amenity.put(amenity_e[i].toLowerCase(), amenity_k[i]);
		}
		req.setAttribute("busiCate", cate);
		req.setAttribute("busiTime", time);
		req.setAttribute("busiAmenity", amenity);
		req.setAttribute("user", dto);
		UtilEx.forward("my_setting.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		Member_Dao dao = Member_Dao.getInstance();
		Member_Dto dto = new Member_Dto(req.getParameter("id"), req.getParameter("pw"), req.getParameter("tel"),
				req.getParameter("post_Num"), req.getParameter("address"), req.getParameter("d_Address"));
		
		boolean done = dao.updateMember(dto);
		if(done) resp.sendRedirect("mypage.jsp");
	}

}
