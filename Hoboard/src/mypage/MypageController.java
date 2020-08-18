package mypage;

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
import javax.servlet.http.HttpSession;

import Util.UtilEx;
import member.BUSI_Member_Dao;
import member.BUSI_Member_Dto;
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
		
		BUSI_Member_Dao b_dao = BUSI_Member_Dao.getInstance();
		BUSI_Member_Dto b_dto = b_dao.getUser(id);
		int auth = dto.getAuth();

		LinkedHashMap<String[], String> cate = new LinkedHashMap<String[], String>();
		LinkedHashMap<String[], String> time = new LinkedHashMap<String[], String>();
		LinkedHashMap<String[], String> amenity = new LinkedHashMap<String[], String>();

		if (auth == 1) {
			System.out.println("개인");
		} else if (auth == 2) {
			System.out.println("병원");

			String cate_k[] = BUSI_Member_Dao.cate;
			String cate_e[] = dao.getBusiCateList();
			String cateValue[] = dao.getBusiCate(id);
			for (int i = 0; i < cate_e.length; i++) {
				String[] cateName = { cate_e[i], cate_k[i] };
				cate.put(cateName, cateValue[i]);
			}

			String time_k[] = BUSI_Member_Dao.time;
			String time_e[] = dao.getBusiTimeList();
			String timeValue[] = dao.getBusiTime(id);
			for (int i = 0; i < timeValue.length; i++) {
				String[] timeName = { time_e[i], time_k[i] };
				time.put(timeName, timeValue[i]);
			}

			String amenity_k[] = BUSI_Member_Dao.amenity;
			String amenity_e[] = dao.getAmenityList();
			String amenityValue[] = dao.getBusiAmenity(id);
			for (int i = 0; i < amenityValue.length; i++) {
				String[] amenityName = { amenity_e[i], amenity_k[i] };
				amenity.put(amenityName, amenityValue[i]);
			}
		}

		req.setAttribute("busiCate", cate);
		req.setAttribute("busiTime", time);
		req.setAttribute("busiAmenity", amenity);
		req.setAttribute("user", dto);
		req.setAttribute("b_user", b_dto);
		UtilEx.forward("my_setting.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		Member_Dao dao = Member_Dao.getInstance();
		Member_Dto dto = new Member_Dto(req.getParameter("id"), req.getParameter("pw"), req.getParameter("tel"),
				req.getParameter("post_Num"), req.getParameter("address"), req.getParameter("d_Address"));

		boolean done = dao.updateMember(dto);

		BUSI_Member_Dao b_dao = BUSI_Member_Dao.getInstance();
		BUSI_Member_Dto b_dto = new BUSI_Member_Dto(req.getParameter("id"), req.getParameter("homepage"),
				req.getParameter("logo"));
		System.out.println("homepage = " + req.getParameter("id"));
		System.out.println("homepage = " + req.getParameter("homepage"));
		System.out.println("homepage = " + req.getParameter("logo"));
		// BUSI_TIME TABLE
		// 월~금, 점심
		String time[] = new String[8];
		// 공휴일, 야간, 응급실
		int extra[] = new int[3];
		// BUSI_CATE TABLE
		// 과목
		int cate[] = new int[16];
		// BUSI_AMENITY TABLE
		// 편의
		int amenity[] = new int[5];

		for (int i = 0; i < time.length; i++) {
			if (req.getParameter("time" + i + "") == null || req.getParameter("time" + i + "").equals("")
					|| req.getParameter("time" + i + "").equals("null"))
				time[i] = "휴무";
			else
				time[i] = req.getParameter("time" + i);
		}
		for (int i = 0; i < extra.length; i++) {
			extra[i] = 0;
			if (req.getParameter("time" + (i + 8)) != null)
				extra[i] = 1;
		}
		for (int i = 0; i < amenity.length; i++) {
			amenity[i] = 0;
			if (req.getParameter("amenity" + i) != null)
				amenity[i] = 1;
		}
		for (int i = 0; i < cate.length; i++) {
			cate[i] = 0;
			if (req.getParameter("cate" + i) != null)
				cate[i] = 1;
		}

		done = b_dao.updateBusi_Member(b_dto, b_dto.getId());
		if (done)
			done = b_dao.updateBusi_Extra(b_dto.getId(), time, extra, cate, amenity);

		if (done)
			resp.sendRedirect("mypage.jsp");
	}

}
