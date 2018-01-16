import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IMDBClientTest {

    // ---- http://www.omdbapi.com/?t=Taxi&apikey=ddb21a66
    private static final Logger LOGGER = Logger.getLogger(IMDBClientTest.class);
    private String restBaseUrl;
    private final String API_KEY = "&apikey=ddb21a66";
    IMDBRequestMaker imdbRequestMaker;

    @BeforeTest
    public void setUp() {
        restBaseUrl = "http://www.omdbapi.com/";
        imdbRequestMaker = new IMDBRequestMaker();
    }

    @Test
    public void restPlusTest_ValidValuesPassed_ShouldPass() {
        LOGGER.info("Test imdb for Dunkirk film");
        String result = imdbRequestMaker.getResultFromJson(restBaseUrl + "?t=Dunkirk&y=2017&plot=full" + API_KEY);
        Assert.assertTrue(result.contains("Nolan"), "Result doesnt contain key word Nolan");
    }
}
