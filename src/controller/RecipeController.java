package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comment.ComInsertAction;
import comment.ComListAction;
import login.CheckIdAction;
import login.CheckNicknameAction;
import login.CheckNowPw;
import login.InfoUpdate;
import login.KakaoLoginAction;
import login.LoginAction;
import login.LogoutAction;
import login.MyPageAction;
import login.RegisterAction;
import login.model.MemberDAO;
import login.model.MemberDTO;
import recipe.RecipeListAction;
import recipe.ShowRecipeAction;
import recipe.ViewAction;
import recipe.model.IrdntTYDAO;
import recipe.model.PrimDTO;
import review.ImageUpload;
import review.IrdntAction;
import review.ReviewInsertAction;
import review.ReviewListAction;
import review.ReviewViewAction;
import review.ReviewWriteAction;
import review.Reviewdelete;
import selfrecipe.SelfDeleteAction;
import selfrecipe.SelfInsertAction;
import selfrecipe.SelfListAction;
import selfrecipe.SelfUpdateAction;
import selfrecipe.SelfViewAction;
import selfrecipe.model.SelfRecipeDAO;


@WebServlet("/recipe/*")
public class RecipeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}

	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		String path = req.getRequestURI();
		String next = "";
		path = path.substring(path.indexOf("/", 2)+1);
		if(path.equals("recipe/home")) {
			IrdntTYDAO dao = new IrdntTYDAO();
			req.setAttribute("aList", dao.list());
			req.setAttribute("tList", dao.tyList());
			dao.exit();
			next = "/jsp/home.jsp";
		}else if(path.equals("recipe/viewResult")) {
			ViewAction act = new ViewAction();
			act.execute(req, resp);
			next = "/ajax/viewResult.jsp";
		}else if(path.equals("recipe/showRecipe")) {
			ShowRecipeAction act = new ShowRecipeAction();
			act.execute(req, resp);
			next = "/jsp/showRecipe.jsp";
		}else if(path.equals("recipe/list")) {
			RecipeListAction list = new RecipeListAction();
			list.execute(req, resp);////////////////////////////////////////////////////////////////////
			next = "/jsp/primList.jsp";
			if(method.equalsIgnoreCase("POST")) {
				next = "/ajax/listResult.jsp";
			}
		}else if(path.equals("recipe/review")) {
			ReviewListAction revList = new ReviewListAction();
			revList.execute(req, resp);
			next = "/review/reviewBoard.jsp";
		}else if(path.equals("recipe/reviewwrite")) {
			HttpSession session = req.getSession();
			String loginID = (String) session.getAttribute("loginID");
			
			if(loginID==null) {
				next ="/recipe/loginForm";
			}else {
				ReviewWriteAction revWrite = new ReviewWriteAction();
				revWrite.execute(req, resp);
				next = "/review/write.jsp";
			}
			
		}else if(path.equals("recipe/reviewinsert")) {
			ReviewInsertAction revInsert = new ReviewInsertAction();
			revInsert.execute(req, resp);
			//next="/review/reviewBoard.jsp";
			resp.sendRedirect("review");
		}else if(path.equals("recipe/reviewdelete")) {
			Reviewdelete revDelete = new Reviewdelete();
			revDelete.execute(req, resp);
		}else if(path.equals("recipe/imageUpload")) {
			ImageUpload imageUp = new ImageUpload();
			imageUp.execute(req, resp);
		}else if(path.equals("recipe/reviewview")) {
			ReviewViewAction revView = new ReviewViewAction();
			revView.execute(req, resp);
			next="/review/view.jsp";
		} else if(path.equals("recipe/irdnt")) {
			IrdntAction irdnt = new IrdntAction();
			irdnt.execute(req, resp);
			next = "/review/irdntList.jsp";
		}else if(path.equals("recipe/comList")) {
			ComListAction comList = new ComListAction();
			comList.execute(req,resp);
			next = "/jsp/comment.jsp";				
		}else if(path.equals("recipe/insertCom")) {
			ComInsertAction insertCom = new ComInsertAction();
			insertCom.execute(req,resp);
		}else if(path.equals("recipe/selfRecipe")) {
			SelfListAction selfList = new SelfListAction();// 셀프 리스트 
			selfList.execute(req, resp);////////////////////////////////////////////////////////////////////////////
			next = "/selfRecipe/selfBoard.jsp";		
			if(method.equalsIgnoreCase("POST")) {
				next = "/ajax/selfList.jsp";
			}
		}else if(path.equals("recipe/selfView")) { //  셀프 뷰
			SelfViewAction viewList = new SelfViewAction();
			viewList.execute(req, resp);
			next = "/selfRecipe/selfView.jsp";	
		}else if(path.equals("recipe/selfInsert")) { //  셀프 인서트
			if(method.equalsIgnoreCase("get")){
				IrdntTYDAO dao = new IrdntTYDAO();
				req.setAttribute("aList", dao.list());
				req.setAttribute("tList", dao.tyList());
				dao.exit();
				next = "/selfRecipe/selfInsert.jsp";				
			}else {				
				SelfInsertAction insertList = new SelfInsertAction();
				insertList.executeMulti(req, resp);
				SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
				int riMax = dao.recipeIdMax();
				resp.sendRedirect("selfRecipe");
			}
		}else if (path.equals("deleteSelfRecipe")) {
				SelfDeleteAction del = new SelfDeleteAction();
				del.execute(req, resp);
				//resp.sendRedirect("selfRecipe?pageNum=" + req.getParameter("pageNum"));
				//삭제 후 마지막 페이지 유지
		}else if(path.equals("recipe/selfUpdate")) { //  셀프 수정
			  if(method.equalsIgnoreCase("get")){ 
				  IrdntTYDAO dao = new IrdntTYDAO();
				  req.setAttribute("aList", dao.list()); 
				  req.setAttribute("tList", dao.tyList()); 
				  dao.exit(); 
				  SelfViewAction viewList = new SelfViewAction();
				  viewList.execute(req, resp);
				  next = "/selfRecipe/selfUpdate.jsp";
			  }else {
				  SelfRecipeDAO dao = SelfRecipeDAO.getInstance(); 
				  SelfUpdateAction suUpdate = new SelfUpdateAction();
				  suUpdate.executeMulti(req, resp); 
				  PrimDTO prdto = new PrimDTO();
				  resp.sendRedirect("selfRecipe");}
		}else if(path.equals("recipe/selfDel")) {	//                  셀프 델리
			SelfDeleteAction sfdel = new SelfDeleteAction();
			sfdel.execute(req, resp);
			resp.sendRedirect("selfRecipe");
		}else if(path.equals("recipe/loginForm")) {
			next = "/jsp/login.jsp";
		}else if(path.equals("recipe/login")) {
			LoginAction login = new LoginAction();
			login.execute(req, resp);
		}else if(path.equals("recipe/kakao_login")) {
			KakaoLoginAction kLogin = new KakaoLoginAction();
			kLogin.execute(req, resp);
		}else if(path.equals("recipe/logout")) {
			LogoutAction logout = new LogoutAction();
			logout.execute(req, resp);
			resp.sendRedirect("home");
		}else if(path.equals("recipe/registerForm")) {
			next = "/jsp/register.jsp";
		}else if(path.equals("recipe/register")) {
			RegisterAction register = new RegisterAction();
			register.execute(req, resp);
			
			HttpSession session = req.getSession();
			session.setAttribute("loginID", req.getParameter("id")); 	// loginID라는 이름으로 id 값을 세션에 저장
			MemberDAO icon = MemberDAO.getInstance();
			MemberDTO icdto = icon.myPage(req.getParameter("id"));
			session.setAttribute("loginICON", icdto.getUser_icon());
			session.setMaxInactiveInterval(30*60); 	// 30분
			
			resp.sendRedirect("home");
		}else if(path.equals("recipe/checkID")) {
			CheckIdAction checkId = new CheckIdAction();
			checkId.execute(req, resp);
		}else if(path.equals("recipe/checkNickname")) {
			CheckNicknameAction checkNickname = new CheckNicknameAction();
			checkNickname.execute(req, resp);
		}else if(path.equals("recipe/checkNowPw")) {
			CheckNowPw checkNowPw = new CheckNowPw();
			checkNowPw.execute(req, resp);
		}else if(path.equals("recipe/myPage")) {
			MyPageAction myPage = new MyPageAction();
			myPage.execute(req, resp);
		}else if(path.indexOf("recipe/infoUpdate/")>-1) {
			InfoUpdate update = new InfoUpdate(); 
			update.execute(req, resp);
		}
		
		if(next!="") {
			System.out.println(next);
			req.getRequestDispatcher(next).forward(req, resp);
		}
	}
}// end class
