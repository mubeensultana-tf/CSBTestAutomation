package com.cztffa.cucumber.parallel.smb;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.page.review.SmbReviewPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertTrue;

@Slf4j
public class SmbMemberDiligencePageStepDefinition {

    private SeleniumDriver seleniumdriver;
    private BrowserActions browserActions;
    private SmbReviewPage smbReviewPage;

    public SmbMemberDiligencePageStepDefinition(SmbReviewPage smbReviewPage) {

        this.seleniumdriver = smbReviewPage.getSeleniumdriver();
        this.browserActions = smbReviewPage.browserActions;
        this.smbReviewPage=smbReviewPage;
    }
    @And("^: I should see the customer due diligence page$")
    public void navigateToDueDiligencePage() throws Throwable {

        log.info("i am on Due dillegence page");
        smbReviewPage.letSpinnerDisappear();
        smbReviewPage.waitForVisibilityWithLoader("//*[contains(text(),'We need some additional information')]");
        assertTrue(seleniumdriver.getWebDriver().getPageSource().contains("Business"));
    }

    @When("^: I provide below due diligence details$")
    public void dueDiligenceDetails() throws Throwable {
        log.info("providing due diligence detail");
        smbReviewPage.letSpinnerDisappear();
        smbReviewPage.completeDueDiligence();
    }
    @Then("^: I click on due diligence next button$")
    public void clickDueDiligenceNextButton() throws Throwable {
        browserActions.scrollToWebElement(seleniumdriver,smbReviewPage.getMemberDiligencePageModel().personalInfoNextButon);
        smbReviewPage.waitWithSpinner(smbReviewPage.getMemberDiligencePageModel().personalInfoNextButon);
        browserActions.clickButton(seleniumdriver, smbReviewPage.getMemberDiligencePageModel().personalInfoNextButon);
    }

    @Then(": I close promotion close button for smb")
    public void iClosePromotionCloseButtonForSmb() throws InterruptedException {
        smbReviewPage.waitForSpinnerToDisappear();
        try {
            smbReviewPage.wait(smbReviewPage.getMemberDiligencePageModel().closeButton);
            browserActions.scrollToWebElement(smbReviewPage.getSeleniumdriver(), smbReviewPage.getMemberDiligencePageModel().closeButton);
            smbReviewPage.waitWithSpinner(smbReviewPage.getMemberDiligencePageModel().closeButton);
            browserActions.clickButton(seleniumdriver, smbReviewPage.getMemberDiligencePageModel().closeButton);
        } catch (Exception ex) {
            log.error("failed to close the close button");
        }
    }

}
