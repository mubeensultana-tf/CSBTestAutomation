package com.cztffa.page.account;

import org.openqa.selenium.JavascriptExecutor;

import com.cztffa.objects.AccountDetails;
import com.cztffa.objects.Beneficiary;
import com.cztffa.objects.Person;
import com.cztffa.objects.Product;
import com.cztffa.objects.ProductTab;
import com.cztffa.page.memberdiligence.MemberDiligencePage;
import com.cztffa.util.ApplicantUtil;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountDetailsPage extends MemberDiligencePage {

//	public void addAcountDetails(Person person) throws InterruptedException {
//		if (person.getAccountDetails().isSkipped()) {
//			log.info("Inside account deatils skipped");
//			closeWindows();
//			JavascriptExecutor j = (JavascriptExecutor) getSeleniumdriver().getWebDriver();
//			j.executeScript("window.scrollBy(0,500)");
//			Thread.sleep(3000);
//			browserActions.clickButton(getSeleniumdriver(), getAccountDetailsPageModel().accountDetailsNextButton);
//			Thread.sleep(10000);
//		} else {
//			log.info("Processing the account details");
//			closeWindows();
//			AccountDetails accountDetails = person.getAccountDetails();
//			JavascriptExecutor j = (JavascriptExecutor) getSeleniumdriver().getWebDriver();
//			everyDayChecking(accountDetails);
//			j.executeScript("window.scrollBy(750,1000)");
//			Thread.sleep(3000);
//			if (accountDetails.isLinkedProtection()) {
//				log.info("clicking on linked protection");
//				browserActions.clickButton(getSeleniumdriver(),
//						getAccountDetailsPageModel().linkedOverdraftToggleButton);
//			}
//			Thread.sleep(3000);
//			if (accountDetails.isAccept()) {
//				log.info("selecting accept button");
//				browserActions.clickButton(getSeleniumdriver(), getAccountDetailsPageModel().acceptCheckBox);
//			}
//			j.executeScript("window.scrollBy(800,1000)");
//			Thread.sleep(3000);
//			browserActions.clickButton(getSeleniumdriver(), getAccountDetailsPageModel().accountDetailsNextButton);
//			Thread.sleep(10000);
//		}
//	}

//	public void everyDayChecking(AccountDetails accountDetails) throws InterruptedException {
//		if (accountDetails.getCardStyle().equalsIgnoreCase("orange")) {
//			log.info("selecting orange card");
//			browserActions.clickButton(getSeleniumdriver(), getAccountDetailsPageModel().orangeVisaDebitCard);
//		}
//		JavascriptExecutor j = (JavascriptExecutor) getSeleniumdriver().getWebDriver();
//		j.executeScript("window.scrollBy(1000,1200)");
//		Thread.sleep(2000);
//		browserActions.clickButton(getSeleniumdriver(), getAccountDetailsPageModel().linkedOverdraftToggleButton);
//		Thread.sleep(2000);
//		browserActions.clickButton(getSeleniumdriver(), getAccountDetailsPageModel().addLinkedOverDraftProtection);
//		Thread.sleep(2000);
//		browserActions.clickButton(getSeleniumdriver(), getAccountDetailsPageModel().acceptCheckBox);
//	}

//	public void everyDayCheckingForMultiApplicant(AccountDetails accountDetails) throws InterruptedException {
//		ApplicantUtil applicantUtil = new ApplicantUtil();
//		JavascriptExecutor j = (JavascriptExecutor) getSeleniumdriver().getWebDriver();
//		j.executeScript("window.scrollBy(0,1500)");
//		Thread.sleep(2000);
//		if (accountDetails.getCardStyle().equalsIgnoreCase("orange")) {
//			log.info("selecting orange card");
//			browserActions.clickButton(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(), getAccountDetailsPageModel().orangeCard, 0));
//			browserActions.clickButton(getSeleniumdriver(), getAccountDetailsPageModel().addDebitCardButton);
//			j.executeScript("window.scrollBy(1500,1900)");
//			Thread.sleep(2000);
//			browserActions.clickButton(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(), getAccountDetailsPageModel().orangeCard, 1));
//		}
//		j.executeScript("window.scrollBy(1900,2200)");
//		Thread.sleep(2000);
//		browserActions.clickButton(getSeleniumdriver(), getAccountDetailsPageModel().linkedOverdraftToggleButton);
//		Thread.sleep(2000);
//		browserActions.clickButton(getSeleniumdriver(), getAccountDetailsPageModel().addLinkedOverDraftProtection);
//		Thread.sleep(2000);
//		browserActions.clickButton(getSeleniumdriver(), getAccountDetailsPageModel().acceptCheckBox);
//	}

//	private void closeWindows() {
//		try {
//			browserActions.clickButton(getSeleniumdriver(), getAccountDetailsPageModel().closeButton);
//			Thread.sleep(3000);
//		} catch (Exception ex) {
//			log.error("failed to close the close button");
//		}
//		try {
//			browserActions.clickButton(getSeleniumdriver(), getMemberDiligencePageModel().complementoryProdButton);
//		} catch (Exception e) {
//			log.error("failed to close the complementory product button");
//		}
//	}

//	public void addAccountDetails(Person person, ProductTab productTab, Product... products)
//			throws InterruptedException {
//		addMemberDiligenceDetails(person, productTab, products);
//		addAcountDetails(person);
//		Thread.sleep(5000);
//	}

//	@SneakyThrows
//	public void fillBeneficiaryDetails(Beneficiary beneficiary, int index) {
//		ApplicantUtil applicantUtil = new ApplicantUtil();
//		browserActions.enterText(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(),
//				getAccountDetailsPageModel().beneficiaryFirstName, index), beneficiary.getFirstName());
//		browserActions.enterText(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(),
//				getAccountDetailsPageModel().beneficiaryLastName, index), beneficiary.getLastName());
//		browserActions.enterText(getSeleniumdriver(),
//				applicantUtil.getWebElement(getSeleniumdriver(), getAccountDetailsPageModel().beneficiarySSN, index),
//				beneficiary.getSsn());
//		browserActions.enterTextKeyEntry(getSeleniumdriver(),
//				applicantUtil.getWebElement(getSeleniumdriver(), getAccountDetailsPageModel().beneficiaryDob, index),
//				beneficiary.getDob());
//		JavascriptExecutor j = (JavascriptExecutor) getSeleniumdriver().getWebDriver();
//		j.executeScript("window.scrollBy(0,600)");
//		Thread.sleep(2000);
//		browserActions.clickApply(getSeleniumdriver().getWebDriver(), applicantUtil.getWebElement(getSeleniumdriver(),
//				getAccountDetailsPageModel().beneficiaryRelashionship, index));
//		browserActions.clickApply(getSeleniumdriver().getWebDriver(),
//				getAccountDetailsPageModel().beneficiaryRelashionshipApply);
//		browserActions.enterText(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(),
//				getAccountDetailsPageModel().beneficiaryPercentage, index), beneficiary.getPercentage());
//		browserActions.enterText(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(),
//				getAccountDetailsPageModel().beneficiaryStreetAddress1, index), beneficiary.getStreetAddress1());
//		browserActions.enterText(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(),
//				getAccountDetailsPageModel().beneficiaryStreetAddress1, index), beneficiary.getStreetAddress1());
//		browserActions.enterText(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(),
//				getAccountDetailsPageModel().beneficiaryCity, index), beneficiary.getCity());
//		browserActions.clickApply(getSeleniumdriver().getWebDriver(), applicantUtil.getWebElement(getSeleniumdriver(),
//				getAccountDetailsPageModel().beneficiaryState, index));
//		browserActions.clickApply(getSeleniumdriver().getWebDriver(),
//				getPersonalInfoPageModel().stateOptApply);
//		browserActions.enterText(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(),
//				getAccountDetailsPageModel().beneficiaryCity, index), beneficiary.getCity());
//		browserActions.enterText(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(),
//				getAccountDetailsPageModel().beneficiaryZip, index), beneficiary.getZip());
//		j.executeScript("window.scrollBy(600,1000)");
//		Thread.sleep(2000);
//	}

}
