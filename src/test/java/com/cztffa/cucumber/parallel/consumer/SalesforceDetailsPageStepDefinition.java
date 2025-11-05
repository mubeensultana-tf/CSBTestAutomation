package com.cztffa.cucumber.parallel.consumer;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.dataproviders.DataCSVExtractor;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.objects.Salesforce;
import com.cztffa.page.review.ReviewPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SalesforceDetailsPageStepDefinition {

    private ReviewPage reviewPage;
    private SeleniumDriver seleniumdriver;
    private BrowserActions browserActions;
    public Properties prop;

    public SalesforceDetailsPageStepDefinition(ReviewPage reviewPage) {
        this.reviewPage = reviewPage;
        this.seleniumdriver = reviewPage.getSeleniumdriver();
        this.browserActions = reviewPage.browserActions;
        this.prop = reviewPage.prop;
    }

    @Then("^: I provide my salesforce account details$")
    public void iClosePromotionCloseButton() throws Throwable {

        WebDriver driver = seleniumdriver.getWebDriver();
        String originalWindow = driver.getWindowHandle();
        List<Map<?,?>> salesforceList = DataCSVExtractor.salesforceDataStore;
        ObjectMapper objectMapper = new ObjectMapper();

        for (int i = 0; i < salesforceList.size(); i++) {
            Map<?,?> row = salesforceList.get(i);
//            if (row.get("submissionId").equals(submissionId)) {
            JSONObject jsonObject = new JSONObject(row);
            Salesforce salesforce = objectMapper.readValue(jsonObject.toString(), Salesforce.class);

            log.info("Entering username");
            browserActions.enterText(seleniumdriver, reviewPage.getSalesforceDetailsPageModel().username, prop.getProperty("email"));

            log.info("Entering password");
            browserActions.enterText(seleniumdriver, reviewPage.getSalesforceDetailsPageModel().password, prop.getProperty("password"));

            log.info("Clicking button");
            browserActions.clickButton(seleniumdriver, reviewPage.getSalesforceDetailsPageModel().logInbutton);

//            Extract OTP from outlook
//            if(seleniumdriver.getWebDriver().getPageSource().contains("Verify Your Identity")) {
//
//                log.info("Entering Verify Your Identity");
//                ((JavascriptExecutor) seleniumdriver.getWebDriver()).executeScript("window.open('https://outlook.office365.com', '_blank');");
//                Thread.sleep(10000);
//                ArrayList<String> tabs = new ArrayList<>(seleniumdriver.getWebDriver().getWindowHandles());
//                seleniumdriver.getWebDriver().switchTo().window(tabs.get(1));
//
//                log.info("Switched to new tab");
//
//                WebElement firstEmail = driver.findElement(By.xpath("//div[@role='option' and contains(@aria-label, 'Unread') and contains(@aria-label, 'Verify your identity in Salesforce')]"));
//                firstEmail.click();
//
//                log.info("Clicked on first email");
//
//                Thread.sleep(2000);
//
//                WebElement preTag = driver.findElement(By.tagName("pre"));
//                String emailText = preTag.getText();
//                Pattern pattern = Pattern.compile("Verification Code:\\s*(\\d+)");
//                Matcher matcher = pattern.matcher(emailText);
//                String otp = matcher.group(1);
//                log.info("Extracted OTP: " + otp);
//                Thread.sleep(2000);
//
//                seleniumdriver.getWebDriver().switchTo().window(tabs.get(0));
//                WebElement otpInput = driver.findElement(By.id("//label[contains(text(),'Verification Code')]//following-sibling::div//input[@type='text']"));
//                Thread.sleep(2000);
//
//                otpInput.sendKeys(otp);
//
//            }

            log.info("Clicking app launcher");
            reviewPage.waitForVisibility("//*[contains(text(),'Home')]");
            Thread.sleep(1000);
            browserActions.clickButton(seleniumdriver, reviewPage.getSalesforceDetailsPageModel().appLauncher);
            Thread.sleep(2000);

            log.info("Entering operations");
            browserActions.enterTextKeyEntry(seleniumdriver, reviewPage.getSalesforceDetailsPageModel().search, salesforce.getTerafina());
            reviewPage.waitForVisibility("//*[contains(text(),'Operation')]");
            log.info("Clicking terafina operations");
            browserActions.clickButton(seleniumdriver, reviewPage.getSalesforceDetailsPageModel().tfOperations);

            reviewPage.waitForVisibility("//span[contains(text(),'Home')]");
            log.info("Clicking Home Menu button");
            browserActions.clickButton(seleniumdriver, reviewPage.getSalesforceDetailsPageModel().homeMenu);
            Thread.sleep(1000);

            log.info("Clicking Home button");
            browserActions.clickButton(seleniumdriver, reviewPage.getSalesforceDetailsPageModel().home);
            Thread.sleep(1000);

            log.info("Clicking Start application");
            browserActions.clickButton(seleniumdriver, reviewPage.getSalesforceDetailsPageModel().startApplication);
            Thread.sleep(7000);

            //Switching tab
            log.info(seleniumdriver.getWindowHandles());
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                }
            }
        }
    }
}
