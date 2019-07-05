package uit.dao.frame;

import java.io.IOException;
import java.sql.SQLException;

public interface FindByCodeFrame<T> {
    public T findByCode(String code) throws ClassNotFoundException, SQLException, IOException;
}
