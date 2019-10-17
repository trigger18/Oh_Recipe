
package recipe.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.sql.DataSource;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import recipe.CommonDAO;


public class IrdntDAO extends CommonDAO {
	private DataSource ds; // DataSource ds  는  아파치톰캣이  제공하는 DBCP(DB Connection Pool)이다.
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public IrdntDAO() {
		super();
	}
	
	public List<IrdntDTO> mainList(int recipe_id){
		List<IrdntDTO> aList = new ArrayList<IrdntDTO>();
		String sql = "select irdnt_nm, irdnt_cpcty, importance, recipe_id, irdnt_sn, irdnt_ty_nm from irdnt where recipe_id = "+recipe_id+" and irdnt_ty_nm != '양념'";
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				IrdntDTO dto = new IrdntDTO();
				dto.setIRDNT_NM(rs.getString(1));
				dto.setIRDNT_CPCTY(rs.getString(2));
				dto.setIMPORTANCE(rs.getString(3));
				dto.setRECIPE_ID(rs.getInt(4));
				dto.setIRDNT_SN(rs.getInt(5));
				dto.setIRDNT_TY_NM(rs.getString(6));
				aList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}

	public List<IrdntDTO> subList(int recipe_id) {
		List<IrdntDTO> aList = new ArrayList<IrdntDTO>();
		String sql = "select irdnt_nm, irdnt_cpcty, importance, recipe_id, irdnt_sn, irdnt_ty_nm from irdnt where recipe_id = "+recipe_id+"and irdnt_ty_nm = '양념'";
		try {
			rs =queryStmt(sql);
			while(rs.next()) {
				IrdntDTO dto = new IrdntDTO();
				dto.setIRDNT_NM(rs.getString(1));
				dto.setIRDNT_CPCTY(rs.getString(2));
				dto.setIMPORTANCE(rs.getString(3));
				dto.setRECIPE_ID(rs.getInt(4));
				dto.setIRDNT_SN(rs.getInt(5));
				dto.setIRDNT_TY_NM(rs.getString(6));
				aList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}

	public boolean chkFirst(int recipe_id) {
		boolean chk = false;
		String firstSql = "select importance from irdnt where recipe_id = "+recipe_id;
		try {
			rs = queryStmt(firstSql);
			rs.next();
			int first = rs.getInt(1);
			if(first==0) {
				chk=true;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return chk;
	}
	
	public void setImportance(HashMap<Integer, Integer> map, int recipe_id) {		
		System.out.println("set");
		String sql = "update irdnt set importance = "
				+ "round((importance*(select count(*) from review where recipe_id = ?)+?)/((select count(*) from review where recipe_id = ?)+1),2)"
				+ " where recipe_id = ? and irdnt_sn = ?";
		Iterator<Integer> keys =  map.keySet().iterator();
		List<Integer> irdnt_sn = new ArrayList<Integer>();
		while (keys.hasNext()) {
			irdnt_sn.add(keys.next());		
		}
		try {
			pstmt=updatePstmt(sql);
			for (int i = 0; i < irdnt_sn.size(); i++) {
				pstmt.setInt(1, recipe_id);
				pstmt.setInt(2, map.get(irdnt_sn.get(i)));
				pstmt.setInt(3, recipe_id);
				pstmt.setInt(4, recipe_id);
				pstmt.setInt(5, irdnt_sn.get(i));
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void firstImportance(HashMap<Integer, Integer> map, int recipe_id) {
		System.out.println("first");
		String sql = "update irdnt set importance = ? where recipe_id = ? and irdnt_sn = ?";
		Iterator<Integer> keys =  map.keySet().iterator();
		List<Integer> irdnt_sn = new ArrayList<Integer>();
		while (keys.hasNext()) {
			irdnt_sn.add(keys.next());		
		}
		try {
			pstmt=updatePstmt(sql);
			for (int i = 0; i < irdnt_sn.size(); i++) {
				pstmt.setInt(1, map.get(irdnt_sn.get(i)));
				pstmt.setInt(2, recipe_id);
				pstmt.setInt(3, irdnt_sn.get(i));
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
