package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.UtilEx;

@WebServlet("/login.do")
public class login_Controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = null;

		HttpSession session = req.getSession();
		String context = req.getContextPath();
		resp.setContentType("text/html; charset=UTF-8");

		Member_Dao mdao = new Member_Dao();
		Member_Dto mdto = new Member_Dto();

		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		System.out.println(id + "," + pw);

		name = mdao.login(id, pw);

		System.out.println(name);

		if (name != null) {
			System.out.println(name + "님 환영");
			
			session.setAttribute("login", 1);
			session.setAttribute("sessionID", id);
			session.setAttribute("name", name);
			
			resp.sendRedirect(context + "/");
		} else {
			System.out.println("아이디 혹은 비밀번호를 다시 확인해주세요.");
			session.setAttribute("login", 0);
			resp.sendRedirect(context + "/login.jsp");
		}

	}

}
