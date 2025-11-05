package com.cztffa.cucumber.parallel.smb;


import com.cztffa.browseractions.BrowserActions;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.page.review.SmbReviewPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertTrue;

@Slf4j

public class SmbDocumentPageStepDefinition {
    private SeleniumDriver seleniumdriver;
    private BrowserActions browserActions;
    private SmbReviewPage smbReviewPage;


    public SmbDocumentPageStepDefinition(SmbReviewPage smbReviewPage) {
        this.seleniumdriver = smbReviewPage.getSeleniumdriver();
        this.browserActions = smbReviewPage.browserActions;
        this.smbReviewPage=smbReviewPage;
    }

    @And("^: I should see the documents page$")
    public void navigateToDocumentsPage() throws Throwable {
        smbReviewPage.waitForVisibilityWithLoader("//*[contains(text(),'Provide specified documents')]");
        smbReviewPage.waitForSpinnerToDisappear();
        log.info("i am going looking at documents page");
        assertTrue(seleniumdriver.getWebDriver().getPageSource().contains("Business"));
        log.info("I am at documents page ");
    }

    @Then("^: I click on documents page next button$")
    public void clickDocumentsNextButton() throws Throwable {
        smbReviewPage.waitForSpinnerToDisappear();
        log.info("Document page next button");
        smbReviewPage.wait(smbReviewPage.getSmbPersonalInfoPageModel().personalInfoNextButon);
        browserActions.scrollToWebElement(seleniumdriver,smbReviewPage.getSmbPersonalInfoPageModel().personalInfoNextButon);
        browserActions.clickUsingEnter(seleniumdriver.getWebDriver(), smbReviewPage.getSmbPersonalInfoPageModel().personalInfoNextButon);
        log.info("Document page next button clicked");
    }
}
