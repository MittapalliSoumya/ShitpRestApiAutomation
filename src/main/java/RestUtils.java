import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;


public class RestUtils {

    /***
     * This class is used to call the rest methods post,get,put and delete
     */

    RestUtils(String baseURI){
        RestAssured.baseURI = baseURI;
    }

    static Logger log = Logger.getLogger(RestUtils.class.getName());


    public Response postRequest(String payload,String Path) throws ShiptExceptionHandling {
        log.info("======> Entered post Request <=========");
        return  given().contentType("application/json")
                        .accept("application/json")
                        .body(payload)
                        .post(Path);
    }

    public Response putRequest(String payload,String Path){
        log.info("======> Entered put Request <=========");
        return given()
                .contentType("application/json")
                .accept("application/json")
                .body(payload)
                .put(Path);
    }

    public Response getRequest(String Path){
        log.info("======> Entered get Request <=========");
        return given()
                .contentType("application/json")
                .accept("application/json")
                .get(Path);
    }


    public Response deleteRequest(String payload,String Path){
        log.info("======> Entered delete Request <=========");
        return given().contentType("application/json")
                     .accept("application/json")
                     .body(payload)
                     .delete(Path );
    }

}

