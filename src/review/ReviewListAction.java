
package review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import review.model.ReviewDAO;
import review.model.ReviewDTO;

public class ReviewListAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ReviewDAO dao = new ReviewDAO();
	//	System.out.println(req.getParameter("searchKey"));
	//	System.out.println(req.getParameter("searchWord"));
		
		Map<String, String> searchMap = new HashMap<String, String>();
		if(req.getParameter("searchKey")=="null") {
			searchMap.put("searchKey","null");
		}else {
			searchMap.put("searchKey",req.getParameter("searchKey"));
		}
		
		if(req.getParameter("searchWord")=="null") {
			searchMap.put("searchWord","");
		}else {
			searchMap.put("searchWord",req.getParameter("searchWord"));
		}
		
		
		
		List<ReviewDTO> aList = dao.listMethod(searchMap);
		
		HttpSession session = req.getSession();
//		session.removeAttribute("reviewList");
		req.setAttribute("reviewList", aList);
		
		int start = 0; 
		int finish = 0;
		
		List<String> imgList = new ArrayList<String>();
		
		for(int i=0; i<aList.size(); i++) {
			String a = aList.get(i).getReview_content();
			start = a.indexOf("src=\"");
			finish = a.indexOf(" title");
			
			if(start>-1) {
			//	System.out.println(a.substring(start+5,finish-1));
				imgList.add(a.substring(start+5,finish-1));
			}else {
				imgList.add("/semiRecipe/review/images/basicImage.png");
			}
		}
	//	req.removeAttribute("imgList");
		req.setAttribute("imgList", imgList);
				
	//	req.setAttribute("aList", aList);
	}
}
