package uit.modelview;

import java.util.ArrayList;
import java.util.List;

public class FilterLopSinhHoatViewModel {
    private String maLop;
    private String batDau;
    private String totNghiep;
    private String ten;
    private String coVan;

    public FilterLopSinhHoatViewModel() {
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getBatDau() {
        return batDau;
    }

    public void setBatDau(String batDau) {
        this.batDau = batDau;
    }

    public String getTotNghiep() {
        return totNghiep;
    }

    public void setTotNghiep(String totNghiep) {
        this.totNghiep = totNghiep;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCoVan() {
        return coVan;
    }

    public void setCoVan(String coVan) {
        this.coVan = coVan;
    }

    public Boolean hasFilter() {
        if ((this.getMaLop() == null || this.getMaLop().isEmpty())
                && (this.getBatDau() == null || this.getBatDau().isEmpty())
                && (this.getTotNghiep() == null || this.getTotNghiep().isEmpty())
                && (this.getTen() == null || this.getTen().isEmpty())
                && (this.getCoVan() == null || this.getCoVan().isEmpty())) {
            return false;
        }
        return true;
    }

    public String getQuery() {
        String result = "";
        List<String> query = new ArrayList<>();

        if (this.getMaLop() != null && !this.getMaLop().isEmpty()) {
            query.add("LopSinhHoat.ma_lop COLLATE UTF8_GENERAL_CI LIKE '%" + this.maLop + "%'");
        }

        if (this.getBatDau() != null && !this.getBatDau().isEmpty()) {
            query.add("LopSinhHoat.batdau COLLATE UTF8_GENERAL_CI LIKE '%" + this.batDau + "%'");
        }

        if (this.getTotNghiep() != null && !this.getTotNghiep().isEmpty()) {
            query.add("LopSinhHoat.totnghiep COLLATE UTF8_GENERAL_CI LIKE '%" + this.totNghiep + "%'");
        }

        if (this.getTen() != null && !this.getTen().isEmpty()) {
            query.add("LopSinhHoat.ten_lop COLLATE UTF8_GENERAL_CI LIKE '%" + this.ten + "%'");
        }

        if (this.getCoVan() != null && !this.getCoVan().isEmpty()) {
            query.add("LopSinhHoat.tencovan COLLATE UTF8_GENERAL_CI LIKE '%" + this.coVan + "%'");
        }

        if (query.size() > 0) {
            result = String.join(" AND ", query);
        }

        return result;
    }
}
