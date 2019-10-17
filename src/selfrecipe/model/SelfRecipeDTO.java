package selfrecipe.model;

import java.util.Date;

public class SelfRecipeDTO {
	private int recipe_id;
	private String user_id, self_views;
	private Date self_date;
	
	public SelfRecipeDTO() {
	}
	
	public SelfRecipeDTO(int recipe_id, String user_id, String self_views, Date self_date) {
		this.recipe_id = recipe_id;
		this.user_id = user_id;
		this.self_views = self_views;
		this.self_date = self_date;
	}

	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSelf_views() {
		return self_views;
	}

	public void setSelf_views(String self_views) {
		this.self_views = self_views;
	}

	public Date getSelf_date() {
		return self_date;
	}

	public void setSelf_date(Date self_date) {
		this.self_date = self_date;
	}

	
}// end class


