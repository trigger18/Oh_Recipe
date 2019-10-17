
package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("loginID"); // session 값이 저장될 때는 Object로 저장되기 때문에 다운캐스팅 해준다.

		if (id != null) { // 세션 연결 종료
			session.invalidate();
		}

	}

}
