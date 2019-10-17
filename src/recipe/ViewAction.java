
package recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.model.PrimDAO;

public class ViewAction {
	
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String irdnt_nm = req.getParameter("irdnt_nm");
		PrimDAO dao = new PrimDAO();
		req.setAttribute("viewPrim", dao.searchRecipe(irdnt_nm));
		dao.exit();
	}
}
