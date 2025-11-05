package com.cztffa.cucumber.parallel.consumer;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.dataproviders.DataCSVExtractor;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.page.review.ReviewPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;

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

        reviewPage.waitForSpinnerToDisappear();
        reviewPage.waitForVisibilityWithLoader("//*[contains(text(),'You are almost done')]");
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
}
