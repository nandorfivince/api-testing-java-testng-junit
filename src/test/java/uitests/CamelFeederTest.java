package uitests;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.CamelFeederPageObj;
import pageobjects.ContactUsPageObj;
import uihelpers.UrlDataProvider;


@DisplayName("Camel Feeder Test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CamelFeederTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamelFeederTest.class);
    private ContactUsPageObj contactUsPageObj = new ContactUsPageObj();
    private CamelFeederPageObj camelFeederPageObj = new CamelFeederPageObj();

    @Test
    @DisplayName("PRECONDITION")
    void test1() {
        LOGGER.info("Precondition");
        camelFeederPageObj.changeURL(UrlDataProvider.CAMEL_CLUB_HOME_PAGE);
        camelFeederPageObj.isElementDisplayed("NameOfTheCamel");
        camelFeederPageObj.isElementDisplayed("Password");
        camelFeederPageObj.clickOnElement("NameOfTheCamel");
        camelFeederPageObj.typeTextToElement("NameOfTheCamel", "Miszter csimpi");
        camelFeederPageObj.clickOnElement("Password");
        camelFeederPageObj.typeTextToElement("Password", "AlmaKorte00");
        camelFeederPageObj.clickOnElement("LoginButton");
    }

    /*
    @Test
    @DisplayName("TEST CASE")
    void test2() {
        LOGGER.info("Action");
        int randomNum = contactUsPageObj.getRandomNum();
        contactUsPageObj.changeURL(UrlDataProvider.BASE_URL);
        contactUsPageObj.changeURL(UrlDataProvider.CONTACT_US_PAGE);
        contactUsPageObj.isElementDisplayed("ContactFormLabel");
        contactUsPageObj.isElementDisplayed("NameTextBox");
        contactUsPageObj.clickOnElement("NameTextBox");
        contactUsPageObj.typeTextToElement("NameTextBox", "vincikusz" + randomNum);
        contactUsPageObj.isElementDisplayed("EmailTextBox");
        contactUsPageObj.clickOnElement("EmailTextBox");
        contactUsPageObj.typeTextToElement("EmailTextBox", "valami@valami.com");
        contactUsPageObj.isElementDisplayed("SubjectTextBox");
        contactUsPageObj.clickOnElement("SubjectTextBox");
        contactUsPageObj.typeTextToElement("SubjectTextBox", "hulla llaaaaa" + randomNum);
    }
     */

    @Test
    @DisplayName("POSTCONDITION")
    void test3() {
        LOGGER.info("Postcondition");
        contactUsPageObj.closeBrowser();
    }

}
