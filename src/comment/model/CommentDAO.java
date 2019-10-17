package comment.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import recipe.CommonDAO;

public class CommentDAO extends CommonDAO {
	private ResultSet rs;
	private PreparedStatement pstmt;
	
	public CommentDAO() {
		super();
	}

	
	
	public List<CommentDTO> listMethod(String com_board, int key, String order, int rownum){
		List<CommentDTO> aList = new ArrayList<CommentDTO>();
		String sql = "select user_id,com_time,com_content,rating,user_icon,user_nickname from (select c.user_id as user_id,com_time,com_content,rating,user_icon,user_nickname, rownum "
				+ "from recipe_comment c, user_table u "
				+ "where com_board='"+com_board+"'";
				sql += " and recipe_id='"+key+"' ";
		sql += "and c.user_id = u.user_id order by ";
		if(order.equalsIgnoreCase("com_time")) {
			sql+="com_time desc)";
		}else if(order.equalsIgnoreCase("rating")) {
			sql+="rating desc)";
		}
		sql+=" where rownum <= "+rownum;
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setUser_id(rs.getString(1));
				String com_time = rs.getString(2);
				com_time = showTime(com_time);
				dto.setCom_time(com_time);
				dto.setCom_content(rs.getString(3));
				dto.setRating(rs.getInt(4));
				dto.setUser_icon(rs.getString(5));
				dto.setUser_nickname(rs.getString(6));
				aList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return aList;
	}//end listmethod
	
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
	
	public void insertCom(CommentDTO dto, int key) {
		String com_board = dto.getCom_board();
		String column = "";
		if(com_board.equalsIgnoreCase("primary")||com_board.equalsIgnoreCase("selfrecipe")) {
			column = "recipe_id";
		}
		if(com_board.equalsIgnoreCase("review")) {
			column = "review_num";
		}	
		String sql = "insert into recipe_comment(com_num,com_content,user_id,com_board,rating,"+column+") "
				+ "values (com_num_seq.nextval,?,?,?,?,?)";
		try {
			pstmt = updatePstmt(sql);
			pstmt.setString(1, dto.getCom_content());
			pstmt.setString(2, dto.getUser_id());
			pstmt.setString(3, com_board);
			pstmt.setInt(4, dto.getRating());
			pstmt.setInt(5, key);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getTotal(int recipe_Id) {
		String sql = "select count(*) from recipe_comment where recipe_id = "+recipe_Id;
		String total="";
		try {
			rs=queryStmt(sql);
			rs.next();
			total = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}
}
