import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class StarWarApi {

    private static Logger log = Logger.getLogger(StarWarApi.class);
    protected CommonHelper commonHelper = new CommonHelper();
    protected static Properties props;
    protected RestUtils restUtils ;



    public StarWarApi() throws IOException {
        log.info("==> logged in to test rail api");
        props = commonHelper.readProperties("application.properties");
        RestAssured.baseURI = props.getProperty("baseURI");
        restUtils = new RestUtils(RestAssured.baseURI );
    }


    /***
     * This method is used to read a url from film search
     * @param path - name of the rest api to read the data
     * @return filmUrl
     */

    public String getUrl(String path){

        Response res = restUtils.getRequest(path);

        ReadContext ctx = JsonPath.parse(res.getBody().asString());

        List<String> filmList = ctx.read("$..results..url", List.class);
        String filmUrl = "";
        if(filmList!=null && !filmList.isEmpty()) {
             filmUrl = filmList.get(0);
        }
        return filmUrl;
    }

    /***
     * This method is used to retrieve person data from search
     * @param path - name of the rest api to read the data
     * @return personlist
     */

    public List<String> personSearch(String path){

        Response res = restUtils.getRequest(path);

        ReadContext ctx = JsonPath.parse(res.getBody().asString());

        List<String> PersonList = (List<String>) ctx.read("$..results..films", List.class).get(0);

        return PersonList;
    }

    /***
     * This method is used to get the count of star ship search
     * @param path - name of the rest api to read the data
     * @return count
     */
    public int getCountOfStarShipsSearch(String path){

        Response res = restUtils.getRequest(path);

        ReadContext ctx = JsonPath.parse(res.getBody().asString());

      List<Integer> count= ctx.read("$..count",List.class);

        return count.get(0);
    }


    /***
     * This method is retrieve data from planet search
     * @param path - name of the rest api to read the data
     * @return residentList
     */

    public List<String> planetSearch(String path){

        Response res = restUtils.getRequest(path);
        ReadContext ctx = JsonPath.parse(res.getBody().asString());
        List<String> residentList = (List<String>) ctx.read("$..residents",List.class).get(0);

        return residentList;
    }

    /***
     * This method is used to get the star ship details
     * @param path - name of the rest api to read the data
     * @return map
     */

    public  List<Map<String,String>> getStartship(String path){

        Response res = restUtils.getRequest(path);
        ReadContext ctx = JsonPath.parse(res.getBody().asString());

        List<Map<String,String>> results = (List<Map<String, String>>) ctx.read("$..results",List.class).get(0);

        return results;
    }


    public  Map<String,Object> getStartshipPaging(String path){

        Response res = restUtils.getRequest(path);
        ReadContext ctx = JsonPath.parse(res.getBody().asString());

        Map<String, Object> results = (Map<String, Object>) ctx.read("$",Map.class);

        return results;

    }
}

