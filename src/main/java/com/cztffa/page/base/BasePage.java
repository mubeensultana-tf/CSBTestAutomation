package com.cztffa.page.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import com.cztffa.application.WebApplicationEnum;
import com.cztffa.browseractions.BrowserActions;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.driver.SeleniumUIDriver;
import com.cztffa.page.consumer.AccountDetailsPageModel;
import com.cztffa.page.consumer.DisclosuresPageModel;
import com.cztffa.page.consumer.FundingPageModel;
import com.cztffa.page.consumer.GettingStartedPageModel;
import com.cztffa.page.consumer.PersonalInfoPageModel;
import com.cztffa.page.consumer.ProductSelectorPageModel;
import com.cztffa.page.consumer.ReviewPageModel;
import com.cztffa.page.consumer.SalesforceDetailsPageModel;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class BasePage {

	private SeleniumDriver seleniumdriver;

	public Properties prop;

	public BrowserActions browserActions = new BrowserActions("capture");

	public static String PATH = System.getProperty("user.dir") + "\\Screenshots\\";

	private String browser;

	private WebDriverWait wait;

	private void loadPropeties() {
		try {
			log.info("Initializing the properties");
			prop = new Properties();
			FileInputStream ip = new FileInputStream("./Configuration/config.properties");
			prop.load(ip);
			log.info("Successfully loaded the properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getApplicationURL(String channel) {
		if ("BRANCH".equalsIgnoreCase(channel)) {
			return prop.getProperty("branchURL");
		}
		return prop.getProperty("baseURL");
	}

	public void launch(String channel, String browser) {
		this.browser = browser;
		loadPropeties();
		setSeleniumdriver(new SeleniumUIDriver());
		getSeleniumdriver().launch(getWebBrowserType(), getApplicationURL(channel));
	}

	public void launchGmail() {
		getSeleniumdriver().launch(getWebBrowserType(), getGmailUrl());
	}

	private WebApplicationEnum getWebBrowserType() {
		WebApplicationEnum type = WebApplicationEnum.CHROME;
		if (browser.equals("firefox")) {
			type = WebApplicationEnum.FIREFOX;
		}
		return type;
	}
	
	public ProductSelectorPageModel getProductSelectorPageModel() {
		return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(ProductSelectorPageModel.class);
	}

	public PersonalInfoPageModel getPersonalInfoPageModel() {
		return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(PersonalInfoPageModel.class);
	}

	public GettingStartedPageModel getgettingStartedPageModel(){
		return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(GettingStartedPageModel.class);
	}
	

	public AccountDetailsPageModel getAccountDetailsPageModel() {
		return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(AccountDetailsPageModel.class);
	}

	public FundingPageModel getFundingPageModel() {
		return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(FundingPageModel.class);
	}

	public ReviewPageModel getReviewPageModel() {
		return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(ReviewPageModel.class);
	}

	public DisclosuresPageModel getDisclosuresPageModel() {
		return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(DisclosuresPageModel.class);
	}

	public SalesforceDetailsPageModel getSalesforceDetailsPageModel() {
		return getSeleniumdriver().getWebApplication(getWebBrowserType()).getModel(SalesforceDetailsPageModel.class);
	}

	public String getGmailUrl() {
		return prop.getProperty("gmailUrl");
	}

	public String getEmail() {
		return prop.getProperty("email");
	}

	public String getPassword() {
		return prop.getProperty("password");
	}

	public SeleniumDriver getSeleniumdriver() {
		return seleniumdriver;
	}

	public void setSeleniumdriver(SeleniumDriver seleniumdriver) {
		this.seleniumdriver = seleniumdriver;
	}

	public void wait(WebElement element){
		this.seleniumdriver = getSeleniumdriver();
		this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void wait(String element){
		this.seleniumdriver = getSeleniumdriver();
		this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
	}

	public void waitForVisibility(String element){
		this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
	}

	public void waitForVisibilityWithLoader(String element){
		this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/app-root/div/main/tf-spinner")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
	}

	public void waitWithSpinner(WebElement element){
		this.seleniumdriver = getSeleniumdriver();
		this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(90));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/app-root/div/main/tf-spinner")));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void spinner(){
		this.seleniumdriver = getSeleniumdriver();
		this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/app-root/div/main/tf-spinner")));
	}

	public void waitInDisclosures(){
		this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(40));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/app-root/div/main/tf-spinner")));
	}

	public void waitForSpinnerToDisappear() {
		this.seleniumdriver = getSeleniumdriver();
		this.wait = new WebDriverWait(seleniumdriver.getWebDriver(), Duration.ofSeconds(50));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//tf-spinner")));
	}

	public void selectElement(WebDriver driver, WebElement elementToSelect) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		new Actions(driver)
				.moveToElement(elementToSelect)
				.pause(Duration.ofMillis(400))
				.perform();

		wait.until(ExpectedConditions.visibilityOf(elementToSelect));
		wait.until(ExpectedConditions.elementToBeClickable(elementToSelect));



		log.info("Selected element (dropdown value or checkbox)");
	}

	public void waitForInnerSpinnersToDisappear(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		By spinnerLocator = By.xpath("//tf-spinner[not(contains(@style,'display: none')) or not(contains(@class,'ng-hide'))]");

		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
			log.info("Inner spinner have disappeared.");
		} catch (TimeoutException e) {
			log.warn("Timeout while waiting for inner spinner to disappear. Proceeding anyway.");
		}
	}

	public void scrollToElementWithActionPause(SeleniumDriver seleniumDriver, WebElement element) {
		try {
			// Scroll to the element using your existing method
			browserActions.scrollToWebElement(seleniumDriver, element);

			// Use Actions to pause for 2.5 seconds
			Actions actions = new Actions(seleniumDriver.getWebDriver());
			actions.pause(Duration.ofMillis(2500)).perform();

			log.info("Scrolling complete");
		} catch (Exception e) {
			log.error("Error during scroll and pause", e);
		}
	}

	public void waitWithShortTime(SeleniumDriver seleniumDriver)
	{
		try
		{
//            browserActions.scrollToWebElement(seleniumDriver,element);
			Actions actions = new Actions(seleniumDriver.getWebDriver());
			actions.pause(Duration.ofMillis(1500)).perform();
		}
		catch(Exception e) {
			log.info("Erroe during wait="+e);
		}

	}

	public void waitWithLongTime(SeleniumDriver seleniumDriver)
	{
		try
		{
//            browserActions.scrollToWebElement(seleniumDriver,element);
			Actions actions = new Actions(seleniumDriver.getWebDriver());
			actions.pause(Duration.ofMillis(2700)).perform();
		}
		catch(Exception e) {
			log.info("Erroe during wait="+e);
		}
	}
}
