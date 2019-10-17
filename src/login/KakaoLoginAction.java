
package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.model.MemberDAO;
import login.model.MemberDTO;

public class KakaoLoginAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		MemberDTO dto = new MemberDTO();
		dto.setKakao_id(req.getParameter("kakao_id"));
		
		MemberDAO dao = MemberDAO.getInstance();
		String user_id = dao.kakaoLogin(dto);
		
		int cnt = 0;
		if(!user_id.equals("")) {
			cnt = 1;
		}
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.write(String.valueOf(cnt));
		
		HttpSession session = null;
		if(cnt==1) {	// 회원일 때
			session = req.getSession();
			
			dao.login(dto);
			
			session.setAttribute("loginID", user_id); 	// loginID라는 이름으로 id 값을 세션에 저장
			session.setMaxInactiveInterval(30*60); 	// 30분
		}else {
		//	System.out.println("카카오 정보로 회원가입 안 되어 있음");
		}
	
	}
}
