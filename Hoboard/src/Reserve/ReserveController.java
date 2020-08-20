package Reserve;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.UtilEx;
import member.Member_Dto;
import review.Review_Dao;

@WebServlet("/reserve")
public class ReserveController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		Reserve_Dao dao = Reserve_Dao.getInstance();

		String cate = req.getParameter("cate");

		if (cate.equals("internal")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getInternal_list(seq);


			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);

		} else if (cate.equals("anpn")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getanpn_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);
		} else if (cate.equals("mtrnt")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getmtrnt_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);
		} else if (cate.equals("pdtrc")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getpdtrc_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);
		} else if (cate.equals("nrlgy")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getnrlgy_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);

		} else if (cate.equals("nrsrg")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getnrsrg_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);

		} else if (cate.equals("crdlg")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getcrdlg_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);

		} else if (cate.equals("xray")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getxray_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);

		} else if (cate.equals("gs")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getgs_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);

		} else if (cate.equals("dprtm")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getdprtm_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);

		} else if (cate.equals("os")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getos_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);

		} else if (cate.equals("rhblt")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getrhblt_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);

		} else if (cate.equals("thrcc")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getthrcc_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);

		} else if (cate.equals("skin_uro")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getskin_uro_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);

		} else if (cate.equals("dent")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getdent_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);

		} else if (cate.equals("ophth")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<Member_Dto> list = dao.getophth_list(seq);

			req.setAttribute("reslist", list);
			UtilEx.forward("reserve.jsp", req, resp);

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
