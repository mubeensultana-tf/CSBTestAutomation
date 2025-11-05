package com.cztffa.cucumber.parallel.smb;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.objects.StepData;
import com.cztffa.page.review.SmbReviewPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import static org.junit.Assert.assertTrue;

@Slf4j

public class SmbHomePageStepDefinition {

    private SeleniumDriver seleniumdriver;
    private BrowserActions browserActions;
    private SmbReviewPage smbReviewPage;
    private StepData stepData;

    public SmbHomePageStepDefinition(SmbReviewPage smbReviewPage) {
        this.browserActions = smbReviewPage.browserActions;
        this.smbReviewPage = smbReviewPage;
        this.stepData = stepData;
    }

    @Before("@SMBFlow")
    public void setUp(Scenario scenario) {
        log.info("Starting scenario {} ", scenario.getName());
    }

    @Given(": I am at the smb home page")
    public void iAmAtTheSmbHomePage() {
        try {
            log.info("Starting of home page");
            String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
            String channel = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("channel");
            smbReviewPage.launch(channel,browser);
            this.seleniumdriver = smbReviewPage.getSeleniumdriver();
            log.info("loaded home page ");
        } catch (Exception ex) {
            log.error("Failed in home page loading");
        }
    }

    @When(": I click on the business tab$")
    public void i_click_on_the_business_tab() throws InterruptedException {
        log.info("Before click of business tab");
        Thread.sleep(3000);
        smbReviewPage.waitForSpinnerToDisappear();
        smbReviewPage.waitForSpinnerToDisappear();
        smbReviewPage.waitWithSpinner(smbReviewPage.getSmbProductSelectorPageModel().clickBusinessTab);
        browserActions.clickButton(seleniumdriver,smbReviewPage.getSmbProductSelectorPageModel().clickBusinessTab);
    }

    @Then(": I should navigate to getting started page for smb")
    public void iShouldNavigateToGettingStartedPageForSmb() throws InterruptedException {

        log.info("Before getting started page");
        Thread.sleep(1000);
        smbReviewPage.waitForSpinnerToDisappear();
        assertTrue(seleniumdriver.getWebDriver().getPageSource().contains("Personal Information"));
        log.info("On getting started page");

    }

    @After("@SMBFlow")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) seleniumdriver.getWebDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        log.info("Finished scenario {}", scenario.getName());
        log.info("Final line it got executed {} ", scenario.getLine());
        log.info("Status of the scenario {} ", scenario.getStatus());
        seleniumdriver.getWebDriver().quit();
    }

}
