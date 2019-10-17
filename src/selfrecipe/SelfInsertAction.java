
package selfrecipe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import recipe.model.IrdntDTO;
import recipe.model.PrimDTO;
import recipe.model.StepDTO;
import selfrecipe.model.SelfRecipeDAO;
import selfrecipe.model.SelfRecipeDTO;

public class SelfInsertAction extends HttpServlet {
	public MultipartRequest executeMulti(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		String user_id = (String) session.getAttribute("loginID");
		MultipartRequest multi = null;
		String saveDirectory = req.getServletContext().getRealPath("")+"selfRecipe\\img_self";
		System.out.println(req.getServletContext().getRealPath(""));
		
		File file = new File(saveDirectory);
		if (!file.exists()) { // 파일 존재확인 후 생성
			file.mkdir();
		}
		int maxPostSize = 6000000; // 약 5MB(5242880Byte) 파일저장 최대크기
		String encoding = "UTF-8"; // 파일명 한글처리

		try {
			multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
			// new DefaultFileRenamePolicy() 동일파일명이 있을때 숫자 붙임
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*-----------------파일 업로드 처리------------------------------------*/

		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		int riMax = dao.recipeIdMax() + 1;

		PrimDTO prdto = new PrimDTO();
		prdto.setRECIPE_ID(riMax);
		prdto.setRECIPE_NM_KO(multi.getParameter("recipe_nm_ko"));
		prdto.setSUMRY(multi.getParameter("sumry"));
		prdto.setIMG_URL(multi.getFilesystemName("img_url")); // 첨부파일은 getFilesystemName()이걸로
		prdto.setNATION_NM(multi.getParameter("natioin_nm"));
		prdto.setCOOKING_TIME(multi.getParameter("cooking_time"));
		prdto.setCALORIE(multi.getParameter("calorie"));
		prdto.setLEVEL_NM(multi.getParameter("level_nm"));
		 prdto.setTY_NM(multi.getParameter("ty_nm"));
		dao.primInsertMethod(prdto);

		SelfRecipeDTO srdto = new SelfRecipeDTO();
		srdto.setRecipe_id(riMax);
		srdto.setUser_id(user_id);
		dao.selfRecipeInsertMethod(srdto);

		List<IrdntDTO> irList = new ArrayList<IrdntDTO>();
		String[] imp = multi.getParameterValues("imp");
		String[] irdnt_ty_nm = multi.getParameterValues("irdnt_ty_nm");
		String[] irdnt_nm = multi.getParameterValues("irdnt_nm");
		
		for (int i = 0; i < imp.length; i++) {
			IrdntDTO irdto = new IrdntDTO();
			irdto.setRECIPE_ID(riMax);
			irdto.setIMPORTANCE(imp[i]);// 필요도
			irdto.setIRDNT_TY_NM(irdnt_ty_nm[i]);
			irdto.setIRDNT_NM(irdnt_nm[i]);// 재료명
			irList.add(irdto);
		}
		
			
		dao.irdntInsertMethod(irList);
		 
		String[] cooking_dc = multi.getParameterValues("recipe_dc");
		List<StepDTO> stList = new ArrayList<StepDTO>();
		for (int i = 0; i < cooking_dc.length; i++) {
			StepDTO stdto = new StepDTO();
			stdto.setRECIPE_ID(riMax);
			stdto.setCOOKING_NO(i+1);
			stdto.setCOOKING_DC(cooking_dc[i]);
			stList.add(stdto);
		}
		dao.stepInsertMethod(stList);
		
		

		return multi;
	}// end executeMulti()
}// end selfWriteAction()
