package admin;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;
import ask.Ask_Dao;
import ask.Ask_Dto;
import member.Member_Dao;
import member.Member_Dto;
import news.News_COMM_Dao;
import news.News_COMM_Dto;
import news.News_Dao;
import news.News_Dto;
import review.Review_Dao;
import review.Review_Dto;


@WebServlet("/admin")
public class AdminController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String adm = req.getParameter("adm");
		
		System.out.println("admin");
		
		News_Dao dao = News_Dao.getInstance();
		Review_Dao dao2 = Review_Dao.getInstance();
		Ask_Dao dao3 = Ask_Dao.getInstance();
		Member_Dao dao4 = Member_Dao.getInstance();
		//News_COMM_Dao dao2 = News_COMM_Dao.getInstance();
		
		//News_Dto dto = new News_Dto();
		//News_COMM_Dto dto2 = new News_COMM_Dto();
		//Ask_Dto dto2 = new Ask_Dto();
		Member_Dto dto = new Member_Dto();
		
		
		List<Member_Dto> hmlist = dao4.getBusiMember_admin();
		List<Member_Dto> pmlist = dao4.getPMember_admin();
		
		
		System.out.println(pmlist.toString());
		
		if(adm == null || "".equals(adm)) {
		
			List<News_Dto> nlist = dao.getNewsList();
			List<Review_Dto > rlist = dao2.getReviewList2();
			List<Ask_Dto> qlist = dao3.getAskList2();
			
			req.setAttribute("nlist", nlist);
			req.setAttribute("rlist", rlist);
			req.setAttribute("qlist", qlist);
			
			req.setAttribute("hmlist", hmlist);
			req.setAttribute("pmlist", pmlist);
			
			UtilEx.forward("admin.jsp", req, resp);
		
		}else if(adm.equals("adminM")) {
			System.out.println("Admin_Mem_Count");
			
			req.setAttribute("hmlist", hmlist);
			req.setAttribute("pmlist", pmlist);
			
			UtilEx.forward("admin_mem.jsp", req, resp);
		
		}else if(adm.equals("adminBD")) {
		
		}else if(adm.equals("adminPD")) {
			
			System.out.println("adminPD");
			
			String id = req.getParameter("id");
			
			System.out.println("adminD id ="+id);
			
			dto = dao4.getUser(id);
		
			req.setAttribute("id", id);
			req.setAttribute("dto", dto);
			
			UtilEx.forward("admin_individ_detail.jsp", req, resp);
			
		}else if(adm.equals("adminIDel")){
			
			System.out.println("adminIDel");
			
			String id = req.getParameter("id");
			
			System.out.println("adminIDel ="+id);
			
			dto = dao4.DelIMember(id);
			
			resp.sendRedirect("admin?adm=adminM");
			
		}else if(adm.equals("adminBDel")){
			
			System.out.println("adminBDel");
			
			String id = req.getParameter("id");
			
			System.out.println("adminBDel ="+id);
			
			dto = dao4.DelBMember(id);
			
			resp.sendRedirect("admin?adm=adminM");
		

		}
	}
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   }

   
   
}