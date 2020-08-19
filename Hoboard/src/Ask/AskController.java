package Ask;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.UtilEx;
import news.News_Dto;

@WebServlet("/ask")
public class AskController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		Ask_Dao dao = Ask_Dao.getInstance();
		String one = req.getParameter("one");
		HttpSession session = req.getSession();

		if (one == null || "".equals(one)) {
			List<Ask_Dto> list = null;
			
			String sW = req.getParameter("searchWord");
			String c = req.getParameter("choice");
			
			int limit = 10;
			int pageNumber = 0;

			if (req.getParameter("page") == null)
				pageNumber = 0;
			else
				pageNumber = Integer.parseInt((String) req.getParameter("page"));

			int len = dao.getAskCount((String) session.getAttribute("sessionID"), c, sW);
			int page = len / limit; // 예: 12개 -> 2page
			if (len % limit > 0)
				page = page + 1; // -> 2
			// 처음 들어왔을때
			if (sW == null && c == null && pageNumber == 0)
				list = dao.getAskPagingList("", "", limit, pageNumber);
			// 페이지만 바뀔때
			else if (sW == null && c == null && req.getParameter("page") != null)
				list = dao.getAskPagingList("", "", limit, pageNumber);
			// 검색후 페이지 바뀔때
			else {
				if (sW == null)
					sW = "";
				list = dao.getAskPagingList(c, sW, limit, pageNumber);
				req.setAttribute("choice", c);
				req.setAttribute("searchWord", sW);
			}
			req.setAttribute("len", len);
			req.setAttribute("pageNumber", pageNumber); // 현재 페이지 넘버
			req.setAttribute("page", page - 1); // 총 페이지수
			req.setAttribute("askList", list); // 실제 데이터
			// 이동
			UtilEx.forward("my_ask.jsp", req, resp);

		}

		else if (one.equals("detail")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			// 시퀀스를 통해 글 상세 보기 접근
			Ask_Dto dto = dao.getAskSeq(seq);
			req.setAttribute("dto", dto);
			UtilEx.forward("ask_detail.jsp", req, resp);
		}

		else if (one.equals("del")) {

			System.out.println("one.equals(del)");
			int seq = Integer.parseInt(req.getParameter("seq"));

			dao.ask_delete(seq);
			resp.sendRedirect("ask.do?one=move");

		} else if (one.equals("update")) { // updateView

			int seq = Integer.parseInt(req.getParameter("seq"));
			System.out.println("ask update seq :" + seq);

			Ask_Dto dto = dao.getAskSeq(seq);
			req.setAttribute("dto", dto);

			UtilEx.forward("ask_update.jsp", req, resp);

		} else if (one.equals("updateAf")) { // 수정후 DB에 저장
			System.out.println("arrive");

			int seq = Integer.parseInt(req.getParameter("seq"));
			String title = req.getParameter("title");
			String content = req.getParameter("content");

			System.out.println("updateAf seq : " + seq);
			System.out.println("title : " + title);
			System.out.println("content :" + content);

			boolean isS = dao.ask_update(seq, title, content);
			req.setAttribute("isS", isS);
			UtilEx.forward("ask_updateAf.jsp", req, resp);

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("This is post");

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");

		String two = req.getParameter("two");
		// HttpSession session = req.getSession();
		Ask_Dao dao = Ask_Dao.getInstance();
		System.out.println("!!!!!!!!!!!!!!");

		// String id = "";

		if (two.equals("write")) {
			System.out.println(2222);

			String id = req.getParameter("id");
			// String title = URLEncoder.encode(req.getParameter("title"), "utf-8");

			String title = req.getParameter("title");
			String content = req.getParameter("content");

			// String content = URLEncoder.encode(req.getParameter("content"), "utf-8");
			System.out.println("title =" + title + ", content= " + content);

			id = "admin";
			dao = Ask_Dao.getInstance();
			Ask_Dto dto = new Ask_Dto(id, title, content);
			boolean b = dao.ask_write(dto);

			if (b) {
				System.out.println("글쓰기 성공");
				resp.sendRedirect("ask.do?one=move");
			} else {
				System.out.println("실패");
				// resp.sendRedirect("news_detail.do");
			}
		}
	}

}
