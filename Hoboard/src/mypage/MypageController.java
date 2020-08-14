package mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.UtilEx;
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
		int auth = dto.getAuth();
		
		String cate[] = new String[16];
		
		if(auth == 1) {
			System.out.println("개인");
		}
		else if (auth == 2) {
			System.out.println("병원");
			cate = dao.getBusiCate(id);
			System.out.println(java.util.Arrays.toString(cate));
		}

		req.setAttribute("user", dto);
		UtilEx.forward("my_setting.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		Member_Dao dao = Member_Dao.getInstance();
		Member_Dto dto = new Member_Dto(req.getParameter("id"), req.getParameter("pw"), req.getParameter("tel"),
				req.getParameter("post_Num"), req.getParameter("address"), req.getParameter("d_Address"));
		
		boolean done = dao.updateMember(dto);
		if(done) resp.sendRedirect("mypage.jsp");
	}

}
