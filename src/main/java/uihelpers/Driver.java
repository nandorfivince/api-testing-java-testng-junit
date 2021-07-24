package uihelpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private static String WEB_DRIVER_CHROME = "webdriver.chrome.driver";
    private static String CHROME_DRIVER_SERVER_LOCATION = "src/test/resources/drivers/chromedriver.exe";
    private static String BASE_URL = UrlDataProvider.BASE_URL;
    private static WebDriver driver = null;

    public static WebDriver getInstance() {
        if (driver == null) {
            if (System.getProperty("current.browser") != null) {
                if (System.getProperty("current.browser").equals("Chrome")) {
                    System.setProperty(WEB_DRIVER_CHROME, CHROME_DRIVER_SERVER_LOCATION);
                    driver = new ChromeDriver();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    driver.get(BASE_URL);
                }
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

    public void closeBrowser() {
        driver.close();
        driver.quit();
        driver = null;
    }

}
