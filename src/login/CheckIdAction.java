
package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.model.MemberDAO;

public class CheckIdAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		MemberDAO dao = MemberDAO.getInstance();
		String id = req.getParameter("id");
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		String chk = dao.checkID(id);
	//	System.out.println(chk);
		out.write(chk);
	}
}
