package recipe.model;

public class StepDTO {
	private int ROW_NUM;
	private int RECIPE_ID;
	private int COOKING_NO;
	private String COOKING_DC;
	private String STRE_STEP_IMAGE_URL;
	private String STEP_TIP;
	
	public StepDTO() {
	}

	public StepDTO(int rOW_NUM, int rECIPE_ID, int cOOKING_NO, String cOOKING_DC, String sTRE_STEP_IMAGE_URL,
			String sTEP_TIP) {
		super();
		ROW_NUM = rOW_NUM;
		RECIPE_ID = rECIPE_ID;
		COOKING_NO = cOOKING_NO;
		COOKING_DC = cOOKING_DC;
		STRE_STEP_IMAGE_URL = sTRE_STEP_IMAGE_URL;
		STEP_TIP = sTEP_TIP;
	}
	
	public int getROW_NUM() {
		return ROW_NUM;
	}

	public void setROW_NUM(int rOW_NUM) {
		ROW_NUM = rOW_NUM;
	}

	public int getRECIPE_ID() {
		return RECIPE_ID;
	}

	public void setRECIPE_ID(int rECIPE_ID) {
		RECIPE_ID = rECIPE_ID;
	}

	public int getCOOKING_NO() {
		return COOKING_NO;
	}

	public void setCOOKING_NO(int cOOKING_NO) {
		COOKING_NO = cOOKING_NO;
	}

	public String getCOOKING_DC() {
		return COOKING_DC;
	}

	public void setCOOKING_DC(String cOOKING_DC) {
		COOKING_DC = cOOKING_DC;
	}

	public String getSTRE_STEP_IMAGE_URL() {
		return STRE_STEP_IMAGE_URL;
	}

	public void setSTRE_STEP_IMAGE_URL(String sTRE_STEP_IMAGE_URL) {
		STRE_STEP_IMAGE_URL = sTRE_STEP_IMAGE_URL;
	}

	public String getSTEP_TIP() {
		return STEP_TIP;
	}

	public void setSTEP_TIP(String sTEP_TIP) {
		STEP_TIP = sTEP_TIP;
	}


}
