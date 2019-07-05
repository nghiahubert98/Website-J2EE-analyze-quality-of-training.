package uit.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import uit.utils.MessageSessionUtil;

public class HocKy {
	private int id;
	private String loai;
	private int namBatDau;
	private int namKetThuc;
	private String name;
    
    public HocKy(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    
	public HocKy() {}
	
	public HocKy(String type, int start, int end) {
		this.loai = type;
		this.namBatDau = start;
		this.namKetThuc = end;
	}
	
	public HocKy(int _id, String type, int start, int end) {
		this.id = _id;
		this.loai = type;
		this.namBatDau = start;
		this.namKetThuc = end;
	}
	
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getNamBatDau() {
        return namBatDau;
    }

    public void setNamBatDau(int namBatDau) {
        this.namBatDau = namBatDau;
    }

    public int getNamKetThuc() {
        return namKetThuc;
    }

    public void setNamKetThuc(int namKetThuc) {
        this.namKetThuc = namKetThuc;
    }

    public Boolean validate(final HttpServletRequest req) {

        if (this.getLoai() != null && !this.getLoai().isEmpty()
          && this.getNamBatDau() >= 0 && this.getNamKetThuc() >= 0) {
            
            return true;
            
        } else {
            // Add error.
            Map<String, String> error = new HashMap<>();
            
            if (this.getLoai() == null || this.getLoai().isEmpty()) {
                error.put("loai", "Loại học kỳ không được để trống");
            }
            
            if (this.getNamBatDau() < 0) {
                error.put("namBatDau", "Năm bắt đầu không hợp lệ");
            }
            
            if (this.getNamKetThuc() < 0) {
                error.put("namKetThuc", "Năm kết thúc không hợp lệ");
            }
            
            MessageSessionUtil.createErrorMsg(req, error, this);
            
            return false;
        }
    }
}
