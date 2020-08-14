package home;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;
import member.Member_Dao;

@WebServlet("/")
public class HomeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member_Dao dao = Member_Dao.getInstance();

		String cate_e[] = dao.getBusiCateList();
		String cate_k[] = { "내과", "마취통증학과", "산부인과", "소아청소년과", "신경과", "신경외과", "심장내과", "영상의학과", "외과", "응급의학과", "정형외과",
				"재활의학과", "흉부심장혈관과", "피부비뇨기과", "치과", "안과" };
		
		LinkedHashMap<String, String> cate = new LinkedHashMap<String, String>();
		for (int i = 0; i < cate_e.length; i++) cate.put(cate_e[i].toLowerCase(), cate_k[i]);
		
		req.setAttribute("busiCate", cate);

		UtilEx.forward("index.jsp", req, resp);
	}

}
