package uit.model;

public class TiLeG {
	private String maG;
	private String moTa;
	private Float tiLe;
	
	public TiLeG() {
		
	}
	
	public TiLeG(String ma, String moTa, Float tiLe) {
		this.maG = ma;
		this.moTa = moTa;
		this.tiLe = tiLe;
	}
	
	public void setMaG(String ma) {
		this.maG = ma;
	}
	
	public String getMaG() {
		return maG;
	}
	
	public void setMoTa(String mota) {
		this.moTa = mota;
	}
	
	public String getMoTa() {
		return moTa;
	}
	
	public void setTiLe(Float tiLe) {
		this.tiLe = tiLe;
	}
	
	public Float getTiLe() {
		return tiLe;
	}
}
