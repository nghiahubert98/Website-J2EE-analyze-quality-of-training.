package uit.dao.frame;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface InsertManyFrame <T>{
    int insertMany(final List<T> tList)  throws ClassNotFoundException, SQLException, IOException;
}
