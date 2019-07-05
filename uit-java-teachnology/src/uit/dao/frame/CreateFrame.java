package uit.dao.frame;

import java.io.IOException;
import java.sql.SQLException;

public interface CreateFrame<T> {

    /**
     * Insert new item to database
     * @param t Item need to insert to database.
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    int create(final T t) throws ClassNotFoundException, SQLException, IOException;
}
