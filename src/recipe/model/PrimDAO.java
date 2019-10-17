package recipe.model;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import recipe.CommonDAO;

public class PrimDAO extends CommonDAO {
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool)이다.
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public PrimDAO() {
		super();
	}
	
	public List<String> getNation_nms() {
		List<String> nms = new ArrayList<String>();
		String sql = "select distinct(nation_nm) from primary where recipe_type='p'";
		
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				nms.add(rs.getString(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nms;
	}
	
	public List<PrimDTO> listView(){
		List<PrimDTO> aList = new ArrayList<PrimDTO>();
		String sql = "select recipe_nm_ko, img_url, prim_views, rating, recipe_id,nation_nm from primary where recipe_type = 'p'";
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				PrimDTO dto = new PrimDTO();
				dto.setRECIPE_NM_KO(rs.getString(1));
				String url = rs.getString(2);
				dto.setIMG_URL(url);
				dto.setPRIM_VIEWS(rs.getInt(3));
				dto.setRATING(rs.getString(4));
				dto.setRECIPE_ID(rs.getInt(5));
				dto.setNATION_NM(rs.getString(6));
				aList.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aList;
	}
	
	public void primViewsCnt(int recipe_id) {
		String sql = "update primary set prim_views = prim_views + 1 where recipe_id ="+recipe_id;
		
		try {
			pstmt = updatePstmt(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void primRating(int recipe_id) {
		String sql = "update primary set rating = round("
				+ "("
				+ "nvl((select sum(rating) from recipe_comment where recipe_id =?),0)"
				+ "+"
				+ "nvl("
				+ "(select sum(review_rate) from review where recipe_id = ?)"
				+ ",0)"
				+ ")"
				+ "/nvl("
				+ "(select count(*) from "
				+ "(select recipe_id from review where recipe_id=? "
				+ "union all select recipe_id from recipe_comment where recipe_id=?)"
				+ ")"
				+ ","
				+ "decode((select count(*) from recipe_comment where recipe_id =?),0,1,(select count(*) from recipe_comment where recipe_id =?)))"
				+ ",2) " + 
				"where recipe_id = ?";
		try {
			pstmt = updatePstmt(sql);
			pstmt.setInt(1, recipe_id);
			pstmt.setInt(2, recipe_id);
			pstmt.setInt(3, recipe_id);
			pstmt.setInt(4, recipe_id);
			pstmt.setInt(5, recipe_id);
			pstmt.setInt(6, recipe_id);
			pstmt.setInt(7, recipe_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PrimDTO listView(int recipe_id){
		PrimDTO dto = new PrimDTO();
		String sql = "select recipe_nm_ko, img_url, prim_views, rating, nation_nm, ty_nm, cooking_time, calorie, level_nm,sumry from primary where recipe_type = 'p' and recipe_id = "+recipe_id;
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				dto.setRECIPE_NM_KO(rs.getString(1));
				String url = rs.getString(2);
				dto.setIMG_URL(url);
				dto.setPRIM_VIEWS(rs.getInt(3));
				dto.setRATING(rs.getString(4));
				dto.setNATION_NM(rs.getString(5));
				dto.setTY_NM(rs.getString(6));
				dto.setCOOKING_TIME(rs.getString(7).replace("분", ""));
				dto.setCALORIE(rs.getString(8).replace("Kcal", ""));
				dto.setLEVEL_NM(rs.getString(9));
				dto.setSUMRY(rs.getString(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	public List<PrimDTO> sortView(String column, String order, String nation_nm, String recipe_nm_ko, String searchType){
		List<PrimDTO> aList = new ArrayList<PrimDTO>();
		String sql = "select recipe_nm_ko, img_url, prim_views, rating, recipe_id, nation_nm from primary where recipe_type = 'p' ";
		if(nation_nm!= null && !nation_nm.isEmpty()) {
			sql += "and nation_nm = '"+nation_nm+"' ";
		}
		if(column!=null && !column.isEmpty() && order!=null && !order.isEmpty()) {
			sql += "order by "+column+" "+order;
		}
		if(recipe_nm_ko!= null && !recipe_nm_ko.isEmpty()) {
			sql = "select recipe_nm_ko, img_url, prim_views, rating, recipe_id, nation_nm from primary where recipe_type = 'p' ";
			switch(searchType) {
			case "both": sql += "and (recipe_nm_ko like '%'||'"+recipe_nm_ko+"'||'%' or"
					+ " recipe_id in (select recipe_id from irdnt where irdnt_nm like '%'||'"+recipe_nm_ko+"'||'%'))"; break;
			case "recipe_nm_ko": sql += "and recipe_nm_ko like '%'||'"+recipe_nm_ko+"'||'%' "; break;
			case "irdnt_nm": sql += "and recipe_id in (select recipe_id from irdnt where irdnt_nm like '%'||'"+recipe_nm_ko+"'||'%') "; break;
			}
		}
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				PrimDTO dto = new PrimDTO();
				dto.setRECIPE_NM_KO(rs.getString(1));
				String url = rs.getString(2);
				dto.setIMG_URL(url);
				dto.setPRIM_VIEWS(rs.getInt(3));
				dto.setRATING(rs.getString(4));
				dto.setRECIPE_ID(rs.getInt(5));
				dto.setNATION_NM(rs.getString(6));
				aList.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aList;
	}

	public List<PrimDTO> searchRecipe(String irdnt_nm) {
		List<PrimDTO> aList = new ArrayList<PrimDTO>();
		String sql = "select * from primary where recipe_type = 'p' and recipe_id in(";
		JsonParser pas = new JsonParser();
		JsonArray jarr = (JsonArray) pas.parse(irdnt_nm);
		for (int i = 0; i < jarr.size(); i++) {
			if (i == jarr.size() - 1) {
				sql += "select recipe_id from irdnt where recipe_type = 'p' and irdnt_nm = '" + jarr.get(i).toString().replace("\"", "") + "') order by rating desc";
				break;
			}
			sql += "select recipe_id from irdnt where recipe_type = 'p' and irdnt_nm = '" + jarr.get(i).toString().replace("\"", "") + "' intersect ";
		}

		try {
			rs = queryStmt(sql);
			if (!rs.next()) {
				sql = "select * from primary where recipe_type = 'p' and recipe_id in " + "(select recipe_id from irdnt where irdnt_nm in (";
				for (int i = 0; i < jarr.size(); i++) {
					if (i == jarr.size() - 1) {
						sql += "'" + jarr.get(i).toString().replace("\"", "") + "') and irdnt_ty_nm = '주재료') order by rating desc";
						break;
					}
					sql += "'" + jarr.get(i).toString().replace("\"", "") + "',";
				}
				rs = queryStmt(sql);
			}
			rs.previous();
			while (rs.next()) {
				PrimDTO dto = new PrimDTO();
				dto.setRECIPE_ID(rs.getInt("RECIPE_ID"));
				dto.setRECIPE_NM_KO(rs.getString("RECIPE_NM_KO"));
				dto.setIMG_URL(rs.getString("IMG_URL"));
				aList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}
}// end searchView()