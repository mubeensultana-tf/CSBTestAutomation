package com.cztffa.page.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import com.cztffa.application.WebApplicationEnum;
import com.cztffa.browseractions.BrowserActions;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.driver.SeleniumUIDriver;

import com.cztffa.page.business.SmbFundingPageModel;
import com.cztffa.page.business.SmbAccountDetailsPageModel;
import com.cztffa.page.business.SmbGettingStartedPageModel;
import com.cztffa.page.business.MemberDiligencePageModel;
import com.cztffa.page.business.BusinessInfoPageModel;
import com.cztffa.page.business.SmbPersonalInfoPageModel;
import com.cztffa.page.business.SmbProductSelectorPageModel;
import com.cztffa.page.business.SmbDisclosurePageModel;
import com.cztffa.page.business.SmbReviewPageModel;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class SmbBasePage {

    private SeleniumDriver seleniumdriver;

    public Properties prop;

    public BrowserActions browserActions = new BrowserActions("capture");

    public static String PATH = System.getProperty("user.dir") + "\\Screenshots\\";

    private String browser;

    private WebDriverWait wait;

    private void loadPropeties() {
        try {
            log.info("Initializing the properties");
            prop = new Properties();
            FileInputStream ip = new FileInputStream("./Configuration/config.properties");
            prop.load(ip);
            log.info("Successfully loaded the properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getApplicationURL(String channel) {
        if ("BRANCH".equalsIgnoreCase(channel)) {
            return prop.getProperty("branchURL");
        }
        return prop.getProperty("baseURL");
    }

    public void launch(String channel, String browser) {
        this.browser = browser;
        loadPropeties();
        setSeleniumdriver(new SeleniumUIDriver());
        getSeleniumdriver().launch(getWebBrowserType(), getApplicationURL(channel));
    }

    public void launchGmail() {
        getSeleniumdriver().launch(getWebBrowserType(), getGmailUrl());
    }

    private WebApplicationEnum getWebBrowserType() {
        WebApplicationEnum type = WebApplicationEnum.CHROME;
        if (browser.equals("firefox")) {
            type = WebApplicationEnum.FIREFOX;
        }
        return type;
    }

    public SmbProductSelectorPageModel getSmbProductSelectorPageModel() {
        return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(SmbProductSelectorPageModel.class);
    }

    public MemberDiligencePageModel getMemberDiligencePageModel() {
        return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(MemberDiligencePageModel.class);
    }

    public SmbPersonalInfoPageModel getSmbPersonalInfoPageModel() {
        return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(SmbPersonalInfoPageModel.class);
    }

    public BusinessInfoPageModel getBusinessInfoPageModel() {
        return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(BusinessInfoPageModel.class);
    }

    public SmbGettingStartedPageModel getSmbGettingStartedPageModel() {
        return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(SmbGettingStartedPageModel.class);
    }

    public SmbAccountDetailsPageModel getSmbAccountDetailsPageModel() {
        return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(SmbAccountDetailsPageModel.class);
    }

    public SmbFundingPageModel getSmbFundingPageModel() {
        return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(SmbFundingPageModel.class);
    }

    public SmbReviewPageModel getSmbReviewPageModel() {
        return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(SmbReviewPageModel.class);
    }
    public SmbDisclosurePageModel getSmbDisclosurePageModel() {
        return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(SmbDisclosurePageModel.class);
    }

    public String getGmailUrl() {
        return prop.getProperty("gmailUrl");
    }

    public String getEmail() {
        return prop.getProperty("email");
    }

    public String getPassword() {
        return prop.getProperty("password");
    }

    public SeleniumDriver getSeleniumdriver() {
        return seleniumdriver;
    }

    public void setSeleniumdriver(SeleniumDriver seleniumdriver) {
        this.seleniumdriver = seleniumdriver;
    }

    public void wait(WebElement element){
        this.seleniumdriver = getSeleniumdriver();
        this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }
    public void wait(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            log.info("Waiting for element to be visible and clickable...");

            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            log.info("Element is visible and clickable: " + element);
        } catch (Exception e) {
            log.error("Error while waiting for element: " + e.getMessage());
            throw e;
        }
    }

    public void selectDropdownOption(WebDriver driver, WebElement optionElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait for option to be visible and clickable
        wait.until(ExpectedConditions.visibilityOf(optionElement));
        wait.until(ExpectedConditions.elementToBeClickable(optionElement));

        // Use Actions to move and click
        Actions actions = new Actions(driver);
        actions.moveToElement(optionElement).click().perform();
    }

    public void waitWithSpinner(WebElement element){
        this.seleniumdriver = getSeleniumdriver();
        this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/app-root/div/main/tf-spinner")));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void wait(String element){
        this.seleniumdriver = getSeleniumdriver();
        this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
    }

    public void waitWithSpinner(By locator){
        this.seleniumdriver = getSeleniumdriver();
        wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//tf-spinner")));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForSpinnerToDisappear() {
        this.seleniumdriver = getSeleniumdriver();
        this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(90));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//tf-spinner")));
    }

    public void waitInDisclosures(){
        this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(40));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/app-root/div/main/tf-spinner")));
    }

    public void selectElement(WebDriver driver, WebElement elementToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        new Actions(driver)
                .moveToElement(elementToSelect)
                .pause(Duration.ofMillis(600))
                .perform();
        // Wait until visible and clickable
        wait.until(ExpectedConditions.visibilityOf(elementToSelect));
        wait.until(ExpectedConditions.elementToBeClickable(elementToSelect));
        log.info("Selected element (dropdown value or checkbox)");
    }

    public void selectBusinessAddress(WebDriver driver, WebElement elementToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        new Actions(driver)
                .moveToElement(elementToSelect)
                .pause(Duration.ofMillis(2000))
                .perform();
        // Wait until visible and clickable
        wait.until(ExpectedConditions.visibilityOf(elementToSelect));
        wait.until(ExpectedConditions.elementToBeClickable(elementToSelect));
        log.info("Selected element (dropdown value or checkbox)");
    }

    public void waitForInnerSpinnersToDisappear(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By spinnerLocator = By.xpath("//tf-spinner[not(contains(@style,'display: none')) or not(contains(@class,'ng-hide'))]");

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
            log.info("Inner spinner have disappeared.");
        } catch (TimeoutException e) {
            log.warn("Timeout while waiting for inner spinner to disappear. Proceeding anyway.");
        }
    }

    public void waitUntilVisibleAndClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitAndScrollToVisibleElement(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Step 1: Wait until it's visible in the DOM
        wait.until(ExpectedConditions.visibilityOf(element));

        // Step 2: Scroll to it using Actions
        new Actions(driver)
                .moveToElement(element)
                .pause(Duration.ofMillis(200))
                .perform();
    }

    public void waitForVisibility(String element){
        this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
    }

    public void waitForVisibilityWithLoader(String element){
        this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/app-root/div/main/tf-spinner")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
    }

    public void waitForVisibility(By locator) {
        this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void scrollToElementAndWaitForScrollEnd(SeleniumDriver seleniumDriver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) seleniumDriver.getWebDriver();

        // Scroll into view using your existing method
        browserActions.scrollToWebElement(seleniumDriver, element);

        WebDriverWait wait = new WebDriverWait(seleniumDriver.getWebDriver(), Duration.ofSeconds(10));

        // Wait until scrollY value becomes stable (indicating scrolling finished)
        wait.until(driver -> {
            long scrollPosition1 = (long) js.executeScript("return window.pageYOffset;");
            try {
                // Wait 100ms using WebDriverWait's polling mechanism (not Thread.sleep)
                return new WebDriverWait(driver, Duration.ofMillis(1500), Duration.ofMillis(1000)).until(d -> {
                    long scrollPosition2 = (long) js.executeScript("return window.pageYOffset;");
                    return scrollPosition1 == scrollPosition2;
                });
            } catch (TimeoutException e) {
                return false;
            }
        });
        log.info("Scroll to element completed and page is stable.");
    }

    public void scrollToElementWithActionPause(SeleniumDriver seleniumDriver, WebElement element) {
        try {
            // Scroll to the element using your existing method
            browserActions.scrollToWebElement(seleniumDriver, element);

            // Use Actions to pause for 2.5 seconds
            Actions actions = new Actions(seleniumDriver.getWebDriver());
            actions.pause(Duration.ofMillis(2500)).perform();

            log.info("Scrolling complete");
        } catch (Exception e) {
            log.error("Error during scroll and pause", e);
        }
    }

    public void waitWithShortTime(SeleniumDriver seleniumDriver)
    {
        try
        {
//            browserActions.scrollToWebElement(seleniumDriver,element);
            Actions actions = new Actions(seleniumDriver.getWebDriver());
            actions.pause(Duration.ofMillis(1000)).perform();
        }
        catch(Exception e) {
            log.info("Erroe during wait="+e);
        }
    }

    public void waitWithLongTime(SeleniumDriver seleniumDriver)
    {
        try
        {
//            browserActions.scrollToWebElement(seleniumDriver,element);
            Actions actions = new Actions(seleniumDriver.getWebDriver());
            actions.pause(Duration.ofMillis(2700)).perform();
        }
        catch(Exception e) {
            log.info("Erroe during wait="+e);
        }
    }
    public void letSpinnerDisappear()
    {
        waitForSpinnerToDisappear();
    }

}
