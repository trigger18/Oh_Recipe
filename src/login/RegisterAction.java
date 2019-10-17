
package login;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.model.MemberDAO;
import login.model.MemberDTO;

public class RegisterAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String nickname = req.getParameter("nickname");
		
		String year = req.getParameter("yy");
		String month = req.getParameter("mm");
		String day = req.getParameter("dd");
		
		String kakao_id = req.getParameter("kakao_id");
		String user_icon = req.getParameter("user_icon");
		
		if(Integer.parseInt(req.getParameter("mm"))<10) {
			month = "0"+month;
		}
		
		Date birthday = Date.valueOf(year+"-"+month+"-"+day);

		MemberDTO dto = new MemberDTO();
		MemberDAO dao = MemberDAO.getInstance();
		
		dto.setUser_id(id);
		dto.setUser_pw(pw);
		dto.setUser_nickname(nickname);
		dto.setUser_birthday(birthday);
		dto.setKakao_id(kakao_id);
		dto.setUser_icon(user_icon);
		dao.registerMethod(dto);
		
	}

}
