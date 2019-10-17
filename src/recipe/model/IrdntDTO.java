
package recipe.model;

public class IrdntDTO {
	private int ROW_NUM;
	private int RECIPE_ID;
	private int IRDNT_SN;
	private String IRDNT_NM;
	private String IRDNT_CPCTY;
	private String IRDNT_TY_CODE;
	private String IRDNT_TY_NM, IMPORTANCE;
	
	public IrdntDTO() {
	}
	
	public IrdntDTO(int rOW_NUM, int rECIPE_ID, int iRDNT_SN, String iRDNT_NM, String iRDNT_CPCTY, String iRDNT_TY_CODE,
			String iRDNT_TY_NM, String iMPORTANCE) {
		super();
		ROW_NUM = rOW_NUM;
		RECIPE_ID = rECIPE_ID;
		IRDNT_SN = iRDNT_SN;
		IRDNT_NM = iRDNT_NM;
		IRDNT_CPCTY = iRDNT_CPCTY;
		IRDNT_TY_CODE = iRDNT_TY_CODE;
		IRDNT_TY_NM = iRDNT_TY_NM;
		IMPORTANCE = iMPORTANCE;
	}

	public String getIMPORTANCE() {
		return IMPORTANCE;
	}

	public void setIMPORTANCE(String iMPORTANCE) {
		IMPORTANCE = iMPORTANCE;
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
	public int getIRDNT_SN() {
		return IRDNT_SN;
	}
	public void setIRDNT_SN(int iRDNT_SN) {
		IRDNT_SN = iRDNT_SN;
	}
	public String getIRDNT_NM() {
		return IRDNT_NM;
	}
	public void setIRDNT_NM(String iRDNT_NM) {
		IRDNT_NM = iRDNT_NM;
	}
	public String getIRDNT_CPCTY() {
		return IRDNT_CPCTY;
	}
	public void setIRDNT_CPCTY(String iRDNT_CPCTY) {
		IRDNT_CPCTY = iRDNT_CPCTY;
	}
	public String getIRDNT_TY_CODE() {
		return IRDNT_TY_CODE;
	}
	public void setIRDNT_TY_CODE(String string) {
		IRDNT_TY_CODE = string;
	}
	public String getIRDNT_TY_NM() {
		return IRDNT_TY_NM;
	}
	public void setIRDNT_TY_NM(String iRDNT_TY_NM) {
		IRDNT_TY_NM = iRDNT_TY_NM;
	}
	
	

}
