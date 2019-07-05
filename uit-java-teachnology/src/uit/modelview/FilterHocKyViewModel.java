package uit.modelview;

import java.util.ArrayList;
import java.util.List;

public class FilterHocKyViewModel {
    private String loai = null;
    private Integer namBatDau;
    private Integer namKetThuc;
    
    public String getLoai() {
        return loai;
    }
    
    public void setLoai(String loai) {
        this.loai = loai;
    }
    
    public Integer getNamBatDau() {
        return namBatDau;
    }
    
    public void setNamBatDau(Integer namBatDau) {
        this.namBatDau = namBatDau;
    }
    
    public Integer getNamKetThuc() {
    	return namKetThuc;
    }
    
    public void setNamKetThuc(Integer namKetThuc) {
    	this.namKetThuc = namKetThuc;
    }
    
    public Boolean hasFilter() {
        if ((this.loai == null || this.loai.isEmpty())
        	&& (this.namBatDau == null || this.namBatDau < 0)
        	&& (this.namKetThuc == null || this.namKetThuc < 0))
        {
            return false;
        }
        
        return true;
    }
    
    public String getQuery() {
        String result = "";
        List<String> query = new ArrayList<>();
        
        if (this.loai != null && !this.loai.isEmpty()) {
            query.add("`Hoc_Ky`.`loai` COLLATE UTF8_GENERAL_CI LIKE '%"+ this.loai +"%'");
        }
         
        if (this.namBatDau != null && this.namBatDau >= 0) {
            query.add("`Hoc_Ky`.`nam_bat_dau` ="+ Integer.toString(this.namBatDau));
        }
        
        if (this.namKetThuc != null && this.namKetThuc >= 0) {
            query.add("`Hoc_Ky`.`nam_ket_thuc` ="+ Integer.toString(this.namKetThuc));
        }
        
        if (query.size() > 0) {
            result = String.join(" AND ", query);
        }
        
        return result;
    }
}
