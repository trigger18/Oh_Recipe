
package review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.model.IrdntDAO;

public class IrdntAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		IrdntDAO idao = new IrdntDAO();
		int recipe_id = Integer.parseInt(req.getParameter("recipe_id"));
		req.setAttribute("irdntMain", idao.mainList(recipe_id));
		req.setAttribute("irdntSub", idao.subList(recipe_id));
		idao.exit();
	}
}
