package com.cztffa.page.funding;

import com.cztffa.driver.SeleniumDriver;
import io.cucumber.java.et.Ja;
import org.openqa.selenium.By;

import com.cztffa.dataproviders.DataCSVExtractor;
import com.cztffa.objects.Funding;
import com.cztffa.objects.Person;
import com.cztffa.objects.Product;
import com.cztffa.objects.ProductTab;
import com.cztffa.page.account.AccountDetailsPage;
import com.cztffa.util.ApplicantUtil;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Slf4j
public class FundingPage extends AccountDetailsPage {
	private SeleniumDriver seleniumdriver;

	public void addFundDetails(Funding funding) throws InterruptedException {
		this.seleniumdriver = getSeleniumdriver();

		ApplicantUtil applicantUtil = new ApplicantUtil();
		log.info("Processing the funding details");
		waitForVisibilityWithLoader("//*[contains(text(),'Specify how you would like to')]");
		String pagesource = seleniumdriver.getWebDriver().getPageSource();
		if(pagesource.contains("Do you want to fund now?")) {
			log.info("Clicking funding toggle");
			waitForVisibilityWithLoader("//*[contains(text(),'Do you want to fund now?')]");
			browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().fundingToggleButton);
			browserActions.clickButton(getSeleniumdriver(), getFundingPageModel().fundingToggleButton);
			log.info("Funding toggle clicked");
		}

		//Funding Amount Code
//		WebDriver driver = seleniumdriver.getWebDriver();
//		log.info("Entering funding amount");
//		List<WebElement> fundingAmountList = driver.findElements(By.xpath("//tf-money[@t-model='Amount__c']//input"));
//		log.info("Found " + fundingAmountList.size() + " funding amounts");
//		for (WebElement fundingAmount : fundingAmountList) {
//			browserActions.scrollToWebElement(seleniumdriver, fundingAmount);
//			Thread.sleep(1000);
//			browserActions.enterTextKeyEntry(getSeleniumdriver(), fundingAmount, funding.getFundingAmount());
//		}

		Thread.sleep(1000);
		waitWithSpinner(getFundingPageModel().sourceOfFundDrApply);
		selectElement(getSeleniumdriver().getWebDriver(),getFundingPageModel().sourceOfFundDrApply);
		browserActions.clickApply(getSeleniumdriver().getWebDriver(), getFundingPageModel().sourceOfFundDrApply);
		log.info("Clicked on funding dropdown");

		if (funding.getFundingSource().equalsIgnoreCase("ACH")) {
			log.info("Selecting source of funding");
			wait(getFundingPageModel().sourceOfFundOpApply);
			selectElement(getSeleniumdriver().getWebDriver(), getFundingPageModel().sourceOfFundOpApply);
			browserActions.clickApply(getSeleniumdriver().getWebDriver(), getFundingPageModel().sourceOfFundOpApply);
			log.info("Source of funding selected");
			Thread.sleep(2000);
			waitForSpinnerToDisappear();

			//Branch flow funding started
			if (getSeleniumdriver().getWebDriver().getPageSource().contains("Funding Bank's ABA Routing Number")) {
				log.info("Scrolling to Funding Bank's ABA Routing Number");
				browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().routingNumber);
				Thread.sleep(1000);
				log.info("Entering Funding Bank's ABA Routing Number");
				browserActions.enterTextKeyEntry(getSeleniumdriver(), getFundingPageModel().routingNumber, funding.getRoutingNumber());
				log.info("Entering Funding Bank Account Number");
				browserActions.enterTextKeyEntry(getSeleniumdriver(), getFundingPageModel().accountNumber, funding.getAccountNumber());
				log.info("Selecting account type as checking");
				browserActions.clickButton(getSeleniumdriver(), getFundingPageModel().accountChecking);
			}
			//End of Branch funding

			else {
				browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().acknowledgePalidToggle);
				waitForVisibilityWithLoader("//*[contains(text(),'Funding Details')]");
				Thread.sleep(2000);
				browserActions.clickButton(getSeleniumdriver(), getFundingPageModel().acknowledgePalidToggle);
				log.info("Selected acknowledgePalidToggle");
				waitWithSpinner(getFundingPageModel().btnPlaid);
				waitForSpinnerToDisappear();
				waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());
				browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().btnPlaid);
				browserActions.clickUsingEnter(getSeleniumdriver().getWebDriver(), getFundingPageModel().btnPlaid);
				log.info("Clicked on Verify with Plaid button");

				browserActions.switchToFrame(getSeleniumdriver(), getFundingPageModel().framePlaid);
				log.info("switching window");
				waitForSpinnerToDisappear();
				log.info("Window switched");
				waitWithSpinner(getFundingPageModel().btnAuthContinueGuest);
				browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().btnAuthContinueGuest);
				waitWithSpinner(getFundingPageModel().btnAuthContinueGuest);
				browserActions.clickApply(getSeleniumdriver().getWebDriver(), getFundingPageModel().btnAuthContinueGuest);
				log.info("Clicked on continue as guest button");

				waitWithSpinner(getFundingPageModel().btnAuthContinue);
				browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().btnAuthContinue);
				browserActions.clickApply(getSeleniumdriver().getWebDriver(), getFundingPageModel().btnAuthContinue);
				log.info("Clicked on continue button");

				waitForSpinnerToDisappear();
				waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());
				log.info("selecting Bank account");
				log.info("bank name locator" + applicantUtil.getWebElement(getSeleniumdriver(), getFundingPageModel().plaidInstutionName, funding.getBank()));
				browserActions.clickButton(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(), getFundingPageModel().plaidInstutionName, funding.getBank()), 3000, 500);
				log.info("Clicking on Continue to login button");
				String parent = getSeleniumdriver().getWebDriver().getWindowHandle();
				browserActions.clickButton(getSeleniumdriver(), getFundingPageModel().btnLogin);
				waitForSpinnerToDisappear();
				waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());

				browserActions.switchToNewTab(getSeleniumdriver());
				log.info("Entering username and password");
				browserActions.enterText(getSeleniumdriver(), getFundingPageModel().txtUsername, funding.getAchUserId());
				browserActions.enterText(getSeleniumdriver(), getFundingPageModel().txtPassword, funding.getAchPassword());

				log.info("Clicking on Sign in button");
				browserActions.clickButton(getSeleniumdriver(), getFundingPageModel().btnAuthSubmit);
				waitForSpinnerToDisappear();
				waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());

				log.info("Clicking on Get code button");
				waitWithSpinner(getFundingPageModel().btnAuthContinueAfter);
				browserActions.clickButton(getSeleniumdriver(), getFundingPageModel().btnAuthContinueAfter);
				waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());

				log.info("Clicking on Submit button");
				waitWithSpinner(getFundingPageModel().btnAuthContinueAfterSuccess);
				browserActions.clickButton(getSeleniumdriver(), getFundingPageModel().btnAuthContinueAfterSuccess);

				log.info("Clicking on Plaid Checking");
				browserActions.clickCheckboxListItem(getSeleniumdriver(), getFundingPageModel().checkbox);
				wait(getFundingPageModel().btnSubmit);
				browserActions.clickButton(getSeleniumdriver(), getFundingPageModel().btnSubmit);
				log.info("Clicked on Continue button");

				waitForSpinnerToDisappear();
				waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());
				selectElement(getSeleniumdriver().getWebDriver(), getFundingPageModel().checkboxTerms);
				browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().checkboxTerms);
				selectElement(getSeleniumdriver().getWebDriver(), getFundingPageModel().checkboxTerms);
				waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());
				browserActions.clickCheckboxListItem(getSeleniumdriver(), getFundingPageModel().checkboxTerms);
				log.info("Accepted Terms and Conditions");

				browserActions.clickButton(getSeleniumdriver(), getFundingPageModel().btnSubmitConfirm);
				log.info("Clicked on Connected account Information button");
				Thread.sleep(3000);

				getSeleniumdriver().getWebDriver().switchTo().window(parent);
				browserActions.switchToDefaultContent(getSeleniumdriver());
				browserActions.switchToFrame(getSeleniumdriver(), getFundingPageModel().framePlaid);
				log.info("Switched frame to frame Plaid");
				waitForSpinnerToDisappear();
				waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());
				log.info("Moved to the last continue button finish without saving");
				Thread.sleep(1000);
				browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().btnContinue);
				Thread.sleep(1000);
				waitWithSpinner(getFundingPageModel().btnContinue);
				browserActions.clickButton(getSeleniumdriver(), getFundingPageModel().btnContinue);
				log.info("Clicked on Continue without saving button");
				browserActions.switchToDefaultContent(getSeleniumdriver());
				log.info("Selecting External account");
				Thread.sleep(2000);
				waitForSpinnerToDisappear();
				waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());
				waitForSpinnerToDisappear();
				waitForVisibilityWithLoader("//*[contains(text(),'External Account')]");
				waitWithSpinner(getFundingPageModel().drpAcNum);
				browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().drpAcNum);
				waitForSpinnerToDisappear();
				selectElement(getSeleniumdriver().getWebDriver(), getFundingPageModel().drpAcNum);
				browserActions.clickButton(getSeleniumdriver(), getFundingPageModel().drpAcNum);
				log.info("Clicked on External account dropdown");

				wait(getFundingPageModel().optAcNum);
				browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().optAcNum);
				selectElement(getSeleniumdriver().getWebDriver(), getFundingPageModel().optAcNum);
				browserActions.clickButton(getSeleniumdriver(), getFundingPageModel().optAcNum);
				log.info("Selected Account");
				log.info("Completed Plaid checking");
				browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().flagTrantoAcc);
				browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().authorizePalidToggle);
				browserActions.clickButton(getSeleniumdriver(), getFundingPageModel().authorizePalidToggle);
				log.info("Authorized Plaid toggle");
			}
		}
		else if(funding.getFundingSource().equalsIgnoreCase("creditCard") || funding.getFundingSource().equalsIgnoreCase("debitCard")) {
			if(funding.getFundingSource().equalsIgnoreCase("creditCard")){
				browserActions.clickApply(getSeleniumdriver().getWebDriver(), getFundingPageModel().sourceOfFundCCApply);
				log.info("Selecting credit card");
			}
			else if(funding.getFundingSource().equalsIgnoreCase("debitCard")){
				browserActions.clickApply(getSeleniumdriver().getWebDriver(), getFundingPageModel().sourceOfFundDCApply);
				log.info("Selecting debit card");
			}
			Thread.sleep(8000);
			browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().frameCardNumber);
			Thread.sleep(2000);
			browserActions.switchToFrame(getSeleniumdriver(), getFundingPageModel().frameCardNumber);
			Thread.sleep(3000);
			browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().cardNumber);
			Thread.sleep(2000);
			browserActions.enterText(getSeleniumdriver(), getFundingPageModel().cardNumber,funding.getCardNumber());
			Thread.sleep(2000);
			log.info("Entering card number");
			browserActions.switchToDefaultContent(getSeleniumdriver());
			browserActions.switchToFrame(getSeleniumdriver(), getFundingPageModel().frameCardName);
			browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().cardHolder);
			browserActions.enterText(getSeleniumdriver(), getFundingPageModel().cardHolder,funding.getCardHolderName());
			log.info("Entering cardholder name");
			Thread.sleep(2000);
			browserActions.switchToDefaultContent(getSeleniumdriver());
			browserActions.switchToFrame(getSeleniumdriver(), getFundingPageModel().frameCardDate);
			browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().cardExpiry);
			browserActions.enterTextKeyEntry(getSeleniumdriver(), getFundingPageModel().cardExpiry,funding.getExpiryDate());
			log.info("Entering card expiry");
			Thread.sleep(2000);
			browserActions.switchToDefaultContent(getSeleniumdriver());
			browserActions.switchToFrame(getSeleniumdriver(), getFundingPageModel().frameCardCVV);
			browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().cvv);
			browserActions.enterTextKeyEntry(getSeleniumdriver(), getFundingPageModel().cvv,funding.getCvv());
			log.info("Entering card cvv");
			Thread.sleep(2000);
			browserActions.switchToDefaultContent(getSeleniumdriver());
			browserActions.switchToFrame(getSeleniumdriver(), getFundingPageModel().frameCardZip);
			browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().zipCode);
			browserActions.enterTextKeyEntry(getSeleniumdriver(), getFundingPageModel().zipCode,funding.getZipCode());
			log.info("Entering zipcode");
			Thread.sleep(2000);
			browserActions.switchToDefaultContent(getSeleniumdriver());
			browserActions.switchToFrame(getSeleniumdriver(), getFundingPageModel().frameCardStreet);
			browserActions.scrollToWebElement(getSeleniumdriver(), getFundingPageModel().street);
			browserActions.enterText(getSeleniumdriver(), getFundingPageModel().street,funding.getStreetAddress());
			log.info("Entering street address");
			browserActions.switchToDefaultContent(getSeleniumdriver());
			Thread.sleep(2000);
			browserActions.clickButton(getSeleniumdriver(), getFundingPageModel().btnPay, 2000, 2000);
			log.info("Clicking on Pay button");
			Thread.sleep(8000);
			browserActions.clickApply(getSeleniumdriver().getWebDriver(), getFundingPageModel().btnAuthContinue);
			log.info("Clicking on Continue button");
			Thread.sleep(2000);
		}
		log.info("Finished processing of Funding details");
	}

}
