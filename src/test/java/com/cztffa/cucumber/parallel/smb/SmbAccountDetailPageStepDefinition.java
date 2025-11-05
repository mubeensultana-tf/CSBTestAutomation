package com.cztffa.cucumber.parallel.smb;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.page.review.SmbReviewPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertTrue;

@Slf4j

public class SmbAccountDetailPageStepDefinition {
    private SeleniumDriver seleniumdriver;
    private BrowserActions browserActions;
    private SmbReviewPage smbReviewPage;

    public SmbAccountDetailPageStepDefinition(SmbReviewPage smbReviewPage) {
        this.seleniumdriver = smbReviewPage.getSeleniumdriver();
        this.browserActions = smbReviewPage.browserActions;
        this.smbReviewPage=smbReviewPage;
    }
    @And("^: I should see the account detail page$")
    public void navigateToAccountDetailsPage() throws Throwable {
        smbReviewPage.letSpinnerDisappear();
        log.info("i am on account details page");
        assertTrue(seleniumdriver.getWebDriver().getPageSource().contains("Business"));
    }
    @Then("^: I should fill up the account details page$")
    public void fillAccountDetails() throws Throwable {
        smbReviewPage.fillAccountDetails();
    }

    @Then("^: I click on account details next page$")
    public void clickAccountDetailNextButton() throws Throwable {
        smbReviewPage.letSpinnerDisappear();
        smbReviewPage.waitAndScrollToVisibleElement(seleniumdriver.getWebDriver(),smbReviewPage.getSmbAccountDetailsPageModel().accountDetailsNextButon);
        browserActions.scrollToWebElement(seleniumdriver,smbReviewPage.getSmbAccountDetailsPageModel().accountDetailsNextButon);
        smbReviewPage.waitUntilVisibleAndClickable(seleniumdriver.getWebDriver(),smbReviewPage.getSmbAccountDetailsPageModel().accountDetailsNextButon);
        smbReviewPage.waitWithSpinner(smbReviewPage.getSmbAccountDetailsPageModel().accountDetailsNextButon);
        browserActions.clickUsingEnter(seleniumdriver.getWebDriver(), smbReviewPage.getSmbAccountDetailsPageModel().accountDetailsNextButon);
        smbReviewPage.waitForSpinnerToDisappear();
    }
}
