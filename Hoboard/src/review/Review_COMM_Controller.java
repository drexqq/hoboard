package review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;

@WebServlet("/reviewComment")
public class Review_COMM_Controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Review_COMM_Dao dao = Review_COMM_Dao.getInstance();
		Review_COMM_Dto dto = new Review_COMM_Dto();
		String key = req.getParameter("key");
		
		if(key.equals("updatecomment")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			int boardnum = Integer.parseInt(req.getParameter("boardnum"));
			
			System.out.println("seq:" + seq);
			
			dto = dao.getOne_Comment(seq);
			
			Review_Dao dao2 = Review_Dao.getInstance();
//			Review_Dto dto2 = dao2.getReviewDetail(boardnum).value();
			
			
			req.setAttribute("commentlist", dto);
//			req.setAttribute("detaillist", dto2);
			UtilEx.forward("review_comment_update.jsp", req, resp);
			
		} else if(key.equals("updatecomplete")) {
			
			int seq = Integer.parseInt(req.getParameter("seq"));
			int boardnum = Integer.parseInt(req.getParameter("boardnum"));
			String content = req.getParameter("content");
			
			boolean update = dao.Comment_update(seq, content);
			
			if (update) {
				System.out.println("덧글 수정 성공");
				resp.sendRedirect("review?key=detail&seq=" + boardnum);

			} else {
				System.out.println("덧글 수정 실패");
				resp.sendRedirect("review?key=detail&seq=" + boardnum);
			}
			
		} else if(key.equals("deletecomment")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			int boardnum = Integer.parseInt(req.getParameter("boardnum"));
			System.out.println("seq:" + seq);
			
			boolean delete = dao.Comment_delete(seq);

			if (delete) {
				System.out.println("덧글 삭제 성공");
				resp.sendRedirect("review?key=detail&seq=" + boardnum);

			} else {
				System.out.println("덧글 삭제 실패");
				resp.sendRedirect("review?key=detail&seq=" + boardnum);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		Review_COMM_Dao dao = Review_COMM_Dao.getInstance();
		Review_COMM_Dto dto = new Review_COMM_Dto();
		
		String id = req.getParameter("id");
		String seq = req.getParameter("no");
		int no = Integer.parseInt(seq);
		String reply_content = req.getParameter("reply_content");
		
		boolean com = dao.insertReviewComment(new Review_COMM_Dto(no, id, reply_content));
		if(com) resp.sendRedirect("review?d=" + seq );
	}
}
