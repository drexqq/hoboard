package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;

@WebServlet("/find")
public class MemberFindController {

protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	
	String idpw = req.getParameter("idpw");
	Member_Dao dao = Member_Dao.getInstance();
	PrintWriter out = resp.getWriter();
	
	Member_Dto dto = new Member_Dto();
	
	
	if(idpw.equals("findID")) {
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		req.setAttribute("name", name);
		req.setAttribute("email", email);
		
		System.out.println(name+email);

		UtilEx.forward("searchIDPWAf.jsp", req, resp);
		
	} else if(idpw.contentEquals("findPW")) {
		
		String id =req.getParameter("id");
		String email = req.getParameter("email");
		
		System.out.println("id = " +id);
		System.out.println("email = " +email);
		
		String pwdSearch = dao.PWfind(id, email);
		
		String pw = pwdSearch;
		

		if(pw != null){
			
			System.out.println("찾은 비밀번호:"+pw);
			req.setAttribute("pwd", pw);
			
			UtilEx.forward("searchIDPWAf.jsp", req, resp);
			
			}else{	
			System.out.println("id없음");
			out.println("<script>alert('존재하지 않는 아이디 입니다'); location.href='Yhidpwd.jsp';</script>");
			}	
			out.flush();
		
	}
		
	}
}
