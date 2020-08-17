package review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;

@WebServlet("/COMM")
public class Review_COMM_Controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		
		Review_COMM_Dao dao = Review_COMM_Dao.getInstance();
		Review_COMM_Dto comment = new Review_COMM_Dto();
		
		PrintWriter out = resp.getWriter();
		
		String key = req.getParameter("key");
		
		if (key.equals("/write")) {
			
			Review_COMM_Dto dto = new Review_COMM_Dto();
			dto.setBoard_no(Integer.parseInt(req.getParameter("no")));
			dto.setId(req.getParameter("id"));
			dto.setContent(req.getParameter("reply_content"));
			
			// db 저장을 위한 dao에 데이터 전달
			boolean com = dao.insertComment(dto);

			if(com) {
				System.out.println("덧글 작성 성공");
			} else {
				System.out.println("덧글 작성 실패");
			}
		}
	}
}
