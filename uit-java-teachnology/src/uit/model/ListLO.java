package uit.model;

public class ListLO {
	private String maLO;
	private String moTa;
	
	public ListLO() {
		
	}
	
	public ListLO(String maLo, String moTa) {
		this.maLO = maLo;
		this.moTa = moTa;
	}
	
	public String getMaLO() {
		return maLO;
	}
	
	public void setMaLO(String ma) {
		this.maLO = ma;
	}
	
	public String getMoTa() {
		return moTa;
	}
	
	public void setMoTa(String mota) {
		this.moTa = mota;
	}
}
