import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonHelper {


    static Logger log = Logger.getLogger(CommonHelper.class.getName());

    /***
     * This method is used to read a given properties file
     * @param filename - name of the properties file it has to read
     * @return Properties
     */
    public Properties readProperties(String filename) throws IOException {
        log.info("==> readProperties of : " + filename);

        Properties appProps = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(filename);
        appProps.load(stream);

        log.info("<== readProperties of : " + filename);
        return appProps;
    }

}
