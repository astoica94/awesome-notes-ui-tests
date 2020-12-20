package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import page_objects.BasePage;

import java.time.Instant;

public class CucumberHooks extends BasePage {
    @Before
    public void setup() {
        //Run before each scenario
    }

    @After
    public void teardown(Scenario scenario) {
        //Run after each scenario
        if (scenario.isFailed()) {
            scenario
                    .attach(((TakesScreenshot) driverFactory.getDriver())
                            .getScreenshotAs(OutputType.BYTES), "image/png", String.format("FAIL_%s_%s", scenario.getName(), Instant.now().toEpochMilli()));
        }

        driverFactory.closeDriver();
    }
}
