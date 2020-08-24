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
import member.BUSI_Member_Dto;
import member.Member_Dao;
import member.Member_Dto;

@WebServlet("/setting")
public class SettingController extends HttpServlet {
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
}
