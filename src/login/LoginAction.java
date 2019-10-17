
package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.model.MemberDAO;
import login.model.MemberDTO;

public class LoginAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		MemberDTO dto = new MemberDTO();
		dto.setUser_id(req.getParameter("user_id"));
		dto.setUser_pw(req.getParameter("user_pw"));
		
		MemberDAO dao = MemberDAO.getInstance();
		int cnt = dao.login(dto);

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.write(String.valueOf(cnt));
		
		HttpSession session = null;

		
		if(cnt==1) {	// 회원일 때
			session = req.getSession();
			session.setAttribute("loginID", req.getParameter("user_id")); 	// loginID라는 이름으로 id 값을 세션에 저장
			MemberDAO icon = MemberDAO.getInstance();
			MemberDTO icdto = icon.myPage(req.getParameter("user_id"));
			session.setAttribute("loginICON", icdto.getUser_icon());
			session.setMaxInactiveInterval(30*60); 	// 30분
		}else {
			System.out.println("LoginAction 실패!");
		}
		
	} // end execute()
}
