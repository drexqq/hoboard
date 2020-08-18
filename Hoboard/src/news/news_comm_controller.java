package news;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;

@WebServlet("/comm.do")
public class news_comm_controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		news_comm_dao dao = news_comm_dao.getInstance();
		news_comm_dto dto = new news_comm_dto();
		
		String nc = req.getParameter("nc");
		
		if(nc.equals("co_update")) {
			int c_seq = Integer.parseInt(req.getParameter("c_seq"));
			int b_seq = Integer.parseInt(req.getParameter("b_seq"));
			
			System.out.println("c_seq"+ c_seq);
			System.out.println("b_seq"+ b_seq);
			
			dto = dao.getCseq(c_seq);
		
			News_Dao dao2 = News_Dao.getInstance();
			//News_Dto dto2 = dao2.getNewsSeq(b_seq);
			
			req.setAttribute("dto", dto);
			//req.setAttribute("dto", dto2);
			UtilEx.forward("news_detail.jsp", req, resp);
			
		}
	

	}
}