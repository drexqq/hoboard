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
import review.Review_COMM_Dao;
import review.Review_COMM_Dto;

@WebServlet("/news")
public class news_controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet");

		req.setCharacterEncoding("UTF-8");

		String work = req.getParameter("work");
		// HttpSession session = req.getSession();
		News_Dao dao = News_Dao.getInstance();
		JSONObject obj = new JSONObject();
		// String id = "";

		if (work.equals("move")) {
			/*
			 * String id = req.getParameter("id"); System.out.println("id:" + id);
			 */

			// 검색
			String searchWord = req.getParameter("searchWord");
			String choice = req.getParameter("choice");

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

			// 서치
		} else if (work.equals("search")) {

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

			// 디테일
		} else if (work.equals("detail")) { // update view로 이동

			System.out.println("detail get");

			int seq = Integer.parseInt(req.getParameter("seq"));
			System.out.println("news_detail.seq =" + seq);

			dao = News_Dao.getInstance();

			News_Dto dto = dao.getNewsSeq(seq);
			System.out.println(dto.toString());

			boolean vc = dao.viewcount(seq);

			req.setAttribute("dto", dto);

			UtilEx.forward("news_detail.jsp", req, resp);
		}
		if(work.equals("update")) {				// update view로 이동			
			
			int seq = Integer.parseInt(req.getParameter("seq"));
			System.out.println("update seq :" + seq);

			News_Dto dto = dao.getNewsSeq(seq);
			req.setAttribute("dto", dto);
			
			UtilEx.forward("news_update.jsp", req, resp);
		
			//글 수정 후
		}else if(work.equals("updateAf")) { // 수정후 DB에 저장
			System.out.println("arrive");
			
			int seq = Integer.parseInt(req.getParameter("seq"));  
			String title =req.getParameter("title");
			String content = req.getParameter("content");
			
			System.out.println("updateAf seq : " +seq);
			System.out.println("title : " + title);
			System.out.println("content :" + content);
		  
			boolean isS = dao.news_update(seq, title, content);
			req.setAttribute("isS", isS);
			UtilEx.forward("news_updateAf.jsp", req, resp);
		  
		}else if(work.equals("del")) { 
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
			//HttpSession session = req.getSession();
			News_Dao dao = News_Dao.getInstance();
			news_comm_dao dao2 = news_comm_dao.getInstance(); // 뉴스 댓글 관련 dao
			
			JSONObject obj = new JSONObject();
			//String id = "";
		
			if(work2.equals("write")) {
		
				String id = req.getParameter("id");
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				//System.out.println("title ="+title+", content= "+content);
		
				id="admin";
				dao = News_Dao.getInstance();
				News_Dto dto = new News_Dto(id,title,content);
				boolean b = dao.news_write(dto);
		
				if(b){ 
					System.out.println("글쓰기 성공");
					resp.sendRedirect("news?work=move");
				} else {
					System.out.println("실패");
					//resp.sendRedirect("news_detail.do");
				}

			// 댓글 작성
			  } else if (work2.equals("c_write")) {
				  
				dao2 = news_comm_dao.getInstance();
				news_comm_dto dto2 = new news_comm_dto();
				
				String id = req.getParameter("id");
				int b_seq = Integer.parseInt(req.getParameter("b_seq"));
				String c_content = req.getParameter("c_content");
				//reply_content = new String(reply_content.getBytes("8859_1"),"utf-8");
							
				System.out.println(req.getParameter("id"));
				System.out.println("b_seq!!!"+ b_seq);
				System.out.println(req.getParameter("c_content"));				  
				 
				boolean isS = dao2.comm_write(new news_comm_dto(id, c_content, b_seq));

				String seq = req.getParameter("b_seq");
				
				if(isS) {
						System.out.println("덧글 작성 성공");
						resp.sendRedirect("news?work2=detail&seq="+b_seq );
				} else {
						System.out.println("덧글 작성 실패");
						resp.sendRedirect("news?work2=detail&seq="+b_seq );
				}
				
				resp.sendRedirect("news?work=move&seq="+b_seq);
			}				
				

				
				
				//UtilEx.forward("news_detail.jsp", req, resp);
				

				
				
			  }
	
}
