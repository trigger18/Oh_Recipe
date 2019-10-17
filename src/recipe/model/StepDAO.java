package recipe.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import recipe.CommonDAO;

public class StepDAO extends CommonDAO {
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool)이다.
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public StepDAO() {
		super();
	}
	
	public List<StepDTO> listView(int recipe_id){
		List<StepDTO> aList = new ArrayList<StepDTO>();
		String sql = "select cooking_no, cooking_dc from step where recipe_id = "+recipe_id;
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				StepDTO dto = new StepDTO();
				dto.setCOOKING_NO(rs.getInt(1));
				dto.setCOOKING_DC(rs.getString(2));
				aList.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aList;
	}
	
	
}