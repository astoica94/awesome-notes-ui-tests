package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public class DriverFactory {
    private static final String BROWSER_TYPE = "chrome";

    private WebDriver driver;
    private boolean driverClosed;

    public boolean isDriverClosed() {
        return driverClosed;
    }

    public void setDriverClosed(boolean driverClosed) {
        this.driverClosed = driverClosed;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            setupDriver(BROWSER_TYPE);

            Runtime.getRuntime().addShutdownHook(new Thread(new DriverCleanup()));

            setDriverClosed(false);
        }

        return driver;
    }

    private void setupDriver(String browser) {
        if (FIREFOX.equals(browser)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }

    public void closeDriver() {
        if (!isDriverClosed()) {
            driver.close();

            setDriverClosed(true);
        }
    }

    private class DriverCleanup implements Runnable {
        public void run() {
            closeDriver();
        }
    }
}
