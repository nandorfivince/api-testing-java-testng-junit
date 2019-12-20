package uitests;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.OurBlogPageObj;
import uihelpers.UrlDataProvider;

@DisplayName("Our Blog Test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class OurBlogTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OurBlogTest.class);
    private OurBlogPageObj ourBlogPageObj = new OurBlogPageObj();

    @Test
    @DisplayName("PRECONDITION")
    void test1() {
        LOGGER.info("Precondition");
        ourBlogPageObj.changeURL(UrlDataProvider.OUR_BLOG_PAGE);
        ourBlogPageObj.isElementDisplayed("AppDevelopmentTitleText");
        ourBlogPageObj.clickOnElement("FirstCommentSection");
        ourBlogPageObj.typeTextToElement("NameTextBox", "Heeeroo Chimpyyy");
    }

    @Test
    @DisplayName("TEST CASE")
    @Disabled
    void test2() {
        LOGGER.info("Test Case");
        ourBlogPageObj.changeURL(UrlDataProvider.BASE_URL);
        ourBlogPageObj.changeURL(UrlDataProvider.OUR_BLOG_PAGE);
        ourBlogPageObj.isElementDisplayed("AppDevelopmentTitleText");
        ourBlogPageObj.isElementDisplayed("FirstDescrOfAppDevCloud");
        ourBlogPageObj.isElementDisplayed("FirstCommentSection");
    }

    @Test
    @DisplayName("POSTCONDITION")
    void test3() {
        LOGGER.info("Postcondition");
        ourBlogPageObj.changeURL(UrlDataProvider.BASE_URL);
        ourBlogPageObj.isElementDisplayed("HeaderLogo");
        ourBlogPageObj.closeBrowser();
    }
}
