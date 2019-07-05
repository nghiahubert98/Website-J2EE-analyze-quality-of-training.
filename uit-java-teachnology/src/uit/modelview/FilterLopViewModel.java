package uit.modelview;

import java.util.ArrayList;
import java.util.List;

public class FilterLopViewModel {
    private String maLop = null;
    private String hocKy = null;
    private String monHoc = null;
    private String giangVien = null;
    private String mssv = null;
    private String ma_gv = null;
    
    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }

    public String getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(String giangVien) {
        this.giangVien = giangVien;
    }
   
    
    public String getMa_gv() {
        return ma_gv;
    }

    public void setMa_gv(String ma_gv) {
        this.ma_gv = ma_gv;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public Boolean hasFilter() {
        if ((this.getMaLop() == null || this.getMaLop().isEmpty())
        && (this.getHocKy() == null || this.getHocKy().isEmpty())
        && (this.getMonHoc() == null || this.getMonHoc().isEmpty())
        && (this.getGiangVien() == null || this.getGiangVien().isEmpty())
        && (this.getMa_gv() == null || this.getMa_gv().isEmpty())
        && (this.getMssv() == null || this.getMssv().isEmpty())) {
            return false;
        }
        return true;
    }

    public String getQuery() {
        String result = "";
        List<String> query = new ArrayList<>();

        if (this.getMaLop() != null && !this.getMaLop().isEmpty()) {
            query.add("SEARCH_LOP.malop COLLATE UTF8_GENERAL_CI LIKE '%" + this.maLop + "%'");
        }

        if (this.getHocKy() != null && !this.getHocKy().isEmpty()) {
            query.add("SEARCH_LOP.hocky COLLATE UTF8_GENERAL_CI LIKE '%" + this.hocKy + "%'");
        }

        if (this.getMonHoc() != null && !this.getMonHoc().isEmpty()) {
            query.add("SEARCH_LOP.ten_monhoc COLLATE UTF8_GENERAL_CI LIKE '%" + this.monHoc + "%'");
        }

        if (this.getGiangVien() != null && !this.getGiangVien().isEmpty()) {
            query.add("SEARCH_LOP.ten_giangvien COLLATE UTF8_GENERAL_CI LIKE '%" + this.giangVien + "%'");
        }
        
        if (this.getMa_gv() != null && !this.getMa_gv().isEmpty()) {
            query.add("SEARCH_LOP.ma_gv = '" + this.ma_gv + "'");
        }
        
        if (this.getMssv() != null && !this.getMssv().isEmpty()) {
            query.add("SEARCH_LOP.mssv = " + this.mssv);
        }
        
        if (query.size() > 0) {
            result = String.join(" AND ", query);
        }

        return result;
    }
}
