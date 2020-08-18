package news;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.UtilEx;
import net.sf.json.JSONObject;

@WebServlet("/news_list.do")
public class news_list_controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String work = req.getParameter("work");
		//HttpSession session = req.getSession();
		News_Dao dao = News_Dao.getInstance();
		JSONObject obj = new JSONObject();
		//String id = "";

		if (work.equals("move")) {
			/*
			 * String id = req.getParameter("id"); System.out.println("id:" + id);
			 */

			// 검색
			String searchWord = req.getParameter("searchWord");
			String choice = req.getParameter("choice");

			if (choice == null || choice.equals("")) {
				System.out.println("들림??");
				choice = "sel";
			}
			// 검색어를 지정하지 않고 choice가 넘어왔을 때
			if (choice.equals("sel")) {
				searchWord = "";// null->""로 변경해줌(=new String())
			}
			if (searchWord == null) {
				searchWord = "";
				choice = "sel";// select에서 "------선택"으로 돌아가기
			}

			// 페이지 생성
			String spageNumber = req.getParameter("pageNumber");
			int pageNumber = 0;// 현재페이지
			if (spageNumber != null && !spageNumber.equals("")) {// 하라미터가 넘어왔을때
				pageNumber = Integer.parseInt(spageNumber);
			}
			System.out.println("pageNumber:" + pageNumber);

			List<News_Dto> list = dao.getNewsPagingList(choice, searchWord, pageNumber);

			// 목록 리스트를 검색한것만 가져옴
			int len = dao.getAllNews(choice, searchWord);
			System.out.println("len:" + len);

			// 보내줄 데이터
			req.setAttribute("list", list);
			req.setAttribute("len", len);
			req.setAttribute("choice", choice);
			req.setAttribute("searchWord", searchWord);
			req.setAttribute("pageNumber", pageNumber);
			// 이동
			UtilEx.forward("news_list.jsp", req, resp);
		
		}  else if (work.equals("search")) {
			String choice = req.getParameter("choice");
			String searchWord = req.getParameter("searchWord");
			System.out.println("search choice=" + choice);
			System.out.println("search searchWord=" + searchWord);

			int len = dao.getAllNews(choice, searchWord);
			System.out.println("len:" + len);

			String spageNumber = req.getParameter("pageNumber");
			int pageNumber = 0;// 현재페이지
			if (spageNumber != null && !spageNumber.equals("")) {// 하라미터가 넘어왔을때
				pageNumber = Integer.parseInt(spageNumber);
			}
			System.out.println("search pageNumber:" + pageNumber);

			List<News_Dto> list = dao.getNewsPagingList(choice, searchWord, pageNumber);

			req.setAttribute("list", list);
			req.setAttribute("len", len);
			req.setAttribute("choice", choice);
			req.setAttribute("searchWord", searchWord);
			req.setAttribute("pageNumber", pageNumber);

			UtilEx.forward("news_list.jsp", req, resp);

			// 페이징
		} else if (work.equals("pageList")) {
			String searchWord = req.getParameter("searchWord");
			String choice = req.getParameter("choice");

			System.out.println("pageList choice=" + choice);
			System.out.println("pageList searchWord=" + searchWord);

			if (choice == null || choice.equals("")) {
				choice = "sel";
			}

			// 검색어를 지정하지 않고 choice가 넘어왔을 때
			if (choice.equals("sel")) {
				searchWord = "";// null->""로 변경해줌(=new String())
			}
			if (searchWord == null) {
				searchWord = "";
				choice = "sel";// select에서 "------선택"으로 돌아가기
			}

			int len = dao.getAllNews(choice, searchWord);

			System.out.println("pageList2 len:" + len);
			System.out.println("pageList2 choice=" + choice);
			System.out.println("pageList2 searchWord=" + searchWord);
			String spageNumber = req.getParameter("pageNumber");
			int pageNumber = 0;// 현재페이지
			if (spageNumber != null && !spageNumber.equals("")) {// 하라미터가 넘어왔을때
				pageNumber = Integer.parseInt(spageNumber);
			}
			System.out.println("pageList pageNumber:" + pageNumber);

			List<News_Dto> list = dao.getNewsPagingList(choice, searchWord, pageNumber);

			obj.put("len", len);
			obj.put("list", list);

			resp.setContentType("\"application/x-json; charset=UTF-8\"");
			resp.getWriter().print(obj);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}