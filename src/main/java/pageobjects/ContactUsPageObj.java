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

@DefaultUrl(UrlDataProvider.CONTACT_US_PAGE)
public class ContactUsPageObj extends BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsPageObj.class);
    private static final int RAND_MIN = 10;
    private static final int RAND_MAX = 1000;

    //===JAVA DICTIONARY <KEY><VALUE> pairs===
    //Java dictionary to could see an organized WebElement table for the easier and more adaptable table for the fast changing paths or IDs.
    private ImmutableMap<String, String> myDict = ImmutableMap.<String, String>builder()
            //===Common usable Elements===
            .put("NameTextBox", "#jform_contact_name")
            .put("EmailTextBox", "#jform_contact_email")
            .put("SubjectTextBox", "#jform_contact_emailmsg")
            .put("MessageTextBox", "//*[@id=\"jform_contact_message\"]")
            .put("ContactFormLabel", "//*[contains(text(),'Contact Form')]")
            .put("ContactFormDescription", "//*[contains(@class,'form-validate')]//*[contains(text(),'Send an email. All fields with an * are required.')]")
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