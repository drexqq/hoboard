package home;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;
import member.Member_Dao;
import review.Review_Dao;
import review.Review_Dto;

@WebServlet("/")
public class HomeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member_Dao m_dao = Member_Dao.getInstance();
		Review_Dao r_dao = Review_Dao.getInstance();

		String cate_e[] = m_dao.getBusiCateList();
		String cate_k[] = { "내과", "마취통증학과", "산부인과", "소아청소년과", "신경과", "신경외과", "심장내과", "영상의학과", "외과", "응급의학과", "정형외과",
				"재활의학과", "흉부심장혈관과", "피부비뇨기과", "치과", "안과" };
		
		List<Review_Dto> list = r_dao.getReviewList();
		
		LinkedHashMap<String, String> cate = new LinkedHashMap<String, String>();
		for (int i = 0; i < cate_e.length; i++) cate.put(cate_e[i].toLowerCase(), cate_k[i]);
		
		req.setAttribute("busiCate", cate);
		req.setAttribute("reviewList", list);

		UtilEx.forward("index.jsp", req, resp);
	}

}
