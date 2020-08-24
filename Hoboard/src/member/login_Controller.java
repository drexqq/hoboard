package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.UtilEx;

@WebServlet("/login")
public class login_Controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String context = req.getContextPath();
		resp.setContentType("text/html; charset=UTF-8");

		Member_Dao mdao = new Member_Dao();
		Member_Dto mdto = new Member_Dto();

		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = null;
		mdto = mdao.getUser(id);
		name = mdao.login(id, pw);

		if (name != null) {
			session.setAttribute("login", 1);
			session.setAttribute("sessionID", mdto.getId());
			session.setAttribute("name", mdto.getName());
			session.setAttribute("auth", mdto.getAuth());
			resp.sendRedirect(context + "/");

		} else {
			session.setAttribute("login", 0);
			resp.sendRedirect(context + "/login.jsp");
		}
	}
}
