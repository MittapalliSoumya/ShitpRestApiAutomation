import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StarWarApiTests extends BaseTest {


   //class variable since used across the class
   private  StarWarApi starWarApi;
   private String personPath = "people?search=";
   private String starshipPath = "/starships" ;

    @BeforeMethod
      public void beforeMethod()throws IOException{
        starWarApi = new StarWarApi();
    }


    @Test
    public void verifyCharacterInFilm() throws IOException {

        String filmName = "A New Hope";
        String personName = "Obi-Wan Kenobi";
        String filmPath = "films/?search=";

       String filmURL = starWarApi.getUrl(filmPath + filmName);
       List<String> personUrl = starWarApi.personSearch(personPath + personName);

       // verify that the characters in there in film
        Assert.assertTrue(personUrl.contains(filmURL));
   }

    @Test
    public void verifyEnterprisIsStarship() throws IOException {

        String starshipsPath = "starships?search=";
        String starshipName = "Enterprise";
        int expectedCount = 1;
        int actualCount = starWarApi.getCountOfStarShipsSearch(starshipsPath +starshipName);

        //verify that the enterprise is star ship. The tc fails as enterprise count is 0
        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test
    public void verifyPersonIsWookie() throws IOException{

        String planetName = "Kashyyyk";
        String planetPath = "planets?search=";
        String peopleName = "Chewbacca";

       List<String> planetSearchRes = starWarApi.planetSearch(planetPath + planetName);
       String peopleSearchRes = starWarApi.getUrl(personPath+ peopleName);

       // verify that the chewbacca is wookie
       Assert.assertTrue(planetSearchRes.contains(peopleSearchRes));
    }

    @Test
    public void verifyStarshipAttributes(){

        List<Map<String,String>> results = starWarApi.getStartship(starshipPath);

        Map<String,String> resMap = results.get(0);
        Assert.assertTrue(resMap.containsKey("name"));
        Assert.assertTrue(resMap.containsKey("model"));
        Assert.assertTrue(resMap.containsKey("crew"));
        Assert.assertTrue(resMap.containsKey("hyperdrive_rating"));
        Assert.assertTrue(resMap.containsKey("pilots"));
        Assert.assertTrue(resMap.containsKey("films"));

    }


    @Test
    public void verifyStarshipCount(){

        Map<String,Object> starShipData = starWarApi.getStartshipPaging(starshipPath);
        int actualCount = (int) starShipData.get("count");

        List<Map<String,String>> results = (List<Map<String, String>>) starShipData.get("results");
        int currentCount = results.size();
        String url = (String) starShipData.get("next");

       int nextPage = 2;

       while(url!=null){
           Map<String,Object> nextStarshipData = starWarApi.getStartshipPaging(starshipPath+ "/?page=" + nextPage);
           nextPage++;
           List<Map<String,String>> nextresults = (List<Map<String, String>>) nextStarshipData.get("results");

           int currentNextCount = nextresults.size();

           url = (String) nextStarshipData.get("next");

           currentCount = currentCount + currentNextCount;

       }

       Assert.assertEquals(currentCount,actualCount);
    }

}
