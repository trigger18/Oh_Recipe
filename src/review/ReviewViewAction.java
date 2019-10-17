
package review;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import review.model.ReviewDAO;
import review.model.ReviewDTO;

public class ReviewViewAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ReviewDAO dao = new ReviewDAO();
		int num = Integer.parseInt(req.getParameter("review_num"));
		dao.readCountMethod(num);
		ReviewDTO aList = dao.viewMethod(num);
		req.setAttribute("viewer", aList);
	}
}