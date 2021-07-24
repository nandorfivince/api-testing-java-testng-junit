package uihelpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Collections.singletonList;

public abstract class BasePage extends Driver {

    private static final int DELAY_MIC = 3; //SEC
    private static final int CONST_TIME_OUT = 30; //SEC

    protected WebDriverWait wait = new WebDriverWait(getInstance(), CONST_TIME_OUT);

    public BasePage() {
        getInstance();
    }

    protected abstract void getElementWithFluentWait(By locatorFromMyDict);

    public abstract By getLocatorFromMyDict(String elemKey);

    private void delay(int delaySec) throws InterruptedException {
        Thread.sleep(delaySec);
    }

    protected String getParentWindowHandle() throws InterruptedException {
        delay(DELAY_MIC);
        return getInstance().getWindowHandle();
    }

    protected Set<String> getAllWindowHandles() throws InterruptedException {
        delay(DELAY_MIC);
        return getInstance().getWindowHandles();
    }

    protected WebDriver switchBetweenWindows(String windowHandlerParam) throws InterruptedException {
        delay(DELAY_MIC);
        return getInstance().switchTo().window(windowHandlerParam);
    }
/*
    public void waitUntilAlertIsPresent() {
        new WebDriverWait(getInstance(), 60)
                .ignoring(NoAlertPresentException.class)
                .until(ExpectedConditions.alertIsPresent());
    }
 */
    protected void alertOK() {
        Alert alert = getInstance().switchTo().alert();
        alert.accept();
    }

    protected void alertDISMISS() {
        Alert alert = getInstance().switchTo().alert();
        alert.dismiss();
    }

    public List<WebElement> findElements(String elemKey) {
        return getInstance().findElements(getLocatorFromMyDict(elemKey));
    }

    public final void waitUntilElementVisible(String elemKey) {
        getElementWithFluentWait(getLocatorFromMyDict(elemKey));
    }

    /*
    public final void waitUntilElementClickable(String elemKey) {
        wait.until(ExpectedConditions.elementToBeClickable(getLocatorFromMyDict(elemKey)));
    }
     */

    public boolean isElementDisplayed(String elemKey) {
        boolean isDisplayed = false;
        try {
            isDisplayed = getInstance().findElement(getLocatorFromMyDict(elemKey)).isDisplayed();
            if (isDisplayed == true) {
                System.out.println(elemKey + " is displayed");
            } else {
                System.out.println(elemKey + " is not displayed");
            }
        } catch (NoSuchElementException e) {
            e.getMessage();
            System.out.println("There is no element displayed like: " + elemKey);
        } catch (NullPointerException e) {
            e.getMessage();
            System.out.println("NPE: " + elemKey + ", please check it manually");
        }
        return isDisplayed;
    }

    public boolean isElementClickable(WebElement element) {
        try {
            element.click();
            return true;
        } catch (Exception e) {
            System.out.println("Element is not clickable");
            return false;
        }
    }

    public String getElementText(String elemKey) {
        String text = getInstance().findElement(getLocatorFromMyDict(elemKey)).getText();
        System.out.println("Text of " + elemKey + " is: " + text);
        return text;
    }

    public String getElementValue(String elemKey) {
        return getInstance().findElement(getLocatorFromMyDict(elemKey)).getAttribute("value");
    }

    public List<String> getTextList(String elemKey) {
        List<String> returnValue = new ArrayList<>();
        List<WebElement> rowData = singletonList(getInstance().findElement(getLocatorFromMyDict(elemKey)));
        for (WebElement webelement : rowData) {
            returnValue.add(webelement.getText());
        }
        System.out.println("Text list: " + returnValue);
        return returnValue;
    }

    public void typeTextToElement(String elemKey, String textToType) {
        //clearTextBoxContent(elemKey);
        //getInstance().findElement(getLocatorFromMyDict(elemKey)).sendKeys(textToType);

        Driver.getInstance().findElement(By.name(elemKey)).sendKeys(textToType);
    }

    public void sendUnicodeChars(String elemKey, Keys keyToSend) {
        getInstance().findElement(getLocatorFromMyDict(elemKey)).sendKeys(keyToSend);
    }

    private void clearTextBoxContent(String elemKey) {
        getInstance().findElement(getLocatorFromMyDict(elemKey)).clear();
    }

    public void clickOnElement(String elemKey) {
        //getInstance().findElement(getLocatorFromMyDict(elemKey)).click();

        Driver.getInstance().findElement(By.name(elemKey)).click();
    }

    public void doubleClickOnElement(String elemKey) {
        //getInstance().findElement(getLocatorFromMyDict(elemKey)).click();

        Driver.getInstance().findElement(By.name(elemKey)).click();
    }

    public void setFrameParent() {
        WebDriver driver = getInstance();
        driver.switchTo().defaultContent();
        driver.switchTo().parentFrame();
    }

    public void setFrameFocusOn(String elemKey) {
        WebDriver driver = getInstance();
        driver.switchTo().frame(elemKey);
    }

    public void changeURL(String urlConst) {
        Driver.getInstance().navigate().to(urlConst);
    }


}