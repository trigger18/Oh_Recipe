
package selfrecipe;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import selfrecipe.model.SelfRecipeDAO;



public class SelfDeleteAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {		
		
		
		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		String saveDirectory = "C:\\study\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\semiRecipe\\selfRecipe\\img_self";
		File file = new File(saveDirectory);

		int recipe_id = Integer.parseInt(req.getParameter("recipe_id"));
		String img_url = dao.fileMethod(recipe_id);
		if (img_url != null) {
				File oldFile = new File(saveDirectory, img_url);
				oldFile.delete();
		}

		dao.primDeleteMethod(Integer.parseInt(req.getParameter("recipe_id")));
		dao.selfRecipeDeleteMethod(Integer.parseInt(req.getParameter("recipe_id")));
		dao.irdntDeleteMethod(Integer.parseInt(req.getParameter("recipe_id")));
		dao.stepDeleteMethod(Integer.parseInt(req.getParameter("recipe_id")));
	}
}// end class
