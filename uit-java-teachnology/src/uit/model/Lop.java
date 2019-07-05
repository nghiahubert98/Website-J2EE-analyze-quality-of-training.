package uit.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import uit.utils.MessageSessionUtil;

public class Lop {
    private int id;
    private String maLop;
    private String hocKy;
    private String monHoc;
    private String giangVien;
    private int hocKyID;
    private int monHocID;
    private int gvID;
    private String mavGV;

    public int getHocKyID() {
        return hocKyID;
    }

    public String getMavGV() {
        return mavGV;
    }

    public void setMavGV(String mavGV) {
        this.mavGV = mavGV;
    }

    public void setHocKyID(int hocKyID) {
        this.hocKyID = hocKyID;
    }

    public int getMonHocID() {
        return monHocID;
    }

    public void setMonHocID(int monHocID) {
        this.monHocID = monHocID;
    }

    public int getGvID() {
        return gvID;
    }

    public void setGvID(int gvID) {
        this.gvID = gvID;
    }

    public Lop() {
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

    public Lop(String maLop, String hocKy, String monHoc, String giangVien, int hocKyID, int monHocID, int gvID) {
        this.maLop = maLop;
        this.hocKy = hocKy;
        this.monHoc = monHoc;
        this.giangVien = giangVien;
        this.hocKyID = hocKyID;
        this.monHocID = monHocID;
        this.gvID = gvID;
    }

    public Lop(String maLop, int hocKyID, int monHocID, int gvID) {
        this.maLop = maLop;
        this.hocKyID = hocKyID;
        this.monHocID = monHocID;
        this.gvID = gvID;
    }

    public Boolean validate(final HttpServletRequest req) {

        if ((this.getMaLop() != null && !this.getMaLop().isEmpty() && this.getHocKy() != null
                && !this.getHocKy().isEmpty() && this.getMonHoc() != null && !this.getMonHoc().isEmpty()
                && this.getGiangVien() != null && !this.getGiangVien().isEmpty())
                || ((this.getMaLop() != null && !this.getMaLop().isEmpty()) && this.getHocKyID() != 0
                        && this.getMonHocID() != 0 && this.getGvID() != 0)) {

            return true;

        } else {
            // Add error.
            Map<String, String> error = new HashMap<>();

            if (this.getMaLop() == null || this.getMaLop().isEmpty()) {
                error.put("maLop", "Mã Lớp không được để trống");
            }
            if (this.getHocKy() == null || this.getHocKy().isEmpty()) {
                error.put("hocKy", "Học kỳ không được để trống");
            }
            if (this.getMonHoc() == null || this.getMonHoc().isEmpty()) {
                error.put("monHoc", "Môn học không được để trống");
            }
            if (this.getGiangVien() == null || this.getGiangVien().isEmpty()) {
                error.put("giangVien", "Giảng viên không được để trống");
            }
            if (this.getHocKyID() == 0) {
                error.put("hocKyID", "Mã học kỳ không được để trống");
            }
            if (this.getMonHocID() == 0) {
                error.put("monHocID", "Mã môn học không được để trống");
            }
            if (this.getGvID() == 0) {
                error.put("gvID", "Mã giảng viên không được để trống");
            }
            MessageSessionUtil.createErrorMsg(req, error, this);

            return false;
        }
    }
}
