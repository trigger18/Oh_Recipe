
package recipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.CommentDAO;
import recipe.model.IrdntDAO;
import recipe.model.PrimDAO;
import recipe.model.PrimDTO;
import recipe.model.StepDAO;

public class ShowRecipeAction {

	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		PrimDAO pdao = new PrimDAO();
		int recipe_id = Integer.parseInt(req.getParameter("recipe_id"));
		pdao.primViewsCnt(recipe_id);
		PrimDTO pdto = pdao.listView(recipe_id);
		req.setAttribute("recipe_id", recipe_id);
		req.setAttribute("prim", pdto);
		pdao.exit();
		StepDAO sdao = new StepDAO();
		req.setAttribute("step", sdao.listView(recipe_id));
		sdao.exit();
		IrdntDAO idao = new IrdntDAO();
		req.setAttribute("irdntMain", idao.mainList(recipe_id));
		req.setAttribute("irdntSub", idao.subList(recipe_id));
		idao.exit();
		int hour=0;
		int minute=0;
		String zero="";
		hour = Integer.parseInt(pdto.getCOOKING_TIME())/60;
		minute = Integer.parseInt(pdto.getCOOKING_TIME())%60;
		if(minute<10)
			zero = "0";
		req.setAttribute("hour", hour);
		req.setAttribute("minute", zero+minute);
		CommentDAO cdao = new CommentDAO();
		req.setAttribute("total", cdao.getTotal(recipe_id));  
		cdao.exit();
	}

}
