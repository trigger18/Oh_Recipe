package review;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import recipe.model.IrdntDAO;
import recipe.model.PrimDAO;
import review.model.ReviewDAO;
import review.model.ReviewDTO;

public class ReviewInsertAction {


	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		ReviewDAO dao = new ReviewDAO();
		ReviewDTO dto = new ReviewDTO();
	
		
		Enumeration<String> nms = req.getParameterNames();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		while (nms.hasMoreElements()) {
			String param = (String) nms.nextElement();
			String key="";
			if(param.contains("importance")) {
				key = param.substring(param.lastIndexOf("_")+1);
				map.put(Integer.parseInt(key), Integer.parseInt(req.getParameter(param)));
			}
		}
		
		dto.setReview_content(req.getParameter("review_content"));
		dto.setReview_rate(req.getParameter("review_rate"));
		dto.setUser_id(req.getParameter("user_id"));
		dto.setRecipe_id(Integer.parseInt(req.getParameter("recipe_id")));
		dto.setRecipe_nm_ko(req.getParameter("recipe_nm_ko"));
		dto.setReview_subject(req.getParameter("review_subject"));
		dto.setUser_nickname(req.getParameter("user_nickname"));
		dao.insertMethod(dto);
		
		PrimDAO pdao = new PrimDAO();
		pdao.primRating(dto.getRecipe_id());
		pdao.exit();
		
		IrdntDAO idao = new IrdntDAO();
		boolean chk = idao.chkFirst(dto.getRecipe_id());
		if(chk) {
			idao.firstImportance(map, dto.getRecipe_id());
		}else {
			idao.setImportance(map,dto.getRecipe_id());
		}
		idao.exit();		
	}

}//end class