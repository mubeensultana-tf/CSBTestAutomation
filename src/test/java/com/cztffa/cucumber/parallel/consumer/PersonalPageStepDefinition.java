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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@Slf4j
public class PersonalPageStepDefinition {

	private ReviewPage reviewPage;
	private SeleniumDriver seleniumdriver;
	private BrowserActions browserActions;
	private StepData stepData;

	public PersonalPageStepDefinition(ReviewPage reviewPage, StepData stepData) {
		this.stepData = stepData;
		this.reviewPage = reviewPage;
		this.seleniumdriver = reviewPage.getSeleniumdriver();
		this.browserActions = reviewPage.browserActions;
	}

	@Then("^: I should navigate to getting started page$")
	public void navigateToGettingStartedPage() throws Throwable {
		reviewPage.waitForVisibility("//*[contains(text(),'Personal Information')]");
		assertTrue(seleniumdriver.getWebDriver().getPageSource().contains("Personal Information"));
		log.info("On getting started page");
	}

	@When(": I provide the following details for {string}")
	public void provideDetails(String submissionId) throws Throwable {
		List<Map<?,?>> applicantDataList = DataCSVExtractor.applicantDataStore;
		ObjectMapper objectMapper = new ObjectMapper();
		for (int i = 0; i < applicantDataList.size(); i++) {
			Map<?,?> row = applicantDataList.get(i);
			if (row.get("submissionId").equals(submissionId)) {
				JSONObject jsonObject = new JSONObject(row);
				Person person = objectMapper.readValue(jsonObject.toString(), Person.class);
				log.info("Entering details: "+person);
				person.setValidation(stepData.getValidation());
				reviewPage.fillDetails(person);
				log.info("Details entered");
				log.info("Personal Information added for submissionId: " + submissionId);
				break;
			}
		}
	}

	@And("^: I click on start Application$")
	public void startApplication() throws Throwable {
		reviewPage.waitWithSpinner(reviewPage.getgettingStartedPageModel().startApplicationButton);
		browserActions.scrollToWebElement(seleniumdriver, reviewPage.getgettingStartedPageModel().startApplicationButton);
		reviewPage.waitWithSpinner(reviewPage.getgettingStartedPageModel().startApplicationButton);
		browserActions.clickButton(seleniumdriver, reviewPage.getgettingStartedPageModel().startApplicationButton);
		log.info("Starting application");
		Thread.sleep(2000);
	}

	@And("^: I should navigate to personal details page$")
	public void navigateToPersonalDetailsPage() throws Throwable {
		Thread.sleep(1000);
		reviewPage.waitForSpinnerToDisappear();
		reviewPage.waitForVisibilityWithLoader("//*[contains(text(),'Personal Information')]");
		assertTrue(seleniumdriver.getWebDriver().getPageSource().contains("Personal Information"));
		log.info("Navigated to personal information page");
	}



	@DataTableType(replaceWithEmptyString = "[blank]")
	public String stringType(String cell) {
		return cell;
	}

	@When(": I provide the below personal details for {string}")
	public void personalDetails(String submissionId) throws Throwable {
		List<Map<?,?>> applicantDataList = DataCSVExtractor.applicantDataStore;
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
				reviewPage.addPrimaryApplicant(person, 0);
				log.info("Personal Information added for submissionId: " + submissionId);
				break;
			}
		}
	}

//    @Then(": I click on add additional applicant button")
//    public void iClickOnAddAdditionalApplicantButton() throws Throwable {
//        log.info("Clicking on Add Additional applicant button");
//        reviewPage.wait(reviewPage.getPersonalInfoPageModel().addAdditionalApplicantButton);
//        browserActions.scrollToWebElement(seleniumdriver,reviewPage.getPersonalInfoPageModel().addAdditionalApplicantButton);
//        Thread.sleep(3000);
//        reviewPage.spinner();
//        browserActions.clickButton(seleniumdriver, reviewPage.getPersonalInfoPageModel().addAdditionalApplicantButton);
//        log.info("Add Additional applicant button clicked");
//        Thread.sleep(2000);
//        reviewPage.spinner();
//    }

	@Then(": I provide the below additional applicant details for {string}")
	public void additionalApplicant(String submissionId) throws Throwable {
		List<Map<?, ?>> applicantDataStore = DataCSVExtractor.applicantDataStore;
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
			reviewPage.waitWithSpinner(reviewPage.getPersonalInfoPageModel().addAdditionalApplicantButton);
			browserActions.scrollToWebElement(seleniumdriver, reviewPage.getPersonalInfoPageModel().addAdditionalApplicantButton);
            Thread.sleep(2000);
            reviewPage.spinner();
			browserActions.clickButton(seleniumdriver, reviewPage.getPersonalInfoPageModel().addAdditionalApplicantButton);
            Thread.sleep(2000);
            reviewPage.spinner();

			JSONObject jsonObject = new JSONObject(row);
			ObjectMapper objectMapper = new ObjectMapper();
			Person person = objectMapper.readValue(jsonObject.toString(), Person.class);

			Validation validation = new Validation();
			validation.setSkipPreferredContactDropdown(true);
			validation.setSkipEmploymentStatusDropdown(true);

			stepData.setValidation(validation);
			person.setValidation(validation);
			stepData.setPerson(person);

		reviewPage.addApplicant(person, 1);
		DataCSVExtractor.applicantCount++;
			currentIndex++;
		log.info("Added applicant index: " + targetIndex);
		}
	}

//	@And(": I provide the below branch additional applicant details for {string}")
//	public void branchAdditionalApplicant(String submissionId) throws Throwable {
//		List<Map<?, ?>> applicantDataStore = DataCSVExtractor.applicantDataStore;
//		int totalApplicants = 0;
//		List<Map<String, String>> matchingApplicants = new ArrayList<>();
//		for (Map<?, ?> row : applicantDataStore) {
//			Object submissionIdObj = row.get("submissionId");
//			if (submissionIdObj != null && submissionIdObj.toString().trim().equals(submissionId)){
//				matchingApplicants.add((Map<String, String>) row);
//				totalApplicants++;
//			}
//		}
//		log.info("Total applicants for submissionId " + submissionId + ": " + matchingApplicants.size());
//		int currentIndex = 0;
//		for (int i = 1; i < matchingApplicants.size(); i++) {
//			Map<String, String> row = matchingApplicants.get(i);
//			log.info("CurrentIndex: " + currentIndex);
//			int targetIndex = currentIndex + 1;
//			if (targetIndex >= totalApplicants) break;
//
//			log.info("applicantDataStore get: "+ applicantDataStore.get(targetIndex).toString());
//			reviewPage.waitWithSpinner(reviewPage.getPersonalInfoPageModel().addAdditionalApplicantButton);
//			browserActions.scrollToWebElement(seleniumdriver, reviewPage.getPersonalInfoPageModel().addAdditionalApplicantButton);
//			browserActions.clickButton(seleniumdriver, reviewPage.getPersonalInfoPageModel().addAdditionalApplicantButton);
//
//			Thread.sleep(1000);
//			reviewPage.waitWithSpinner(reviewPage.getProductSelectorPageModel().addNew);
//			browserActions.scrollToWebElement(seleniumdriver, reviewPage.getProductSelectorPageModel().addNew);
//			reviewPage.waitWithSpinner(reviewPage.getProductSelectorPageModel().addNew);
//			browserActions.clickButton(seleniumdriver, reviewPage.getProductSelectorPageModel().addNew);
//
//			JSONObject jsonObject = new JSONObject(row);
//			ObjectMapper objectMapper = new ObjectMapper();
//			Person person = objectMapper.readValue(jsonObject.toString(), Person.class);
//
//			Validation validation = new Validation();
//			validation.setSkipPreferredContactDropdown(true);
//			validation.setSkipEmploymentStatusDropdown(true);
//
//			stepData.setValidation(validation);
//			person.setValidation(validation);
//			stepData.setPerson(person);
//
//			reviewPage.addApplicant(person, targetIndex);
//			DataCSVExtractor.applicantCount++;
//			currentIndex++;
//			log.info("Added applicant index: " + targetIndex);
//		}
//	}

	@Then("^: I click on personal details next button$")
	public void clickPersonalDetailNextButton() throws Throwable {
		log.info("Clicking on next button");
		reviewPage.wait(reviewPage.getPersonalInfoPageModel().personalInfoNextButton);
		browserActions.scrollToWebElement(seleniumdriver,reviewPage.getPersonalInfoPageModel().personalInfoNextButton);
        Thread.sleep(1000);
		browserActions.clickButton(seleniumdriver, reviewPage.getPersonalInfoPageModel().personalInfoNextButton);
		log.info("Next button clicked");
		Thread.sleep(1000);
		reviewPage.spinner();
	}
    @Then("^: I click on personal details save button$")
    public void clickPersonalDetailSaveButton() throws Throwable {
        log.info("Clicking on Save button");
        reviewPage.wait(reviewPage.getPersonalInfoPageModel().personalInfoSaveButton);
        browserActions.scrollToWebElement(seleniumdriver,reviewPage.getPersonalInfoPageModel().personalInfoSaveButton);
        Thread.sleep(1000);
        browserActions.clickButton(seleniumdriver, reviewPage.getPersonalInfoPageModel().personalInfoSaveButton);
        log.info("Save button clicked");
        Thread.sleep(2000);
        reviewPage.spinner();
        browserActions.clickButton(seleniumdriver, reviewPage.getPersonalInfoPageModel().saveModalSavemyProgress);
        Thread.sleep(3000);
        reviewPage.spinner();
        browserActions.clickButton(seleniumdriver, reviewPage.getPersonalInfoPageModel().saveModalContinueButton);
        Thread.sleep(3000);
        reviewPage.spinner();
        browserActions.clickButton(seleniumdriver, reviewPage.getPersonalInfoPageModel().personalInfoNextButton);
        Thread.sleep(3000);
        reviewPage.spinner();
    }




	@Then("^: I click on personal details cancel button$")
	public void clickPersonalDetailCancelButton() throws Throwable {
        log.info("Clicking on Cancel button");
        Thread.sleep(1000);
        reviewPage.wait(reviewPage.getPersonalInfoPageModel().personalInfoCancelButton);
        browserActions.scrollToWebElement(seleniumdriver,reviewPage.getPersonalInfoPageModel().personalInfoCancelButton);
        Thread.sleep(2000);
        browserActions.clickButton(seleniumdriver, reviewPage.getPersonalInfoPageModel().personalInfoCancelButton);
        log.info("cancel button clicked");
        Thread.sleep(2000);
        reviewPage.spinner();
        browserActions.clickButton(seleniumdriver, reviewPage.getPersonalInfoPageModel().CancelButton);
        Thread.sleep(3000);
        reviewPage.spinner();

	}

    @When(": I provide the below personal details for existing applicant {string}")
    public void iProvideTheBelowPersonalDetailsForExistingApplicant(String submissionId) throws Throwable {
        List<Map<?,?>> existingApplicantDataList = DataCSVExtractor.existingApplicantDataStore;
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < existingApplicantDataList.size(); i++) {
            Map<?,?> row = existingApplicantDataList.get(i);
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
                reviewPage.addExistingApplicant(person, 1);
                log.info("Personal Information added for submissionId: " + submissionId);
                break;
            }
        }
    }
}


//	@Then("^: I click on Application Verification next button$")
//	public void clickApplicationVerificationNextButton() throws Throwable {
//		log.info("Clicking on next button");
//		reviewPage.wait(reviewPage.getPersonalInfoPageModel().personalInfoNextButton);
//		browserActions.scrollToWebElement(seleniumdriver,reviewPage.getPersonalInfoPageModel().personalInfoNextButton);
//		Thread.sleep(1000);
//		browserActions.clickButton(seleniumdriver, reviewPage.getPersonalInfoPageModel().personalInfoNextButton);
//		log.info("Next button clicked");
//		Thread.sleep(2000);
//		reviewPage.spinner();
//	}

