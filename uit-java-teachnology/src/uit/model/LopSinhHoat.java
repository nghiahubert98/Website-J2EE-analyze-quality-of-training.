package uit.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import uit.utils.MessageSessionUtil;

public class LopSinhHoat {
    private int id;
    private String maLop;
    private String batDau;
    private String totNghiep;
    private String ten;
    private int coVanID;
    private String coVan;
    private int idNganh;

    public LopSinhHoat() {
    }

    public LopSinhHoat(int id, String malop) {
        this.id = id;
        this.maLop = malop;
    }

    public LopSinhHoat(String malop, String batDau, String totnghiep, String ten, String coVan) {
        this.maLop = malop;
        this.batDau = batDau;
        this.totNghiep = totnghiep;
        this.ten = ten;
        this.coVan = coVan;
    }

    public LopSinhHoat(String malop, String batDau, String totnghiep, String ten, int coVanID) {
        this.maLop = malop;
        this.batDau = batDau;
        this.totNghiep = totnghiep;
        this.ten = ten;
        this.coVanID = coVanID;
    }

    public int getIdNganh() {
        return idNganh;
    }

    public void setIdNganh(int idNganh) {
        this.idNganh = idNganh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCoVanID() {
        return coVanID;
    }

    public void setCoVanID(int coVanID) {
        this.coVanID = coVanID;
    }

    public String getCoVan() {
        return coVan;
    }

    public void setCoVan(String coVan) {
        this.coVan = coVan;
    }

    public Boolean validate(final HttpServletRequest req) {

        if ((this.getMaLop() != null && !this.getMaLop().isEmpty() && this.getBatDau() != null
                && !this.getBatDau().isEmpty() && this.getTotNghiep() != null && !this.getTotNghiep().isEmpty()
                && this.getTen() != null && !this.getTen().isEmpty() && this.getCoVan() != null
                && !this.getCoVan().isEmpty())
                || (this.getMaLop() != null && !this.getMaLop().isEmpty() && this.getBatDau() != null
                        && !this.getBatDau().isEmpty() && this.getTotNghiep() != null && !this.getTotNghiep().isEmpty()
                        && this.getTen() != null && !this.getTen().isEmpty() && this.getCoVanID() != 0)) {

            return true;

        } else {
            // Add error.
            Map<String, String> error = new HashMap<>();

            if (this.getMaLop() == null || this.getMaLop().isEmpty()) {
                error.put("maLop", "Mã lớp sinh hoạt không được để trống");
            }
            if (this.getBatDau() == null || this.getBatDau().isEmpty()) {
                error.put("batDau", "Thời gian bắt đầu không được để trống");
            }
            if (this.getTotNghiep() == null || this.getTotNghiep().isEmpty()) {
                error.put("totNghiep", "Thời gian tốt nghiệp không được để trống");
            }
            if (this.getTen() == null || this.getTen().isEmpty()) {
                error.put("ten", "Tên lớp sinh hoạt không được để trống");
            }
            if (this.getCoVan() == null || this.getCoVan().isEmpty()) {
                error.put("coVan", "Tên cố vấn không được để trống");
            }
            if (this.getCoVanID() == 0) {
                error.put("coVanID", "Mã cố vấn không được để trống");
            }

            MessageSessionUtil.createErrorMsg(req, error, this);

            return false;
        }
    }
}
