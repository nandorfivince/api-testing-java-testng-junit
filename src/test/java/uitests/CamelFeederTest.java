package uitests;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.CamelFeederPageObj;
import uihelpers.Driver;
import uihelpers.UrlDataProvider;


@DisplayName("Camel Feeder Test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CamelFeederTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamelFeederTest.class);
    private CamelFeederPageObj camelFeederPageObj = new CamelFeederPageObj();

    @Test
    @DisplayName("PRECONDITION")
    void test1() {
        LOGGER.info("Precondition");
        Driver.getInstance().navigate().to(UrlDataProvider.CAMEL_CLUB_HOME_PAGE);
        Driver.getInstance().findElement(By.name("tevenev")).sendKeys("Miszter csimpi");
        Driver.getInstance().findElement(By.name("pass")).sendKeys("AlmaKorte00");
        Driver.getInstance().findElement(By.cssSelector("input[type=image]:nth-child(1)")).click();
    }

    @Test
    @DisplayName("TEST CASE")
    void test2() throws InterruptedException {
        LOGGER.info("Action");
        Driver.getInstance().findElement(By.xpath("(//*[contains(@name,'etet')])[2]")).click();
        Thread.sleep(3000);
    }

    @Test
    @DisplayName("POSTCONDITION")
    void test3() {
        LOGGER.info("Postcondition");
        camelFeederPageObj.closeBrowser();
    }

}
