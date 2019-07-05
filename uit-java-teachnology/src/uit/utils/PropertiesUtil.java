package uit.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static Properties pro;
    
    /**  Load properties form application.properties file. */
    public static Properties getProperties() throws IOException {
        if (pro == null) {
            pro = new Properties();
            ClassLoader classLoader = PropertiesUtil.class.getClassLoader();
            InputStream proStream = classLoader.getResourceAsStream("application.properties");
            pro.load(proStream);
        }     
        
        return pro;
    }
}
