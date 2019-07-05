package uit.dao.frame;

import java.io.IOException;
import java.sql.SQLException;

public interface CountFrame {
    /**
     * Count number of items in database.
     * @return 
     * 
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    int count()  throws ClassNotFoundException, SQLException, IOException ;
}
