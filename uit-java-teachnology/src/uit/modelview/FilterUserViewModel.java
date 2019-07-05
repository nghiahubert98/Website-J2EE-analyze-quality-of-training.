package uit.modelview;

import java.util.ArrayList;
import java.util.List;

public class FilterUserViewModel {
    private String code = null;
    private String name  = null;
    private Boolean status = null;
    private Integer userType = null;
    private String role;
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Boolean isStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Integer getUserType() {
        return userType;
    }
    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
    public Boolean hasFilter() {
        if (this.status == null && this.userType == null
         && (this.role == null || this.role.isEmpty())
         && (this.code == null || this.code.isEmpty())
         && (this.name == null || this.name.isEmpty())) {
            return false;
        }
        
        return true;
    }
    
    public String getQuery() {
        String result = "";
        List<String> query = new ArrayList<>();
        
        if (status != null) {
            query.add("`users`.`status`=" + this.status);
        }
        if (this.userType != null) {
            query.add("`users`.`user_type`=" + this.userType);
        }
        if (this.role != null && !this.role.isEmpty()) {
            query.add("`users`.`roles` COLLATE UTF8_GENERAL_CI LIKE '%"+ this.role +"%'");
        }
        if (this.code != null && ! this.code.isEmpty()) {
            query.add(
               "(sinh_vien.mssv COLLATE UTF8_GENERAL_CI LIKE '%"+ this.code +"%'"
                + " OR giang_vien.ma_gv COLLATE UTF8_GENERAL_CI LIKE '%"+ this.code +"%')"
            );
        }
        if (this.name != null && !this.name.isEmpty()) {
            query.add(
               "(`sinh_vien`.`ten` COLLATE UTF8_GENERAL_CI LIKE '%"+ this.name +"%'"
                + " OR giang_vien.ten COLLATE UTF8_GENERAL_CI LIKE '%"+ this.name +"%')"
            );
        }
        
        if (query.size() > 0) {
            result = String.join(" AND ", query);
        }
        
        return result;
    }
}
