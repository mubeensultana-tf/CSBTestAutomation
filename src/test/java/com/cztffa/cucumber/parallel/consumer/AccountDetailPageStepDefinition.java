package com.cztffa.cucumber.parallel.consumer;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.dataproviders.DataCSVExtractor;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.objects.AccountDetails;
import com.cztffa.objects.Beneficiary;
import com.cztffa.page.review.ReviewPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@Slf4j
public class AccountDetailPageStepDefinition {

	private ReviewPage reviewPage;
	private SeleniumDriver seleniumdriver;
	private BrowserActions browserActions;

	public AccountDetailPageStepDefinition(ReviewPage reviewPage) {
		this.reviewPage = reviewPage;
		this.seleniumdriver = reviewPage.getSeleniumdriver();
		this.browserActions = reviewPage.browserActions;
	}

//	@Then("^: I close promotion close button$")
//	public void iClosePromotionCloseButton() throws Throwable {
//		try {
//			if(seleniumdriver.getWebDriver().getPageSource().contains("Dont miss out on these Offers")){
//				reviewPage.wait(reviewPage.getAccountDetailsPageModel().closeButton);
//				WebElement closeBtn = reviewPage.getAccountDetailsPageModel().closeButton;
//				log.info("closebtn status = {}", closeBtn.isDisplayed());
//				browserActions.clickUsingEnter(seleniumdriver.getWebDriver(), closeBtn);
//				log.info("Clicked on continue");
//			}
//		} catch (Exception ex) {
//			log.error("failed to close the close button");
//		}
//	}
//    @Then("^: I close promotion cancel button$")
//    public void iClosePromotionCancelButton() throws Throwable {
//        try {
//            if(seleniumdriver.getWebDriver().getPageSource().contains("Dont miss out on these Offers")){
//                reviewPage.wait(reviewPage.getAccountDetailsPageModel().cancelButton);
//                WebElement cancelButton = reviewPage.getAccountDetailsPageModel().cancelButton;
//                log.info("cancelButton status = {}", cancelButton.isDisplayed());
//                browserActions.clickUsingEnter(seleniumdriver.getWebDriver(), cancelButton);
//                log.info("Clicked on continue");
//            }
//        } catch (Exception ex) {
//            log.error("failed to cancel the cancel button ");
//        }
//    }

    @Then(": I should see the account details page")
    public void iShouldSeeTheAccountDetailsPage() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
           Thread.sleep(1000);
           reviewPage.spinner();
//        if (!seleniumdriver.getWebDriver().getPageSource().contains("Choose additional services")) {
            browserActions.scrollToWebElement(seleniumdriver, reviewPage.getAccountDetailsPageModel().accountDetailsNextButton);
        Thread.sleep(1000);
            reviewPage.wait(reviewPage.getAccountDetailsPageModel().accountDetailsNextButton);
            browserActions.clickUsingEnter(seleniumdriver.getWebDriver(), reviewPage.getAccountDetailsPageModel().accountDetailsNextButton);
            log.info("Clicked on Next button");
//        }
    }

//	@Then("^: I click on the overdraft Coverage no checkbox$")
//	public void iClickOnTheOverdraftCoveragecheckNoBox() throws Throwable {
//		WebDriver driver = seleniumdriver.getWebDriver();
//		log.info("On overdraft page");
//		reviewPage.waitForVisibilityWithLoader("//*[contains(text(),'Setup your new account')]");
//		log.info("Checking premier checking availability");
//
//		if(driver.getPageSource().contains("Checking")) {
//			log.info("Checking presence detected");
//			List<WebElement> checkingSections = driver.findElements(By.xpath("//h2[contains(text(),'Checking')]"));
//			List<WebElement> OverDraftCoverageNoCheckBoxes = driver.findElements(By.xpath("//div[contains(text(),'Overdraft Coverage')]//ancestor::div[@class='row ng-star-inserted']//tf-radio//input[@value='No']"));
//			log.info(String.valueOf(checkingSections.size()));
//			log.info(String.valueOf(OverDraftCoverageNoCheckBoxes.size()));
//			log.info("Entering for loop");
//			for (int i = 0; i < checkingSections.size(); i++) {
//				WebElement checkingSection = checkingSections.get(i);
//				WebElement OverDraftCoverageNoCheckBox = OverDraftCoverageNoCheckBoxes.get(i);
//				log.info("Selecting Checking " + i);
//				reviewPage.waitWithSpinner(checkingSection);
//				browserActions.scrollToWebElement(seleniumdriver, checkingSection);
//				Thread.sleep(1000);
//				browserActions.clickButton(seleniumdriver, checkingSection);
//				Thread.sleep(1000);
//				log.info("Clicking on No " + i);
//				browserActions.scrollToWebElement(seleniumdriver, OverDraftCoverageNoCheckBox);
//				Thread.sleep(1000);
//				browserActions.clickButton(seleniumdriver, OverDraftCoverageNoCheckBox);
//				Thread.sleep(1000);
//			}
//		}
//		else if(driver.getPageSource().contains("Basic Savings")){
//			browserActions.scrollToWebElement(seleniumdriver,reviewPage.getAccountDetailsPageModel().basicSavingsSection);
//			reviewPage.waitWithSpinner(reviewPage.getAccountDetailsPageModel().basicSavingsSection);
//			WebElement basicSavingsbtn = reviewPage.getAccountDetailsPageModel().basicSavingsSection;
//			browserActions.scrollToWebElement(seleniumdriver, basicSavingsbtn);
//			browserActions.clickButton(seleniumdriver, basicSavingsbtn);
//			log.info("Clicked on Basic Savings");
//		}
//	}

	@Then("^: I click on the account details next button$")
	public void iClickOnTheAccountDetailsNextButton() throws Throwable {
		Thread.sleep(1000);
//
//		if(seleniumdriver.getWebDriver().getPageSource().contains("Premier Certificate")) {
//			reviewPage.wait(reviewPage.getAccountDetailsPageModel().certificateSection);
//			reviewPage.waitWithShortTime(seleniumdriver);
//			reviewPage.scrollToElementWithActionPause(seleniumdriver, reviewPage.getAccountDetailsPageModel().certificateSection);
//			browserActions.clickApply(seleniumdriver.getWebDriver(), reviewPage.getAccountDetailsPageModel().certificateSection);
//			log.info("Clicked on Premier Certificate");
//			reviewPage.waitWithShortTime(seleniumdriver);
//			browserActions.scrollToWebElement(seleniumdriver, reviewPage.getAccountDetailsPageModel().certificateTermDrp);
//
//			reviewPage.selectElement(seleniumdriver.getWebDriver(),reviewPage.getAccountDetailsPageModel().certificateTermDrp);
//			reviewPage.wait(reviewPage.getAccountDetailsPageModel().certificateTermDrp);
//			browserActions.clickApply(seleniumdriver.getWebDriver(), reviewPage.getAccountDetailsPageModel().certificateTermDrp);
//			log.info("Clicked on Certificate Term dropdown");
//			reviewPage.wait(reviewPage.getAccountDetailsPageModel().certificateTermValue);
//			reviewPage.selectElement(seleniumdriver.getWebDriver(),reviewPage.getAccountDetailsPageModel().certificateTermValue);
//			browserActions.clickApply(seleniumdriver.getWebDriver(), reviewPage.getAccountDetailsPageModel().certificateTermValue);
//			log.info("Selected 12 months");
//			reviewPage.wait(reviewPage.getAccountDetailsPageModel().certificateAmount);
//			browserActions.scrollToWebElement(seleniumdriver, reviewPage.getAccountDetailsPageModel().certificateAmount);
//			browserActions.enterTextKeyEntry(seleniumdriver, reviewPage.getAccountDetailsPageModel().certificateAmount,"2000");
//			log.info("Amount entered");
//			DataCSVExtractor.isFundingCompulsory = true;
//		}
		if(!seleniumdriver.getWebDriver().getPageSource().contains("Fund your Account")) {
			browserActions.scrollToWebElement(seleniumdriver, reviewPage.getAccountDetailsPageModel().accountDetailsNextButton);
			reviewPage.wait(reviewPage.getAccountDetailsPageModel().accountDetailsNextButton);
			browserActions.clickUsingEnter(seleniumdriver.getWebDriver(), reviewPage.getAccountDetailsPageModel().accountDetailsNextButton);
			log.info("Clicked on Next button");
		}
	}

}
