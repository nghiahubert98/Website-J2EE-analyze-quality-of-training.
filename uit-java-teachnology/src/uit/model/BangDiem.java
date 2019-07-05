package uit.model;

public class BangDiem {
	private String tenMon;
	private float diemQT;
	private float diemGK;
	private float diemCK;
	
	public BangDiem() {
		
	}
	
	public BangDiem(String ten, float diemQT, float diemGK, float diemCK) {
		this.tenMon = ten;
		this.diemQT = diemQT;
		this.diemGK = diemGK;
		this.diemCK = diemCK;
	}
	
	public void setTenMon(String ten) {
		this.tenMon = ten;
	}
	
	public String getTenMon() {
		return tenMon;
	}
	
	public void setDiemQt(float diemQT) {
		this.diemQT = diemQT;
	}
	
	public float getDiemQt() {
		return diemQT;
	}
	
	public void setDiemGk(float diemGk) {
		this.diemGK = diemGk;
	}
	
	public float getDiemGk() {
		return diemGK;
	}
	
	public void setDiemCk(float diemCK) {
		this.diemCK = diemCK;
	}
	
	public float getDiemCk() {
		return diemCK;
	}
}
