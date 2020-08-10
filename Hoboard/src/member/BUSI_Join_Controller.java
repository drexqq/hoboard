package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BUSI_Join.do")
public class BUSI_Join_Controller extends HttpServlet {

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
		
		BUSI_Member_Dto bDto = new BUSI_Member_Dto();
		
		BUSI_Member_Dao bDao = BUSI_Member_Dao.getInstance();
				
		bDto.setName(req.getParameter("name"));
		bDto.setId(req.getParameter("id"));
		bDto.setPw(req.getParameter("pw"));
		//String pw_Check = req.getParameter("pw_Check");
		bDto.setTel(req.getParameter("tel"));
		bDto.setEmail(req.getParameter("email"));
		bDto.setPost_Num(req.getParameter("post_Num"));
		bDto.setAddress(req.getParameter("address"));
		bDto.setD_Address(req.getParameter("d_Address"));
		bDto.setTime(req.getParameter("time"));
		bDto.setHomepage(req.getParameter("homepage"));

		
		bDto.setName(req.getParameter("name"));
		bDto.setId(req.getParameter("id"));
		bDto.setPw(req.getParameter("pw"));
		//String pw_Check = req.getParameter("pw_Check");
		bDto.setTel(req.getParameter("tel"));
		bDto.setEmail(req.getParameter("email"));
		bDto.setPost_Num(req.getParameter("post_Num"));
		bDto.setAddress(req.getParameter("address"));
		bDto.setD_Address(req.getParameter("d_Address"));

		System.out.println(req.getParameter("name"));
		System.out.println(req.getParameter("id"));
		System.out.println(req.getParameter("pw"));
		System.out.println(req.getParameter("tel"));
		System.out.println(req.getParameter("email"));
		System.out.println(req.getParameter("post_Num"));
		System.out.println(req.getParameter("address"));
		System.out.println(req.getParameter("d_Address"));
		
		System.out.println(req.getParameter("time"));
		System.out.println(req.getParameter("homepage"));

		bDao.addBUSI_Member(bDto);
		
	}

}
