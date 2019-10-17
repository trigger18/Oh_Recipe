
package recipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.model.PrimDAO;

public class RecipeListAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {	
		String method = req.getMethod();
		PrimDAO dao = new PrimDAO();
		
		if(method.equalsIgnoreCase("GET")) {
			req.setAttribute("recList", dao.listView());
			req.setAttribute("nation_nm", dao.getNation_nms());
			dao.exit();
		}else if(method.equalsIgnoreCase("POST")) {
			String column = req.getParameter("column");
			String order = req.getParameter("order");
			String nation_nm = req.getParameter("nation_nm");
			String recipe_nm_ko = req.getParameter("recipe_nm_ko");
			String searchType = req.getParameter("searchType");
			req.setAttribute("recList", dao.sortView(column, order,nation_nm,recipe_nm_ko,searchType));
			dao.exit();
		}
	}
}
