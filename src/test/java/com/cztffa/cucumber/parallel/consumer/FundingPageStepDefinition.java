package com.cztffa.cucumber.parallel.consumer;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.dataproviders.DataCSVExtractor;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.objects.Funding;
import com.cztffa.objects.StepData;
import com.cztffa.page.review.ReviewPage;
import com.cztffa.util.ApplicantUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@Slf4j
public class FundingPageStepDefinition {

	private ReviewPage reviewPage;
	private SeleniumDriver seleniumdriver;
	private BrowserActions browserActions;
	private StepData stepData;

	public FundingPageStepDefinition(ReviewPage reviewPage, StepData stepData) {
		this.stepData = stepData;
		this.reviewPage = reviewPage;
		this.seleniumdriver = reviewPage.getSeleniumdriver();
		this.browserActions = reviewPage.browserActions;
	}

	@And("^: I should see the funding page$")
	public void iShouldSeeTheFundingPage() throws Throwable {
		Thread.sleep(2000);
		reviewPage.waitForVisibilityWithLoader("//*[contains(text(),'Personal Information')]");
		assertTrue(seleniumdriver.getWebDriver().getPageSource().contains("Personal Information"));
		log.info("On the funding page");
	}

	@Then(": I provide below funding details for {string}")
	public void i_provide_the_funding_details(String submissionId) throws Throwable {

		List<Map<?,?>> fundingList = DataCSVExtractor.fundingDataStore;
		ObjectMapper objectMapper = new ObjectMapper();
		for (int i = 0; i < fundingList.size(); i++) {
			Map<?,?> row = fundingList.get(i);
			Object fundingObj = row.get("submissionId");
			if (fundingObj != null && fundingObj.toString().trim().equals(submissionId)){
				JSONObject jsonObject = new JSONObject(row);
				Funding funding = objectMapper.readValue(jsonObject.toString(), Funding.class);
				reviewPage.addFundDetails(funding);
				log.info("Funding added for submissionId: " + submissionId);
				break;
			}
		}
		log.info("Funding Page Completed for submissionId: " + submissionId);
	}

	@And("^: I click on funding details next button$")
	public void iClickOnFundingDetailsNextButton() throws Throwable {
		reviewPage.waitWithSpinner(reviewPage.getFundingPageModel().fundingNextButton);

		browserActions.scrollToWebElement(seleniumdriver, reviewPage.getFundingPageModel().fundingNextButton);
		reviewPage.wait(reviewPage.getFundingPageModel().fundingNextButton);
		reviewPage.waitForSpinnerToDisappear();
		browserActions.clickUsingEnter(seleniumdriver.getWebDriver(), reviewPage.getFundingPageModel().fundingNextButton);
		log.info("Clicked on Next Button");
	}
}
