package com.cztffa.cucumber.parallel.consumer;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.dataproviders.DataCSVExtractor;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.page.review.ReviewPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;
import java.util.UUID;

@Slf4j
public class ReviewPageStepDefinition {

    private ReviewPage reviewPage;
    private SeleniumDriver seleniumdriver;
    private BrowserActions browserActions;

    public ReviewPageStepDefinition(ReviewPage reviewPage) {
        this.reviewPage = reviewPage;
        this.browserActions = reviewPage.browserActions;
        this.seleniumdriver = reviewPage.getSeleniumdriver();
    }

    @Then("^: I should accept the all terms and conditions (\\d+)$")
    public void acceptAllTermsAndConditions(int numberOfDisclosure) throws Throwable {
        log.info(" Starting of T&C Page");
Thread.sleep(1000);
        reviewPage.waitForSpinnerToDisappear();
      //  reviewPage.waitForVisibilityWithLoader("//*[contains(text(),'You are almost done')]");
        reviewPage.spinner();

        reviewPage.scrollToElementWithActionPause(seleniumdriver,reviewPage.getReviewPageModel().termAndConditions);
        browserActions.clickButton(seleniumdriver, reviewPage.getReviewPageModel().termAndConditions);
        log.info("First checkbox selected");

        int index = 2;
        while(true) {
            try {
                String termAndConditions = "(//tf-checkbox[@t-model='Accepted__c']//input)[" + index + "]";
                log.info(termAndConditions);
                WebElement checkbox;
                try {
                    checkbox = seleniumdriver.getWebDriver().findElement(By.xpath(termAndConditions));
                    browserActions.scrollToWebElement(seleniumdriver, checkbox);
                    browserActions.clickButton(seleniumdriver, checkbox);
                    log.info("Selected checkbox: " +index);
                    index++;
                } catch (Exception e) {
                    break;
                }
            } catch (NoSuchElementException e) {
                log.info("No more checkboxes found. Exiting loop.");
                break;
            }
        }
    }

    @And("^: I click on review details submit button$")
    public void clickReviewDetailsNextButton() throws Throwable {
        browserActions.scrollToWebElement(seleniumdriver, reviewPage.getReviewPageModel().submitButton);
        browserActions.clickUsingEnter(seleniumdriver.getWebDriver(), reviewPage.getReviewPageModel().submitButton);
        log.info("Application submitted");
        Thread.sleep(14000);
    }

    @Then(": I should see the reference Id page.")
    public void i_should_see_the_reference_id_page() throws Throwable {
        DataCSVExtractor.applicantCount++;
        reviewPage.spinner();
        assertTrue(reviewPage.getReviewPageModel().successMsg.isDisplayed());
        log.info("Successfully completed the flow");
    }



    @Then(": I should see the review page.")
    public void iShouldSeeTheReviewPage() throws Throwable {
        DataCSVExtractor.applicantCount++;
        reviewPage.spinner();
        assertTrue(reviewPage.getReviewPageModel().reviewpageloadmsg.isDisplayed());
        log.info("Successfully loaded a review page");
    }

    @Then(": I complete OLB registration for primary applicant")
    public void i_should_see_the_OLB_registration() throws Throwable {

        reviewPage.spinner();
        String baseUsername = "testUser";
        String uniqueUsername = baseUsername + "_" + UUID.randomUUID().toString().substring(0, 8); // 8-char unique suffix
        String password = "TestPassword@123";

        log.info("Entering username: " + uniqueUsername);
        browserActions.scrollToWebElement(seleniumdriver, reviewPage.getReviewPageModel().olbusername);
         Thread.sleep(1000);
        browserActions.enterText(seleniumdriver, reviewPage.getReviewPageModel().olbusername, uniqueUsername);
        log.info("Entering password");
        browserActions.enterText(seleniumdriver, reviewPage.getReviewPageModel().olbpassword, password);
        log.info("Entering retype password");
        browserActions.enterText(seleniumdriver, reviewPage.getReviewPageModel().olbrenterpassword, password);
        log.info("clicking on olb registration  accept checkbox");
        browserActions.clickButton(seleniumdriver, reviewPage.getReviewPageModel().olbregisteracceptbtn);
        log.info("OLb registration checkbox selected");
        Thread.sleep(1000);
        browserActions.clickButton(seleniumdriver, reviewPage.getReviewPageModel().Registerbutton);
        Thread.sleep(1000);
        reviewPage.spinner();
        assertTrue(reviewPage.getReviewPageModel().olbregistrationsuccessmsg.isDisplayed());
       log.info("Successfully completed the flow");
    }
    }

