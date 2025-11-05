package com.cztffa.page.personalinfo;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.cztffa.driver.SeleniumDriver;
import com.cztffa.page.base.BasePage;
import com.cztffa.page.review.ReviewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cztffa.objects.Applicant;
import com.cztffa.objects.Business;
import com.cztffa.objects.Person;
import com.cztffa.objects.Product;
import com.cztffa.objects.ProductTab;
import com.cztffa.objects.Validation;
import com.cztffa.util.ApplicantUtil;
import com.cztffa.page.product.ProductSelectorPage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonalInfoPage extends ProductSelectorPage {
	private SeleniumDriver seleniumdriver;
	private ReviewPage reviewPage;

	public WebElement element(String xpath, int index){
		ApplicantUtil applicantUtil = new ApplicantUtil();
		return applicantUtil.getWebElement(getSeleniumdriver(), xpath, index);
	}

	public void addApplicant(Person person, int index) throws InterruptedException {
		ApplicantUtil applicantUtil = new ApplicantUtil();
		Validation validation = person.getValidation();

		waitForSpinnerToDisappear();
		waitWithSpinner(element(getPersonalInfoPageModel().firstName, index));
		log.info("Entering first name");
		WebElement firstName=applicantUtil.getWebElement(getSeleniumdriver(),getPersonalInfoPageModel().firstName, index);
		browserActions.scrollToWebElement(getSeleniumdriver(),firstName);
		wait(firstName);
		browserActions.enterText(getSeleniumdriver(),
				element(getPersonalInfoPageModel().firstName, index),
				person.getFirstName());
		assertTrue(true);
		log.info("Entering middle name");
		browserActions.enterText(getSeleniumdriver(),
				element(getPersonalInfoPageModel().middleName, index),
				person.getMiddleName());
		log.info("Entering last name");
		browserActions.enterText(getSeleniumdriver(),
				element(getPersonalInfoPageModel().lastName, index),
				person.getLastName());

		log.info("Entering date of birth");
		wait(element(getPersonalInfoPageModel().dateOfBirth, index));
		assertTrue(true);
		browserActions.scrollToWebElement(getSeleniumdriver(), element(getPersonalInfoPageModel().dateOfBirth, index));
		browserActions.enterTextKeyEntry(getSeleniumdriver(), element(getPersonalInfoPageModel().dateOfBirth, index), person.getDob());

		log.info("selecting the citizenship dropdown");
		browserActions.scrollToWebElement(getSeleniumdriver(), element(getPersonalInfoPageModel().citizenship, index));
		wait(element(getPersonalInfoPageModel().citizenship, index));
		browserActions.clickButton(getSeleniumdriver(),
				element(getPersonalInfoPageModel().citizenship, index));

		if ((validation == null) || validation != null && !validation.isSkipCitizenshipDropDown()) {
			if (person.getCitizenShip().equalsIgnoreCase("citizen")) {
				log.info("selecting citizenship");				
				browserActions.clickApply(getSeleniumdriver().getWebDriver(),
						getPersonalInfoPageModel().citizenshipCitizenOptApply);
				assertTrue(true);
				log.info("selected citizen dropdown");
			} else {
				browserActions.clickApply(getSeleniumdriver().getWebDriver(),
						getPersonalInfoPageModel().citizenshipAlienApply);
				log.info("selected alien citizenship dropdown");

				browserActions.clickApply(getSeleniumdriver().getWebDriver(), applicantUtil
						.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().citizenshipCountry, index));
				browserActions.clickApply(getSeleniumdriver().getWebDriver(),
						getPersonalInfoPageModel().citizenshipCountryApply);
				browserActions.clickApply(getSeleniumdriver().getWebDriver(), applicantUtil
						.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().willingForeign, index));
				browserActions.clickApply(getSeleniumdriver().getWebDriver(),
						getPersonalInfoPageModel().willingFreignApply);
				assertTrue(true);
			}
		}

		log.info("selecting street address dropdown ::"+person.getStreetAddress1());
		WebElement streetAddress1 = applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().streetAddress1, index);
		browserActions.scrollToWebElement(getSeleniumdriver(),streetAddress1);
		wait(streetAddress1);
		selectElement(getSeleniumdriver().getWebDriver(),streetAddress1);
		browserActions.enterText(getSeleniumdriver(), element(getPersonalInfoPageModel().streetAddress1, index), person.getStreetAddress1());

		Thread.sleep(4000);
		WebElement addressText = applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().addressText, person.getStreetAddress1());
		browserActions.scrollToWebElement(getSeleniumdriver(),addressText);
		wait(addressText);
		selectElement(getSeleniumdriver().getWebDriver(),addressText);
		addressText.click();

		browserActions.scrollToWebElement(getSeleniumdriver(), element(getPersonalInfoPageModel().city, index));
		browserActions.enterText(getSeleniumdriver(), element(getPersonalInfoPageModel().city, index), person.getCity());
		
		if ((validation == null) || validation != null && !validation.isSkipStateDropdown()) {			
			browserActions.clickApply(getSeleniumdriver().getWebDriver(), element(getPersonalInfoPageModel().state, index));
			wait(getPersonalInfoPageModel().stateOptApply);
			browserActions.clickApply(getSeleniumdriver().getWebDriver(), getPersonalInfoPageModel().stateOptApply);
			log.info("After entering state drop down ");
		}

		browserActions.enterText(getSeleniumdriver(),
				applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().zip, index),
				person.getZip());
		log.info("After entering Zipcode");

		if ((validation == null) || validation != null && !validation.isSkipPreferredContactDropdown()) {
			WebElement prefferedContact=applicantUtil.getWebElement(getSeleniumdriver(),getPersonalInfoPageModel().prefferedContact, index);
			browserActions.scrollToWebElement(getSeleniumdriver(),prefferedContact);
			wait(prefferedContact);
			browserActions.clickApply(getSeleniumdriver().getWebDriver(), applicantUtil
					.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().prefferedContact, index));
			if (person.getPrefferedContactMethod().equalsIgnoreCase("mobile")) {
				log.info("selecting preferred contact method {}", person.getPrefferedContactMethod());
				browserActions.clickApply(getSeleniumdriver().getWebDriver(),
						getPersonalInfoPageModel().preferredContactMobileApply);
			}
		}

		log.info("entering mobile phone no");
		WebElement mobilePhone = applicantUtil.getWebElement(getSeleniumdriver(),getPersonalInfoPageModel().mobilePhone, index);
		browserActions.scrollToWebElement(getSeleniumdriver(),mobilePhone);
		wait(mobilePhone);
		browserActions.enterText(getSeleniumdriver(),
				applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().mobilePhone, index),
				person.getPhoneNumber());

		log.info("entering email");
		WebElement email=applicantUtil.getWebElement(getSeleniumdriver(),getPersonalInfoPageModel().email, index);
		browserActions.scrollToWebElement(getSeleniumdriver(),email);
		wait(email);
		browserActions.enterText(getSeleniumdriver(),
				applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().email, index),
				person.getEmail());
		
		if ((validation == null) || validation != null && !validation.isSkipEmploymentStatusDropdown()) {
			browserActions.clickApply(getSeleniumdriver().getWebDriver(),
					applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().employment, index));
			log.info("selecting employment method {}", person.getEmploymentStatus());
			if (person.getEmploymentStatus().equalsIgnoreCase("Employed")) {
				browserActions.clickApply(getSeleniumdriver().getWebDriver(),
						getPersonalInfoPageModel().employmentEmployedApply);
				assertTrue(true);
			}
			browserActions.enterTextKeyEntry(getSeleniumdriver(),
					applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().occupation, index),
					person.getOccupation());
			browserActions.enterText(getSeleniumdriver(),
					applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().employer, index),
					person.getEmployer());
		}
		
		log.info("entering SSN");
		browserActions.scrollToWebElement(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().ssn, index));
		browserActions.enterText(getSeleniumdriver(),
				applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().ssn, index),
				person.getSsn());

		log.info("SSN entered");
		if(getSeleniumdriver().getWebDriver().getPageSource().contains("Identification Documents")) {
			log.info("Identification Documents applicable");
			browserActions.scrollToWebElement(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().idType, index));
			wait(element(getPersonalInfoPageModel().idType, index));
			browserActions.clickApply(getSeleniumdriver().getWebDriver(), applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().idType, index));

			log.info("selecting preferred id method {}", person.getPrefferedId());
			if (person.getPrefferedId().equalsIgnoreCase("military")) {
				browserActions.clickApply(getSeleniumdriver().getWebDriver(),
						getPersonalInfoPageModel().idTypeMilitaryApply);
			} else if (person.getPrefferedId().equalsIgnoreCase("Resident Alien")) {
				browserActions.clickApply(getSeleniumdriver().getWebDriver(),
						getPersonalInfoPageModel().idTypeResidentAlienApply);
			} else if (person.getPrefferedId().equalsIgnoreCase("driverLicence")) {
				browserActions.clickApply(getSeleniumdriver().getWebDriver(),
						getPersonalInfoPageModel().idTypeDriverLicenceApply);
				getSeleniumdriver().getWebDriver().manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);
				browserActions.clickApply(getSeleniumdriver().getWebDriver(),
						applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().stateIssued, index));
				browserActions.clickApply(getSeleniumdriver().getWebDriver(), getPersonalInfoPageModel().stateApply);
			} else if (person.getPrefferedId().equalsIgnoreCase("stateId")) {
				browserActions.clickApply(getSeleniumdriver().getWebDriver(),
						getPersonalInfoPageModel().idTypeStateIdApply);
				browserActions.clickApply(getSeleniumdriver().getWebDriver(),
						applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().stateIssued, index));
				browserActions.clickApply(getSeleniumdriver().getWebDriver(), getPersonalInfoPageModel().stateApply);
			} else if (person.getPrefferedId().equalsIgnoreCase("Passport")) {
				wait(getPersonalInfoPageModel().idTypePassportApply);
				browserActions.clickApply(getSeleniumdriver().getWebDriver(),
						getPersonalInfoPageModel().idTypePassportApply);
				log.info("Passport selected");
				wait(element(getPersonalInfoPageModel().countryIssued, index));
				browserActions.clickApply(getSeleniumdriver().getWebDriver(),
						applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().countryIssued, index));
				wait(getPersonalInfoPageModel().citizenshipCountryApply);
				browserActions.clickApply(getSeleniumdriver().getWebDriver(), getPersonalInfoPageModel().citizenshipCountryApply);
				log.info("Albania Selected");
			}

			log.info("Entering id number");
			WebElement idNumber=applicantUtil.getWebElement(getSeleniumdriver(),getPersonalInfoPageModel().idNumber, index);
			browserActions.scrollToWebElement(getSeleniumdriver(),idNumber);
			Thread.sleep(1000);
			wait(element(getPersonalInfoPageModel().idNumber, index));
			browserActions.enterText(getSeleniumdriver(),
					applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().idNumber, index),
					person.getIdentificationNumber());

			log.info("Entering issue date");
			wait(element(getPersonalInfoPageModel().issueDate, index));
			browserActions.enterTextKeyEntry(getSeleniumdriver(),
					applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().issueDate, index),
					person.getIssueDate());

			log.info("Entering expiry date");
			wait(element(getPersonalInfoPageModel().expiryDate, index));
			browserActions.enterTextKeyEntry(getSeleniumdriver(),
					applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().expiryDate, index),
					person.getExpiryDate());

			if(index!=0) {
				WebElement relationCode=applicantUtil.getWebElement(getSeleniumdriver(),getPersonalInfoPageModel().relationCode,index);
				wait(relationCode);
				browserActions.scrollToWebElement(getSeleniumdriver(), applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().relationCode, index));
			    selectElement(getSeleniumdriver().getWebDriver(),relationCode);
				browserActions.enterText(getSeleniumdriver(),
						applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().relationCode, index),
						person.getRelationship());
				browserActions.clickApply(getSeleniumdriver().getWebDriver(), applicantUtil.getWebElement(getSeleniumdriver(), getPersonalInfoPageModel().relation, person.getRelationship()));
				log.info("After entering Relationcode" + person.getRelationship());
			}
			Thread.sleep(1000);

			if(getSeleniumdriver().getWebDriver().getPageSource().contains("Electronic Communications Delivery Policy")){
				log.info("Clicking on checkBox");
				browserActions.clickButton(getSeleniumdriver(), getPersonalInfoPageModel().checkbox);
			}
		}
		Thread.sleep(1000);
		log.info("End of Identification Documents");
	}
}
