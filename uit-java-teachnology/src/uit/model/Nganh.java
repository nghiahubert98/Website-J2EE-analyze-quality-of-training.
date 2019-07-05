package uit.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import uit.utils.MessageSessionUtil;

public class Nganh {
    private String name;
    private int id;
    private String manganh;  
    
    public Nganh() {}
    
    public Nganh(String name, String manganh) {
        super();
        this.name = name;
        this.manganh = manganh;
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
    public String getManganh() {
        return manganh;
    }
    public void setManganh(String manganh) {
        this.manganh = manganh;
    }
    
    public Boolean validate(final HttpServletRequest req) {

        if (this.getName() != null && !this.getName().isEmpty() 
          && this.getManganh() != null && !this.getManganh().isEmpty()) {
            
            return true;
            
        } else {
            // Add error.
            Map<String, String> error = new HashMap<>();
            
            if (this.getName() == null || this.getName().isEmpty()) {
                error.put("name", "Tên ngành không được để trống");
            }
            if (this.getManganh() == null || this.getManganh().isEmpty()) {
                error.put("ma_nganh", "Mã ngành không được để trống");
            }
            
            MessageSessionUtil.createErrorMsg(req, error, this);
            
            return false;
        }
    }
}
