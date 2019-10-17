
package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.model.MemberDAO;
import login.model.MemberDTO;

public class CheckNowPw {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String loginId = req.getParameter("loginId");
		String now_pw = req.getParameter("now_pw");
		
		MemberDTO dto = new MemberDTO();
		dto.setUser_id(loginId);
		dto.setUser_pw(now_pw);
		
		MemberDAO dao = MemberDAO.getInstance();
		int chk = dao.checkNowPw(dto);
		
		String result = "";
		
		if(chk!=1) {
			result = "현재 비밀번호를 확인해주세요.";
		}
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write(result);
	}
}
