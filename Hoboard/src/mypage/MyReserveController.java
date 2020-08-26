package mypage;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Reserve.Reserve_Dao;
import Reserve.Reserve_Dto;
import Util.UtilEx;

@WebServlet("/myreserve")
public class MyReserveController extends HttpServlet implements Serializable {
	Reserve_Dao dao = Reserve_Dao.getInstance();
	List<LinkedHashMap<Reserve_Dto, String>> list = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		// 로그인한 사용자
		int auth = (int) session.getAttribute("auth");
		String id = (String) session.getAttribute("sessionID");
		// parameters
		String c = (String) req.getParameter("choice");
		String sW = (String) req.getParameter("searchWord");
		int count = dao.getUserReserveCount(c, sW, id, auth);
		if (sW == null && c == null) 
			list = dao.getUserReserveList(c, sW, id, auth);
		else {
			list = dao.getUserReserveList(c, sW, id, auth);
			req.setAttribute("choice", c);
			req.setAttribute("searchWord", sW);
		}
		req.setAttribute("reserveList", list);
		req.setAttribute("count", count);
		UtilEx.forward("my_reserve.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		System.out.println("ajax in");
		
		HttpSession session = req.getSession();
		
		// 로그인한 사용자
		int auth = (int) session.getAttribute("auth");
		String id = (String) session.getAttribute("sessionID");
		// parameters
		String c = (String) req.getParameter("choice");
		String sW = (String) req.getParameter("searchWord");
		System.out.println(c);
		System.out.println(sW);
		int count = dao.getUserReserveCount(c, sW, id, auth);
		
		if (sW == null && c == null) 
			list = dao.getUserReserveList(c, sW, id, auth);
		else {
			list = dao.getUserReserveList(c, sW, id, auth);
			req.setAttribute("choice", c);
			req.setAttribute("searchWord", sW);
		}
		resp.setContentType("application/x-json; charset=UTF-8");
		resp.getWriter().print(UtilEx.mapToJson(list.get(1)));
	}
	
	
}
