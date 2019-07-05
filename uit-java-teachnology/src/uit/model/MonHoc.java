package uit.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import uit.utils.MessageSessionUtil;

public class MonHoc {
    private String name;
    private int id;
    private String maMonHoc;

    public MonHoc() {
    }

    public MonHoc(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public MonHoc(String name, String maMonHoc) {
        this.name = name;
        this.maMonHoc = maMonHoc;
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

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public Boolean validate(final HttpServletRequest req) {

        if (this.getName() != null && !this.getName().isEmpty() && this.getMaMonHoc() != null
                && !this.getMaMonHoc().isEmpty()) {

            return true;

        } else {
            // Add error.
            Map<String, String> error = new HashMap<>();

            if (this.getName() == null || this.getName().isEmpty()) {
                error.put("name", "Tên môn học không được để trống");
            }
            if (this.getMaMonHoc() == null || this.getMaMonHoc().isEmpty()) {
                error.put("ma_MonHoc", "Mã môn học không được để trống");
            }

            MessageSessionUtil.createErrorMsg(req, error, this);

            return false;
        }
    }
}
