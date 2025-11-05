package com.cztffa.cucumber.parallel.smb;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.dataproviders.DataCSVExtractor;
import com.cztffa.driver.SeleniumDriver;

import com.cztffa.page.review.SmbReviewPage;
import com.cztffa.xpath.business.Product;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Slf4j

public class SmbProductSelectorStepDefinition {

    private SeleniumDriver seleniumdriver;
    private BrowserActions browserActions;
    private SmbReviewPage smbReviewPage;

    public SmbProductSelectorStepDefinition(SmbReviewPage smbReviewPage) {
        this.seleniumdriver = smbReviewPage.getSeleniumdriver();
        this.browserActions = smbReviewPage.browserActions;
        this.smbReviewPage = smbReviewPage;
    }

    @Then(": I click on smb products for {string}")
    public void iClickOnSmbProducts(String submissionId) throws InterruptedException {
        List<Map<?,?>> productDataStore = DataCSVExtractor.smbproductDataStore;

        log.info("Clicking all products for submissionId: " + submissionId);

        for (Map<?,?> map : productDataStore) {
            String rowSubmissionId = map.get("submissionId").toString().trim();
            if (!rowSubmissionId.equals(submissionId)) continue;
            String product = map.get("product").toString().trim();
            String xpath = Product.getByName(product).getXpath();
            smbReviewPage.wait(xpath);
            WebElement element = browserActions.findElement(seleniumdriver, By.xpath(xpath));
            browserActions.scrollToWebElement(seleniumdriver, element);
            smbReviewPage.waitWithSpinner(element);
            browserActions.clickUsingEnter(seleniumdriver.getWebDriver(),element);
            log.info("Clicked product: " + product);
        }
    log.info("Clicked on all products");
    }
        @And(": I click on the checkout for smb")
        public void iClickOnTheCheckoutForSmb() throws InterruptedException {
            smbReviewPage.waitForSpinnerToDisappear();
            log.info("On click of checkout btn");
            smbReviewPage.waitWithSpinner(smbReviewPage.getSmbProductSelectorPageModel().checkOutBtn);
            browserActions.clickButton(seleniumdriver, smbReviewPage.getSmbProductSelectorPageModel().checkOutBtn);
            log.info("After click of checkout btn");
            smbReviewPage.waitForSpinnerToDisappear();
            smbReviewPage.waitWithSpinner(smbReviewPage.getSmbProductSelectorPageModel().proceedBtn);
            browserActions.clickButton(seleniumdriver, smbReviewPage.getSmbProductSelectorPageModel().proceedBtn);
            log.info("After click of proceed btn");
        }



    @Then(": I click on proceed without prefill for smb")
    public void iClickOnProceedWithoutPrefillForSmb() {
        log.info("Before clicking on proceed without prefill button");
        smbReviewPage.waitForSpinnerToDisappear();
        WebElement proceedBtn = smbReviewPage.getSmbProductSelectorPageModel().proceedWithoutPrefillBtn;
        WebDriverWait wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(proceedBtn));
        wait.until(ExpectedConditions.elementToBeClickable(proceedBtn));
        log.info("Waited till proceedWithoutPrefillBtn");
        browserActions.scrollToWebElement(seleniumdriver, proceedBtn);
        smbReviewPage.wait(proceedBtn);
        browserActions.clickUsingEnter(seleniumdriver.getWebDriver(), proceedBtn);
        log.info("After clicking on proceed without prefill button");
    }
}
