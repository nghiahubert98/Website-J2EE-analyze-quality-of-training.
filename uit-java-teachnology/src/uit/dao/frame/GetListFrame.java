package uit.dao.frame;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GetListFrame<T> {    
    /**
     * Parse Result to List of {@link T}.
     * T is class which represent a Database Table.
     * @param rs {@link ResultSet}.
     * @return {@link List} of T
     * @throws SQLException
     * @see {@link TransferResultSetToList}
     */
    List<T> transferResultSetToList(final ResultSet rs) throws SQLException ;
    
    List<T> getList(int skip, int limit) throws ClassNotFoundException, SQLException, IOException;
}
