package admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;
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
		News_COMM_Dao dao2 = News_COMM_Dao.getInstance();
		Review_Dao dao3 = Review_Dao.getInstance();
		
		News_Dto dto = new News_Dto();
		News_COMM_Dto dto2 = new News_COMM_Dto();
		
		List<News_Dto> nlist = dao.getNewsList();
		List<News_COMM_Dto> clist = dao2.getComm(dto2.getB_seq());
		//List<Review_Dto> rlist = dao3.getReviewList();
		System.out.println(clist);
		
		
		req.setAttribute("nlist", nlist);
		req.setAttribute("clist", clist);
		//req.setAttribute("rlist", rlist);
		
		UtilEx.forward("admin.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	
	
}
