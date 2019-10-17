
package selfrecipe;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import selfrecipe.model.SelfRecipeDAO;

public class SelfRecipeDeleteAction {
	public void execute(HttpServletRequest req) {
		String saveDirectory = "/selfRecipe/img_self";
		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		int recipe_id = Integer.parseInt(req.getParameter("recipe_id"));
		String fileName = dao.fileMethod(recipe_id);
		if (fileName != null) {
			File file = new File(saveDirectory, fileName);
			file.delete();
		}
		dao.primDeleteMethod(recipe_id);
		dao.irdntDeleteMethod(recipe_id);
		dao.selfRecipeDeleteMethod(recipe_id);
		dao.stepDeleteMethod(recipe_id);
	}

}// end class
