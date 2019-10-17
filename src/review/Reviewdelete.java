package review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.ReviewDAO;

public class Reviewdelete {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		ReviewDAO dao = new ReviewDAO();
		
		int review_num = Integer.parseInt(req.getParameter("review_num"));
		dao.deleteMethod(review_num);
	}

}
