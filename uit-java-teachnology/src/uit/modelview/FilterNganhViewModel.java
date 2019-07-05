package uit.modelview;

import java.util.ArrayList;
import java.util.List;

public class FilterNganhViewModel {
    private String name = null;
    private String manganh = null;  
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getManganh() {
        return manganh;
    }
    public void setManganh(String manganh) {
        this.manganh = manganh;
    }
    
    public Boolean hasFilter() {
        if ((this.name == null || this.name.isEmpty())
         && (this.manganh == null || this.manganh.isEmpty())) {
            return false;
        }
        
        return true;
    }
    
    public String getQuery() {
        String result = "";
        List<String> query = new ArrayList<>();
        
        if (this.name != null && !this.name.isEmpty()) {
            query.add("`nganh`.`ten` COLLATE UTF8_GENERAL_CI LIKE '%"+ this.name +"%'");
        }
        
        if (this.manganh != null && !this.manganh.isEmpty()) {
            query.add("`nganh`.`ma_nganh` COLLATE UTF8_GENERAL_CI LIKE '%"+ this.manganh +"%'");
        }
        
        if (query.size() > 0) {
            result = String.join(" AND ", query);
        }
        
        return result;
    }
}
