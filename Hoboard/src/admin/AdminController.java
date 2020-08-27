package admin;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;
import ask.Ask_Dao;
import ask.Ask_Dto;
import member.BUSI_Member_Dao;
import member.BUSI_Member_Dto;
import member.Member_Dao;
import member.Member_Dto;
import news.News_COMM_Dao;
import news.News_COMM_Dto;
import news.News_Dao;
import news.News_Dto;
import review.Review_Dao;
import review.Review_Dto;


@WebServlet("/admin")
public class AdminController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String adm = req.getParameter("adm");
		
		System.out.println("admin");
		
		News_Dao dao = News_Dao.getInstance();
		Review_Dao dao2 = Review_Dao.getInstance();
		Ask_Dao dao3 = Ask_Dao.getInstance();
		Member_Dao dao4 = Member_Dao.getInstance();
		
		BUSI_Member_Dao b_dao = BUSI_Member_Dao.getInstance();
		
		
		
		Member_Dto dto = new Member_Dto();
		
		//병원회원
		List<Member_Dto> hmlist = dao4.getBusiMember_admin();
		//개인회원
		List<Member_Dto> pmlist = dao4.getPMember_admin();
		
		System.out.println(pmlist.toString());
		
		if(adm == null || "".equals(adm)) {
		
			List<News_Dto> nlist = dao.getNewsList();
			
			List<Review_Dto> rlist = dao2.getReviewList2();
			//ALL 문의게시판 리스트
			List<Ask_Dto> qlist = dao3.getAskList2();
			//답변대기 리스트 (comm = '0'인 리스트)
			List<Ask_Dto> qlist_all = dao3.getAskListComment();
			
			req.setAttribute("nlist", nlist);
			
			req.setAttribute("rlist", rlist);
			//Ask list
			req.setAttribute("qlist_all", qlist_all);
			req.setAttribute("qlist", qlist);
			
			
			
			req.setAttribute("hmlist", hmlist);
			req.setAttribute("pmlist", pmlist);
			
			UtilEx.forward("admin.jsp", req, resp);
		
		}else if(adm.equals("adminM")) {
			System.out.println("Admin_Mem_Count");
			
			req.setAttribute("hmlist", hmlist);
			req.setAttribute("pmlist", pmlist);
			
			UtilEx.forward("admin_mem.jsp", req, resp);
		
		}else if(adm.equals("adminBD")) {
			
			String id = req.getParameter("id");
			System.out.println("adminD id =" +id);
			
			
			int auth = dto.getAuth();
			dto = dao4.getUser(id);
			BUSI_Member_Dto b_dto = b_dao.getUser(id);
			
			LinkedHashMap<String[], String> cate = new LinkedHashMap<String[], String>();
			LinkedHashMap<String[], String> time = new LinkedHashMap<String[], String>();
			LinkedHashMap<String[], String> amenity = new LinkedHashMap<String[], String>();
			
			String cate_k[] = BUSI_Member_Dao.cate;
			String cate_e[] = dao4.getBusiCateList();
			String cateValue[] = dao4.getBusiCate(id);
			for (int i = 0; i < cate_e.length; i++) {
				String[] cateName = { cate_e[i], cate_k[i] };
				cate.put(cateName, cateValue[i]);
			}

			String time_k[] = BUSI_Member_Dao.time;
			String time_e[] = dao4.getBusiTimeList();
			String timeValue[] = dao4.getBusiTime(id);
			for (int i = 0; i < timeValue.length; i++) {
				String[] timeName = { time_e[i], time_k[i] };
				time.put(timeName, timeValue[i]);
			}

			String amenity_k[] = BUSI_Member_Dao.amenity;
			String amenity_e[] = dao4.getAmenityList();
			String amenityValue[] = dao4.getBusiAmenity(id);
			for (int i = 0; i < amenityValue.length; i++) {
				String[] amenityName = { amenity_e[i], amenity_k[i] };
				amenity.put(amenityName, amenityValue[i]);
			}
			
			req.setAttribute("busiCate", cate);
			req.setAttribute("busiTime", time);
			req.setAttribute("busiAmenity", amenity);
			req.setAttribute("user", dto);
			req.setAttribute("b_user", b_dto);
			UtilEx.forward("admin_busi_detail.jsp", req, resp);
			
		}else if(adm.equals("adminPD")) {
			
			System.out.println("adminPD");
			
			String id = req.getParameter("id");
			
			System.out.println("adminD id =" +id);
			
			dto = dao4.getUser(id);
		
			req.setAttribute("id", id);
			req.setAttribute("dto", dto);
			
			UtilEx.forward("admin_individ_detail.jsp", req, resp);
			
		}else if(adm.equals("adminIDel")){
			
			System.out.println("adminIDel");
			
			String id = req.getParameter("id");
			
			System.out.println("adminIDel ="+id);
			
			dto = dao4.DelIMember(id);
			
			resp.sendRedirect("admin?adm=adminM");
			
		}else if(adm.equals("adminBDel")){
			
			System.out.println("adminBDel");
			
			String id = req.getParameter("id");
			
			System.out.println("adminBDel ="+id);
			
			dto = dao4.DelBMember(id);
			
			resp.sendRedirect("admin?adm=adminM");
		}
	}
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   }

   
   
}