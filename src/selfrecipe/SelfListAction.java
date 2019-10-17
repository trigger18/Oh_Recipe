
package selfrecipe;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe.model.PrimDTO;
import selfrecipe.model.SelfRecipeDAO;

public class SelfListAction extends HttpServlet{
	
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String method = req.getMethod();
		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		if(method.equalsIgnoreCase("GET")) {
			// 첫 페이지 번호 설정
			String pageNum = req.getParameter("pageNum");
			if ( pageNum == null || pageNum.equals("null")) {
				pageNum = "1";
			}
			
			//int currentPage = Integer.parseInt(pageNum);
			//int cnt = dao.rowTotalCount();
			//if(cnt > 0) {
			//PageDTO pdto = new PageDTO(currentPage, cnt);
			
			req.setAttribute("prList", dao.primListMethod());
			//req.setAttribute("srList", dao.selfRecipeListMethod(pdto));
			//req.setAttribute("irList", dao.irdntListMethod(pdto));
			//req.setAttribute("stList", dao.stepListMethod(pdto));
			
			//}
		} else if (method.equalsIgnoreCase("POST")) {
			String column = req.getParameter("column");
			String order = req.getParameter("order");
			String recipe_nm_ko = req.getParameter("recipe_nm_ko");
			String searchType = req.getParameter("searchType");			
			
			req.setAttribute("prList", dao.sortView(column, order, recipe_nm_ko, searchType));
			//req.setAttribute("srList", dao.selfRecipeListMethod(pdto));
			try {
				dao.exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		/*
		 * for(int i=0; i<dao.primListMethod(pdto).size(); i++) {
		 * System.out.println(dao.primListMethod(pdto).get(i).getRECIPE_ID()); }
		 */		
		
		
		
		
	}// end exectue()
}// end class
