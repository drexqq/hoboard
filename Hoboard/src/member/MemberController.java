package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

@WebServlet("/MEMBER")
public class MemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		System.out.println(id);
		Member_Dao dao = Member_Dao.getInstance();
		JSONObject jsonData = new JSONObject();
		jsonData.put("chk", dao.chkId(id));
		
		System.out.println(dao.chkId(id));
		resp.setContentType("application/x-json; charset=UTF-8");
		resp.getWriter().print(jsonData);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int auth = Integer.parseInt(req.getParameter("auth"));
		Member_Dao dao = Member_Dao.getInstance();
		Member_Dto dto = new Member_Dto(
									auth,
									req.getParameter("name"),
									req.getParameter("id"),
									req.getParameter("pw"),
									req.getParameter("email"),
									req.getParameter("tel"),
									req.getParameter("post_Num"),
									req.getParameter("address"),
									req.getParameter("d_Address")
									);
		dao.addMember(dto);
		if (auth == 1) {
			System.out.println("개인회원가입하기 member controller");
			INDVD_Member_Dao i_dao = INDVD_Member_Dao.getInstance();
			
			i_dao.addINDVD_Member(req.getParameter("id"));
		}
		else if (auth == 2) {
			System.out.println("병원회원가입하기 member controller");
			BUSI_Member_Dao b_dao = BUSI_Member_Dao.getInstance();
			BUSI_Member_Dto b_dto = new BUSI_Member_Dto(
											req.getParameter("id"),
											req.getParameter("homepage"),
											req.getParameter("logo")
											);
			// BUSI_TIME TABLE
			// 월~금, 점심
			String time[] = new String[8];
			// 공휴일, 야간, 응급실
			int extra[] = new int[3];
			
			// BUSI_CATE TABLE
			// 과목
			int cate[] = new int[16];
			
			// BUSI_AMENITY TABLE
			// 편의
			int amenity[] = new int[5];
			
			for (int i = 0; i < time.length; i++) {
				time[i] = "휴무";
				if (req.getParameter("time"+i) != null || !req.getParameter("time"+i).equals("")) time[i] = req.getParameter("time"+i);
			}
			for (int i = 0; i < extra.length; i++) {
				extra[i] = 0;
				if (req.getParameter("time"+(i+8)) != null) extra[i] = 1;
			}
			for (int i = 0; i < amenity.length; i++) {
				cate[i] = 0;
				if (req.getParameter("amenity"+i) != null) amenity[i] = 1;
			}
			for (int i = 0; i < cate.length; i++) {
				cate[i] = 0;
				if (req.getParameter("cate"+i) != null) cate[i] = 1;
			}
			
			b_dao.addBUSI_Member(b_dto, b_dto.getId());
			b_dao.addBUSI_Extra(b_dto.getId(), time, extra, cate, amenity);
		}
	}
	
	
	
}
