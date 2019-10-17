
package selfrecipe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import recipe.model.IrdntDTO;
import recipe.model.PrimDTO;
import recipe.model.StepDTO;
import selfrecipe.model.SelfRecipeDAO;
import selfrecipe.model.SelfRecipeDTO;

public class SelfUpdateAction extends HttpServlet {
	
	public MultipartRequest executeMulti(HttpServletRequest req, HttpServletResponse resp)  {

		MultipartRequest multi = null;
		String saveDirectory = "C:\\study\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\semiRecipe\\selfRecipe\\img_self";
		File file = new File(saveDirectory);

		int maxPostSize = 6000000; // 약 5MB(5242880Byte) 파일저장 최대크기
		String encoding = "UTF-8"; // 파일명 한글처리

		try {
			multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
			// new DefaultFileRenamePolicy() 동일파일명이 있을때 숫자 붙임
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		PrimDTO prdto = new PrimDTO();				
		int recipe_id = Integer.parseInt(multi.getParameter("recipe_id"));
		String fileName = dao.fileMethod(recipe_id);
		
		if (multi.getFilesystemName("img_url") != null) {
			if(fileName != null) {
				File oldFile = new File(saveDirectory, fileName);
				oldFile.delete();
			}
			prdto.setIMG_URL(multi.getFilesystemName("img_url"));
		} else {
			if(fileName != null) {
				prdto.setIMG_URL(fileName);
			}
		}
		prdto.setRECIPE_NM_KO(multi.getParameter("recipe_nm_ko"));
		prdto.setSUMRY(multi.getParameter("sumry"));
		prdto.setTY_NM(multi.getParameter("ty_nm"));
		prdto.setLEVEL_NM(multi.getParameter("levele_nm"));
		prdto.setCOOKING_TIME(multi.getParameter("cooking_time"));
		prdto.setCALORIE(multi.getParameter("calorie"));
		
		SelfRecipeDTO srdto = new SelfRecipeDTO();
		srdto.setRecipe_id(Integer.parseInt(multi.getParameter("recipe_id")));
		srdto.setUser_id(multi.getParameter("user_id"));
	
		List<IrdntDTO> irList = new ArrayList<IrdntDTO>();
		IrdntDTO irdto = new IrdntDTO();
		for (int i = 0; i < irList.size(); i++) {
		irdto.setRECIPE_ID(Integer.parseInt(multi.getParameter("recipe_id")));
		irdto.setIMPORTANCE(multi.getParameter("importance"));
		irdto.setIRDNT_SN(Integer.parseInt(multi.getParameter("irdnt_sn")));
		irdto.setIRDNT_NM(multi.getParameter("irdnt_nm"));
		irList.add(irdto);
		}
			
		
		String[] cooking_dc = multi.getParameterValues("recipe_dc");
		List<StepDTO> stList = new ArrayList<StepDTO>();
		for (int i = 0; i < cooking_dc.length; i++) {
			StepDTO stdto = new StepDTO();
			stdto.setRECIPE_ID(Integer.parseInt(multi.getParameter("recipe_id")));
			stdto.setCOOKING_NO(i+1);
			stdto.setCOOKING_DC(cooking_dc[i]);
			stList.add(stdto);
		}
		dao.stepUpdateMethod(stList);
		
		return multi;
	}// end executeMulti()
}// end class
