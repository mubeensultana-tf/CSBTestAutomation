package com.cztffa.cucumber.parallel.consumer;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.dataproviders.DataCSVExtractor;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.objects.*;
import com.cztffa.page.review.ReviewPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DataTableType;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@Slf4j
public class DocumentPageStepDefinition {
    private ReviewPage reviewPage;
    private SeleniumDriver seleniumdriver;
    private BrowserActions browserActions;
    private StepData stepData;

    public DocumentPageStepDefinition(ReviewPage reviewPage, StepData stepData) {
        this.stepData = stepData;
        this.reviewPage = reviewPage;
        this.seleniumdriver = reviewPage.getSeleniumdriver();
        this.browserActions = reviewPage.browserActions;
    }

    @Then("^: I should see the consumer documents page$")

        public void navigateToDocumentsPage() throws Throwable {
            reviewPage.waitForVisibilityWithLoader("//*[contains(text(),'Provide specified documents')]");
            reviewPage.waitForSpinnerToDisappear();
            log.info("i am going looking at documents page");
            assertTrue(seleniumdriver.getWebDriver().getPageSource().contains("Provide specified documents"));
            log.info("I am at documents page");
        }


//    @Then("^: I click on documents page next button$")
//    public void clickDocumentsNextButton() throws Throwable {
//        reviewPage.waitForSpinnerToDisappear();
//        log.info("Document page next button");
//        reviewPage.wait(reviewPage.getDocumentPageModel().documentNextButton);
//        browserActions.scrollToWebElement(seleniumdriver,reviewPage.getDocumentPageModel().documentNextButton);
//        browserActions.clickUsingEnter(seleniumdriver.getWebDriver(), reviewPage.getDocumentPageModel().documentNextButton);
//        log.info("Document page next button clicked");
//    }

    @Then(": I upload requested documents")
    public void iUploadRequestedDocuments() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        reviewPage.waitForSpinnerToDisappear();
        Thread.sleep(1000);
        reviewPage.waitForSpinnerToDisappear();
        try {


            reviewPage.waitForSpinnerToDisappear();
            log.info("I am at documents page");

            // Define file paths (extend if needed)
            String[] files = {
                    "C:\\Users\\ms185944\\IdeaProjects\\CambridgeTest\\cucumber\\IDFiles\\license_front_a.JPG",
                    "C:\\Users\\ms185944\\IdeaProjects\\CambridgeTest\\cucumber\\IDFiles\\license_front_h.jpeg"
            };

            // Find all upload buttons
            List<WebElement> uploadButtons = seleniumdriver.getWebDriver()
                    .findElements(By.xpath("//button[contains(text(),'Upload')]"));

            log.info("Total Upload buttons found: " + uploadButtons.size());

            // Iterate over each Upload button
            for (int i = 0; i < uploadButtons.size(); i++) {
                log.info("Clicking Upload button " + (i + 1) + " of " + uploadButtons.size());

                WebElement uploadBtn = uploadButtons.get(i);
                browserActions.scrollToWebElement(seleniumdriver, uploadBtn);
                Thread.sleep(1000);
                uploadBtn.click();
                Thread.sleep(3000);

                // Wait for Browse button to appear inside popup
                WebDriverWait wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(10));
                WebElement browseInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//input[@type='file' and contains(@id,'tf-file-capture')]")));
                Thread.sleep(4000);

                // Upload the file
                if (i < files.length) {
                    browseInput.sendKeys(files[i]);
                    log.info("Uploaded file: " + files[i]);
                    reviewPage.waitForSpinnerToDisappear();
                    Thread.sleep(1000);
                    reviewPage.waitForSpinnerToDisappear();
                    Thread.sleep(1000);

//                        Thread.sleep(12000);

                } else {
                    log.warn("No file specified for Upload button " + (i + 1));
                }

                // Wait for popup to close (adjust depending on behavior)
                reviewPage.waitForSpinnerToDisappear();
                Thread.sleep(2000);
            }
            reviewPage.waitForSpinnerToDisappear();
            log.info("Document page next button");
            reviewPage.wait(reviewPage.getDocumentPageModel().documentNextButton);
            browserActions.scrollToWebElement(seleniumdriver,reviewPage.getDocumentPageModel().documentNextButton);
            browserActions.clickUsingEnter(seleniumdriver.getWebDriver(),reviewPage.getDocumentPageModel().documentNextButton);
            log.info("Document page next button clicked");

        } catch (Exception e) {
            log.info("Document Page not found");
        }
    }
}






