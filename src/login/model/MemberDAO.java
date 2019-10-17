package login.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDAO {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private MemberDAO() {

	}

	private static MemberDAO dao = new MemberDAO();

	public static MemberDAO getInstance() {
		return dao;
	}

	private Connection init() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin://@192.168.30.72:1521:xe";
		String user = "hong";
		String password = "1234";
		return DriverManager.getConnection(url, user, password);
	}// end init()

	private void exit() throws SQLException {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}// end exit()
	
	public void registerMethod(MemberDTO dto) {
		try {
			conn = init();
			String sql = "insert into user_table values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getUser_pw());
			pstmt.setString(3, dto.getUser_nickname());
			pstmt.setDate(4, dto.getUser_birthday());
			pstmt.setString(5, dto.getUser_icon());
			pstmt.setString(6, dto.getKakao_id());
			
			System.out.println("여기까진 되나요");
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String checkID(String id) {
		String chk = "";
		try {
			conn = init();
			String sql = "select user_id from user_table where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // true이면
				chk = "이미 사용중인 아이디 입니다.";
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return chk;
	}
	
	public String checkNickname(String nickname) {
		String chk = "";
		try {
			conn = init();
			String sql = "select user_id from user_table where user_nickname = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // true이면
				chk = "이미 사용중인 닉네임 입니다.";
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return chk;
	}
	
	public int login(MemberDTO dto) {
		int cnt=0;
		try {
			conn = init();

			System.out.println(dto.getKakao_id());
			
			String sql = "select count(user_id) from user_table where user_id=? and user_pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getUser_pw());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	public String kakaoLogin(MemberDTO dto) {		// 카카오 로그인 했을 때 회원등록되어있는지 여부
		String user_id = "";
		try {
			conn = init();
			String sql = "select user_id from user_table where kakao_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getKakao_id());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user_id = rs.getString(1);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user_id;
	}
	
	public MemberDTO myPage(String sessionChkId) {
		MemberDTO dto = new MemberDTO();
		
		try {
			conn=init();
			String sql = "select * from user_table where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessionChkId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_pw(rs.getString("user_pw"));
				dto.setUser_nickname(rs.getString("user_nickname"));
				dto.setUser_birthday(rs.getDate("user_birthday"));
				dto.setUser_icon(rs.getString("user_icon"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public void update(MemberDTO dto) {
		try {
			conn = init();
			String sql = "update user_table set "+dto.getUpdateName()+"= ? where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			if(dto.getUpdateName().equals("user_pw")) {
				pstmt.setString(1, dto.getUser_pw());
			} else if(dto.getUpdateName().equals("user_nickname")) {
				pstmt.setString(1, dto.getUser_nickname());
			} else if(dto.getUpdateName().equals("user_birthday")) {
				pstmt.setDate(1, dto.getUser_birthday());
			} else if(dto.getUpdateName().equals("user_icon")) {
				pstmt.setString(1, dto.getUser_icon());
			}
			
			pstmt.setString(2, dto.getUser_id());
			rs = pstmt.executeQuery();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int checkNowPw(MemberDTO dto) {
		int chk=0;
		try {
			conn = init();
			String sql = "select count(*) from user_table where user_id=? and user_pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getUser_pw());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				chk = rs.getInt(1);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return chk;
	}

}




















