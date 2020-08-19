package Ask;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.UtilEx;
import net.sf.json.JSONObject;
import news.News_Dto;

@WebServlet("/ask.do")
public class AskController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("This is get");

			req.setCharacterEncoding("UTF-8");
			
			String one = req.getParameter("one");
			//HttpSession session = req.getSession();
			Ask_Dao dao = Ask_Dao.getInstance();
			JSONObject obj = new JSONObject();
			
			//String id = "";
			
			if(one.equals("move")) {
			
				String searchWord = req.getParameter("searchWord");
				String choice = req.getParameter("choice");
				
				if (choice == null || choice.equals("")) {
					System.out.println("들리시나요ㅛㅛㅛㅛ??");
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
				System.out.println("AskpageNumber:" + pageNumber);
				
				List<Ask_Dto> list = dao.getAskPagingList(choice, searchWord, pageNumber);

				// 목록 리스트를 검색한것만 가져옴
				int len = dao.getAllAsk(choice, searchWord);
				System.out.println("Asklen:" + len);
				
				// 보내줄 데이터
				req.setAttribute("list", list);
				req.setAttribute("len", len);
				req.setAttribute("choice", choice);
				req.setAttribute("searchWord", searchWord);
				req.setAttribute("pageNumber", pageNumber);
				// 이동
				UtilEx.forward("my_ask.jsp", req, resp);
				
			}
			
			else if(one.equals("detail")) {
				
				int seq = Integer.parseInt(req.getParameter("seq"));
				System.out.println("Askdetail.seq =" +seq);
				
				dao = Ask_Dao.getInstance();
				
				//시퀀스를 통해 글 상세 보기 접근
				Ask_Dto dto = dao.getAskSeq(seq);
				System.out.println(dto.toString());
				
				//boolean vc = de_dao.viewcount(seq);
				
				req.setAttribute("dto", dto);
				
				UtilEx.forward("ask_detail.jsp", req, resp);
			}
			
			else if(one.equals("del")) {
				 
					System.out.println("one.equals(del)");
				  int seq = Integer.parseInt(req.getParameter("seq"));
				  
				  dao.ask_delete(seq);
				  resp.sendRedirect("ask.do?one=move");
				  
			
			}else if(one.equals("update")) {	//updateView
				
				int seq = Integer.parseInt(req.getParameter("seq"));
				System.out.println("ask update seq :" + seq);

				Ask_Dto dto = dao.getAskSeq(seq);
				req.setAttribute("dto", dto);
				
				UtilEx.forward("ask_update.jsp", req, resp);
		
			} else if(one.equals("updateAf")) { // 수정후 DB에 저장
				System.out.println("arrive");
				
				int seq = Integer.parseInt(req.getParameter("seq"));
				String title =req.getParameter("title");
				String content = req.getParameter("content");
				
				System.out.println("updateAf seq : " +seq);
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
			//HttpSession session = req.getSession();
			Ask_Dao dao = Ask_Dao.getInstance();
			JSONObject obj = new JSONObject();
			
			//String id = "";
			
			if(two.equals("write")) {
				System.out.println(2222);
				
				String id = req.getParameter("id");
				// String title = URLEncoder.encode(req.getParameter("title"), "utf-8");
				
				
				String title = req.getParameter("title");  
				String content = req.getParameter("content"); 
								
				//String content = URLEncoder.encode(req.getParameter("content"), "utf-8");
				System.out.println("title ="+title+", content= "+content);
				
				id="admin";
				dao = Ask_Dao.getInstance();
				Ask_Dto dto = new Ask_Dto(id,title,content);
				boolean b = dao.ask_write(dto);
				
				if(b){ 
					System.out.println("글쓰기 성공");
					resp.sendRedirect("ask.do?one=move");
				} else {
					System.out.println("실패");
					//resp.sendRedirect("news_detail.do");
				}
			}
			
			else if (two.equals("c_write")) {
				System.out.println("comm post");
				
				String id = req.getParameter("id");
				String content = req.getParameter("content");
				int nseq = Integer.parseInt(req.getParameter("nseq"));
				
				System.out.println("id ="+id+", content= "+content);
				
				id="admin";
				Ask_Comm_Dao dao2 = Ask_Comm_Dao.getInstance();
				
				Ask_Comm_Dto dto2 = new Ask_Comm_Dto ();
				
				boolean b = dao2.comm_write(dto2);
				
				if(b){ 
					System.out.println("댓글쓰기 성공");
					resp.sendRedirect("ask.do?one=detail&nseq="+nseq);
				} else {
					System.out.println("실패");
					//resp.sendRedirect("news_detail.do");
				}
				
				
			}
			
	}

}
