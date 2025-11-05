package com.cztffa.page.funding;

import com.cztffa.dataproviders.DataCSVExtractor;
import com.cztffa.objects.Funding;
import com.cztffa.page.account.SmbAccountDetailsPage;
import com.cztffa.util.ApplicantUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

@Slf4j
public class SmbFundingPage extends SmbAccountDetailsPage {
    public void addFundDetailsForSMB(Funding funding) throws InterruptedException {
        ApplicantUtil applicantUtil = new ApplicantUtil();
        log.info("Processing the funding details");
        waitForSpinnerToDisappear();
        waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());
        waitForVisibilityWithLoader("//*[contains(text(),'Specify how you would like to')]");
        String pageSource = getSeleniumdriver().getWebDriver().getPageSource();

//        if(pageSource.contains("Do you want to fund now?")) {
//            browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().fundingToggleButton);
//            Thread.sleep(2000);
//            waitWithSpinner(getSmbFundingPageModel().fundingToggleButton);
//            browserActions.clickButton(getSeleniumdriver(), getSmbFundingPageModel().fundingToggleButton);
//            log.info("After funding toggle click");
//            Thread.sleep(2000);
//        }

        if(pageSource.contains("Do you want to fund now?")) {
            log.info("Clicking funding toggle");
            waitForVisibilityWithLoader("//*[contains(text(),'Do you want to fund now?')]");
            browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().fundingToggleButton);
            browserActions.clickButton(getSeleniumdriver(), getSmbFundingPageModel().fundingToggleButton);
            log.info("Funding toggle clicked");
        }
        browserActions.clickApply(getSeleniumdriver().getWebDriver(), getSmbFundingPageModel().sourceOfFundDrApply);
        log.info("After funding text box click");

        if (funding.getFundingSource().equalsIgnoreCase("ACH")) {
            browserActions.scrollToWebElement(getSeleniumdriver(),getSmbFundingPageModel().sourceOfFundOpApply);
            waitWithSpinner(getSmbFundingPageModel().sourceOfFundOpApply);
            browserActions.clickApply(getSeleniumdriver().getWebDriver(), getSmbFundingPageModel().sourceOfFundOpApply);
            log.info("After selected External Account");

            waitForSpinnerToDisappear();
            if (pageSource.contains("I acknowledge that I want to ")) {
                waitForVisibilityWithLoader("//*[contains(text(),'Do you want to fund now?')]");
                if (getSmbFundingPageModel().acknowledgePalidToggle.isDisplayed()) {
                    browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().acknowledgePalidToggle);
                    waitWithSpinner(getSmbFundingPageModel().acknowledgePalidToggle);
                    browserActions.clickCheckboxListItem(getSeleniumdriver(), getSmbFundingPageModel().acknowledgePalidToggle);
                    log.info("Selected acknowledgePalidToggle");
                } else {
                    log.info("acknowledgePalidToggle is not displayed");
                }
            } else {
                log.info("Text 'I acknowledge that I want to' not found in page source, skipping checkbox selection");
            }

           log.info("Clciking on btn Plaid");
           wait(getSmbFundingPageModel().btnPlaid);
           browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().btnPlaid);
            waitWithSpinner(getSmbFundingPageModel().btnPlaid);
            waitWithShortTime(getSeleniumdriver());
            browserActions.clickUsingEnter(getSeleniumdriver().getWebDriver(), getSmbFundingPageModel().btnPlaid);
             waitForSpinnerToDisappear();
            browserActions.switchToFrame(getSeleniumdriver(), getSmbFundingPageModel().framePlaid);

            browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().btnAuthContinueGuest);
            waitWithSpinner(getSmbFundingPageModel().btnAuthContinueGuest);
            browserActions.clickApply(getSeleniumdriver().getWebDriver(), getSmbFundingPageModel().btnAuthContinueGuest);
            waitForSpinnerToDisappear();
            browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().btnAuthContinue);
            waitWithSpinner(getSmbFundingPageModel().btnAuthContinue);
            browserActions.clickApply(getSeleniumdriver().getWebDriver(), getSmbFundingPageModel().btnAuthContinue);
            waitForSpinnerToDisappear();
            waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());
//		if (funding.getBank().equalsIgnoreCase("Citi Bank")) {
            log.info("selecting citi bank acccount");
            log.info("bank name locator"+applicantUtil.getWebElement(getSeleniumdriver(),getSmbFundingPageModel().plaidInstutionName,funding.getBank()));
             waitWithShortTime(getSeleniumdriver());
            browserActions.clickButton(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(),getSmbFundingPageModel().plaidInstutionName,funding.getBank()),3000, 500);
//		}
            String parent=getSeleniumdriver().getWebDriver().getWindowHandle();
            browserActions.clickButton(getSeleniumdriver(),getSmbFundingPageModel().btnLogin);
            waitForSpinnerToDisappear();
            waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());

            browserActions.switchToNewTab(getSeleniumdriver());
            browserActions.enterText(getSeleniumdriver(), getSmbFundingPageModel().txtUsername, funding.getAchUserId());
            browserActions.enterText(getSeleniumdriver(), getSmbFundingPageModel().txtPassword, funding.getAchPassword());
            browserActions.scrollToWebElement(getSeleniumdriver(),getSmbFundingPageModel().btnAuthSubmit);
            waitWithSpinner(getSmbFundingPageModel().btnAuthSubmit);
            browserActions.clickButton(getSeleniumdriver(), getSmbFundingPageModel().btnAuthSubmit);
            waitForSpinnerToDisappear();
            waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());

            waitWithSpinner(getSmbFundingPageModel().btnAuthContinueAfter);
            browserActions.clickButton(getSeleniumdriver(), getSmbFundingPageModel().btnAuthContinueAfter);
            waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());

            waitWithSpinner(getSmbFundingPageModel().btnAuthContinueAfterSuccess);
            browserActions.clickButton(getSeleniumdriver(), getSmbFundingPageModel().btnAuthContinueAfterSuccess);
            selectElement(getSeleniumdriver().getWebDriver(),getSmbFundingPageModel().checkbox);

            browserActions.clickCheckboxListItem(getSeleniumdriver(),getSmbFundingPageModel().checkbox);
            waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());
             log.info("Clicking on submit button");
            waitWithSpinner(getSmbFundingPageModel().btnSubmit);
            browserActions.clickButton(getSeleniumdriver(), getSmbFundingPageModel().btnSubmit);
            waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());

            selectElement(getSeleniumdriver().getWebDriver(),getSmbFundingPageModel().checkboxTerms);
            browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().checkboxTerms);
            selectElement(getSeleniumdriver().getWebDriver(),getSmbFundingPageModel().checkboxTerms);
            waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());

            browserActions.clickCheckboxListItem(getSeleniumdriver(),getSmbFundingPageModel().checkboxTerms);
            waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());
            log.info("submit and confirm");
            waitWithSpinner(getSmbFundingPageModel().btnSubmitConfirm);

            waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());
            Thread.sleep(2500);
            browserActions.clickButton(getSeleniumdriver(), getSmbFundingPageModel().btnSubmitConfirm);
            Thread.sleep(3000);

            waitForSpinnerToDisappear();
             log.info("Before Switch");
            getSeleniumdriver().getWebDriver().switchTo().window(parent);
            log.info("After Switch");
            browserActions.switchToDefaultContent(getSeleniumdriver());

            browserActions.switchToFrame(getSeleniumdriver(), getSmbFundingPageModel().framePlaid);
            log.info("Switched to framePlaid");
            waitForSpinnerToDisappear();
            waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());
            log.info("Scrolling to finish without saving button");
            browserActions.scrollToWebElement(getSeleniumdriver(),getSmbFundingPageModel().btnContinue);
            log.info(" before finish without Saving click");
             browserActions.clickButton(getSeleniumdriver(),getSmbFundingPageModel().btnContinue);
            log.info(" After finish without Saving click");

            browserActions.switchToDefaultContent(getSeleniumdriver());
            log.info("Waiting for going to parent page");
            waitForSpinnerToDisappear();
            waitForVisibilityWithLoader("//*[contains(text(),'External Account')]");

            browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().drpAcNum);
            log.info("Scrolled to drop Acnum");
            waitForSpinnerToDisappear();
            selectElement(getSeleniumdriver().getWebDriver(),getSmbFundingPageModel().drpAcNum);
            browserActions.clickButton(getSeleniumdriver(), getSmbFundingPageModel().drpAcNum);

            waitForSpinnerToDisappear();
            log.info("I am on optAcNum");
            browserActions.scrollToWebElement(getSeleniumdriver(),getSmbFundingPageModel().optAcNum);
            selectElement(getSeleniumdriver().getWebDriver(),getSmbFundingPageModel().optAcNum);
            browserActions.clickButton(getSeleniumdriver(),getSmbFundingPageModel().optAcNum);
            log.info("Selected Drop Down Value Clicked");
            waitForSpinnerToDisappear();
            browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().flagTrantoAcc);
            log.info("Scrolled to Entered Amount");
            waitWithSpinner(getSmbFundingPageModel().flagTrantoAcc);
            browserActions.scrollToWebElement(getSeleniumdriver(),getSmbFundingPageModel().authorizePalidToggle);
            log.info("Clciking on Authorise Plaid toggle");

            browserActions.clickButton(getSeleniumdriver(),getSmbFundingPageModel().authorizePalidToggle);//added
            waitForSpinnerToDisappear();
            waitForInnerSpinnersToDisappear(getSeleniumdriver().getWebDriver());
            log.info("clicked Authorise plaid toggle");

        }
        else if(funding.getFundingSource().equalsIgnoreCase("creditCard") || funding.getFundingSource().equalsIgnoreCase("debitCard")) {

            if (funding.getFundingSource().equalsIgnoreCase("creditCard")) {
                browserActions.clickApply(getSeleniumdriver().getWebDriver(), getSmbFundingPageModel().sourceOfFundCCApply);
                log.info("Credit card selected");
            } else if (funding.getFundingSource().equalsIgnoreCase("debitCard")) {
                browserActions.clickApply(getSeleniumdriver().getWebDriver(), getSmbFundingPageModel().sourceOfFundDCApply);
                log.info("Debit card selected");
            }
            Thread .sleep(5000);

            browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().frameCardNumber);
            Thread.sleep(2000);
            browserActions.switchToFrame(getSeleniumdriver(), getSmbFundingPageModel().frameCardNumber);
            Thread.sleep(3000);

            browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().cardNumber);
            Thread.sleep(2000);
            browserActions.enterText(getSeleniumdriver(), getSmbFundingPageModel().cardNumber,funding.getCardNumber());
            Thread.sleep(2000);
            log.info("Frame Card number enetered");
            browserActions.switchToDefaultContent(getSeleniumdriver());
            browserActions.switchToFrame(getSeleniumdriver(), getSmbFundingPageModel().frameCardName);
            browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().cardHolder);
            browserActions.enterText(getSeleniumdriver(), getSmbFundingPageModel().cardHolder,funding.getCardHolderName());
            Thread.sleep(2000);
            browserActions.switchToDefaultContent(getSeleniumdriver());
            browserActions.switchToFrame(getSeleniumdriver(), getSmbFundingPageModel().frameCardDate);
            browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().cardExpiry);
            browserActions.enterTextKeyEntry(getSeleniumdriver(), getSmbFundingPageModel().cardExpiry,funding.getExpiryDate());
            Thread.sleep(2000);
            browserActions.switchToDefaultContent(getSeleniumdriver());
            browserActions.switchToFrame(getSeleniumdriver(), getSmbFundingPageModel().frameCardCVV);
            browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().cvv);
            browserActions.enterTextKeyEntry(getSeleniumdriver(), getSmbFundingPageModel().cvv,funding.getCvv());
            Thread.sleep(2000);
            browserActions.switchToDefaultContent(getSeleniumdriver());
            browserActions.switchToFrame(getSeleniumdriver(), getSmbFundingPageModel().frameCardZip);
            browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().zipCode);
            browserActions.enterTextKeyEntry(getSeleniumdriver(), getSmbFundingPageModel().zipCode,funding.getZipCode());
            Thread.sleep(2000);
            browserActions.switchToDefaultContent(getSeleniumdriver());
            browserActions.switchToFrame(getSeleniumdriver(), getSmbFundingPageModel().frameCardStreet);
            browserActions.scrollToWebElement(getSeleniumdriver(), getSmbFundingPageModel().street);
            browserActions.enterText(getSeleniumdriver(), getSmbFundingPageModel().street,funding.getStreetAddress());
            browserActions.switchToDefaultContent(getSeleniumdriver());
            Thread.sleep(2000);
            browserActions.clickButton(getSeleniumdriver(), getSmbFundingPageModel().btnPay, 2000, 2000);
            Thread.sleep(8000);
            browserActions.clickApply(getSeleniumdriver().getWebDriver(), getSmbFundingPageModel().btnAuthContinueGuest);
            Thread.sleep(2000);
        }
        log.info("Finished processing of Funding details");
    }
}
