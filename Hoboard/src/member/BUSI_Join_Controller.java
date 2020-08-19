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

@WebServlet("/busijoin")
public class BUSI_Join_Controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member_Dao dao = Member_Dao.getInstance();
		
		String cate_e[] = dao.getBusiCateList();
		String cate_k[] = BUSI_Member_Dao.cate;
		LinkedHashMap<String, String> cate = new LinkedHashMap<String, String>();
		for (int i = 0; i < cate_e.length; i++) cate.put(cate_e[i].toLowerCase(), cate_k[i]);

		String time_e[] = dao.getBusiTimeList();
		String time_k[] = BUSI_Member_Dao.time;
		LinkedHashMap<String, String> time = new LinkedHashMap<String, String>();
		for (int i = 0; i < time_e.length; i++) time.put(time_e[i].toLowerCase(), time_k[i]);
		
		String amenity_e[] = dao.getAmenityList();
		String amenity_k[] = BUSI_Member_Dao.amenity;
		LinkedHashMap<String, String> amenity = new LinkedHashMap<String, String>();
		for (int i = 0; i < amenity_e.length; i++) amenity.put(amenity_e[i].toLowerCase(), amenity_k[i]);
		
		
		req.setAttribute("busiTime", time);
		req.setAttribute("busiCate", cate);
		req.setAttribute("busiAmenity", amenity);
		UtilEx.forward("busi_Join.jsp", req, resp);	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
