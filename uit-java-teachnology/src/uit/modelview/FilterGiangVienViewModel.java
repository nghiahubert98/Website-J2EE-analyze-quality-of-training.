package uit.modelview;

import java.util.ArrayList;
import java.util.List;

public class FilterGiangVienViewModel {
    private String name = null;
    private String code = null;
    private String email = null;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean hasFilter() {
        if ((this.name == null || this.name.isEmpty()) && (this.code == null || this.code.isEmpty())) {
            return false;
        }

        return true;
    }

    public String getQuery() {
        String result = "";
        List<String> query = new ArrayList<>();

        if (this.name != null && !this.name.isEmpty()) {
            query.add("`giang_vien`.`ten` COLLATE UTF8_GENERAL_CI LIKE '%" + this.name + "%'");
        }

        if (this.code != null && !this.code.isEmpty()) {
            query.add("`giang_vien`.`ma_gv` COLLATE UTF8_GENERAL_CI LIKE '%" + this.code + "%'");
        }

        if (query.size() > 0) {
            result = String.join(" AND ", query);
        }

        return result;
    }
}
