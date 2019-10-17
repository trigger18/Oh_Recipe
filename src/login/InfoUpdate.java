
package login;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.model.MemberDAO;
import login.model.MemberDTO;

public class InfoUpdate {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String path = req.getRequestURI();
		path = path.substring(path.lastIndexOf("/"));
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		if(path.equals("/pw")) {
			dto.setUser_pw(req.getParameter("pw"));
			dto.setUpdateName("user_pw");
		}else if(path.equals("/nickname")) {
			dto.setUser_nickname(req.getParameter("nickname"));
			dto.setUpdateName("user_nickname");
		}else if(path.equals("/birthday")) {
			Date birthday = Date.valueOf(req.getParameter("birthday"));
			dto.setUser_birthday(birthday);
			dto.setUpdateName("user_birthday");
		}else if(path.equals("/icon")) {
			dto.setUser_icon(req.getParameter("icon"));
			dto.setUpdateName("user_icon");
		}
		
		HttpSession session = req.getSession();
		
		dto.setUser_id((String) session.getAttribute("loginID"));
		dao.update(dto);
	}
}
