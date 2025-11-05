package com.cztffa.driver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.cztffa.application.WebApplication;
import com.cztffa.application.WebApplicationEnum;
import com.cztffa.application.WebApplicationFactory;
import com.cztffa.exception.UXException;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class SeleniumUIDriver implements SeleniumDriver {

	protected WebApplication webApplication;

	protected WebDriver driver;

	private boolean isLaunched;

	private WebApplicationFactory webApplicationFactory = new WebApplicationFactory();

	@Override
	public void launch(WebApplicationEnum webApplicationType, String url) {
		webApplication = getWebApplication(webApplicationType);
		driver = webApplication.launch();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		webApplication.navigateToPageUrl(url);
		log.info("Application launched");
		setLaunched(true);
	}

	@Override
	public void launch(WebApplicationEnum webApplicationType, String url, int implicitWait) {
		webApplication = getWebApplication(webApplicationType);
		driver = webApplication.launch(implicitWait);
		webApplication.navigateToPageUrl(url);
		log.info("Application launched");
		setLaunched(true);
	}

	@Override
	public void shrinkScreenWidth(int width) {
		webApplication.shrinkScreenWidth(width);
	}

	@Override
	public void logout() {
		if (null != webApplication) {
			webApplication.cleanup();
		}
	}

	@Override
	public void navigateToPage(String url) {
		webApplication.navigateToPageUrl(url);
	}

	@Override
	public void switchToFrame(String frameName) {
		webApplication.switchToFrame(frameName);
	}

	@Override
	public void switchToParentFrame() {
		webApplication.switchToParentFrame();
	}

	@Override
	public void switchToDefaultContentFrame() {
		webApplication.switchToDefaultContentFrame();
	}

	@Override
	public void waitUntilElementIsClickable(WebElement webElement, int timeout) {
		webApplication.waitUntilElementIsClickable(webElement, timeout);
	}

	@Override
	public Actions retrieveWebActions() {
		return webApplication.retrieveWebActions();
	}

	@Override
	public UXException exceptionHandler(String testMethod, WebDriverException e) {
		List<String> exceptionMessages = generalExceptionHandler(e);
		return createUXException(String.format("%s%n%s", exceptionMessages.get(0), exceptionMessages.get(1)),
				testMethod);
	}

	@Override
	public UXException exceptionHandler(Exception e) {
		List<String> exceptionMessages = generalExceptionHandler(e);
		return createUXException(String.format("%s%n%s", exceptionMessages.get(0), exceptionMessages.get(1)));
	}

	@Override
	public UXException exceptionHandler(String message) {
		return createUXException(message);
	}

	@Override
	public UXException exceptionHandler(Exception e, String message) {
		List<String> exceptionMessages = generalExceptionHandler(e);
		return createUXException(
				String.format("%s%n%s%n%s", message, exceptionMessages.get(0), exceptionMessages.get(1)));
	}

	@Override
	public void exceptionIgnored(Exception e, String message) {
		log.info("Exception ignored: {}", e.getMessage());
		log.info("Exception for the following reason: {}", message);
	}

	@Override
	public final WebDriver getWebDriver() {
//        ChromeOptions options = new ChromeOptions();
//        String local = System.getProperty(RUN_LOCALLY_PROPERTY, FALSE);
//        if (local.equalsIgnoreCase(FALSE)) {
//            options.addArguments("--headless");
//            options.addArguments("window-size=1920,1080");
//        }
		return this.driver;
	}

	private UXException createUXException(String message) {
		String errorMessage = getApplicationErrorMessage();
		message = StringUtils.isEmpty(errorMessage) ? message : message + errorMessage;

		return new UXException(webApplication, message);
	}

	private UXException createUXException(String message, String testMethod) {
		String errorMessage = getApplicationErrorMessage();
		message = StringUtils.isEmpty(errorMessage) ? message : message + errorMessage;

		return new UXException(webApplication, message, testMethod);
	}

	protected String getApplicationErrorMessage() {
		return StringUtils.EMPTY;
	}

	@Override
	public String capture() {
		String capturePath = webApplication.takeScreenshot();
		log.info("Screenshot taken: {}", capturePath);
		return capturePath;
	}

	private List<String> generalExceptionHandler(Exception e) {
		List<String> messageCause = new ArrayList<>();
		messageCause.add(StringUtils.substringBefore(e.getMessage(), "\n"));
		messageCause.add(e.getCause() != null ? StringUtils.substringBefore(e.getCause().getMessage(), "\n")
				: "No cause returned");
		return messageCause;
	}

	@Override
	public boolean isLaunched() {
		return isLaunched;
	}

	public void setLaunched(boolean launched) {
		isLaunched = launched;
	}

	@Override
	public WebApplication getWebApplication(WebApplicationEnum webApplicationType) {
		if (this.webApplication == null) {
			return webApplicationFactory.getWebApplication(webApplicationType);
		} else {
			return webApplication;
		}
	}

	@Override
	public String getWindowHandles() {
		return driver.getWindowHandle();
	}

	@Override
	public void switchTo(WebDriver webDriver, String windowHandle) {
		webDriver.switchTo().window(windowHandle);
	}

	@Override
	public void launchApplication(String url) {
		launch(WebApplicationEnum.CHROME, url);
	}

	@Override
	public boolean browserExists() {
		return isLaunched();
	}
}
