package uit.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import uit.utils.MessageSessionUtil;

public class SinhVien {
    private int id;
    private String mssv;
    private String ten;
    private int maLopSH;
    private String codeLSH;
    private String tenLSH;

    public SinhVien() {
    }

    public String getTenLSH() {
        return tenLSH;
    }

    public void setTenLSH(String tenLSH) {
        this.tenLSH = tenLSH;
    }

    public SinhVien(String mssv, String ten, int maLopSH) {
        this.mssv = mssv;
        this.ten = ten;
        this.maLopSH = maLopSH;
    }

    public SinhVien(String mssv, String ten, String codeLSH) {
        this.mssv = mssv;
        this.ten = ten;
        this.codeLSH = codeLSH;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getMaLopSH() {
        return maLopSH;
    }

    public void setMaLopSH(int maLopSH) {
        this.maLopSH = maLopSH;
    }

    public String getCodeLSH() {
        return codeLSH;
    }

    public void setCodeLSH(String codeLSH) {
        this.codeLSH = codeLSH;
    }

    public Boolean validate(final HttpServletRequest req) {

        if (this.getTen() != null && !this.getTen().isEmpty() && this.getMssv() != null && !this.getMssv().isEmpty()
                && this.getMaLopSH() != 0) {

            return true;

        } else {
            // Add error.
            Map<String, String> error = new HashMap<>();

            if (this.getTen() == null || this.getTen().isEmpty()) {
                error.put("ten", "Tên sinh viên không được để trống");
            }
            if (this.getMssv() == null || this.getMssv().isEmpty()) {
                error.put("mssv", "Mã số sinh viên không được để trống");
            }
            if (this.getMaLopSH() == 0) {
                error.put("malopsh", "Mã lớp sinh hoạt không được để trống");
            }

            MessageSessionUtil.createErrorMsg(req, error, this);

            return false;
        }
    }
}
