package uit.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import uit.utils.MessageSessionUtil;

public class GiangVien {
    private int id;
    private String code;
    private String name;
    private String email;

    public GiangVien(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GiangVien(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public GiangVien(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public GiangVien(int id, String code, String name, String email) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
    }

    public GiangVien(String code, String name, String email) {
        this.code = code;
        this.name = name;
        this.email = email;
    }

    public GiangVien() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean validate(final HttpServletRequest req) {

        if ((this.getName() != null && !this.getName().isEmpty() && this.getCode() != null && !this.getCode().isEmpty())
                || (this.getName() != null && !this.getName().isEmpty() && this.getCode() != null
                        && !this.getCode().isEmpty() && this.getEmail() != null && !this.getEmail().isEmpty())) {

            return true;

        } else {
            // Add error.
            Map<String, String> error = new HashMap<>();

            if (this.getName() == null || this.getName().isEmpty()) {
                error.put("name", "Tên giảng viên không được để trống");
            }
            if (this.getCode() == null || this.getCode().isEmpty()) {
                error.put("code", "Mã giảng viên không được để trống");
            }
            if (this.getEmail() == null || this.getEmail().isEmpty()) {
                error.put("email", "Email không được để trống");
            }
            MessageSessionUtil.createErrorMsg(req, error, this);

            return false;
        }
    }
}
