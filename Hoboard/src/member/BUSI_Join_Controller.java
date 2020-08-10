package member;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import Util.UtilEx;

@WebServlet("/BUSI_JOIN")
public class BUSI_Join_Controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member_Dao dao = Member_Dao.getInstance();
		
		String cate_e[] = dao.getBusiCate();
		String cate_k[] = {"내과", "마취통증학과", "산부인과", "소아청소년과", "신경과", "신경외과", "심장내과", "영상의학과", "외과", "응급의학과", "정형외과", "재활의학과", "흉부심장혈관과", "피부비뇨기과", "치과", "안과"};
		LinkedHashMap<String, String> cate = new LinkedHashMap<String, String>();
		for (int i = 0; i < cate_e.length; i++) cate.put(cate_e[i].toLowerCase(), cate_k[i]);
		
		String amenity_e[] = dao.getAmenity();
		String amenity_k[] = {"주차장", "편의점", "ATM,은행", "약국", "대중 교통"};
		LinkedHashMap<String, String> amenity = new LinkedHashMap<String, String>();
		for (int i = 0; i < amenity_e.length; i++) amenity.put(amenity_e[i].toLowerCase(), amenity_k[i]);
		
		String time_e[] = dao.getBusiTime();
		String time_k[] = {"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일", "점심시간", "공휴일", "야간진료", "응급실"};
		LinkedHashMap<String, String> time = new LinkedHashMap<String, String>();
		for (int i = 0; i < time_e.length; i++) time.put(time_e[i].toLowerCase(), time_k[i]);
		
		req.setAttribute("busiTime", time);
		req.setAttribute("busiAmenity", amenity);
		req.setAttribute("busiCate", cate);
		UtilEx.forward("busi_Join.jsp", req, resp);	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
