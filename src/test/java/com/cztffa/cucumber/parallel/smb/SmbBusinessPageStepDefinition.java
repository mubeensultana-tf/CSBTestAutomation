package com.cztffa.cucumber.parallel.smb;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.dataproviders.DataCSVExtractor;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.objects.Business;
import com.cztffa.objects.Person;
import com.cztffa.objects.StepData;
import com.cztffa.objects.Validation;
import com.cztffa.page.personalinfo.SmbPersonalInfoPage;
import com.cztffa.page.review.SmbReviewPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@Slf4j
public class SmbBusinessPageStepDefinition {
    private SeleniumDriver seleniumdriver;
    private BrowserActions browserActions;
    private StepData stepData;
    private SmbReviewPage smbReviewPage;


    public SmbBusinessPageStepDefinition(StepData stepData, SmbReviewPage smbReviewPage) {
        this.stepData = stepData;
        this.seleniumdriver = smbReviewPage.getSeleniumdriver();
        this.browserActions = smbReviewPage.browserActions;
        this.smbReviewPage=smbReviewPage;
    }

    @When(": I provide the below business details for {string}")
    public void businessDetails(String submissionId) throws Throwable {

        List<Map<?,?>> businessDataList = DataCSVExtractor.businessDataStore;
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < businessDataList.size(); i++) {
            Map<?,?> row = businessDataList.get(i);
            if (row.get("submissionId").equals(submissionId)) {
                JSONObject jsonObject = new JSONObject(row);
                Business business = objectMapper.readValue(jsonObject.toString(), Business.class);
                Validation validation= new Validation();
                validation.setSkipPreferredContactDropdown(true);
                validation.setSkipEmploymentStatusDropdown(true);
                log.info("validation before setting "+validation);
                stepData.setValidation(validation);
                business.setValidation(stepData.getValidation());
                smbReviewPage.addBusinessDetailForSmb(business, 0);
                log.info("Business Information added for submissionId: " + submissionId);
                break;
            }
        }
    }

    @Then("^: I click on business details next button$")
    public void clickBusinesslDetailNextButton() throws Throwable {
        smbReviewPage.waitForSpinnerToDisappear();
        browserActions.scrollToWebElement(seleniumdriver,smbReviewPage.getBusinessInfoPageModel().businessInfoNextButon);
        Thread.sleep(1000);
        smbReviewPage.waitWithSpinner(smbReviewPage.getBusinessInfoPageModel().businessInfoNextButon);
        browserActions.clickButton(seleniumdriver, smbReviewPage.getBusinessInfoPageModel().businessInfoNextButon);
        smbReviewPage.waitForSpinnerToDisappear();
    }


    @And("^: I should navigate to business details page$")
    public void navigateToBusinesslDetailsPage() throws Throwable {
        smbReviewPage.waitForSpinnerToDisappear();
        log.info(" i am on business info page");
        assertTrue(seleniumdriver.getWebDriver().getPageSource().contains("Business"));
        log.info(" business info page validated");
    }
}
