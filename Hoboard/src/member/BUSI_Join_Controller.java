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
				
		bDto.setId(req.getParameter("id"));
		bDto.setTime(req.getParameter("time"));
		bDto.setHomepage(req.getParameter("homepage"));

		System.out.println(req.getParameter("name"));
		System.out.println(req.getParameter("time"));
		System.out.println(req.getParameter("homepage"));

		bDao.addBUSI_Member(bDto);
		
	}

}
