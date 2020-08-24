package news;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;
import net.sf.json.JSONObject;
import review.Review_COMM_Dto;

@WebServlet("/news")
public class NewsController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String work = req.getParameter("work");
		News_Dao dao = News_Dao.getInstance();
		JSONObject obj = new JSONObject();

		if (work == null || "".equals(work)) {
			List<News_Dto> list = null;
			// 검색
			String sW = req.getParameter("searchWord");
			String c = req.getParameter("choice");

			int limit = 5;
			int pageNumber = 0;

			if (req.getParameter("page") == null)
				pageNumber = 0;
			else
				pageNumber = Integer.parseInt((String) req.getParameter("page"));

			int len = dao.getAllNews(c, sW);
			int page = len / limit;
			if (len % limit > 0)
				page = page + 1;
			// 처음 들어왔을때
			if (sW == null && c == null && pageNumber == 0)
				list = dao.getNewsPagingList("", "", limit, pageNumber);
			// 페이지만 바뀔때
			else if (sW == null && c == null && req.getParameter("page") != null)
				list = dao.getNewsPagingList("", "", limit, pageNumber);
			// 검색후 페이지 바뀔때
			else {
				list = dao.getNewsPagingList(c, sW, limit, pageNumber);
				;
				req.setAttribute("choice", c);
				req.setAttribute("searchWord", sW);
			}
			// 보내줄 데이터
			req.setAttribute("len", len);
			req.setAttribute("pageNumber", pageNumber);
			req.setAttribute("page", page - 1);
			req.setAttribute("list", list);
			// 이동

			UtilEx.forward("news.jsp", req, resp);

			// 디테일
		} else if (work.equals("detail")) { // update view로 이동
			System.out.println("detail get");

			int seq = Integer.parseInt(req.getParameter("seq"));
			System.out.println("news_detail.seq =" + seq);

			News_Dto dto = dao.getNewsSeq(seq);
			News_COMM_Dao commDao = News_COMM_Dao.getInstance();

			System.out.println(dto.toString());
			System.out.println(commDao.getComm(seq).toString());
			boolean vc = dao.viewcount(seq);

			req.setAttribute("comm", commDao.getComm(seq));
			req.setAttribute("dto", dto);
			UtilEx.forward("news_detail.jsp", req, resp);

		} else if (work.equals("update")) { // update view로 이동

			int seq = Integer.parseInt(req.getParameter("seq"));
			System.out.println("update seq :" + seq);

			News_Dto dto = dao.getNewsSeq(seq);
			req.setAttribute("dto", dto);

			UtilEx.forward("news_update.jsp", req, resp);

			// 글 수정 후
		} else if (work.equals("updateAf")) { // 수정후 DB에 저장
			System.out.println("arrive");

			int seq = Integer.parseInt(req.getParameter("seq"));
			String title = req.getParameter("title");
			String content = req.getParameter("content");

			System.out.println("updateAf seq : " + seq);
			System.out.println("title : " + title);
			System.out.println("content :" + content);

			boolean isS = dao.news_update(seq, title, content);
			req.setAttribute("isS", isS);
			UtilEx.forward("news_updateAf.jsp", req, resp);

		} else if (work.equals("del")) {
			System.out.println("work.equals(del)");
			int seq = Integer.parseInt(req.getParameter("seq"));

			dao.news_delete(seq);
			resp.sendRedirect("news?work=move");

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("doPost");

		req.setCharacterEncoding("UTF-8");

		String work2 = req.getParameter("work2");
		// HttpSession session = req.getSession();
		News_Dao dao = News_Dao.getInstance();
		News_COMM_Dao dao2 = News_COMM_Dao.getInstance(); // 뉴스 댓글 관련 dao

		JSONObject obj = new JSONObject();
		// String id = "";

		if (work2.equals("write")) {

			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			// System.out.println("title ="+title+", content= "+content);

			id = "admin";
			dao = News_Dao.getInstance();
			News_Dto dto = new News_Dto(id, title, content);
			boolean b = dao.news_write(dto);

			if (b) {
				System.out.println("글쓰기 성공");
				resp.sendRedirect("news?work=move");
			} else {
				System.out.println("실패");
				// resp.sendRedirect("news_detail.do");
			}

			// 댓글 수정
		} else if (work2.equals("co_update")) {

			int c_seq = Integer.parseInt(req.getParameter("c_seq"));
			int b_seq = Integer.parseInt(req.getParameter("b_seq"));

			System.out.println("c_seq" + c_seq);
			System.out.println("b_seq" + b_seq);

			News_COMM_Dto dto2 = new News_COMM_Dto();
			dao2 = News_COMM_Dao.getInstance();

			dto2 = dao2.getCseq(c_seq);

			// News_Dto dto2 = dao2.getNewsSeq(b_seq);

			req.setAttribute("dto2", dto2);

			UtilEx.forward("news_detail.jsp", req, resp);

		} // 댓글 작성

		else if (work2.equals("c_write")) {

			dao2 = News_COMM_Dao.getInstance();

			int b_seq = Integer.parseInt(req.getParameter("b_seq"));
			List<News_COMM_Dto> clist = dao2.getComm(b_seq);

			System.out.println("clist=" + clist);

			News_COMM_Dto dto2 = new News_COMM_Dto();

			String id = req.getParameter("id");
			// int b_seq = Integer.parseInt(req.getParameter("b_seq"));
			String c_content = req.getParameter("c_content");
			// reply_content = new String(reply_content.getBytes("8859_1"),"utf-8");
			// System.out.println(req.getParameter("id"));
			System.out.println("b_seq!!!" + b_seq);
			System.out.println(req.getParameter("c_content"));

			System.out.println("dto2zzzzzz" + dto2);

			boolean isS = dao2.comm_write(new News_COMM_Dto(id, c_content, b_seq));

			String seq = req.getParameter("b_seq");

			if (isS) {
				System.out.println("덧글 작성 성공");
				resp.sendRedirect("news?work=detail&seq=" + b_seq);
			} else {
				System.out.println("덧글 작성 실패");
				resp.sendRedirect("news?work=detail&seq=" + b_seq);
			}

			resp.sendRedirect("news?work=move&seq=" + b_seq);
		}
	}

}
