package uit.dao.frame;

import java.io.IOException;
import java.sql.SQLException;

public interface FindByIdFrame<T> {
    /**
     * Find Item by Item's Id
     * @param id Id of Item need get Info.
     * @return
     *     <ul>
     *         <li>NULL: if Item not existed in database.</li>
     *         <li>A instance of Item: If Item is existed in database.</li>
     *     </ul>
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    T findById(final int id)  throws ClassNotFoundException, SQLException, IOException;
}
