package review;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/REVIEW")
public class ReviewController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String review = req.getParameter("review");
		if(review.equals("writeView")) {
			resp.sendRedirect("review_write.jsp");
		}
		else if(review.equals("������Ʈ")) {
			
		}
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String review = req.getParameter("review");
		if(review.equals("writedata")) {
			
			String BUSI_id = req.getParameter("BUSI_id");
			String INDVD_id = req.getParameter("INDVD_id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			int score = Integer.parseInt(req.getParameter("score"));
			
			System.out.println("BUSI_id:" + BUSI_id);
			System.out.println("INDVD_id:" + INDVD_id);
			System.out.println("title:" + title);
			System.out.println("content" + content);
			System.out.println("score:" + score);
			
			// DB insert
			Review_Dao dao = Review_Dao.getInstance();
			dao.writeReview(new Review_Dto(BUSI_id, INDVD_id, title, content, score));
			
			resp.sendRedirect("list");
			
			List<Review_Dto> list = dao.getReviewList();
			
			req.setAttribute("reviewlist", list);			
			forward("review.jsp", req, resp);	
			
			
			//resp.sendRedirect("review.jsp");
			
			
		}

	}

	private void forward(String link, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dis = req.getRequestDispatcher(link);
		dis.forward(req, resp);		
		
	}
}
