import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        plugin = {
                "pretty",
                "html:target/cucumber-report/cucumber-html-report.html",
                "json:target/cucumber-report/cucumber-json.json"
        },
        glue = "steps",
        tags = "not @wip")
public class CucumberExecutor {
        @BeforeClass
        public static void setup() {
                //Run before test suite
        }

        @AfterClass
        public static void teardown() {
                //Run after test suite
        }
}
