package com.cztffa.cucumber.parallel.smb;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.page.review.ReviewPage;
import com.cztffa.page.review.SmbReviewPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertTrue;

@Slf4j
public class SmbRelationPageStepDefinition {

    private ReviewPage reviewPage;
    private SeleniumDriver seleniumdriver;
    private BrowserActions browserActions;
    private SmbReviewPage smbReviewPage;

    public SmbRelationPageStepDefinition(ReviewPage reviewPage, SmbReviewPage smbReviewPage) {
        this.reviewPage = reviewPage;
        this.seleniumdriver = smbReviewPage.getSeleniumdriver();
        this.browserActions = smbReviewPage.browserActions;
        this.smbReviewPage=smbReviewPage;
    }

    @And("^: I should see the relations page$")
    public void navigateToRelationsPage() throws Throwable {
        smbReviewPage.letSpinnerDisappear();
        log.info("i am on relation page");
        assertTrue(seleniumdriver.getWebDriver().getPageSource().contains("Business"));
    }

    @Then("^: I click on relations page next button$")
    public void clickRelationsDetailNextButton() throws Throwable {
        smbReviewPage.letSpinnerDisappear();
        smbReviewPage.clickRelationsDetailNextButtonHelper();
    }
}
