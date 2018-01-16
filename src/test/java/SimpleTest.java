import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class SimpleTest {
    static final Logger LOGGER = Logger.getLogger(SimpleTest.class);
    @Test
    public void test() {
        LOGGER.info("simple test");
        Assert.assertTrue(true);
    }
}
