
package review;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.model.MemberDAO;
import login.model.MemberDTO;
import recipe.model.PrimDAO;
import recipe.model.PrimDTO;
import review.model.ReviewDAO;
import review.model.ReviewDTO;

public class ReviewWriteAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PrimDAO dao = new PrimDAO();
		List<PrimDTO> aList = dao.listView();
		req.setAttribute("recipes", aList);
		
		RequestDispatcher dis = null;
		HttpSession session = req.getSession();
		
		String sessionChkId = (String) session.getAttribute("loginID");
		MemberDAO mdao = MemberDAO.getInstance();
		
		MemberDTO mdto = mdao.myPage(sessionChkId);
		req.setAttribute("mdto", mdto);

	}
}//end class