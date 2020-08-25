package admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;
import ask.Ask_Dao;
import ask.Ask_Dto;
import news.News_COMM_Dao;
import news.News_COMM_Dto;
import news.News_Dao;
import news.News_Dto;
import review.Review_Dao;
import review.Review_Dto;


@WebServlet("/admin")
public class admincontroller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//String adm = req.getParameter("adm");
		
		System.out.println("admin");
		
		News_Dao dao = News_Dao.getInstance();
		//News_COMM_Dao dao2 = News_COMM_Dao.getInstance();
		Review_Dao dao2 = Review_Dao.getInstance();
		Ask_Dao dao3 = Ask_Dao.getInstance();
		
		//News_Dto dto = new News_Dto();
		//News_COMM_Dto dto2 = new News_COMM_Dto();
		//Ask_Dto dto2 = new Ask_Dto();
		
		
		List<News_Dto> nlist = dao.getNewsList();
		List<Review_Dto> rlist = dao2.getReviewList();
		List<Ask_Dto> qlist = dao3.getAskList2();
		
		req.setAttribute("nlist", nlist);
		req.setAttribute("rlist", rlist);
		req.setAttribute("qlist", qlist);
		
		UtilEx.forward("admin.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	
	
}
