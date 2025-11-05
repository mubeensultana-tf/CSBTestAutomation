package com.cztffa.cucumber.parallel.consumer;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.dataproviders.DataCSVExtractor;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.xpath.consumer.Product;
import com.cztffa.objects.StepData;
import com.cztffa.page.review.ReviewPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.testng.Reporter;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@Slf4j
public class ProductStepDefinition {

	private ReviewPage reviewPage;
	private SeleniumDriver seleniumdriver;
	private BrowserActions browserActions;
	private StepData stepData;

	public ProductStepDefinition(ReviewPage reviewPage, StepData stepData) {
		this.reviewPage = reviewPage;
		this.browserActions = reviewPage.browserActions;
		this.stepData = stepData;
	}

	@Before()
	public void setUp(Scenario scenario) {
		log.info("Starting scenario {} ", scenario.getName());
		String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
		List<String> tags = (List<String>) scenario.getSourceTagNames();
		log.info(tags.toString());
		if(!tags.isEmpty())
		{
			for (String tag : tags) {
				log.info(" tag name ============ " + tag);
				if (tag.startsWith("@flow")) {
					String flowName = tag.toLowerCase();
					if (flowName.contains("digital")) {
						reviewPage.launch("Digital", browser);
					} else if (flowName.contains("branch")) {
						reviewPage.launch("Branch", browser);
					}
				}
			}
		}
		else {
			reviewPage.launch("Digital", browser);
		}
	}

	@Given("^: I am at the home page$")
	public void _i_am_at_the_home_page() throws Throwable {
		try {
		log.info("Starting of home page");
//		String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
//		String channel = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("channel");
//		reviewPage.launch(channel, browser);
		this.seleniumdriver = reviewPage.getSeleniumdriver();
			log.info("loaded home page ");
			reviewPage.waitForSpinnerToDisappear();
		} catch (Exception ex) {
			log.error("Failed in home page loading");
		}
	}

	@When(": I click on the consumer tab$")
	@SneakyThrows
	public void i_click_on_the_consumer_tab() {
		log.info("Before click of consumer tab");
		Thread.sleep(2000);
		reviewPage.spinner();
		reviewPage.spinner();
		log.info(seleniumdriver.getWindowHandles());
		reviewPage.waitWithSpinner(reviewPage.getProductSelectorPageModel().clickConsumerTab);
		browserActions.clickButton(seleniumdriver,reviewPage.getProductSelectorPageModel().clickConsumerTab);
		log.info("On click of consumer tab");
	}

	@Then(": I click on product for {string}")
	public void iClickOnProduct(String submissionId) throws Throwable {
			List<Map<?,?>> productDataStore = DataCSVExtractor.productDataStore;

			log.info("Clicking all products for submissionId: " + submissionId);

        for (Map<?,?> map : productDataStore) {
            String rowSubmissionId = map.get("submissionId").toString().trim();
            if (!rowSubmissionId.equals(submissionId)) continue;
            String product = map.get("product").toString().trim();
            String xpath = Product.getByName(product).getXpath();
            reviewPage.wait(xpath);
            WebElement element = browserActions.findElement(seleniumdriver, By.xpath(xpath));
            browserActions.scrollToWebElement(seleniumdriver, element);
			reviewPage.waitWithSpinner(element);
			browserActions.clickUsingEnter(seleniumdriver.getWebDriver(),element);
            log.info("Clicked product: " + product);
        }
	}

	@And("^: I click on the checkout$")
	public void iClickOnTheCheckout() throws Throwable {
		log.info("Before click of checkout btn");
		reviewPage.wait(reviewPage.getProductSelectorPageModel().checkOutBtn);
		browserActions.scrollToWebElement(seleniumdriver, reviewPage.getProductSelectorPageModel().checkOutBtn);
		Thread.sleep(1000);
		browserActions.clickButton(seleniumdriver, reviewPage.getProductSelectorPageModel().checkOutBtn);
		log.info("On click of checkout btn");
		reviewPage.wait(reviewPage.getProductSelectorPageModel().proceedBtn);
		log.info("Before click of proceed btn");
		browserActions.scrollToWebElement(seleniumdriver, reviewPage.getProductSelectorPageModel().proceedBtn);
		reviewPage.waitWithSpinner(reviewPage.getProductSelectorPageModel().proceedBtn);
		browserActions.clickButton(seleniumdriver, reviewPage.getProductSelectorPageModel().proceedBtn);
		log.info("On click of proceed btn");
	}

    @Then(": I click on the open an account")
    public void iClickOpenAnAccount() throws InterruptedException {
        log.info("Before clicking on Add New button");
        Thread.sleep(1000);
        reviewPage.waitWithSpinner(reviewPage.getProductSelectorPageModel().OpenAnAccount);
        browserActions.scrollToWebElement(seleniumdriver, reviewPage.getProductSelectorPageModel().OpenAnAccount);
        reviewPage.waitWithSpinner(reviewPage.getProductSelectorPageModel().OpenAnAccount);
        browserActions.clickButton(seleniumdriver, reviewPage.getProductSelectorPageModel().addNew);
        log.info("On click of Open an account button");
    }
	@Then(": I click on proceed without prefill")
	public void iClickOnProceedWithoutPrefill() throws InterruptedException {
		log.info("Before clicking on proceed without prefill button");
		reviewPage.waitWithSpinner(reviewPage.getProductSelectorPageModel().proceedWithoutPrefillBtn);
		browserActions.scrollToWebElement(seleniumdriver, reviewPage.getProductSelectorPageModel().proceedWithoutPrefillBtn);
		reviewPage.waitWithSpinner(reviewPage.getProductSelectorPageModel().proceedWithoutPrefillBtn);
		browserActions.clickButton(seleniumdriver, reviewPage.getProductSelectorPageModel().proceedWithoutPrefillBtn);
		log.info("On click of proceed without prefill button");
	}

	@Then(": I click on Add New")
	public void iClickOnAddNew() throws InterruptedException {
		log.info("Before clicking on Add New button");
		Thread.sleep(1000);
		reviewPage.waitWithSpinner(reviewPage.getProductSelectorPageModel().addNew);
		browserActions.scrollToWebElement(seleniumdriver, reviewPage.getProductSelectorPageModel().addNew);
		reviewPage.waitWithSpinner(reviewPage.getProductSelectorPageModel().addNew);
		browserActions.clickButton(seleniumdriver, reviewPage.getProductSelectorPageModel().addNew);
		log.info("On click of Add New button");
	}

	@After()
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) seleniumdriver.getWebDriver())
					.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		log.info("Finished scenario {}", scenario.getName());
		log.info("Final line it got executed {} ", scenario.getLine());
		log.info("Status of the scenario {} ", scenario.getStatus());
		seleniumdriver.getWebDriver().quit();
	}


}
