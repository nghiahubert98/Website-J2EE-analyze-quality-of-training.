package uit.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import uit.utils.MessageSessionUtil;

public class ChuanLO {
    private int id;
    private String maLO;
    private String mota;
    
    public ChuanLO() {};
    
    public ChuanLO(int id, String maLO, String mota) {
        this.id = id;
        this.maLO = maLO;
        this.mota = mota;
    }
    
    public ChuanLO(String maLO, String mota) {
        this.maLO = maLO;
        this.mota = mota;
    }
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getMaLO() {
        return maLO;
    }
    
    public void setMaLO(String maLO) {
        this.maLO = maLO;
    }
    
    public String getMota() {
        return mota;
    }
    
    public void setMota(String mota) {
        this.mota = mota;
    }
    
    public Boolean validate(final HttpServletRequest req) {

        if (this.getMaLO() != null && !this.getMaLO().isEmpty() 
          && this.getMota() != null && !this.getMota().isEmpty()) {
            
            return true;
            
        } else {
            // Add error.
            Map<String, String> error = new HashMap<>();
            
            if (this.getMota() == null || this.getMota().isEmpty()) {
                error.put("description", "Mô tả LO không được để trống");
            }
            if (this.getMaLO() == null || this.getMaLO().isEmpty()) {
                error.put("code", "Mã LO không được để trống");
            }
            
            MessageSessionUtil.createErrorMsg(req, error, this);
            
            return false;
        }
    }
}

