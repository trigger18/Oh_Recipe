package selfrecipe.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import recipe.model.IrdntDTO;
import recipe.model.IrdntTYDTO;
import recipe.model.PrimDTO;
import recipe.model.StepDTO;

public class SelfRecipeDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

	private SelfRecipeDAO() {
	}

	private static SelfRecipeDAO dao = new SelfRecipeDAO();

	public static SelfRecipeDAO getInstance() {
		return dao;
	}

	public Connection init() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");

		String url = "jdbc:oracle:thin://@192.168.30.72:1521:xe";
		String user = "hong";
		String password = "1234";
		return DriverManager.getConnection(url, user, password);
	}// end init()

	public void exit() throws SQLException {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}// end exit()

	
	// ------------------------------------------------------------------- 정렬 시작
	public List<HashMap<String, String>> sortView(String column, String order, String recipe_nm_ko, String searchType){
		List<HashMap<String, String>> aList = new ArrayList<HashMap<String,String>>();
		String sql = "select recipe_nm_ko, img_url, rating, p.recipe_id, self_views, self_date, u.user_id,user_nickname, nation_nm from primary p, selfrecipe s, user_table u where p.recipe_id=s.recipe_id and u.user_id=s.user_id and recipe_type = 's' ";
		if(column!=null && !column.isEmpty() && order!=null && !order.isEmpty()) {
			sql += "order by "+column+" "+order;
		}
		if(recipe_nm_ko!= null && !recipe_nm_ko.isEmpty()) {
			sql = "select recipe_nm_ko, img_url, rating, p.recipe_id, self_views, self_date, u.user_id,user_nickname from primary p, selfrecipe s, user_table u where p.recipe_id=s.recipe_id and u.user_id=s.user_id and recipe_type = 's' ";
			switch(searchType) {
			case "both": sql += "and (recipe_nm_ko like '%'||'"+recipe_nm_ko+"'||'%' or"
					+ " p.recipe_id in (select recipe_id from irdnt where irdnt_nm like '%'||'"+recipe_nm_ko+"'||'%'))"; break;
			case "recipe_nm_ko": sql += "and recipe_nm_ko like '%'||'"+recipe_nm_ko+"'||'%' "; break;
			case "irdnt_nm": sql += "and p.recipe_id in (select recipe_id from irdnt where irdnt_nm like '%'||'"+recipe_nm_ko+"'||'%') "; break;
			}
		}
		try {
			conn = init();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("recipe_id", rs.getString("recipe_id"));
				map.put("user_id", rs.getString("user_id"));
				map.put("self_date", showTime(rs.getString("self_date")));
				map.put("recipe_nm_ko", rs.getString("recipe_nm_ko"));
				map.put("img_url", rs.getString("img_url"));
				map.put("rating", rs.getString("rating"));
				map.put("self_views", rs.getString("self_views"));
				map.put("user_nickname", rs.getString("user_nickname"));
				aList.add(map);
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aList;
	}
	// ------------------------------------------------------------------- 정렬 끝	
	
	public String showTime(String com_time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		com_time = com_time.replace(".0", "");
		try {
			Date org = sdf.parse(com_time);
			long diff = System.currentTimeMillis() - org.getTime();
			long diffYear = diff/(24*60*60*1000)/30/12;
	    	long diffMonth = diff/(24*60*60*1000)/30;
	    	long diffDay = diff/(24*60*60*1000);
	    	long diffHour = diff/(60*60*1000);
	    	long diffMin = diff/(60*1000);
	    	long diffSec = diff/(1000);
	    	if(diffSec<60) {
	    		com_time = diffSec+"초전";
	    	}else if(diffMin<60) {
	    		com_time = diffMin+"분전";
	    	}else if(diffHour<24) {
	    		com_time = diffHour+"시간전";
	    	}else if(diffDay<=30) {
	    		com_time = diffDay+"일전";
	    	}else if(diffMonth<12) {
	    		com_time = diffMonth+"달전";
	    	}else if(diffMonth>=12) {
	    		com_time = diffYear+"년전";
	    	}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return com_time;
	}
	
	// -------------------------------------------------------------------리스트 출력 메소드 시작

	public List<HashMap<String, String>> primListMethod() {
		List<HashMap<String, String>> prList = new ArrayList<HashMap<String,String>>();
		try {
			conn = init();
			String sql = "select recipe_nm_ko, sumry, rating, self_views, img_url, ty_nm,"
					+ " cooking_time, calorie, level_nm, nation_nm,"
					+ " s.recipe_id, u.user_id,user_nickname, self_date "
					+ "from selfrecipe s, primary p, user_table u "
					+ "where S.RECIPE_ID=P.RECIPE_ID and u.user_id=s.user_id and recipe_type = 's' ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("recipe_id", rs.getString("recipe_id"));
				map.put("user_id", rs.getString("user_id"));
				map.put("self_date", showTime(rs.getString("self_date")));
				map.put("recipe_nm_ko", rs.getString("recipe_nm_ko"));
				map.put("sumry", rs.getString("sumry"));
				map.put("img_url", rs.getString("img_url"));
				map.put("ty_nm", rs.getString("ty_nm"));
				map.put("cooking_time", rs.getString("cooking_time"));
				map.put("calorie", rs.getString("calorie"));
				map.put("level_nm", rs.getString("level_nm"));
				map.put("nation_nm", rs.getString("nation_nm"));
				map.put("rating", rs.getString("rating"));
				map.put("self_views", rs.getString("self_views"));
				map.put("user_nickname", rs.getString("user_nickname"));
				prList.add(map);
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
		return prList;
	}// end primListMethod()

	public List<IrdntDTO> irdntListMethod() {
		List<IrdntDTO> irList = new ArrayList<IrdntDTO>();
		IrdntDTO dto = new IrdntDTO();
		try {
			conn = init();
			String sql = "select irdnt_sn, importance, p.recipe_id, irdnt_nm ";
			sql	+= "from irdnt i, primary p where i.recipe_id = p.recipe_id and recipe_type = 's'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setIRDNT_SN(rs.getInt("irdnt_sn"));
				dto.setRECIPE_ID(rs.getInt("recipe_id"));
				dto.setIMPORTANCE(rs.getString("importance"));
				dto.setIRDNT_NM(rs.getString("irdnt_nm"));
				irList.add(dto);
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
		return irList;
	}// end irdntListMethod()

	public List<StepDTO> stepListMethod() {
		List<StepDTO> stList = new ArrayList<StepDTO>();
		StepDTO stdto = new StepDTO();
		try {
			conn = init();
			String sql = "select cooking_no, recipe_id  from step where  recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stdto.getRECIPE_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				stdto.setCOOKING_NO(rs.getInt("cooking_no"));
				stdto.setRECIPE_ID(rs.getInt("recipe_id"));
				stList.add(stdto);
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
		return stList;
	}// end stepListMethod()
	// -------------------------------------------------------------------리스트 출력 끝
	
	
	// -------------------------------------------------------------------뷰 시작
	
	public HashMap<String, String> primViewMethod(String recipe_id) {
		 HashMap<String, String> pvmap = new HashMap<String,String>();
		try {
			conn = init();
			String sql = "select p.recipe_id, recipe_nm_ko, sumry, img_url, ty_nm, cooking_time, calorie, level_nm, nation_nm, u.user_id, user_nickname, self_date, self_views ";
			sql += "from primary p, selfrecipe s, user_table u where p.recipe_id = s.recipe_id and u.user_id = s.user_id and recipe_type = 's' and p.recipe_id = ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(recipe_id));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pvmap.put("recipe_id", rs.getString("recipe_id"));
				pvmap.put("recipe_nm_ko", rs.getString("recipe_nm_ko"));
				pvmap.put("sumry", rs.getString("sumry"));
				pvmap.put("img_url", rs.getString("img_url"));
				pvmap.put("ty_nm", rs.getString("ty_nm"));
				pvmap.put("cooking_time", rs.getString("cooking_time"));
				pvmap.put("calorie", rs.getString("calorie"));
				pvmap.put("level_nm", rs.getString("level_nm"));
				pvmap.put("nation_nm", rs.getString("nation_nm"));
				pvmap.put("user_id", rs.getString("user_id"));
				String time = rs.getString("self_date");
				pvmap.put("self_date", showTime(time));
				pvmap.put("self_views", rs.getString("self_views"));
				pvmap.put("user_nickname", rs.getString("user_nickname"));
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

		return pvmap;
	}// end primViewMethod()
	
	
	
	public List<IrdntDTO> irdntViewMethod(String recipe_id) {
		List<IrdntDTO> irList = new ArrayList<IrdntDTO>();
		try {
			conn = init();
			String sql = "select irdnt_sn, importance, recipe_id, irdnt_nm, irdnt_ty_nm from irdnt where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(recipe_id));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				IrdntDTO irdto = new IrdntDTO();
				irdto.setIRDNT_SN(rs.getInt("irdnt_sn"));
				irdto.setRECIPE_ID(rs.getInt("recipe_id"));
				irdto.setIMPORTANCE(rs.getString("importance"));
				irdto.setIRDNT_NM(rs.getString("irdnt_nm"));
				irdto.setIRDNT_TY_NM(rs.getString("irdnt_ty_nm"));
				irList.add(irdto);
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
		return irList;
	}// end irdntViewMethod()

	public List<StepDTO> stepViewMethod(String recipe_id) {
		List<StepDTO> stList = new ArrayList<StepDTO>();
		try {
			conn = init();
			String sql = "select cooking_no, recipe_id, cooking_dc from step where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(recipe_id));
			rs = pstmt.executeQuery();
		while (rs.next()) {
			StepDTO stdto = new StepDTO();
			stdto.setCOOKING_NO(rs.getInt("cooking_no"));
			stdto.setCOOKING_DC(rs.getString("cooking_dc"));
			stdto.setRECIPE_ID(rs.getInt("recipe_id"));
			stList.add(stdto);
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
		return stList;
	}// end stepViewMethod()
	
	public List<IrdntTYDTO> irdntTyViewMethod(String recipe_id) {
		List<IrdntTYDTO> irtList = new ArrayList<IrdntTYDTO>();
		try {
			conn = init();
			String sql = "select irdnt_nm from irdnt where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(recipe_id));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				IrdntTYDTO irtdto = new IrdntTYDTO();
				irtdto.setIrdnt_nm(rs.getString("irdnt_nm"));
				irtList.add(irtdto);
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
		return irtList;
	}// end indntTyViewMethod()	
	
	// -------------------------------------------------------------------뷰 끝

	
	// -------------------------------------------------------------------검색 시작
	public PrimDTO primSearchMethod(int recipe_id){
		PrimDTO prdto = new PrimDTO();
		try {
			conn = init();
			String sql = "select recipe_id, recipe_nm_ko, sumry, img_url, ty_nm, cooking_time, calorie, level_nm, nation_nm ";
			sql += "from primary where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prdto.setRECIPE_ID(rs.getInt("recipe_id"));
				prdto.setRECIPE_NM_KO(rs.getString("recipe_nm_ko"));
				prdto.setSUMRY(rs.getString("sumry"));
				prdto.setIMG_URL(rs.getString("img_url"));
				prdto.setTY_NM(rs.getString("ty_nm"));
				prdto.setCOOKING_TIME(rs.getString("cooking_time"));
				prdto.setCALORIE(rs.getString("calorie"));
				prdto.setLEVEL_NM(rs.getString("level_nm"));
				prdto.setNATION_NM(rs.getString("nation_nm"));
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
		return prdto;
	}// end primSearchMethod()
	
	public SelfRecipeDTO selfSearchMethod(int recipe_id){
		SelfRecipeDTO srdto = new SelfRecipeDTO();
		try {
			conn = init();
			String sql = "select recipe_id, user_id, self_date from selfrecipe where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				srdto.setRecipe_id(rs.getInt("recipe_id"));
				srdto.setUser_id(rs.getString("user_id"));
				srdto.setSelf_date(rs.getDate("self_date"));
				srdto.setSelf_views(rs.getString("self_views"));
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
		return srdto;
	}// end selfSearchMethod()
	
	public List<IrdntDTO> irdntSearchMethod(int recipe_id){
		List<IrdntDTO> irList = new ArrayList<IrdntDTO>();
		try {
			conn = init();
			String sql ="select irdnt_sn, importance, recipe_id, irdnt_nm, irdnt_ty_nm from irdnt where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				IrdntDTO irdto = new IrdntDTO();
				irdto.setIRDNT_SN(rs.getInt("irdnt_sn"));
				irdto.setIMPORTANCE(rs.getString("importance"));
				irdto.setIRDNT_NM(rs.getString("irdnt_nm"));
				irdto.setIRDNT_TY_NM(rs.getString("irdnt_ty_nm"));
				irList.add(irdto);
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
		return irList;
	}// end irdntSearchMethod()
	
	public List<StepDTO> steptSearchMethod(int recipe_id){
		List<StepDTO> stList = new ArrayList<StepDTO>();
		StepDTO stdto = new StepDTO();
		try {
			conn = init();
			String sql = "select cooking_no, recipe_id from step where  recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				stdto.setCOOKING_NO(rs.getInt("cooking_no"));
				stdto.setRECIPE_ID(rs.getInt("recipe_id"));
				stList.add(stdto);
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
		return stList;
	}// end steptSearchMethod()
	// -------------------------------------------------------------------검색 끝
	
	
	// -------------------------------------------------------------------글쓰기 시작
	public void primInsertMethod(PrimDTO prdto) {
		try {
			conn = init();
			String sql = "insert into primary (recipe_id, recipe_nm_ko, sumry, img_url, ty_nm, cooking_time, calorie, level_nm, recipe_type, nation_nm) ";
			sql += "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prdto.getRECIPE_ID());
			pstmt.setString(2, prdto.getRECIPE_NM_KO());
			pstmt.setString(3, prdto.getSUMRY());
			pstmt.setString(4, prdto.getIMG_URL());
			pstmt.setString(5, prdto.getTY_NM());
			pstmt.setString(6, prdto.getCOOKING_TIME());
			pstmt.setString(7, prdto.getCALORIE());
			pstmt.setString(8, prdto.getLEVEL_NM());
			pstmt.setString(9, "s");
			pstmt.setString(10, prdto.getNATION_NM());
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
	}// end primInsertMethod()

	public void selfRecipeInsertMethod(SelfRecipeDTO srdto) {
		try {
			conn = init();

			String sql = "insert into selfrecipe (recipe_id, user_id, self_date) ";
			sql += "values(?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, srdto.getRecipe_id());
			pstmt.setString(2, srdto.getUser_id());
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
	}// end selfRecipeInsertMethod()

	public void irdntInsertMethod(List<IrdntDTO> irdList) {
		try {
			conn = init();
			String sql = "insert into irdnt (irdnt_sn, importance, recipe_id, irdnt_nm, irdnt_ty_nm) ";
			sql += "values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < irdList.size(); i++) {
				int irdnt_sn = irdList.get(i).getRECIPE_ID()*10+i;
			pstmt.setInt(1, irdnt_sn);
			String importance = irdList.get(i).getIMPORTANCE();
			int impnum=0;
			switch (importance) {
			case "필수":
				impnum=4;
				break;
			case "상":
				impnum=3;
				break;
			case "중":
				impnum=2;
				break;
			case "하":
				impnum=1;
				break;
			
			}
			pstmt.setInt(2, impnum);
			pstmt.setInt(3, irdList.get(i).getRECIPE_ID());
			pstmt.setString(4, irdList.get(i).getIRDNT_NM());
			pstmt.setString(5, irdList.get(i).getIRDNT_TY_NM());
			pstmt.executeUpdate();
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
	}// end irdntInsertMethod()

	public void stepInsertMethod(List<StepDTO> stdto) {
		try {
			conn = init();
			String sql = "insert into step (recipe_id, cooking_no, cooking_dc) ";
			sql += "values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < stdto.size(); i++) {
				pstmt.setInt(1, stdto.get(i).getRECIPE_ID());
				pstmt.setInt(2, stdto.get(i).getCOOKING_NO());
				pstmt.setString(3, stdto.get(i).getCOOKING_DC());
				pstmt.executeUpdate();
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
	}// end stepInsertMethod()

	// -------------------------------------------------------------------글쓰기 끝
	
	// -------------------------------------------------------------------레시피 아이디 큰값시작
	public int recipeIdMax () {	
		int riMax = 0;
		try {
			conn = init();
			String sql = "select max(recipe_id) from primary";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			riMax = rs.getInt(1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return riMax;		
	}
	// -------------------------------------------------------------------레시피 아이디 큰값끝

	
	// -------------------------------------------------------------------수정하기 시작
	public void primUpdateMethod(PrimDTO dto) {
		try {
			conn = init();
			String sql = "update primary set recipe_nm_ko = ?, sumry = ?, img_url = ?, ty_nm = ?, cooking_time = ?, calorie = ?, level_nm = ?, nation_nm = ? ";
			sql += "where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getRECIPE_NM_KO());
			pstmt.setString(2, dto.getSUMRY());
			pstmt.setString(3, dto.getIMG_URL());
			pstmt.setString(4, dto.getTY_NM());
			pstmt.setString(4, dto.getCOOKING_TIME());
			pstmt.setString(6, dto.getCALORIE());
			pstmt.setString(7, dto.getLEVEL_NM());
			pstmt.setInt(8, dto.getRECIPE_ID());
			pstmt.setString(9, dto.getNATION_NM());
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
	}// end primUpdateMethod()

	public void selfRecipeUpdateMethod(SelfRecipeDTO dto) {
		try {
			conn = init();
			String sql = "update primary set self_date = sysdate where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getRecipe_id());
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
	}// end selfRecipeUpdateMethod()
	
	public void irdntUpdateMethod(IrdntDTO dto) {
		try {
			conn = init();
			String sql = "update irdnt set irdnt_sn = ?, importance = ?, irdnt_nm = ? where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getIRDNT_SN());
			pstmt.setString(2, dto.getIMPORTANCE());
			pstmt.setString(3, dto.getIRDNT_NM());
			pstmt.setInt(4, dto.getRECIPE_ID());
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
	}// end irdntUpdateMethod()
	
	public void stepUpdateMethod(List<StepDTO> stList) {
		try {
			conn = init();
			String sql = "update step set cooking_no = ?, cooking_dc = ? where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < stList.size(); i++) {
				pstmt.setInt(1, stList.get(i).getCOOKING_NO());
				pstmt.setString(2, stList.get(i).getCOOKING_DC());
				pstmt.setInt(3, stList.get(i).getRECIPE_ID());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end stepUpdateMethod()
	// -------------------------------------------------------------------수정하기 끝

	
	// -------------------------------------------------------------------삭제하기 시작
	public void primDeleteMethod(int recipe_id) {
		try {
			conn = init();
			String sql = "delete from primary where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
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
	}// end primDeleteMethod()

	public void selfRecipeDeleteMethod(int recipe_id) {
		try {
			conn = init();
			String sql = "delete from selfrecipe where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
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
	}// end selfRecipeDeleteMethod()
	
	public void irdntDeleteMethod(int recipe_id) {
		try {
			conn = init();
			String sql = "delete from irdnt where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
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
	}// end irdntDeleteMethod()
	
	public void stepDeleteMethod(int recipe_id) {
		try {
			conn = init();
			String sql = "delete from step where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
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
	}// end stepDeleteMethod()
	
	// -------------------------------------------------------------------삭제하기 끝
	
	
	// -------------------------------------------------------------------파일 시작
	public String fileMethod(int recipe_id) {
		String fileName = null;
		try {
			conn = init();
			String sql = "select img_url from primary where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fileName = rs.getString("img_url");
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
		return fileName;
	}// end fileMethod()
	// -------------------------------------------------------------------파일 끝
	
	
	// -------------------------------------------------------------------조회수 증가 시작
	public void readCountMethod(int recipe_id) {
		try {
			conn = init();
			String sql = "update selfrecipe set self_views = self_views + 1 where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}// end readCountMethod()
	// -------------------------------------------------------------------조회수 증가 끝

	

	// -------------------------------------------------------------------
	public int rowTotalCount() {
		int cnt = 0;
		try {
			conn = init();
			String sql = "select count(*) from selfrecipe";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(cnt);
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
	}// end rowTotalCount()
	// -------------------------------------------------------------------


}// end class
