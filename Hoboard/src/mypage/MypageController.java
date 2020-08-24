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
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// setting
		req.setCharacterEncoding("utf-8");
		Member_Dao dao = Member_Dao.getInstance();
		Member_Dto dto = new Member_Dto(req.getParameter("id"), req.getParameter("pw"), req.getParameter("tel"),
				req.getParameter("post_Num"), req.getParameter("address"), req.getParameter("d_Address"));

		boolean done = dao.updateMember(dto);

		BUSI_Member_Dao b_dao = BUSI_Member_Dao.getInstance();
		BUSI_Member_Dto b_dto = new BUSI_Member_Dto(req.getParameter("id"), req.getParameter("homepage"),
				req.getParameter("logo"));
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
