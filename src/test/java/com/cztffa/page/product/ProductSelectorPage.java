package com.cztffa.page.product;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import com.cztffa.driver.SeleniumDriver;
import com.cztffa.page.review.ReviewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.cztffa.objects.Person;
import com.cztffa.objects.Product;
import com.cztffa.objects.ProductTab;
import com.cztffa.objects.Validation;
import com.cztffa.page.base.BasePage;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class ProductSelectorPage extends BasePage {

	public void fillDetails(Person person) throws InterruptedException {
		waitForSpinnerToDisappear();
		wait(getgettingStartedPageModel().firstName);

		log.info("Entering first name");
		browserActions.enterText(getSeleniumdriver(), getgettingStartedPageModel().firstName, person.getFirstName());
		log.info("Entering last name");
		browserActions.enterText(getSeleniumdriver(), getgettingStartedPageModel().lastName, person.getLastName());
		log.info("Entering phone number");
		browserActions.scrollToWebElement(getSeleniumdriver(), getgettingStartedPageModel().phoneNumber);
		browserActions.enterText(getSeleniumdriver(), getgettingStartedPageModel().phoneNumber, person.getPhoneNumber());
		log.info("Entering email");
		browserActions.enterText(getSeleniumdriver(), getgettingStartedPageModel().personalEmail, person.getEmail());
		log.info("Accepting policy");
		browserActions.clickButton(getSeleniumdriver(), getgettingStartedPageModel().acceptPolicy);
		waitForSpinnerToDisappear();
		waitWithSpinner(getgettingStartedPageModel().acceptBtn);
		browserActions.clickUsingEnter(getSeleniumdriver().getWebDriver(), getgettingStartedPageModel().acceptBtn);
		log.info("Clicked on accept button");
	}

//	public void saveResumePage(Person person) throws InterruptedException {
//		log.info("Processing started for save and resume");
//		Thread.sleep(4000);
//		browserActions.clickButton(getSeleniumdriver(), getProductSelectorPageModel().saveButton);
//		Thread.sleep(3000);
//		browserActions.clickButton(getSeleniumdriver(), getProductSelectorPageModel().popupSaveButton);
//		Thread.sleep(5000);
//		browserActions.clickButton(getSeleniumdriver(), getProductSelectorPageModel().popupContinue);
//		Thread.sleep(5000);
//		getSeleniumdriver().getWebDriver().close();
//		Thread.sleep(5000);
//
//		launchGmail();
//		Thread.sleep(4000);
//
//		// gmail login and open link
//		browserActions.enterText(getSeleniumdriver(), getProductSelectorPageModel().gmailUserName, getEmail());
//		browserActions.clickButton(getSeleniumdriver(), getProductSelectorPageModel().signinSubmit);
//		Thread.sleep(5000);
//		browserActions.enterText(getSeleniumdriver(), getProductSelectorPageModel().gmailPassword, getPassword());
//		Thread.sleep(4000);
//		browserActions.clickButton(getSeleniumdriver(), getProductSelectorPageModel().signinSubmit);
//		Thread.sleep(5000);
//
//		List<WebElement> unreademail = browserActions.findElements(getSeleniumdriver(), By.className("zE"));
//
//		for (WebElement element : unreademail) {
//			if (element.getText().contains("cztffaui Site Guest U.")) {
//				element.click();
//				break;
//			}
//		}
//		Thread.sleep(5000);
//		browserActions.clickButton(getSeleniumdriver(), getProductSelectorPageModel().clickHereLink);
//		Thread.sleep(20000);
//		browserActions.switchToNewTab(getSeleniumdriver());
//		browserActions.clickButton(getSeleniumdriver(), getProductSelectorPageModel().loginWithCredentials);
//		Thread.sleep(2000);
//		browserActions.enterText(getSeleniumdriver(), getProductSelectorPageModel().lastNameTextBox,
//				person.getLastName());
//		browserActions.enterText(getSeleniumdriver(), getProductSelectorPageModel().lastFourSSNTextBox,
//				person.getSsn().substring(person.getSsn().length() - 4, person.getSsn().length()));
//		browserActions.enterText(getSeleniumdriver(), getProductSelectorPageModel().primaryEmailTextBox,
//				person.getEmail());
//		Thread.sleep(3000);
//		browserActions.clickButton(getSeleniumdriver(), getProductSelectorPageModel().loginButton);
//		Thread.sleep(10000);
//
//	}

}
