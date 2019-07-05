package uit.modelview;

import java.util.ArrayList;
import java.util.List;

public class FilterSinhVienViewModel {
    private String mssv = null;
    private String ten = null;
    private String tenLSH = null;

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

    public String getTenLSH() {
        return tenLSH;
    }

    public void setTenLSH(String tenLSH) {
        this.tenLSH = tenLSH;
    }

    public Boolean hasFilter() {
        if ((this.ten == null || this.ten.isEmpty()) && (this.mssv == null || this.mssv.isEmpty())
                && (this.tenLSH == null || this.tenLSH.isEmpty())) {
            return false;
        }

        return true;
    }

    public String getQuery() {
        String result = "";
        List<String> query = new ArrayList<>();

        if (this.mssv != null && !this.mssv.isEmpty()) {
            query.add("`SinhVien_LSH`.`mssv` COLLATE UTF8_GENERAL_CI LIKE '%" + this.mssv + "%'");
        }

        if (this.tenLSH != null && !this.tenLSH.isEmpty()) {
            query.add("`SinhVien_LSH`.`tenLSH` COLLATE UTF8_GENERAL_CI LIKE '%" + this.tenLSH + "%'");
        }
        if (this.ten != null && !this.ten.isEmpty()) {
            query.add("`SinhVien_LSH`.`ten` COLLATE UTF8_GENERAL_CI LIKE '%" + this.ten + "%'");
        }
        if (query.size() > 0) {
            result = String.join(" AND ", query);
        }

        return result;
    }
}
