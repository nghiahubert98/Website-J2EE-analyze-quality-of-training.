package uit.dao.frame;

import java.io.IOException;
import java.sql.SQLException;

public interface EditFrame<T> {
    public int edit(final T t) throws ClassNotFoundException, SQLException, IOException;
}
