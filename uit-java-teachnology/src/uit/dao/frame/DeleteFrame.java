package uit.dao.frame;

import java.io.IOException;
import java.sql.SQLException;

public interface DeleteFrame<T> {
    /**
     * Delete Entity By Id.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    int delete(final int id) throws ClassNotFoundException, SQLException, IOException;
}
