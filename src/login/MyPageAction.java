
package login;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.model.MemberDAO;
import login.model.MemberDTO;

public class MyPageAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dis = null;
		HttpSession session = req.getSession();
		
		String sessionChkId = (String) session.getAttribute("loginID");
		MemberDAO dao = MemberDAO.getInstance();
		
		if(sessionChkId!=null) {	// 로그인이 되어 있으면
			MemberDTO dto = dao.myPage(sessionChkId);
			req.setAttribute("dto", dto);
			dis = req.getRequestDispatcher("/jsp/myPage.jsp");
		}else {
			dis = req.getRequestDispatcher("/jsp/login.jsp");
		}
		
		ArrayList<Integer> aList = new ArrayList<Integer>();
		
		dis.forward(req, resp);
		
	}
}

