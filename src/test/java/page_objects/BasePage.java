package page_objects;

import driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasePage {
    public DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver = driverFactory.getDriver();

    public static final int DEFAULT_TIMEOUT = 30;

    public int getListOfElementsSize(By by) {
        return driver.findElements(by).size();
    }

    public boolean isElementDisplayed(By by) {
        return isElementDisplayed(by, DEFAULT_TIMEOUT);
    }

    public boolean isElementDisplayed(By by, int timeout) {
        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void click(By by) {
        driver.findElement(by).click();
    }

    public void sendText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public List<String> getTextFromElements(By by) {
        List<String> elementsText = new ArrayList<>();
        driver.findElements(by).forEach(webElement -> elementsText.add(webElement.getText()));

        return elementsText;
    }

    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    public void setBrowserResolution(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
            //Wait for window resize
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public void scrollIntoView(By by) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
    }

    public String getAttributeValue(By by, String attribute) {
        return driver.findElement(by).getAttribute(attribute);
    }
}
