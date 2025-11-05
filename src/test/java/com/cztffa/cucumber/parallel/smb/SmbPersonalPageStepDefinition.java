package com.cztffa.cucumber.parallel.smb;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.dataproviders.DataCSVExtractor;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.objects.Person;
import com.cztffa.objects.StepData;
import com.cztffa.objects.Validation;
import com.cztffa.page.review.SmbReviewPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@Slf4j

public class SmbPersonalPageStepDefinition {
    private SeleniumDriver seleniumdriver;
    private BrowserActions browserActions;
    private StepData stepData;
    private SmbReviewPage smbReviewPage;

    public SmbPersonalPageStepDefinition(StepData stepData, SmbReviewPage smbReviewPage) {
        this.stepData = stepData;
        this.seleniumdriver = smbReviewPage.getSeleniumdriver();
        this.browserActions = smbReviewPage.browserActions;
        this.smbReviewPage=smbReviewPage;
    }

    @When(": I provide the following details for smb for {string}")
    public void iProvideTheFollowingDetailsForSmb(String submissionId) throws InterruptedException, JsonProcessingException {
        log.info("Before filling the details");
        List<Map<?,?>> applicantDataList = DataCSVExtractor.businessPersonDataStore;
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < applicantDataList.size(); i++) {
            Map<?,?> row = applicantDataList.get(i);
            if (row.get("submissionId").equals(submissionId)) {
                JSONObject jsonObject = new JSONObject(row);
                Person person = objectMapper.readValue(jsonObject.toString(), Person.class);
                log.info("Entering details: "+person);
                person.setValidation(stepData.getValidation());
                smbReviewPage.fillDetailsForSMB(person);
                log.info("Details entered");
                log.info("Personal Information added for submissionId: " + submissionId);
                break;
            }
        }
    }

    @When(": I provide the below business personal details for {string}")
    public void businesspersonalDetails(String submissionId) throws Throwable {
        smbReviewPage.waitForSpinnerToDisappear();
        List<Map<?,?>> applicantDataList = DataCSVExtractor.businessPersonDataStore;
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < applicantDataList.size(); i++) {
            Map<?,?> row = applicantDataList.get(i);
            if (row.get("submissionId").equals(submissionId)) {
                JSONObject jsonObject = new JSONObject(row);
                Person person = objectMapper.readValue(jsonObject.toString(), Person.class);
                Validation validation= new Validation();
                validation.setSkipPreferredContactDropdown(true);
                validation.setSkipEmploymentStatusDropdown(true);
                log.info("validation before setting "+validation);
                stepData.setValidation(validation);
                person.setValidation(stepData.getValidation());
                stepData.setPerson(person);
                smbReviewPage.addApplicantForSMB(person, 0);
                log.info("Personal Information added for submissionId: " + submissionId);
                break;
            }
        }

    }
    @And(": I provide the below additional applicant details for smb for {string}")
    public void iProvideTheBelowAdditionalApplicantDetailsForSmb(String submissionId) throws InterruptedException, JsonProcessingException {

        List<Map<?, ?>> applicantDataStore = DataCSVExtractor.businessPersonDataStore;
        int totalApplicants = 0;
        List<Map<String, String>> matchingApplicants = new ArrayList<>();
        for (Map<?, ?> row : applicantDataStore) {
            Object submissionIdObj = row.get("submissionId");
            if (submissionIdObj != null && submissionIdObj.toString().trim().equals(submissionId)){
                matchingApplicants.add((Map<String, String>) row);
                totalApplicants++;
            }
        }
        log.info("Total applicants for submissionId " + submissionId + ": " + matchingApplicants.size());
        int currentIndex = 0;
        for (int i = 1; i < matchingApplicants.size(); i++) {
            Map<String, String> row = matchingApplicants.get(i);
            log.info("CurrentIndex: " + currentIndex);
            int targetIndex = currentIndex + 1;
            if (targetIndex >= totalApplicants) break;

            log.info("applicantDataStore get: "+ applicantDataStore.get(targetIndex).toString());
            browserActions.scrollToWebElement(seleniumdriver,smbReviewPage.getSmbPersonalInfoPageModel().addAdditionalApplicantButton);
            smbReviewPage.waitWithSpinner(smbReviewPage.getSmbPersonalInfoPageModel().addAdditionalApplicantButton);
            browserActions.clickButton(seleniumdriver, smbReviewPage.getSmbPersonalInfoPageModel().addAdditionalApplicantButton);
            Thread.sleep(1000);
            smbReviewPage.waitForSpinnerToDisappear();

            String pageSource = seleniumdriver.getWebDriver().getPageSource();
            String textToCheck = "Proceed without prefill";
            if (pageSource.contains(textToCheck)) {
                browserActions.scrollToWebElement(seleniumdriver, smbReviewPage.getSmbPersonalInfoPageModel().proceedWithoutPrefillBtn);
                smbReviewPage.waitWithSpinner(smbReviewPage.getSmbPersonalInfoPageModel().proceedWithoutPrefillBtn);
                browserActions.clickButton(seleniumdriver, smbReviewPage.getSmbPersonalInfoPageModel().proceedWithoutPrefillBtn);
                log.info("Clicked on Proceed Without prefill");
                smbReviewPage.waitForSpinnerToDisappear();
            }

            JSONObject jsonObject = new JSONObject(row);
            ObjectMapper objectMapper = new ObjectMapper();
            Person person = objectMapper.readValue(jsonObject.toString(), Person.class);

            Validation validation = new Validation();
            validation.setSkipPreferredContactDropdown(true);
            validation.setSkipEmploymentStatusDropdown(true);

            stepData.setValidation(validation);
            person.setValidation(validation);
            stepData.setPerson(person);

            smbReviewPage.addApplicantsForSmb(person, targetIndex+1);
            DataCSVExtractor.applicantCount++;
            currentIndex++;
            log.info("Added applicant index: " + targetIndex);
        }
    }

    @Then(": I click on personal details next button for smb")
    public void iClickOnPersonalDetailsNextButtonForSmb() throws InterruptedException {

        log.info("Clicking on personal Info Next button");
        smbReviewPage.waitForSpinnerToDisappear();
        smbReviewPage.waitWithSpinner(smbReviewPage.getSmbPersonalInfoPageModel().personalInfoNextButon);
        smbReviewPage.waitForSpinnerToDisappear();
        browserActions.scrollToWebElement(seleniumdriver,smbReviewPage.getSmbPersonalInfoPageModel().personalInfoNextButon);
        smbReviewPage.waitWithSpinner(smbReviewPage.getSmbPersonalInfoPageModel().personalInfoNextButon);
        browserActions.clickApply(seleniumdriver.getWebDriver(), smbReviewPage.getSmbPersonalInfoPageModel().personalInfoNextButon);
    }

    @And(": I should navigate to business details personal page")
    public void iShouldNavigateToBusinessDetailsPersonalPage() throws InterruptedException {
        smbReviewPage.waitForSpinnerToDisappear();
        log.info(" i am on business info page");
        assertTrue(seleniumdriver.getWebDriver().getPageSource().contains("Business"));
        log.info(" business info page validated");
    }

    @And(": I click on start Application for smb")
    public void iClickOnStartApplicationForSmb() {
        log.info("after start button  click");
        smbReviewPage.waitForSpinnerToDisappear();
        smbReviewPage.wait(smbReviewPage.getBusinessInfoPageModel().startApplicationButton);
        browserActions.scrollToWebElement(seleniumdriver, smbReviewPage.getBusinessInfoPageModel().startApplicationButton);
        browserActions.clickUsingEnter(seleniumdriver.getWebDriver(), smbReviewPage.getBusinessInfoPageModel().startApplicationButton);
        log.info("after start button  click");
    }
}
