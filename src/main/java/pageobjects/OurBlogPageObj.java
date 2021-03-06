package pageobjects;

import com.google.common.collect.ImmutableMap;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uihelpers.BasePage;
import uihelpers.UrlDataProvider;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@DefaultUrl(UrlDataProvider.OUR_BLOG_PAGE)
public class OurBlogPageObj extends BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(OurBlogPageObj.class);
    private static final int RAND_MIN = 10;
    private static final int RAND_MAX = 1000;

    //===JAVA DICTIONARY <KEY><VALUE> pairs===
    //Java dictionary to could see an organized WebElement table for the easier and more adaptable table for the fast changing paths or IDs.
    private ImmutableMap<String, String> myDict = ImmutableMap.<String, String>builder()
            //===Common usable Elements===
            .put("AppDevelopmentTitleText", "//*[contains(text(),'App development in the cloud')]")
            .put("FirstDescrOfAppDevCloud", "//*[@id=\"itemListPrimary\"]/div[1]/div/div[3]/div[2]/p/span")
            .put("FirstCommentSection", "#itemListPrimary > div:nth-child(1) > div > div.blog-item-meta > span.catItemCommentsLink > a")
            .put("NameTextBox", "#userName")
            .put("HeaderLogo", "#ja-header > div > div > h1 > a")
            .build();

    @Override
    protected void getElementWithFluentWait(By locatorFromMyDict) {
    }

    public By getLocatorFromMyDict(String elemKey) {
        if (myDict.get(elemKey).contains("//")) {
            return By.xpath(myDict.get(elemKey));
        } else {
            return By.cssSelector(myDict.get(elemKey));
        }
    }

    public String alertWindowOK() throws InterruptedException {
        String parentWindowHandler = getParentWindowHandle();
        Set<String> allWindowHandles = getAllWindowHandles();
        String subWindowHandler = null;
        for (String allWindowHandle : allWindowHandles) {
            subWindowHandler = allWindowHandle;
        }
        Alert alertWindow = switchBetweenWindows(subWindowHandler).switchTo().alert();
        String textOfAlertWindow = alertWindow.getText();
        LOGGER.info(">>> Alert window text: " + textOfAlertWindow);
        alertOK();
        switchBetweenWindows(parentWindowHandler);
        return textOfAlertWindow;
    }

    public String alertWindowDismiss() throws InterruptedException {
        Thread.sleep(3000);
        String parentWindowHandler = getParentWindowHandle();
        Set<String> allWindowHandles = getAllWindowHandles();
        String subWindowHandler = null;
        for (String allWindowHandle : allWindowHandles) {
            subWindowHandler = allWindowHandle;
        }
        Alert alertWindow = switchBetweenWindows(subWindowHandler).switchTo().alert();
        String textOfAlertWindow = alertWindow.getText();
        LOGGER.info(">>> Alert window text: " + textOfAlertWindow);
        alertDISMISS();
        switchBetweenWindows(parentWindowHandler);
        Thread.sleep(3000);
        return textOfAlertWindow;
    }

    public int getRandomNum() {
        return ThreadLocalRandom.current().nextInt(RAND_MIN, RAND_MAX + 1);
    }
}