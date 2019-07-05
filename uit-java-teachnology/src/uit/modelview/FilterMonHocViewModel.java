package uit.modelview;

import java.util.ArrayList;
import java.util.List;

public class FilterMonHocViewModel {
    private String name = null;
    private String maMonHoc = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public Boolean hasFilter() {
        if ((this.name == null || this.name.isEmpty()) && (this.maMonHoc == null || this.maMonHoc.isEmpty())) {
            return false;
        }

        return true;
    }

    public String getQuery() {
        String result = "";
        List<String> query = new ArrayList<>();

        if (this.name != null && !this.name.isEmpty()) {
            query.add("`Mon_Hoc`.`ten` COLLATE UTF8_GENERAL_CI LIKE '%" + this.name + "%'");
        }

        if (this.maMonHoc != null && !this.maMonHoc.isEmpty()) {
            query.add("`Mon_Hoc`.`ma_mon` COLLATE UTF8_GENERAL_CI LIKE '%" + this.maMonHoc + "%'");
        }

        if (query.size() > 0) {
            result = String.join(" AND ", query);
        }

        return result;
    }
}
