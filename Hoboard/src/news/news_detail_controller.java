package news;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Util.UtilEx;
@WebServlet("/news_detail.do")
public class news_detail_controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int nseq = Integer.parseInt(req.getParameter("nseq"));
		System.out.println("detail.nseq =" +nseq);
		
		News_Dao ndao = News_Dao.getInstance();
		
		//시퀀스를 통해 글 상세 보기 접근
		News_Dto ndto = ndao.getNewsSeq(nseq);
		System.out.println(ndto.toString());
		
		boolean vc = ndao.viewcount(nseq);
		
		req.setAttribute("ndto", ndto);
		
		
		/*
		 * if(vc) { UtilEx.forward.setRedirect(false); UtilEx.forwrd.setNextPath() }
		 */
		
		
		
		UtilEx.forward("news_detail.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}