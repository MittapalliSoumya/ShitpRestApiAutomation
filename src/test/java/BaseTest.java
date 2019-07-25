import org.apache.log4j.Logger;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {


    static Logger log = Logger.getLogger(BaseTest.class.getName());


    protected CommonHelper commonHelper = new CommonHelper();
    protected RestUtils restUtils;

    @BeforeSuite(alwaysRun=true)
    public void setUpSuite() throws IOException {
        log.info("==> Before Suite reading properties file");
        Properties props = commonHelper.readProperties("application.properties");
        restUtils =  new RestUtils(props.getProperty("baseURI")) ;
    }


}


