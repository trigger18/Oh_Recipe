
package recipe.model;

public class IrdntTYDTO {
	private String irdnt_nm;
	private String ty_nm;
	
	public IrdntTYDTO() {
	}

	public IrdntTYDTO(String irdnt_nm, String ty_nm) {
		this.irdnt_nm = irdnt_nm;
		this.ty_nm = ty_nm;
	}

	public String getIrdnt_nm() {
		return irdnt_nm;
	}

	public void setIrdnt_nm(String irdnt_nm) {
		this.irdnt_nm = irdnt_nm;
	}

	public String getTy_nm() {
		return ty_nm;
	}

	public void setTy_nm(String ty_nm) {
		this.ty_nm = ty_nm;
	}
	
}
