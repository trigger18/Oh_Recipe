package comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.CommentDAO;

public class ComListAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String com_board = req.getParameter("com_board");
		int key = Integer.parseInt(req.getParameter("key"));
		String order = req.getParameter("order");
		int rownum = Integer.parseInt(req.getParameter("rownum"));		
		CommentDAO dao = new CommentDAO();
		req.setAttribute("comList", dao.listMethod(com_board, key, order,rownum));
		dao.exit();
	}
}
