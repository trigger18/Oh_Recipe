package recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.sql.DataSource;

import common.JdbcTemplate;


public class CommonDAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public CommonDAO() {
		try {
			conn = JdbcTemplate.getConnection();
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
	}

	public ResultSet queryPstmt(String sql, String[] param) throws SQLException {
		pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < param.length; i++) {
			pstmt.setString(i+1, param[i]);
		}
		rs = pstmt.executeQuery();
		return rs;
	}

	public ResultSet queryStmt(String sql) throws SQLException {
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = stmt.executeQuery(sql);
		return rs;
	}

	public PreparedStatement updatePstmt(String sql) throws SQLException {
		pstmt = conn.prepareStatement(sql);			
		return pstmt;
	}

	public void exit() {
		JdbcTemplate.close(rs);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(stmt);
		JdbcTemplate.close(conn);
	}
}
