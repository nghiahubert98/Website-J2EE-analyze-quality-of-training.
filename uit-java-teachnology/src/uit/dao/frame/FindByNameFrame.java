package uit.dao.frame;

import java.io.IOException;
import java.sql.SQLException;

public interface FindByNameFrame<T> {
    public T findByName(String name) throws ClassNotFoundException, SQLException, IOException;
}
