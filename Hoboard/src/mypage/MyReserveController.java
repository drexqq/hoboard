package mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Reserve.Reserve_Dao;
import Util.UtilEx;
import review.Review_Dao;

@WebServlet("/myreserve")
public class MyReserveController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get get get");
		HttpSession session = req.getSession();
		Reserve_Dao dao = Reserve_Dao.getInstance();
		// 로그인한 사용자
		int auth = (int) session.getAttribute("auth");
		String id = (String) session.getAttribute("sessionID");
		int count = dao.getUserReserveCount("", "", id, auth);
		System.out.println(count);
		req.setAttribute("count", count);
		UtilEx.forward("my_reserve.jsp", req, resp);
	}
}
