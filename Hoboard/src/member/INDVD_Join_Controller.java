package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/INDVD_Join.do")
public class INDVD_Join_Controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		reqPro(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		reqPro(req,resp);
	}

	public void reqPro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		Member_Dto mDto = new Member_Dto();
		
		Member_Dao mDao = Member_Dao.getInstance();
				
		mDto.setName(req.getParameter("name"));
		mDto.setId(req.getParameter("id"));
		mDto.setPw(req.getParameter("pw"));
		//String pw_Check = req.getParameter("pw_Check");
		mDto.setTel(req.getParameter("tel"));
		mDto.setEmail(req.getParameter("email"));
		mDto.setPost_Num(req.getParameter("post_Num"));
		mDto.setAddress(req.getParameter("address"));
		mDto.setD_Address(req.getParameter("d_Address"));

		System.out.println(req.getParameter("name"));
		System.out.println(req.getParameter("id"));
		System.out.println(req.getParameter("pw"));
		System.out.println(req.getParameter("tel"));
		System.out.println(req.getParameter("email"));
		System.out.println(req.getParameter("post_Num"));
		System.out.println(req.getParameter("address"));
		System.out.println(req.getParameter("d_Address"));

		mDao.addMember(mDto);
		
	}

}
