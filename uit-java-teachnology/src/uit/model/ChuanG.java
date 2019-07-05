package uit.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import uit.utils.MessageSessionUtil;

public class ChuanG {
    private int id;
    private String maG;
    private String mota;
    
    public ChuanG() {};
    
    public ChuanG(int id, String maG, String mota) {
        this.id = id;
        this.maG = maG;
        this.mota = mota;
    };
    
    public ChuanG(String maG, String mota) {
        this.maG = maG;
        this.mota = mota;
    };
    
    public int getId() {
        return id;
    }
    
    public String getMaG() {
        return maG;
    }
    
    public String getMota() {
        return mota;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setMaG(String maG) {
        this.maG = maG;
    }
    
    public void setMota(String mota) {
        this.mota = mota;
    }
    
    public Boolean validate(final HttpServletRequest req) {

        if (this.getMaG() != null && !this.getMaG().isEmpty() 
          && this.getMota() != null && !this.getMota().isEmpty()) {
            
            return true;
            
        } else {
            // Add error.
            Map<String, String> error = new HashMap<>();
            
            if (this.getMota() == null || this.getMota().isEmpty()) {
                error.put("code", "Mô tả G không được để trống");
            }
            if (this.getMaG() == null || this.getMaG().isEmpty()) {
                error.put("description", "Mã G không được để trống");
            }
            
            MessageSessionUtil.createErrorMsg(req, error, this);
            
            return false;
        }
    }
}
