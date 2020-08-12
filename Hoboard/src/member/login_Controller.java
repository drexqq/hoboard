package member;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
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
		/*
		 * String work = req.getParameter("work"); if (work.equals("move")) {
		 * 
		 * }
		 */
		
		String url = req.getRequestURL().toString();
		
		String name = null;
		
		HttpSession session = req.getSession();
		String context= req.getContextPath();
		System.out.println(context);
		req.setCharacterEncoding("UTF-8");
		
		Member_Dao mdao = new Member_Dao();
		Member_Dto mdto = new Member_Dto();
		
		String id = req.getParameter("id");
	    String pw = req.getParameter("pw");
	    System.out.println(id + "," + pw);
	    
	    name = mdao.login(id,pw);
	    
	    String message = new String();
	    
	    if(name != null) {
	    	System.out.println(name + "님 환영");
	 
	           session.setAttribute("login",1);
	           session.setAttribute("sessionID", id);
	           session.setAttribute("name", name);
	           req.setAttribute("name", name);
//	           UtilEx.forward("login.jsp", req, resp);
	           resp.sendRedirect(context+"/index.jsp");
	    } else {
	    	System.out.println("아이디 혹은 비밀번호를 다시 확인해주세요.");
	    	session.setAttribute("login",0);
	    	session.setAttribute("name", name);
	    	req.setAttribute("name", name);
	    	resp.sendRedirect(context+"/login_fail.jsp");
	    }
	    
	    //message = "로그아웃 됏습니다.";
	   // resp.sendRedirect(req.getContextPath()+"login.jsp?message="+URLEncoder.encode(message, "utf-8"));
	  
	}
	
	
}
