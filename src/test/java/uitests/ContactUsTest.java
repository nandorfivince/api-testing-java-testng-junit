package uitests;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.ContactUsPageObj;
import uihelpers.UrlDataProvider;

@DisplayName("Contact Us Page Test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ContactUsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsTest.class);
    private ContactUsPageObj contactUsPageObj = new ContactUsPageObj();

    @Test
    @DisplayName("PRECONDITION")
    void test1() {
        LOGGER.info("Precondition");
        contactUsPageObj.changeURL(UrlDataProvider.CONTACT_US_PAGE);
        contactUsPageObj.isElementDisplayed("ContactFormLabel");
    }

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

    @Test
    @DisplayName("POSTCONDITION")
    void test3() {
        LOGGER.info("Postcondition");
        contactUsPageObj.changeURL(UrlDataProvider.BASE_URL);
        contactUsPageObj.isElementDisplayed("HeaderLogo");
        contactUsPageObj.closeBrowser();
    }
}
