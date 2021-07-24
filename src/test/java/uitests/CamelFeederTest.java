package uitests;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.CamelFeederPageObj;
import uihelpers.Driver;
import uihelpers.UrlDataProvider;

import java.util.List;


@DisplayName("Camel Feeder Test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CamelFeederTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamelFeederTest.class);
    private final CamelFeederPageObj camelFeederPageObj = new CamelFeederPageObj();

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
    void test2() {
        LOGGER.info("Feeding");
        try {
            Driver.getInstance().findElement(By.name("kaja")).click();
            List<WebElement> days = Driver.getInstance().findElements(By.xpath("(//*[contains(@name,'kaja')])[2]/option"));

            if (days.size() <= 7) {
                String xpathSelector = "((//*[contains(@name,'kaja')])[2]/option)[%s]";
                System.out.println("Remained numbers of food days: " + days.size());
                String numberOfDays = String.valueOf(days.size());
                String fullXpath = String.format(xpathSelector, numberOfDays);
                Driver.getInstance().findElement(By.xpath(fullXpath)).click();
                Driver.getInstance().findElement(By.xpath("(//*[contains(@name,'etet')])[2]")).click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("TEST CASE")
    void test3() {
        LOGGER.info("Drinking");
        try {
            Driver.getInstance().findElement(By.name("pia")).click();
            List<WebElement> days = Driver.getInstance().findElements(By.xpath("(//*[contains(@name,'pia')])[2]/option"));

            if (days.size() <= 7) {
                String xpathSelector = "((//*[contains(@name,'pia')])[2]/option)[%s]";
                System.out.println("Remained numbers of drink days: " + days.size());
                String numberOfDays = String.valueOf(days.size());
                String fullXpath = String.format(xpathSelector, numberOfDays);
                Driver.getInstance().findElement(By.xpath(fullXpath)).click();
                Driver.getInstance().findElement(By.xpath("(//*[contains(@name,'etet')])[2]")).click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("POSTCONDITION")
    void test4() {
        LOGGER.info("Postcondition");
        camelFeederPageObj.closeBrowser();
    }

}
