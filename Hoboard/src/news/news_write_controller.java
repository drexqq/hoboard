package news;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;

@WebServlet("/news_write.do")
public class news_write_controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		//String work = req.getParameter("work");
		
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		//System.out.println("title ="+title+", content= "+content);
		
		id="admin";
		News_Dao dao = News_Dao.getInstance();
		News_Dto dto = new News_Dto(id,title,content);
		boolean b = dao.news_write(dto);
		
		if(b){ 
			System.out.println("글쓰기 성공");
			resp.sendRedirect("news_list.do?work=move");
		} else {
			System.out.println("실패");
			//resp.sendRedirect("news_detail.do");
			
			
		}
	}
}