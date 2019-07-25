import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StarWarApiTests extends BaseTest {


   //class variable since used across the class
   private  StarWarApi starWarApi;
   protected String personPath = "people?search=";


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

        String starshipPath = "/starships" ;

        Map<String,String> results = starWarApi.getStartship(starshipPath);

    }


    @Test
    public void verifyStarshipCount(){

        String starshipCountPath = "starships/";

        int count =starWarApi.getCountOfStarShipsSearch(starshipCountPath);
    }

}
