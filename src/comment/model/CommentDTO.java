
package comment.model;

public class CommentDTO {
	@Override
	public String toString() {
		return "CommentDTO [com_num=" + com_num + ", recipe_id=" + recipe_id + ", review_num=" + review_num
				+ ", rating=" + rating + ", com_content=" + com_content + ", user_id=" + user_id + ", com_board="
				+ com_board + ", com_time=" + com_time + ", user_icon=" + user_icon + "]";
	}
	private int com_num,recipe_id,review_num,rating;
	private String com_content,user_id,com_board,com_time,user_icon,user_nickname;
	
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getUser_icon() {
		return user_icon;
	}
	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}
	public int getCom_num() {
		return com_num;
	}
	public void setCom_num(int com_num) {
		this.com_num = com_num;
	}
	public int getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getCom_time() {
		return com_time;
	}
	public void setCom_time(String com_time) {
		this.com_time = com_time;
	}
	public String getCom_content() {
		return com_content;
	}
	public void setCom_content(String com_content) {
		this.com_content = com_content;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCom_board() {
		return com_board;
	}
	public void setCom_board(String com_board) {
		this.com_board = com_board;
	}
	
	
	 
}
