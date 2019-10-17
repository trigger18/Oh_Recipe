package recipe.model;

public class PrimDTO {
	private int ROW_NUM, PRIM_VIEWS;
	private int RECIPE_ID;
	private String RECIPE_NM_KO, RATING;
	private String SUMRY;
	private String NATION_CODE;
	private String NATION_NM;
	private String TY_NM;
	private String COOKING_TIME;
	private String CALORIE;
	private String QNT;
	private String LEVEL_NM;
	private String IRDNT_CODE;
	private String PC_NM;
	private String IMG_URL;
	private String DET_URL;
	
	public PrimDTO() {
	}

	public PrimDTO(int rOW_NUM, int pRIM_VIEWS, int rECIPE_ID, String rECIPE_NM_KO, String rATING, String sUMRY,
			String nATION_CODE, String nATION_NM, String tY_NM, String cOOKING_TIME, String cALORIE,
			String qNT, String lEVEL_NM, String iRDNT_CODE, String pC_NM, String iMG_URL, String dET_URL) {
		super();
		ROW_NUM = rOW_NUM;
		PRIM_VIEWS = pRIM_VIEWS;
		RECIPE_ID = rECIPE_ID;
		RECIPE_NM_KO = rECIPE_NM_KO;
		RATING = rATING;
		SUMRY = sUMRY;
		NATION_CODE = nATION_CODE;
		NATION_NM = nATION_NM;
		TY_NM = tY_NM;
		COOKING_TIME = cOOKING_TIME;
		CALORIE = cALORIE;
		QNT = qNT;
		LEVEL_NM = lEVEL_NM;
		IRDNT_CODE = iRDNT_CODE;
		PC_NM = pC_NM;
		IMG_URL = iMG_URL;
		DET_URL = dET_URL;
	}

	public int getPRIM_VIEWS() {
		return PRIM_VIEWS;
	}



	public void setPRIM_VIEWS(int pRIM_VIEWS) {
		PRIM_VIEWS = pRIM_VIEWS;
	}



	public String getRATING() {
		return RATING;
	}



	public void setRATING(String rATING) {
		RATING = rATING;
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

	public String getRECIPE_NM_KO() {
		return RECIPE_NM_KO;
	}

	public void setRECIPE_NM_KO(String rECIPE_NM_KO) {
		RECIPE_NM_KO = rECIPE_NM_KO;
	}

	public String getSUMRY() {
		return SUMRY;
	}

	public void setSUMRY(String sUMRY) {
		SUMRY = sUMRY;
	}

	public String getNATION_CODE() {
		return NATION_CODE;
	}

	public void setNATION_CODE(String nATION_CODE) {
		NATION_CODE = nATION_CODE;
	}

	public String getNATION_NM() {
		return NATION_NM;
	}

	public void setNATION_NM(String nATION_NM) {
		NATION_NM = nATION_NM;
	}

	public String getTY_NM() {
		return TY_NM;
	}

	public void setTY_NM(String tY_NM) {
		TY_NM = tY_NM;
	}

	public String getCOOKING_TIME() {
		return COOKING_TIME;
	}

	public void setCOOKING_TIME(String cOOKING_TIME) {
		COOKING_TIME = cOOKING_TIME;
	}

	public String getCALORIE() {
		return CALORIE;
	}

	public void setCALORIE(String cALORIE) {
		CALORIE = cALORIE;
	}

	public String getQNT() {
		return QNT;
	}

	public void setQNT(String qNT) {
		QNT = qNT;
	}

	public String getLEVEL_NM() {
		return LEVEL_NM;
	}

	public void setLEVEL_NM(String lEVEL_NM) {
		LEVEL_NM = lEVEL_NM;
	}

	public String getIRDNT_CODE() {
		return IRDNT_CODE;
	}

	public void setIRDNT_CODE(String iRDNT_CODE) {
		IRDNT_CODE = iRDNT_CODE;
	}

	public String getPC_NM() {
		return PC_NM;
	}

	public void setPC_NM(String pC_NM) {
		PC_NM = pC_NM;
	}

	public String getIMG_URL() {
		return IMG_URL;
	}

	public void setIMG_URL(String iMG_URL) {
		IMG_URL = iMG_URL;
	}

	public String getDET_URL() {
		return DET_URL;
	}

	public void setDET_URL(String dET_URL) {
		DET_URL = dET_URL;
	}

}
