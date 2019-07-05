package uit.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import uit.utils.PropertiesUtil;

/**
 * This class provides a instance of java.sql.Connection
 * and using SingleTon Design Pattern.
 * 
 * @author PhongNguyen
 */
public class DBConnectionProvider {
    
    private String dbURL;
    private String dbUser;
    private String dbPasword;
    private static DBConnectionProvider instance;     
    

    
    /** Set values use to connect database */
    private void setDbConfig() throws IOException {
        Properties appPro = PropertiesUtil.getProperties();

        this.dbURL = appPro.getProperty("dbURL");
        this.dbUser = appPro.getProperty("dbUser", "root");
        this.dbPasword = appPro.getProperty("dbPassword", "");
    }
    
    /** Default Constructor. */
    private DBConnectionProvider() throws IOException {        
        setDbConfig();
    }
    
    /** SigleTon Design Pattern.*/
    public static DBConnectionProvider getInstance() throws IOException {
        if (instance == null) {
            instance = new DBConnectionProvider();
        }    
        return instance;
    }

    /** 
     * Get MySql connection.
     * @return A instnace of {@link Connection}
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPasword);
        
        return connection;
    }
        
}
