
package comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.CommentDAO;
import comment.model.CommentDTO;
import recipe.model.PrimDAO;

public class ComInsertAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String com_content = req.getParameter("com_content");
		String user_id = req.getParameter("user_id");
		int rating = Integer.parseInt(req.getParameter("rating"));
		String com_board = req.getParameter("com_board");
		int key = Integer.parseInt(req.getParameter("key"));
		CommentDTO dto = new CommentDTO();
		dto.setCom_content(com_content);
		dto.setCom_board(com_board);
		dto.setRating(rating);
		dto.setUser_id(user_id);
		CommentDAO cdao = new CommentDAO();
		cdao.insertCom(dto, key);
		cdao.exit();
		PrimDAO pdao = new PrimDAO();
		pdao.primRating(key);
		pdao.exit();
	}
}
