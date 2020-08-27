package map;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;
import member.Member_Dao;
@WebServlet("/map")
public class MAPController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Member_Dao m_dao = Member_Dao.getInstance();
		
		
		List<String[]> list = m_dao.getBusiMember();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(java.util.Arrays.toString(list.get(i)));
		}
		req.setAttribute("busiMembers", m_dao.getBusiMember());
		UtilEx.forward("map.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
