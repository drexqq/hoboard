package news;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;
@WebServlet("/news_update.do")
public class news_update_controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String work = req.getParameter("work");
		
		News_Dao ndao = News_Dao.getInstance();
		
		if(work.equals("update")) {				// update view로 이동			
			
			int seq = Integer.parseInt(req.getParameter("nseq"));
			System.out.println("update seq :" + seq);

			News_Dto ndto = ndao.getNewsSeq(seq);
			req.setAttribute("ndto", ndto);
			
			UtilEx.forward("news_update.jsp", req, resp);
			
		} else if(work.equals("updateAf")) { // 수정후 DB에 저장
			System.out.println("arrive");
			
			int seq = Integer.parseInt(req.getParameter("nseq"));  
			String title =req.getParameter("title");
			String content = req.getParameter("content");
			
			System.out.println("updateAf seq : " +seq);
			System.out.println("title : " + title);
			System.out.println("content :" + content);
		  
			boolean isS = ndao.news_update(seq, title, content);
			req.setAttribute("isS", isS);
			UtilEx.forward("news_updateAf.jsp", req, resp);
		  
		  } else if(work.equals("del")) { 
		  System.out.println("work.equals(del)");
		  int seq = Integer.parseInt(req.getParameter("nseq"));
		  
		  ndao.news_delete(seq);
		  resp.sendRedirect("news_list.do?work=move");
		  
		  }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
	
	}
}