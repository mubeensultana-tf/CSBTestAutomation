package com.cztffa.browseractions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cztffa.driver.SeleniumDriver;
import com.google.common.base.Function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BrowserActions {

	public static final Duration ONE_SECOND_DURATION = Duration.ofSeconds(1);

	private static final String FAILED_TO_FIND_ELEMENT_MESSAGE = "Failed to find WebElement";

	private final String testMethod;

	/**
	 * Enter text in a text box
	 *
	 * @param seleniumDriver      The selenium driver implementation
	 * @param enterTextWebElement The text box
	 * @param text                The text to enter
	 */
	public void enterText(SeleniumDriver seleniumDriver, WebElement enterTextWebElement, String text) {
		try {
			enterTextWebElement.clear();
			enterTextWebElement.sendKeys(text);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Enter text in a text box
	 *
	 * @param seleniumDriver      The selenium driver implementation
	 * @param enterTextWebElement The text box
	 * @param text                The text to enter
	 */
	public void enterTextNoTextClear(SeleniumDriver seleniumDriver, WebElement enterTextWebElement, String text) {
		try {
			enterTextWebElement.sendKeys(text);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Enter text in a text box with a wait until clickable period
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param webElement     The text box
	 * @param text           The text to enter
	 * @param timeoutPeriod  The maximum amount of time to wait in millisecs.
	 * @param backoffPeriod  The time between each attempt in millisecs.
	 */
	public void enterText(SeleniumDriver seleniumDriver, WebElement webElement, String text, int timeoutPeriod,
			int backoffPeriod) {

		WebElement element = waitToBeClickable(seleniumDriver, webElement, timeoutPeriod, backoffPeriod);
		enterText(seleniumDriver, element, text);
	}

	/**
	 * Clear text in a text box
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param webElement     The text box
	 */
	public void clearText(SeleniumDriver seleniumDriver, WebElement webElement) {
		try {
			webElement.clear();
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Click on a button
	 *
	 * @param seleniumDriver   The selenium driver implementation
	 * @param buttonWebElement The button to click
	 */
	public void clickButton(SeleniumDriver seleniumDriver, WebElement buttonWebElement) {
		try {
			log.info("Attempting to click: {}", buttonWebElement);
			buttonWebElement.click();
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Clicks a button with a wait until clickable period
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param webElement     The button to click
	 * @param timeoutPeriod  The maximum amount of time to wait in millisecs.
	 * @param backoffPeriod  The time between each attempt in millisecs.
	 */
	public void clickButton(SeleniumDriver seleniumDriver, WebElement webElement, int timeoutPeriod,
			int backoffPeriod) {

		if (webElementExists(seleniumDriver, webElement, timeoutPeriod, backoffPeriod)) {
			WebElement element = waitToBeClickable(seleniumDriver, webElement, timeoutPeriod, backoffPeriod);
			clickButton(seleniumDriver, element);
		} else {
			throw seleniumDriver
					.exceptionHandler("Button could not be clicked as not displayed & enabled within timeout period");
		}
	}

	/**
	 * Click on a button
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param element        The element to click
	 * @param x              Offset of x pixels
	 * @param y              Offset of y pixels
	 */
	public void clickButtonWithOffset(SeleniumDriver seleniumDriver, WebElement element, int x, int y) {
		try {
			Actions action = seleniumDriver.retrieveWebActions().moveToElement(element, x, y).click();
			action.build().perform();
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Select an element using a mouse over click with Javascript - ensures that an
	 * element that is difficult to click in UI directly will always be clicked even
	 * if covered by another element
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param element        The element to click
	 */
	public void mouseOverClickElement(SeleniumDriver seleniumDriver, WebElement element) {
		try {
			((JavascriptExecutor) seleniumDriver.getWebDriver())
					.executeScript("var evObj = document.createEvent('MouseEvents');"
							+ "evObj.initMouseEvent('mouseover',true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
							+ "arguments[0].dispatchEvent(evObj);" + "arguments[0].click();", element);
		} catch (NotFoundException e) {
			log.error(String.format("%s", e.getMessage()));
			throw seleniumDriver.exceptionHandler(e);
		} catch (WebDriverException wde) {
			log.error(String.format("%s", wde.getMessage()));
			throw seleniumDriver.exceptionHandler(wde);
		}
	}

	/**
	 * Return the String value of the currently selected frame
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @return String String value of the frame currently selected
	 */
	public String getCurrentFrame(SeleniumDriver seleniumDriver) {
		return (String) ((JavascriptExecutor) seleniumDriver.getWebDriver())
				.executeScript("return self.name.toString()");
	}

	/**
	 * Click on a button
	 *
	 * @param seleniumDriver     The selenium driver implementation
	 * @param checkboxWebElement The checkbox to click
	 */
	public void clickCheckboxListItem(SeleniumDriver seleniumDriver, WebElement checkboxWebElement) {
		try {
			Actions action = seleniumDriver.retrieveWebActions().moveToElement(checkboxWebElement).click();
			action.build().perform();
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Confirm that the checkbox is not currently in the state expected
	 * (checkedState) and click the checkbox if this is the case.
	 *
	 * @param seleniumDriver              The selenium driver implementation
	 * @param checkboxWebElement          The checkbox to click
	 * @param expectedCurrentCheckedState If true, the checkbox should currently be
	 *                                    in a checked state; if false, currently
	 *                                    unchecked
	 */
	public void clickCheckboxListItem(SeleniumDriver seleniumDriver, WebElement checkboxWebElement,
			boolean expectedCurrentCheckedState) {
		Boolean actualCurrentCheckState = Boolean.parseBoolean(
				getAttribute(seleniumDriver, checkboxWebElement.findElement(By.tagName("input")), "checked"));
		if (expectedCurrentCheckedState == actualCurrentCheckState) {
			clickCheckboxListItem(seleniumDriver, checkboxWebElement.findElement(By.tagName("input")));
		} else {
			log.info("Checkbox is already in expected state");
		}
	}

	/**
	 * Click a checkbox in a cbs-list group which corresponds to the passed text
	 *
	 * @param seleniumDriver      The selenium driver implementation
	 * @param parentElement       The parent of the checkbox group
	 * @param expectedDescription the text of the checkbox to click
	 */
	public void clickCheckboxListItem(SeleniumDriver seleniumDriver, WebElement parentElement,
			String expectedDescription, int timeoutPeriod, int backoffPeriod) {
		clickCheckboxByLabel(seleniumDriver, parentElement, By.cssSelector("cbs-list > cbs-list-item"),
				expectedDescription, timeoutPeriod, backoffPeriod);
	}

	/**
	 * Click a checkbox which corresponds to the passed text
	 *
	 * @param seleniumDriver      The selenium driver implementation
	 * @param parentElement       The parent of the checkbox group
	 * @param expectedDescription the text of the checkbox to click
	 */
	public void clickCheckbox(SeleniumDriver seleniumDriver, WebElement parentElement, String expectedDescription,
			int timeoutPeriod, int backoffPeriod) {
		clickCheckboxByLabel(seleniumDriver, parentElement, By.cssSelector("cbs-checkbox"), expectedDescription,
				timeoutPeriod, backoffPeriod);
	}

	private void clickCheckboxByLabel(SeleniumDriver seleniumDriver, WebElement parentElement, By childIdentifier,
			String expectedDescription, int timeoutPeriod, int backoffPeriod) {
		List<WebElement> childElements = findElements(seleniumDriver, parentElement, childIdentifier, timeoutPeriod,
				backoffPeriod);
		for (WebElement childElement : childElements) {
			WebElement label = findElement(seleniumDriver, childElement,
					By.cssSelector("label[class^='mdc-checkbox__label']"), timeoutPeriod, backoffPeriod);
			log.info("Found label: '{}'", label.getText());
			if (StringUtils.startsWithIgnoreCase(label.getText(), expectedDescription)) {
				clickButton(seleniumDriver, label);
				return;
			}
		}

		throw seleniumDriver.exceptionHandler(String.format("Failed to find Checkbox for '%s'", expectedDescription));
	}

	/**
	 * Determine whether a checkbox which corresponds to the passed text is selected
	 * or not
	 *
	 * @param seleniumDriver      The selenium driver implementation
	 * @param parentElement       The parent of the checkbox group
	 * @param expectedDescription the text of the checkbox to click
	 * @return true if selected, otherwise false
	 */
	public boolean isCheckboxSelectedListItem(SeleniumDriver seleniumDriver, WebElement parentElement,
			String expectedDescription, int timeoutPeriod, int backoffPeriod) {
		return isCheckboxSelectedByLabel(seleniumDriver, parentElement, By.cssSelector("cbs-list > cbs-list-item"),
				expectedDescription, timeoutPeriod, backoffPeriod);
	}

	/**
	 * Determine whether a checkbox which corresponds to the passed text is selected
	 * or not
	 *
	 * @param seleniumDriver      The selenium driver implementation
	 * @param parentElement       The parent of the checkbox group
	 * @param expectedDescription the text of the checkbox to click
	 * @return true if selected, otherwise false
	 */
	public boolean isCheckboxSelected(SeleniumDriver seleniumDriver, WebElement parentElement,
			String expectedDescription, int timeoutPeriod, int backoffPeriod) {
		return isCheckboxSelectedByLabel(seleniumDriver, parentElement, By.cssSelector("cbs-checkbox"),
				expectedDescription, timeoutPeriod, backoffPeriod);
	}

	private boolean isCheckboxSelectedByLabel(SeleniumDriver seleniumDriver, WebElement parentElement,
			By childIdentifier, String expectedDescription, int timeoutPeriod, int backoffPeriod) {
		List<WebElement> childElements = findElements(seleniumDriver, parentElement, childIdentifier, timeoutPeriod,
				backoffPeriod);
		for (WebElement childElement : childElements) {
			WebElement label = findElement(seleniumDriver, childElement,
					By.cssSelector("label[class^='mdc-checkbox__label']"), timeoutPeriod, backoffPeriod);
			log.info("Found label: '{}'", label.getText());
			if (StringUtils.startsWithIgnoreCase(label.getText(), expectedDescription)) {
				boolean actualCurrentCheckState = Boolean
						.parseBoolean(getAttribute(seleniumDriver, childElement, "checked.bind"));
				log.info("Checkbox is selected: {}", actualCurrentCheckState);
				return actualCurrentCheckState;
			}
		}

		throw seleniumDriver.exceptionHandler(String.format("Failed to find Checkbox for '%s'", expectedDescription));
	}

	/**
	 * Determine whether a checkbox which corresponds to the passed text is enabled
	 * or not
	 *
	 * @param seleniumDriver      The selenium driver implementation
	 * @param parentElement       The parent of the checkbox group
	 * @param expectedDescription the text of the checkbox to click
	 * @return true if enabled, otherwise false
	 */
	public boolean isCheckboxEnabledListItem(SeleniumDriver seleniumDriver, WebElement parentElement,
			String expectedDescription, int timeoutPeriod, int backoffPeriod) {
		return isCheckboxEnabledByLabel(seleniumDriver, parentElement, By.cssSelector("cbs-list > cbs-list-item"),
				expectedDescription, timeoutPeriod, backoffPeriod);
	}

	/**
	 * Determine whether a checkbox which corresponds to the passed text is enabled
	 * or not
	 *
	 * @param seleniumDriver      The selenium driver implementation
	 * @param parentElement       The parent of the checkbox group
	 * @param expectedDescription the text of the checkbox to click
	 * @return true if enabled, otherwise false
	 */
	public boolean isCheckboxEnabled(SeleniumDriver seleniumDriver, WebElement parentElement,
			String expectedDescription, int timeoutPeriod, int backoffPeriod) {
		return isCheckboxEnabledByLabel(seleniumDriver, parentElement, By.cssSelector("cbs-checkbox"),
				expectedDescription, timeoutPeriod, backoffPeriod);
	}

	private boolean isCheckboxEnabledByLabel(SeleniumDriver seleniumDriver, WebElement parentElement,
			By childIdentifier, String expectedDescription, int timeoutPeriod, int backoffPeriod) {
		List<WebElement> childElements = findElements(seleniumDriver, parentElement, childIdentifier, timeoutPeriod,
				backoffPeriod);
		for (WebElement childElement : childElements) {
			WebElement label = findElement(seleniumDriver, childElement,
					By.cssSelector("label[class^='mdc-checkbox__label']"), timeoutPeriod, backoffPeriod);
			log.info("Found label: '{}'", label.getText());
			if (StringUtils.startsWithIgnoreCase(label.getText(), expectedDescription)) {
				boolean enabled = findElement(seleniumDriver, childElement, By.id(label.getAttribute("for")),
						timeoutPeriod, backoffPeriod).isEnabled();
				log.info("Checkbox is enabled: {}", enabled);
				return enabled;
			}
		}

		throw seleniumDriver.exceptionHandler(String.format("Failed to find Checkbox for '%s'", expectedDescription));
	}

	/**
	 * Determine whether a checkbox which corresponds to the passed text is
	 * displayed or not
	 *
	 * @param seleniumDriver      The selenium driver implementation
	 * @param parentElement       The parent of the checkbox group
	 * @param expectedDescription the text of the checkbox to click
	 * @return true if displayed, otherwise false
	 */
	public boolean isCheckboxDisplayedListItem(SeleniumDriver seleniumDriver, WebElement parentElement,
			String expectedDescription, int timeoutPeriod, int backoffPeriod) {
		return isCheckboxDisplayedByLabel(seleniumDriver, parentElement, By.cssSelector("cbs-list > cbs-list-item"),
				expectedDescription, timeoutPeriod, backoffPeriod);
	}

	/**
	 * Determine whether a checkbox which corresponds to the passed text is
	 * displayed or not
	 *
	 * @param seleniumDriver      The selenium driver implementation
	 * @param parentElement       The parent of the checkbox group
	 * @param expectedDescription the text of the checkbox to click
	 * @return true if displayed, otherwise false
	 */
	public boolean isCheckboxDisplayed(SeleniumDriver seleniumDriver, WebElement parentElement,
			String expectedDescription, int timeoutPeriod, int backoffPeriod) {
		return isCheckboxDisplayedByLabel(seleniumDriver, parentElement, By.cssSelector("cbs-checkbox"),
				expectedDescription, timeoutPeriod, backoffPeriod);
	}

	private boolean isCheckboxDisplayedByLabel(SeleniumDriver seleniumDriver, WebElement parentElement,
			By childIdentifier, String expectedDescription, int timeoutPeriod, int backoffPeriod) {
		List<WebElement> childElements = findElements(seleniumDriver, parentElement, childIdentifier, timeoutPeriod,
				backoffPeriod);
		for (WebElement childElement : childElements) {
			WebElement label = findElement(seleniumDriver, childElement,
					By.cssSelector("label[class^='mdc-checkbox__label']"), timeoutPeriod, backoffPeriod);
			log.info("Found label: '{}'", label.getText());
			if (StringUtils.startsWithIgnoreCase(label.getText(), expectedDescription)) {
				boolean isDisplayed = this.isElementDisplayed(seleniumDriver, childElement, timeoutPeriod,
						backoffPeriod);
				log.info("Checkbox is displayed: {}", isDisplayed);
				return isDisplayed;
			}
		}

		throw seleniumDriver.exceptionHandler(String.format("Failed to find Checkbox for '%s'", expectedDescription));
	}

	/**
	 * Scroll to a WebElement
	 *
	 * @param seleniumDriver     The selenium driver implementation
	 * @param scrollToWebElement WebElement to scroll into view
	 */
	public void scrollTo(SeleniumDriver seleniumDriver, WebElement scrollToWebElement) {
		try {
			Actions action = seleniumDriver.retrieveWebActions().moveToElement(scrollToWebElement);
			action.build().perform();
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * get the text contents of a web element
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param textElement    the object to retrieve text from
	 * @return the text
	 */
	public String getText(SeleniumDriver seleniumDriver, WebElement textElement) {
		try {
			return textElement.getText();
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return null;
		}
	}

	/**
	 * Tries to get the text in a node and its children, handles any exception
	 * quietly
	 *
	 * @param webElement the root element to get the text from
	 * @return a single concatenated text block of the text contained in a
	 *         webelement and its children
	 */
	public String getInnerText(WebElement webElement) {
		String text = "";
		try {
			text = webElement.getText().trim();
			log.info("Got text {} from element {}", text, webElement);

			List<WebElement> children = webElement.findElements(By.xpath("./*"));
			int size = children.size();
			log.info("Found {} children of element {}", size, webElement);

			for (WebElement child : children) {
				String childText = child.getText().trim();
				log.info("Got text {} from child element {}", childText, webElement);
				text = new StringBuilder().append(text).append(childText).toString();
			}
		} catch (Exception e) {
			log.error(String.format("%s", e.getMessage()));
		}
		return text;
	}

	/**
	 * Tries to get the text in a node and its children, handles any exception
	 * quietly
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param webElement     the root element to get the text from
	 * @param timeoutPeriod  The maximum amount of time to wait in millisecs.
	 * @param backoffPeriod  The time between each attempt in millisecs.
	 * @return a single concatenated text block of the text contained in a
	 *         webelement and its children
	 */
	public String getInnerText(SeleniumDriver seleniumDriver, WebElement webElement, int timeoutPeriod,
			int backoffPeriod) {

		WebElement element = waitToBeClickable(seleniumDriver, webElement, timeoutPeriod, backoffPeriod);
		return getInnerText(element);
	}

	/**
	 * get the text contents of a web element
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param webElement     the object to retrieve text from
	 * @param timeoutPeriod  The maximum amount of time to wait in millisecs.
	 * @param backoffPeriod  The time between each attempt in millisecs.
	 * @return the text
	 */
	public String getText(SeleniumDriver seleniumDriver, WebElement webElement, int timeoutPeriod, int backoffPeriod) {

		WebElement element = waitToBeClickable(seleniumDriver, webElement, timeoutPeriod, backoffPeriod);
		return getText(seleniumDriver, element);
	}

	/**
	 * Retrieve a specified attribute from the WebElement
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param textElement    The WebElement to retrieve information from
	 * @param attribute      The attribute to retrieve
	 * @return the attribute value
	 */
	public String getAttribute(SeleniumDriver seleniumDriver, WebElement textElement, String attribute) {
		try {
			return textElement.getAttribute(attribute);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return null;
		}
	}

	/**
	 * Confirm if an attribute property is present for a WebElement
	 *
	 * @param seleniumDriver WebDriver implementation
	 * @param element        WebElement to be checked for attribute
	 * @param attribute      The attribute property value to be checked on the
	 *                       element
	 * @return Boolean true if attribute present, false is not
	 */
	public boolean isAttributePresent(SeleniumDriver seleniumDriver, WebElement element, String attribute) {
		try {
			return element.getAttribute(attribute) != null;
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return false;
		}
	}

	/**
	 * Confirm if an attribute property value is an exact match or contains a value
	 * for a WebElement attribute
	 *
	 * @param seleniumDriver WebDriver implementation
	 * @param element        WebElement to be checked for attribute
	 * @param attribute      The attribute property
	 * @param value          The attribute property value expected
	 * @param exactMatch     True for exact match within attribute, false otherwise
	 * @return Boolean true if attribute present, false is not
	 */
	public boolean isAttributeValuePresent(SeleniumDriver seleniumDriver, WebElement element, String attribute,
			String value, boolean exactMatch) {
		try {
			String attributeValue = element.getAttribute(attribute);
			if (exactMatch) {
				if (attributeValue != null && attributeValue.equalsIgnoreCase(value)) {
					return true;
				}
			} else {
				if (attributeValue != null && attributeValue.contains(value)) {
					return true;
				}
			}
			return false;
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return false;
		}
	}

	/**
	 * Wait for an element to be enabled
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param webElement     The object to be waited for
	 * @return true if enabled
	 */
	public boolean waitForElementToBeEnabled(SeleniumDriver seleniumDriver, WebElement webElement) {
		try {
			return webElement.isEnabled();
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return false;
		}
	}

	/**
	 * Wait for an element to be enabled
	 *
	 * @param seleniumDriver Selenium driver implementation
	 * @param webElement     WebElement to check property of
	 * @param timeoutPeriod  Time out period in millis as int
	 * @param backoffPeriod  Back off period in millis as int
	 * @return boolean true if enabled, false if not after wait
	 */
	public boolean waitForElementToBeEnabled(SeleniumDriver seleniumDriver, WebElement webElement, int timeoutPeriod,
			int backoffPeriod) {
		try {
			return waitForWebElementToBeEnabled(seleniumDriver, webElement, timeoutPeriod, backoffPeriod);
		} catch (WebDriverException e) {
			log.warn("Exception: {}", e.getMessage());
			return false;
		}
	}

	/**
	 * Get a specified cell from a table by row and column number
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param table          The table to return the cell from
	 * @param row            The row number
	 * @param column         The column number
	 * @return The contents of the specified cell
	 */
	public String getCellContentsFromTable(SeleniumDriver seleniumDriver, WebElement table, int row, int column) {
		return getCellFromTable(seleniumDriver, table, row, column).getText();
	}

	/**
	 * Get a specified cell WebElement from a table by row and column number
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param table          The table to return the cell from
	 * @param row            The row number
	 * @param column         The column number
	 * @return The WebElement of the specified cell
	 */
	public WebElement getCellFromTable(SeleniumDriver seleniumDriver, WebElement table, int row, int column) {
		try {
			return table.findElements(By.tagName("tr")).get(row).findElements(By.tagName("td")).get(column);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return null;
		}
	}

	/**
	 * Return a WebElement found by the link text of the element
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param elementText    The elements link text
	 * @return WebElement The element found by link text
	 */
	public WebElement getElementByLinkText(SeleniumDriver seleniumDriver, String elementText) {
		try {
			return seleniumDriver.getWebDriver().findElement(By.linkText(elementText));
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return null;
		}
	}

	/**
	 * Get a row from a table
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param table          The table to get the row from
	 * @param row            The row number to retrieve
	 * @return The requested row
	 */
	public WebElement getRowFromTable(SeleniumDriver seleniumDriver, WebElement table, int row) {
		try {
			return table.findElements(By.tagName("tr")).get(row);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return null;
		}
	}

//	public void clickApply(WebDriver driver, WebElement drpApply) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(180));
//		try {
//			wait.until(ExpectedConditions.elementToBeClickable(drpApply)).click();
//		} catch (Exception e) {
//			e.getMessage();
//		}
//	}
public void clickApply(WebDriver driver, WebElement element) {
	try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();  // native click
	} catch (Exception e) {
		throw new RuntimeException("Failed to click element: " + e.getMessage(), e);
	}
}

	public void clickUsingActions(WebDriver driver, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(element));

			Actions actions = new Actions(driver);
			actions.moveToElement(element).pause(Duration.ofMillis(200)).click().perform();

		} catch (Exception e) {
			throw new RuntimeException("Click using Actions failed: " + e.getMessage(), e);
		}
	}
	public void safeNativeClick(WebDriver driver, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));

			Actions actions = new Actions(driver);
			actions.moveToElement(element).pause(Duration.ofMillis(200)).click().perform();
		} catch (Exception e) {
			throw new RuntimeException("safeNativeClick failed: " + e.getMessage(), e);
		}
	}
	public void clickUsingEnter(WebDriver driver, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));

			element.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			throw new RuntimeException("clickUsingEnter failed: " + e.getMessage(), e);
		}
	}
	public void safeClick(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		new Actions(driver).moveToElement(element).pause(Duration.ofMillis(200)).click().perform();
	}

	public void SelectApply(WebDriver driver, WebElement optApply) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(180));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(optApply)).click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void setDOBDate(WebDriver driver, String dob, WebElement dobElement) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(180));
			dobElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			wait.until(ExpectedConditions.elementToBeClickable(dobElement)).sendKeys(Keys.BACK_SPACE);
			wait.until(ExpectedConditions.elementToBeClickable(dobElement)).sendKeys(dob);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * Returns list of rows
	 *
	 * @param table table WebElement
	 * @return List of WebElement
	 */
	public List<WebElement> getRowsFromTable(WebElement table) {
		return table.findElements(By.tagName("tr"));
	}

	/**
	 * Get the entire contents of a specified column in a table
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param table          The table web element
	 * @param header         The table header
	 * @return A list of the column values
	 */
	public List<String> getColumnValuesFromTableUnderHeader(SeleniumDriver seleniumDriver, WebElement table,
			String header) {

		List<String> columnValues = new ArrayList<>();

		try {
			WebElement tableHeader = table.findElement(By.tagName("thead"));
			List<WebElement> tableHeaders = tableHeader.findElements(By.tagName("th"));
			int columnNumber = IntStream.range(0, tableHeaders.size())
					.filter(i -> header.equals(tableHeaders.get(i).getText())).findFirst().orElse(-1);

			WebElement tableBody = table.findElement(By.tagName("tbody"));
			List<WebElement> tableRows = tableBody.findElements(By.tagName("tr"));

			for (WebElement tableRow : tableRows) {
				List<WebElement> rowColumn = tableRow.findElements(By.tagName("td"));
				columnValues.add(rowColumn.get(columnNumber).getText());
			}
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}

		log.info("Column : {}", header);

		for (String columnValue : columnValues) {
			log.info("** column value : {}", columnValue);
		}

		return columnValues;
	}

	/**
	 * Get all the products in the table
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param table          The table web element
	 * @return A list of the column values
	 */
	public List<String> getColumnValuesFromTable(SeleniumDriver seleniumDriver, WebElement table) {

		List<String> columnValues = new ArrayList<>();

		try {
			WebElement tableBody = table.findElement(By.tagName("tbody"));
			List<WebElement> tableRows = tableBody.findElements(By.tagName("tr"));

			for (WebElement tableRow : tableRows) {
				List<WebElement> rowColumn = tableRow.findElements(By.tagName("td"));
				columnValues.add(rowColumn.iterator().next().getText());
			}
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}

		for (String columnValue : columnValues) {
			log.info("**  column value: {}", columnValue);
		}

		return columnValues;
	}

	/**
	 * Get the entire contents of all columns in a table
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param table          The table web element
	 * @return A Map of the column values
	 */
	public Map<String, String> getAllColumnValuesFromTable(SeleniumDriver seleniumDriver, WebElement table) {

		Map<String, String> columnValues = new HashMap<>();

		try {
			WebElement tableBody = table.findElement(By.tagName("tbody"));
			List<WebElement> tableRows = tableBody.findElements(By.tagName("tr"));

			for (WebElement tableRow : tableRows) {
				List<WebElement> rowColumn = tableRow.findElements(By.tagName("td"));
				columnValues.put(rowColumn.get(0).getText(), rowColumn.get(1).getText());
			}
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}

		return columnValues;
	}

	/**
	 * Get the entire contents of a table
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param table          The table web element
	 * @return A Map of row number and corresponding column values
	 */
	public Map<Integer, List<String>> getAllValuesFromTable(SeleniumDriver seleniumDriver, WebElement table) {

		Map<Integer, List<String>> tableValues = new HashMap<>();
		int rowNumber = 0;

		try {
			WebElement tableBody = table.findElement(By.tagName("tbody"));
			List<WebElement> tableRows = tableBody.findElements(By.tagName("tr"));

			for (WebElement tableRow : tableRows) {
				log.info("Table Row : " + tableRow);
				List<WebElement> rowColumns = tableRow.findElements(By.tagName("td"));
				if (rowColumns.size() > 0) {
					List<String> columnValues = new ArrayList<>();
					for (WebElement rowColumn : rowColumns) {
						log.info("Column : " + rowColumn);
						columnValues.add(rowColumn.getText());
					}
					tableValues.put(rowNumber, columnValues);
				}
				rowNumber++;
			}
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}

		return tableValues;
	}

	/**
	 * Select a given entry from a list
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param list           The list web element
	 * @param listItem       The name of the item to be selected from the list
	 */

	public void selectItemFromList(SeleniumDriver seleniumDriver, WebElement list, String listItem, int timeoutPeriod,
			int backoffPeriod) {
		try {
			waitForWebElementToBeEnabled(seleniumDriver, list, timeoutPeriod, backoffPeriod);
			List<WebElement> listItems = list.findElements(By.tagName("li"));
			boolean matchFound = false;
			for (WebElement item : listItems) {
				log.info("** list item: {}", item.getText());
				if (item.getText().toLowerCase().contains(listItem.toLowerCase())) {
					List<WebElement> itemLabels = item.findElements(By.tagName("label"));
					if (itemLabels.isEmpty()) {
						waitUntilWebElementIsClickable(seleniumDriver, item, timeoutPeriod, backoffPeriod);
						item.click();
						matchFound = true;
					} else {
						WebElement itemLabel = itemLabels.get(0);
						this.clickButton(seleniumDriver, itemLabel, timeoutPeriod, backoffPeriod);
						matchFound = true;
					}
					break;
				}
			}
			if (!matchFound) {
				throw seleniumDriver.exceptionHandler(String.format("Failed to find a match for '%s'", listItem));
			}
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Select the last item from a list
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param list           The list web element
	 */
	public void selectLastItemFromList(SeleniumDriver seleniumDriver, WebElement list, int timeoutPeriod,
			int backoffPeriod) {
		try {
			waitForWebElementToBeEnabled(seleniumDriver, list, timeoutPeriod, backoffPeriod);
			List<WebElement> listItems = list.findElements(By.tagName("li"));
			if (CollectionUtils.isEmpty(listItems)) {
				throw seleniumDriver.exceptionHandler("Cannot select from an empty list");
			}

			WebElement item = listItems.get(listItems.size() - 1);
			waitUntilWebElementIsClickable(seleniumDriver, item, timeoutPeriod, backoffPeriod);
			item.click();
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Select the last item from DropDown
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param list           The list web element
	 */
	public void selectLastItemFromDropDown(SeleniumDriver seleniumDriver, WebElement list, int timeoutPeriod,
			int backoffPeriod) {
		try {
			waitForWebElementToBeEnabled(seleniumDriver, list, timeoutPeriod, backoffPeriod);
			Select select = new Select(list);
			if (select.getOptions().isEmpty()) {
				throw seleniumDriver.exceptionHandler("Cannot select from an empty list");
			}
			select.selectByIndex(select.getOptions().size() - 1);

		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Select a given entry from a list
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param list           The list web element
	 */
	public void selectRandomItemFromListByIndex(SeleniumDriver seleniumDriver, WebElement list, int timeoutPeriod,
			int backoffPeriod) {
		try {
			waitForWebElementToBeEnabled(seleniumDriver, list, timeoutPeriod, backoffPeriod);
			Select select = new Select(list);
			select.selectByIndex(new Random().nextInt(select.getOptions().size()));
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Select item from list by index
	 *
	 * @param seleniumDriver
	 * @param list
	 * @param index
	 */
	public void selectItemFromListByIndex(SeleniumDriver seleniumDriver, WebElement list, int index, int timeoutPeriod,
			int backoffPeriod) {
		try {
			waitForWebElementToBeEnabled(seleniumDriver, list, timeoutPeriod, backoffPeriod);
			new Select(list).selectByIndex(index);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Select a given entry from a list
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param list           The list web element
	 * @param listOption     The name of the item to be selected from the list
	 */
	public void selectOptionFromList(SeleniumDriver seleniumDriver, WebElement list, String listOption,
			int timeoutPeriod, int backoffPeriod) {
		try {
			waitForWebElementToBeEnabled(seleniumDriver, list, timeoutPeriod, backoffPeriod);
			List<WebElement> listOptions = list.findElements(By.tagName("OPTION"));
			for (WebElement item : listOptions) {
				log.info("** list item: {}", item.getText());
				if (item.getText().equalsIgnoreCase(listOption)) {
					item.click();
					break;
				}
			}
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Return the String values in a List of the text inside a dropdown selection
	 * box
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param list           The WebElement of the dropdown
	 * @return List<String> List of Strings of text returned from the dropdown menu
	 */
	public List<String> getValuesFromOptionList(SeleniumDriver seleniumDriver, WebElement list, int timeoutPeriod,
			int backoffPeriod) {
		try {
			waitForWebElementToBeEnabled(seleniumDriver, list, timeoutPeriod, backoffPeriod);
			List<String> optionsAvailableText = new ArrayList<>();
			List<WebElement> listOptions = list.findElements(By.tagName("OPTION"));
			for (WebElement item : listOptions) {
				optionsAvailableText.add(item.getText());
			}
			return optionsAvailableText;
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return new ArrayList<>();
		}
	}

	/**
	 * Select a given value from a drop down box
	 *
	 * @param seleniumDriver     The selenium driver implementation
	 * @param dropdownBoxElement The WebElement to select value from
	 * @param value              of the drop down box
	 */
	public void selectValueFromDropdownBox(SeleniumDriver seleniumDriver, WebElement dropdownBoxElement, String value,
			int timeoutPeriod, int backoffPeriod) {
		try {
			waitForWebElementToBeEnabled(seleniumDriver, dropdownBoxElement, timeoutPeriod, backoffPeriod);
			new Select(dropdownBoxElement).selectByVisibleText(value);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Select a given value from a drop down box
	 *
	 * @param seleniumDriver     The selenium driver implementation
	 * @param dropdownBoxElement The WebElement to select value from
	 * @param value              of the drop down box
	 */
	public void selectValueFromDropdownBox(SeleniumDriver seleniumDriver, WebElement dropdownBoxElement, String value) {
		try {
			new Select(dropdownBoxElement).selectByVisibleText(value);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Return the String values in a List of the text inside a dropdown selection
	 * box
	 *
	 * @param seleniumDriver     The selenium driver implementation
	 * @param dropdownBoxElement The WebElement of the dropdown
	 * @return List<String> List of Strings of text returned from the dropdown menu
	 */
	public List<String> getValuesFromSelectDropdownBox(SeleniumDriver seleniumDriver, WebElement dropdownBoxElement,
			int timeoutPeriod, int backoffPeriod) {
		try {
			waitForWebElementToBeEnabled(seleniumDriver, dropdownBoxElement, timeoutPeriod, backoffPeriod);
			List<String> optionsAvailableText = new ArrayList<>();
			List<WebElement> optionsAvailable = new Select(dropdownBoxElement).getOptions();
			for (WebElement element : optionsAvailable) {
				optionsAvailableText.add(element.getText());
			}
			return optionsAvailableText;
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return new ArrayList<>();
		}
	}

	/**
	 * Return the String values in a List of the text inside a dropdown selection
	 * box
	 *
	 * @param seleniumDriver     The selenium driver implementation
	 * @param dropdownBoxElement The WebElement of the dropdown
	 * @return List<String> List of Strings of text returned from the dropdown menu
	 */
	public List<String> getValuesFromSelectDropdownBox(SeleniumDriver seleniumDriver, WebElement dropdownBoxElement) {
		try {
			List<String> optionsAvailableText = new ArrayList<>();
			List<WebElement> optionsAvailable = new Select(dropdownBoxElement).getOptions();
			for (WebElement element : optionsAvailable) {
				optionsAvailableText.add(element.getText());
			}
			return optionsAvailableText;
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return new ArrayList<>();
		}
	}

	/**
	 * Checks if the element is displayed
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param webElement     the element to be checked
	 * @return true if displayed
	 */
	public boolean isElementDisplayed(SeleniumDriver seleniumDriver, WebElement webElement, int timeoutPeriod,
			int backoffPeriod) {
		try {
			return waitForWebElementToBeDisplayed(seleniumDriver, webElement, timeoutPeriod, backoffPeriod);
		} catch (NotFoundException | TimeoutException e) {
			log.info("Cause = {}", e.getMessage());
			return false;
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return false;
		}
	}

	/**
	 * Checks if the element is enabled
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param webElement     the element to be checked
	 * @return true if enabled
	 */
	public boolean isElementEnabled(SeleniumDriver seleniumDriver, WebElement webElement, int timeoutPeriod,
			int backoffPeriod) {
		return waitForElementToBeEnabled(seleniumDriver, webElement, timeoutPeriod, backoffPeriod);
	}

	/**
	 * Switch to the specified frame
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param frameName      The name of the frame to switch to
	 */
	public void switchToFrame(SeleniumDriver seleniumDriver, String frameName) {
		performFrameSwitch(seleniumDriver, frameName);
	}

	/**
	 * Switch to the specified frame
	 *
	 * @param seleniumDriver The selenium driver implementation
	 */
	public void switchToDefaultContent(SeleniumDriver seleniumDriver) {
		performFrameSwitch(seleniumDriver, "DEFAULT_CONTENT_FRAME");
	}

	/**
	 * Switch to the parent frame
	 *
	 * @param seleniumDriver The selenium driver implementation
	 */
	public void switchToParentFrame(SeleniumDriver seleniumDriver) {
		performFrameSwitch(seleniumDriver, "PARENT_FRAME");
	}

	private void performFrameSwitch(SeleniumDriver seleniumDriver, String frameName) {
		try {
			switch (frameName) {
			case "DEFAULT_CONTENT_FRAME":
				seleniumDriver.switchToDefaultContentFrame();
				break;
			case "PARENT_FRAME":
				seleniumDriver.switchToParentFrame();
				break;
			default:
				seleniumDriver.switchToFrame(frameName);
				break;
			}
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Send a key to a WebElement
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param webElement     The element to send the key to
	 * @param aKey           The key to send
	 */
	public void sendKey(SeleniumDriver seleniumDriver, WebElement webElement, Keys aKey) {
		try {
			webElement.sendKeys(aKey);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Send a key to a WebElement with a wait until clickable period
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param webElement     The element to send the key to
	 * @param aKey           The key to send
	 * @param timeoutPeriod  The maximum amount of time to wait in millisecs.
	 * @param backoffPeriod  The time between each attempt in millisecs.
	 */
	public void sendKey(SeleniumDriver seleniumDriver, WebElement webElement, Keys aKey, int timeoutPeriod,
			int backoffPeriod) {

		WebElement element = waitToBeClickable(seleniumDriver, webElement, timeoutPeriod, backoffPeriod);
		sendKey(seleniumDriver, element, aKey);
	}

	/**
	 * Click a radio button by text
	 *
	 * @param seleniumDriver        The selenium driver implementation
	 * @param radioButtonWebElement the parent object for the radio buttons
	 * @param radioButtonText       the text of the radio button
	 */
	public void selectRadioButtonByTag(SeleniumDriver seleniumDriver, WebElement radioButtonWebElement,
			String radioButtonText, String parentTag, String childTag, int timeoutPeriod, int backoffPeriod) {
		try {
			String uiRadioButtonTextToSelect;
			boolean found = false;
			waitForWebElementToBeEnabled(seleniumDriver, radioButtonWebElement, timeoutPeriod, backoffPeriod);
			List<WebElement> radioButtons = radioButtonWebElement.findElements(By.tagName(parentTag));
			for (WebElement radioButton : radioButtons) {
				log.info("** radio button: {}", radioButton.getText());
				if (radioButton.getText().contains("\n")) {
					String[] radioButtonList = radioButton.getText().split("\n");
					uiRadioButtonTextToSelect = radioButtonList[0];
				} else {
					uiRadioButtonTextToSelect = radioButton.getText();
				}

				if (StringUtils.startsWithIgnoreCase(uiRadioButtonTextToSelect, radioButtonText)) {
					clickButton(seleniumDriver,
							findElement(seleniumDriver,
									findElements(seleniumDriver, radioButtonWebElement, By.tagName(parentTag),
											timeoutPeriod, backoffPeriod).get(radioButtons.indexOf(radioButton)),
									By.tagName(childTag), timeoutPeriod, backoffPeriod),
							timeoutPeriod, backoffPeriod);
					found = true;
					break;
				}
			}

			if (!found) {
				throw seleniumDriver
						.exceptionHandler(String.format("Failed to find Radio Button for '%s'", radioButtonText));
			}
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	public void scrollToWebElement(SeleniumDriver seleniumDriver, WebElement element) {
		try {
			((JavascriptExecutor) seleniumDriver.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);",
					element);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Enter text to a text field using single keys for input fields that have
	 * overlying functionality and do not function as expected using sendKey
	 * function. Will retrieve the character length and clear using delete if
	 * necessary.
	 *
	 * @param seleniumDriver Selenium Driver implementation
	 * @param element        WebElement of the input field
	 * @param text           Text to enter using Keys.chord
	 */
	public void enterTextKeyEntry(SeleniumDriver seleniumDriver, WebElement element, String text) {
		try {
			int currentCharacterLength = element.getText().length();
			element.click();
			Actions actions = seleniumDriver.retrieveWebActions();
			for (int i = 0; i < currentCharacterLength; i++) {
				actions.sendKeys(Keys.chord(Keys.DELETE)).build().perform();
			}
			actions.sendKeys(Keys.chord(text)).build().perform();
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Scroll the WebElement into view.
	 *
	 * @param seleniumDriver Selenium Driver implementation
	 * @param element        WebElement to scroll into view
	 */
	public void scrollElementIntoView(SeleniumDriver seleniumDriver, WebElement element) {
		try {
			Actions action = seleniumDriver.retrieveWebActions().moveToElement(element);
			action.build().perform();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			waitUntilElementNoLongerMoving(seleniumDriver, element, 5000, 500);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	public WebElement waitToBeClickable(SeleniumDriver seleniumDriver, WebElement webElement, int timeoutPeriod,
			int backoffPeriod) {
		try {
			return waitToBeClickableWithoutErrorHandling(seleniumDriver, webElement, timeoutPeriod, backoffPeriod);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return null;
		}
	}

	private WebElement waitToBeClickableWithoutErrorHandling(SeleniumDriver seleniumDriver, WebElement webElement,
			int timeoutPeriod, int backoffPeriod) {
		Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
				.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
				.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + webElement).ignoring(WebDriverException.class);

		return wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	/**
	 * Wait for a WebElement to be enabled
	 *
	 * @param seleniumDriver Selenium Driver implementation
	 * @param webElement     Element to wait for enablement
	 * @param timeoutPeriod  Time out period as int millis
	 * @param backoffPeriod  Back off period as int millis
	 * @return boolean true if enabled, false if not after wait
	 */
	private boolean waitForWebElementToBeEnabled(SeleniumDriver seleniumDriver, WebElement webElement,
			int timeoutPeriod, int backoffPeriod) {
		Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
				.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
				.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + webElement).ignoring(WebDriverException.class);

		return wait.until(new ElementToBeEnabled(webElement));
	}

	private boolean waitForWebElementToBeDisplayed(SeleniumDriver seleniumDriver, WebElement webElement,
			int timeoutPeriod, int backoffPeriod) {
		Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
				.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
				.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + webElement).ignoring(WebDriverException.class);

		return wait.until(new ElementToBeDisplayed(webElement));
	}

	private boolean waitToCeaseBeingVisible(SeleniumDriver seleniumDriver, WebElement webElement, int timeoutPeriod,
			int backoffPeriod) {
		Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
				.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
				.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + webElement).ignoring(WebDriverException.class);

		return wait.until(ExpectedConditions.invisibilityOf(webElement));
	}

	/**
	 * Wait for the provided WebElement to be visible on the current page
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param webElement     WebElement to check for visibility of
	 * @param timeoutPeriod  Time out period
	 * @param backoffPeriod  Polling period
	 * @return WebElement WebElement object if visible, null if not
	 */
	private WebElement waitToBeVisible(SeleniumDriver seleniumDriver, WebElement webElement, int timeoutPeriod,
			int backoffPeriod) {
		Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
				.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
				.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + webElement).ignoring(WebDriverException.class);

		return wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	/**
	 * Wait for a List of WebElements found by By locator to be zero
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param locator        By locator for WebElement to be found by
	 * @param timeoutPeriod  Timeout period
	 * @param backoffPeriod  Polling period
	 * @return Boolean true if number of elements found by By locator 0, false
	 *         otherwise
	 */
	private Boolean waitWebElementToCeaseExisting(SeleniumDriver seleniumDriver, By locator, int timeoutPeriod,
			int backoffPeriod) {
		Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
				.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
				.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + locator).ignoring(WebDriverException.class);

		return wait.until(new NumberOfElementsToBeEqualTo(locator, 0));
	}

	/**
	 * Check if one or more of an element found by locator exists at the present
	 * time
	 *
	 * @param seleniumDriver WebDriver implementation
	 * @param locator        Locator to use to find element
	 * @return boolean True if element found, false if not
	 */
	private boolean checkWebElementsExist(SeleniumDriver seleniumDriver, By locator) {
		return !seleniumDriver.getWebDriver().findElements(locator).isEmpty();
	}

	/**
	 * Utility method to check if a WebElement exists or not within the page.
	 *
	 * Does this by checking if the webElement exists or is displayed, if an
	 * exception is thrown then it simply handled quietly and assumed that it does
	 * not exist
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param element        WebElement to check for attribute with value
	 * @param timeoutPeriod  Timeout period
	 * @param backoffPeriod  Polling period
	 * @return true if it exists (defined as being enabled or displayed), false
	 *         otherwise
	 */
	public boolean webElementExists(SeleniumDriver seleniumDriver, WebElement element, int timeoutPeriod,
			int backoffPeriod) {
		boolean exists = false;
		log.info("Checking if webElement: '{}' exists", element);
		try {
			if (waitForWebElementToBeEnabled(seleniumDriver, element, timeoutPeriod, backoffPeriod)
					|| waitForWebElementToBeDisplayed(seleniumDriver, element, timeoutPeriod, backoffPeriod)) {
				exists = true;
			}
		} catch (Exception nfe) {
			// if the element cannot be found, or any exception is thrown, then it probably
			// doesn't exist within the context
			log.error(nfe.getMessage());
		}
		return exists;
	}

	/**
	 * Wait for an attribute of element to not contain value
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param element        WebElement to check for attribute with value
	 * @param attribute      Attribute to exist within element
	 * @param value          Value to not exist in attribute
	 * @param timeoutPeriod  Timeout period
	 * @param backoffPeriod  Polling period
	 * @return Boolean true if attribute does not contain value, false otherwise
	 */
	private Boolean waitAttributeValueToCeaseExisting(SeleniumDriver seleniumDriver, WebElement element,
			String attribute, String value, int timeoutPeriod, int backoffPeriod) {
		Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
				.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
				.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + element).ignoring(WebDriverException.class);

		return wait.until(new AttributeNotPresent(element, attribute, value));
	}

	/**
	 * Wait for an element to no longer be moving on screen
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param element        WebElement to check for attribute with value
	 * @param timeoutPeriod  Timeout period
	 * @param backoffPeriod  Polling period
	 * @return Boolean true if attribute does not contain value, false otherwise
	 */
	private Boolean waitUntilElementNoLongerMoving(SeleniumDriver seleniumDriver, WebElement element, int timeoutPeriod,
			int backoffPeriod) {
		Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
				.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
				.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + element).ignoring(WebDriverException.class);

		int xLocation = element.getLocation().getX();
		int yLocation = element.getLocation().getY();

		return wait.until(new ElementNotMoving(element, xLocation, yLocation));
	}

	/**
	 * Wait for an attribute of element to contain a value
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param element        WebElement to check for attribute with value
	 * @param attribute      Attribute to exist within element
	 * @param value          Value to not exist in attribute
	 * @param timeoutPeriod  Timeout period
	 * @param backoffPeriod  Polling period
	 * @return Boolean true if attribute does contains value, false otherwise
	 */
	private Boolean waitAttributeValueToExist(SeleniumDriver seleniumDriver, WebElement element, String attribute,
			String value, int timeoutPeriod, int backoffPeriod) {
		Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
				.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
				.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + element).ignoring(WebDriverException.class);

		return wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
	}

	/**
	 * Wait for a List of WebElements found by By locator to be greater than zero
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param locator        By locator for WebElement to be found by
	 * @param timeoutPeriod  Timeout period
	 * @param backoffPeriod  Polling period
	 * @return Boolean true if number of elements found by By locator > 0, false
	 *         otherwise
	 */
	private Boolean waitToExist(SeleniumDriver seleniumDriver, By locator, int timeoutPeriod, int backoffPeriod) {
		Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
				.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
				.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + locator).ignoring(WebDriverException.class);

		return !wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0)).isEmpty();
	}

	/**
	 * Wait for a frame to exist and return a WebDriver instance
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param frameName      name attribute of the iFrame to wait for
	 * @param timeoutPeriod  timeout period to wait
	 * @param backoffPeriod  polling period
	 * @return WebDriver WebDriver instance to return
	 */
	private WebDriver waitForFrame(SeleniumDriver seleniumDriver, String frameName, int timeoutPeriod,
			int backoffPeriod) {
		Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
				.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
				.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + frameName).ignoring(WebDriverException.class);

		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	/**
	 * Wait for a frame to exist and return a WebDriver instance
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param locator        locator of the iFrame to wait for
	 * @param timeoutPeriod  timeout period to wait
	 * @param backoffPeriod  polling period
	 * @return WebDriver WebDriver instance to return
	 */
	private WebDriver waitForFrameByLocator(SeleniumDriver seleniumDriver, By locator, int timeoutPeriod,
			int backoffPeriod) {
		Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
				.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
				.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + locator).ignoring(WebDriverException.class);

		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	/**
	 * Wait for the specified String text to be present within the WebElement
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param webElement     WebElement object
	 * @param text           String text to wait for
	 * @param timeoutPeriod  Timeout period
	 * @param backoffPeriod  Polling period
	 * @return boolean true if text present within wait time, false otherwise
	 */
	private boolean waitForTextToBePresent(SeleniumDriver seleniumDriver, WebElement webElement, String text,
			int timeoutPeriod, int backoffPeriod) {
		Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
				.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
				.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + webElement).ignoring(WebDriverException.class);

		return wait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
	}

	/**
	 * Wait for the specified String text to no longer be present within the
	 * WebElement
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param webElement     WebElement object
	 * @param text           String text to wait for
	 * @param timeoutPeriod  Timeout period
	 * @param backoffPeriod  Polling period
	 * @return boolean true if text not present within wait time, false otherwise
	 */
	private boolean waitForTextToBeRemoved(SeleniumDriver seleniumDriver, WebElement webElement, String text,
			int timeoutPeriod, int backoffPeriod) {
		Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
				.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
				.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + webElement).ignoring(WebDriverException.class);

		return wait.until(new TextToNotBePresentInElement(webElement, text));
	}

	public boolean isWebElementClickable(SeleniumDriver seleniumDriver, WebElement webElement, int timeoutPeriod,
			int backoffPeriod) {
		try {
			WebElement element = waitToBeClickableWithoutErrorHandling(seleniumDriver, webElement, timeoutPeriod,
					backoffPeriod);
			return null != element;
		} catch (NotFoundException | TimeoutException e) {
			log.info("Cause = {}", e.getMessage());
			return false;
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return false;
		}
	}

	/**
	 * Confirm if a WebElement is currently enabled
	 *
	 * @param element The WebElement to check is currently enabled
	 * @return Return boolean value
	 */
	public boolean checkElementEnabled(SeleniumDriver seleniumDriver, WebElement element) {
		return waitForElementToBeEnabled(seleniumDriver, element);
	}

	/**
	 * @param seleniumDriver WebDriver instance
	 * @param webElement     WebElement to be checked ot be visible
	 * @param timeoutPeriod  timeout period to wait
	 * @param backoffPeriod  polling period
	 * @return boolean true or false value of if the switch to the frame occurred
	 *         successfully
	 */
	public boolean waitUntilWebElementIsClickable(SeleniumDriver seleniumDriver, WebElement webElement,
			int timeoutPeriod, int backoffPeriod) {
		try {
			log.info("Waiting for {} milliseconds for element {} to be clickable", timeoutPeriod, webElement);
			return isWebElementClickable(seleniumDriver, webElement, timeoutPeriod, backoffPeriod);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return false;
		}
	}

	/**
	 * Wait until a WebElement is visible on the page
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param webElement     WebElement to be checked for visibility of on page
	 * @param timeoutPeriod  timeout period to wait
	 * @param backoffPeriod  polling period
	 * @return boolean true or false value of if the switch to the frame occurred
	 *         successfully
	 */
	public boolean waitUntilWebElementIsVisible(SeleniumDriver seleniumDriver, WebElement webElement, int timeoutPeriod,
			int backoffPeriod) {
		try {
			log.info("Waiting for webElement '{}' to become visible", webElement);
			WebElement element = waitToBeVisible(seleniumDriver, webElement, timeoutPeriod, backoffPeriod);
			return null != element;
		} catch (NotFoundException | TimeoutException e) {
			log.error(e.getMessage());
			return false;
		} catch (WebDriverException wde) {
			handleWebDriverException(seleniumDriver, wde);
			return false;
		}
	}

	/**
	 * Switch the browser window to a newly opened tab from the current tab
	 *
	 * @param seleniumDriver WebDriver instance
	 */
	public void switchToNewTab(SeleniumDriver seleniumDriver) {
		try {
			for (String windowHandle : seleniumDriver.getWebDriver().getWindowHandles()) {
				if (!windowHandle.equals(seleniumDriver.getWebDriver().getWindowHandle())) {
					seleniumDriver.getWebDriver().switchTo().window(windowHandle);
				}
			}
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	public void switchToFrame(SeleniumDriver seleniumDriver, WebElement element) {
		try {
			seleniumDriver.getWebDriver().switchTo().frame(element);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Wait until a WebElement is no longer visible on the page
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param webElement     WebElement to be checked for invisibility of on page
	 * @param timeoutPeriod  timeout period to wait
	 * @param backoffPeriod  polling period
	 * @return boolean true if the element is no longer visible, false otherwise
	 */
	public boolean waitUntilWebElementCeasesBeingVisible(SeleniumDriver seleniumDriver, WebElement webElement,
			int timeoutPeriod, int backoffPeriod) {
		try {
			return waitToCeaseBeingVisible(seleniumDriver, webElement, timeoutPeriod, backoffPeriod);
		} catch (NotFoundException | TimeoutException e) {
			log.error(e.getMessage());
			return false;
		} catch (WebDriverException wde) {
			handleWebDriverException(seleniumDriver, wde);
			return false;
		}
	}

	/**
	 * Wait until a WebElement found using the By locator no longer exists on the
	 * page. A By locator is used as a POM WebElement object will remain available
	 * even when the page object has been removed and return a Null Pointer.
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param locator        By locator for use to search for a WebElement by
	 * @param timeoutPeriod  timeout period to wait
	 * @param backoffPeriod  polling period
	 * @return boolean true or false value of if the switch to the frame occurred
	 *         successfully
	 */
	public boolean waitUntilWebElementsCeaseToExist(SeleniumDriver seleniumDriver, By locator, int timeoutPeriod,
			int backoffPeriod) {
		boolean result = true;
		try {
			List<WebElement> elements = findElements(seleniumDriver, locator);
			if (CollectionUtils.isNotEmpty(elements)) {
				result = waitWebElementToCeaseExisting(seleniumDriver, locator, timeoutPeriod, backoffPeriod);
			} else {
				log.info("Element {} does not exist", locator);
			}
		} catch (NotFoundException | TimeoutException e) {
			log.error(e.getMessage());
			return false;
		} catch (WebDriverException wde) {
			handleWebDriverException(seleniumDriver, wde);
			return false;
		}

		return result;
	}

	/**
	 * Check if a WebElement or list of WebElements currently exist based on locator
	 *
	 * @param seleniumDriver WebDriver implementation
	 * @param locator        By locator for WebElements
	 * @return boolean indicating if element exists
	 */
	public boolean doWebElementsExist(SeleniumDriver seleniumDriver, By locator) {
		try {
			return checkWebElementsExist(seleniumDriver, locator);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
			return false;
		}
	}

	/**
	 * Wait for an attribute of element to not contain a specific value
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param element        WebElement to check for attribute with value
	 * @param attribute      Attribute to exist within element
	 * @param value          Value to not exist in attribute
	 * @param timeoutPeriod  Timeout period
	 * @param backoffPeriod  Polling period
	 * @return Boolean true or false of if the attribute value was no longer in
	 *         existence
	 */
	public boolean waitUntilAttributeValueCeaseToExist(SeleniumDriver seleniumDriver, WebElement element,
			String attribute, String value, int timeoutPeriod, int backoffPeriod) {
		try {
			return waitAttributeValueToCeaseExisting(seleniumDriver, element, attribute, value, timeoutPeriod,
					backoffPeriod);
		} catch (NotFoundException | TimeoutException e) {
			log.error(e.getMessage());
			return false;
		} catch (WebDriverException wde) {
			handleWebDriverException(seleniumDriver, wde);
			return false;
		}
	}

	/**
	 * Wait for an attribute of element to contain a specific value
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param element        WebElement to check for attribute with value
	 * @param attribute      Attribute to exist within element
	 * @param value          Value to exist in attribute
	 * @param timeoutPeriod  Timeout period
	 * @param backoffPeriod  Polling period
	 * @return Boolean true or false of if the attribute value is in existence
	 */
	public boolean waitUntilAttributeValueExists(SeleniumDriver seleniumDriver, WebElement element, String attribute,
			String value, int timeoutPeriod, int backoffPeriod) {
		try {
			return waitAttributeValueToExist(seleniumDriver, element, attribute, value, timeoutPeriod, backoffPeriod);
		} catch (NotFoundException | TimeoutException e) {
			log.error(e.getMessage());
			return false;
		} catch (WebDriverException wde) {
			handleWebDriverException(seleniumDriver, wde);
			return false;
		}
	}

	/**
	 * Wait until WebElements found using the By locator exist on the page. A By
	 * locator is used as a POM WebElement object may already exist with different
	 * attributes.
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param locator        By locator for use to search for a WebElement by
	 * @param timeoutPeriod  timeout period to wait
	 * @param backoffPeriod  polling period
	 * @return boolean true or false value of if the switch to the frame occurred
	 *         successfully
	 */
	public boolean waitUntilWebElementsExist(SeleniumDriver seleniumDriver, By locator, int timeoutPeriod,
			int backoffPeriod) {
		try {
			return waitToExist(seleniumDriver, locator, timeoutPeriod, backoffPeriod);
		} catch (NotFoundException | TimeoutException e) {
			log.error(e.getMessage());
			return false;
		} catch (WebDriverException wde) {
			handleWebDriverException(seleniumDriver, wde);
			return false;
		}
	}

	/**
	 * Select a single square on a grid, based on an input value, grid WebElement
	 * and locator for each square on the grid
	 *
	 * @param seleniumDriver    WebDriver instance
	 * @param webElement        WebElement of the grid
	 * @param value             String text value contained within the grid square
	 *                          element to select
	 * @param gridSquareLocator By locator of individual WebElements contained
	 *                          within the grid to split squares by
	 */
	public void selectElementOnGrid(SeleniumDriver seleniumDriver, WebElement webElement, String value,
			By gridSquareLocator) {
		try {
			List<WebElement> gridSquares = webElement.findElements(gridSquareLocator);
			for (WebElement square : gridSquares) {
				if (square.getText().equals(value) && !square.getAttribute("class").contains("disabled")) {
					clickButton(seleniumDriver, square);
					break;
				}
			}
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	public boolean getClassAttributeValueElementOnGrid(SeleniumDriver seleniumDriver, WebElement webElement,
			String value, By gridSquareLocator, String attributeValue) {
		boolean found = false;
		try {
			List<WebElement> gridSquares = webElement.findElements(gridSquareLocator);
			for (WebElement square : gridSquares) {
				if (square.getText().equals(value) && square.getAttribute("class").contains(attributeValue)) {
					found = true;
					break;
				}
			}
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
		return found;
	}

	public boolean getClassAttributeDoesNotContainValueElementOnGrid(SeleniumDriver seleniumDriver,
			WebElement webElement, String value, By gridSquareLocator, String attributeValue) {
		boolean found = false;
		try {
			List<WebElement> gridSquares = webElement.findElements(gridSquareLocator);
			for (WebElement square : gridSquares) {
				if (square.getText().equals(value) && !square.getAttribute("class").contains(attributeValue)) {
					found = true;
					break;
				}
			}
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
		return found;
	}

	/**
	 * Wait for a String text to be present within the WebElement
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param webElement     WebElement to check for text in
	 * @param text           String text to wait for
	 * @param timeoutPeriod  timeout period to wait
	 * @param backoffPeriod  polling period
	 * @return boolean true if text present, false if not
	 */
	public boolean waitUntilWebElementTextContains(SeleniumDriver seleniumDriver, WebElement webElement, String text,
			int timeoutPeriod, int backoffPeriod) {

		try {
			return waitForTextToBePresent(seleniumDriver, webElement, text, timeoutPeriod, backoffPeriod);
		} catch (NotFoundException | TimeoutException e) {
			log.error(e.getMessage());
			return false;
		} catch (WebDriverException wde) {
			handleWebDriverException(seleniumDriver, wde);
			return false;
		}
	}

	/**
	 * Wait for a String text to be no longer be present within the WebElement
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param webElement     WebElement to check for text in
	 * @param text           String text to wait for being removed
	 * @param timeoutPeriod  timeout period to wait
	 * @param backoffPeriod  polling period
	 * @return boolean true if text is not present, false if present
	 */
	public boolean waitUntilWebElementTextNoLongerContains(SeleniumDriver seleniumDriver, WebElement webElement,
			String text, int timeoutPeriod, int backoffPeriod) {
		try {
			return waitForTextToBeRemoved(seleniumDriver, webElement, text, timeoutPeriod, backoffPeriod);
		} catch (NotFoundException | TimeoutException e) {
			log.error(e.getMessage());
			return false;
		} catch (WebDriverException wde) {
			handleWebDriverException(seleniumDriver, wde);
			return false;
		}
	}

	/**
	 * Wait until an iFrame is visible and once visible switch control to it
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param frameName      the name attribute of the frame
	 * @param timeoutPeriod  timeout period to wait
	 * @param backoffPeriod  polling period
	 * @return boolean true or false value of if the switch to the frame occurred
	 *         successfully
	 */

	public boolean waitUntilFrameIsVisibleAndSwitch(SeleniumDriver seleniumDriver, String frameName, int timeoutPeriod,
			int backoffPeriod) {
		try {
			WebDriver driver = waitForFrame(seleniumDriver, frameName, timeoutPeriod, backoffPeriod);
			return null != driver;
		} catch (NotFoundException | TimeoutException e) {
			log.error(e.getMessage());
			return false;
		} catch (WebDriverException wde) {
			handleWebDriverException(seleniumDriver, wde);
			return false;
		}
	}

	/**
	 * Wait until an iFrame is visible and once visible switch control to it
	 *
	 * @param seleniumDriver WebDriver instance
	 * @param locator        the locator of the frame
	 * @param timeoutPeriod  timeout period to wait
	 * @param backoffPeriod  polling period
	 * @return boolean true or false value of if the switch to the frame occurred
	 *         successfully
	 */

	public boolean waitUntilFrameIsVisibleAndSwitchByLocator(SeleniumDriver seleniumDriver, By locator,
			int timeoutPeriod, int backoffPeriod) {
		try {
			WebDriver driver = waitForFrameByLocator(seleniumDriver, locator, timeoutPeriod, backoffPeriod);
			return null != driver;
		} catch (NotFoundException | TimeoutException e) {
			log.error(e.getMessage());
			return false;
		} catch (WebDriverException wde) {
			handleWebDriverException(seleniumDriver, wde);
			return false;
		}
	}

	/**
	 * Select a dropdown item
	 *
	 * @param seleniumDriver SeleniumDriver WebDriver
	 * @param webElement     The DropDown web element
	 * @param text           The text in dropDown item to be selected
	 */
	public void selectDropDownItem(SeleniumDriver seleniumDriver, WebElement webElement, String text) {
		try {
			Select dropDownItems = new Select(webElement);
			dropDownItems.selectByVisibleText(text);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Double click
	 *
	 * @param seleniumDriver The selenium driver implementation
	 * @param webElement     The web element to double click
	 */
	public void doubleclick(SeleniumDriver seleniumDriver, WebElement webElement) {
		try {
			Actions action = seleniumDriver.retrieveWebActions().moveToElement(webElement).doubleClick();
			action.build().perform();
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	/**
	 * Click inside an inner element found by innerElementLocator inside webElement
	 * based on if the itemText string is contained within said inner element.
	 *
	 * @param seleniumDriver      Standard Selenium Driver
	 * @param webElement          WebElement
	 * @param innerElementLocator Locator for inner element inside WebElement
	 * @param itemText            String text to find from inner element to select
	 *                            as element to click (using Javascript click
	 *                            funciton)
	 */
	public void innerElementClick(SeleniumDriver seleniumDriver, WebElement webElement, By innerElementLocator,
			String itemText) {
		try {
			Optional<WebElement> innerElement = webElement.findElements(innerElementLocator).stream()
					.filter(element -> element.getText().contains(itemText)).findFirst();
			if (innerElement.isPresent()) {
				jsClick(seleniumDriver, innerElement.get());
			}
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	public void waitForElementToAppear(SeleniumDriver seleniumDriver, WebElement webElement, Duration  waitSecond) {
		try {
			resetImplicitWait(seleniumDriver);
			new WebDriverWait(seleniumDriver.getWebDriver(), waitSecond)
					.until(ExpectedConditions.visibilityOf(webElement));
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	public void waitForEitherElementToAppear(SeleniumDriver seleniumDriver, WebElement webElement1,
			WebElement webElement2, Duration  waitSecond) {
		try {
			resetImplicitWait(seleniumDriver);
			WebDriverWait wait = new WebDriverWait(seleniumDriver.getWebDriver(), waitSecond);
			wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOf(webElement1),
					ExpectedConditions.visibilityOf(webElement2)));
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	private void resetImplicitWait(SeleniumDriver seleniumDriver) {
		seleniumDriver.getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

	public void waitForElement(SeleniumDriver seleniumDriver, WebElement webElement, Integer timeoutSeconds) {
		try {
			Wait wait = new FluentWait(seleniumDriver.getWebDriver()).withTimeout(Duration.ofSeconds(timeoutSeconds))
					.pollingEvery(ONE_SECOND_DURATION).ignoring(NoSuchElementException.class);
			wait.until((Function<WebDriver, Boolean>) driver -> webElement.findElement(By.xpath(".")) != null);
		} catch (WebDriverException e) {
			handleWebDriverException(seleniumDriver, e);
		}
	}

	public void staleElementClick(SeleniumDriver seleniumDriver, WebElement webElement) {
		int count = 0;
		boolean isPresent = false;
		while (count < 10 && !isPresent) {
			try {
				isPresent = webElement.findElement(By.xpath(getElementXPath(seleniumDriver, webElement))).isDisplayed();
				jsClick(seleniumDriver, webElement);
			} catch (StaleElementReferenceException | NotFoundException notFound) {
				log.info("Trying to recover from a stale element : {} --- {} --- {}", count, isPresent,
						notFound.getMessage());
				count = count + 1;
			}
		}
	}

	private void jsClick(SeleniumDriver seleniumDriver, WebElement webElement) {
		scrollToWebElement(seleniumDriver, webElement);
		JavascriptExecutor js = (JavascriptExecutor) seleniumDriver.getWebDriver();
		js.executeScript("var evt = document.createEvent('MouseEvents');"
				+ "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
				+ "arguments[0].dispatchEvent(evt);", webElement);

	}

	public WebElement getWebElement(SeleniumDriver seleniumDriver, String xpath) {
		WebDriver webDriver = seleniumDriver.getWebDriver();
		return webDriver.findElement(By.xpath(xpath));
	}

	public String getElementXPath(SeleniumDriver seleniumDriver, WebElement webElement) {
		return (String) ((JavascriptExecutor) seleniumDriver.getWebDriver())
				.executeScript("getXPath=function(node)" + "{" + "if (node.id !== '')" + "{"
						+ "return '//' + node.tagName.toLowerCase() + '[@id=\"' + node.id + '\"]'" + "}" +

						"if (node === document.body)" + "{" + "return node.tagName.toLowerCase()" + "}" +

						"var nodeCount = 0;" + "var childNodes = node.parentNode.childNodes;" +

						"for (var i=0; i<childNodes.length; i++)" + "{" + "var currentNode = childNodes[i];" +

						"if (currentNode === node)" + "{"
						+ "return getXPath(node.parentNode) + '/' + node.tagName.toLowerCase() + '[' + (nodeCount+1) + ']'"
						+ "}" + "if (currentNode.nodeType === 1 && "
						+ "currentNode.tagName.toLowerCase() === node.tagName.toLowerCase())" + "{" + "nodeCount++"
						+ "}" + "}" + "};" +

						"return getXPath(arguments[0]);", webElement);
	}

	public void switchToDefaultFrame(SeleniumDriver seleniumDriver) {
		seleniumDriver.getWebDriver().switchTo().defaultContent();
	}

	public void waitUntilElementIsEnabled(SeleniumDriver seleniumDriver, final WebElement webElement) {
		WebDriver driver = seleniumDriver.getWebDriver();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		ExpectedCondition elementIsDisplayed = arg0 -> {
			try {
				webElement.isEnabled();
				return true;
			} catch (NoSuchElementException | StaleElementReferenceException e) {
				return false;
			}
		};
		wait.until(elementIsDisplayed);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	private void handleWebDriverException(SeleniumDriver seleniumDriver, WebDriverException e) {
		log.error(e.getMessage());
		throw seleniumDriver.exceptionHandler(testMethod, e);
	}

	/**
	 * Click a radio button by text, of type "cbs-radio"
	 *
	 * @param seleniumDriver        The selenium driver implementation
	 * @param radioButtonWebElement the parent object for the radio buttons
	 * @param radioButtonText       the text of the radio button
	 */
	public void selectRadioButton(SeleniumDriver seleniumDriver, WebElement radioButtonWebElement,
			String radioButtonText, int timeoutPeriod, int backoffPeriod) {
		selectRadioButtonByTag(seleniumDriver, radioButtonWebElement, radioButtonText, "cbs-radio", "cbs-form-field",
				timeoutPeriod, backoffPeriod);
	}

	/**
	 * Click a radio button by text, of type "ul"
	 *
	 * @param seleniumDriver        The selenium driver implementation
	 * @param radioButtonWebElement the parent object for the radio buttons
	 * @param radioButtonText       the text of the radio button
	 */
	public void selectRadioListButton(SeleniumDriver seleniumDriver, WebElement radioButtonWebElement,
			String radioButtonText, int timeoutPeriod, int backoffPeriod) {
		selectRadioButtonByTag(seleniumDriver, radioButtonWebElement, radioButtonText, "li", "label", timeoutPeriod,
				backoffPeriod);
	}

	/**
	 * Click a radio button by text, of type "div"
	 *
	 * @param seleniumDriver        The selenium driver implementation
	 * @param radioButtonWebElement the parent object for the radio buttons
	 * @param radioButtonText       the text of the radio button
	 */
	public void selectInlineRadioButton(SeleniumDriver seleniumDriver, WebElement radioButtonWebElement,
			String radioButtonText, int timeoutPeriod, int backoffPeriod) {
		selectRadioButtonByTag(seleniumDriver, radioButtonWebElement, radioButtonText, "div", "label", timeoutPeriod,
				backoffPeriod);
	}

	/**
	 * Finds an element on the page, handles the the NoSuchElementException error
	 * and wraps in an appropriate error
	 *
	 * @param selector The selector to use
	 * @return The found WebElement
	 */
	public WebElement findElement(SeleniumDriver seleniumDriver, By selector) {
		try {
			return seleniumDriver.getWebDriver().findElement(selector);
		} catch (NoSuchElementException e) {
			handleWebDriverException(seleniumDriver, e);
		}
		return null;
	}

	/**
	 * Finds elements on the page, handles the the NoSuchElementException error and
	 * wraps in an appropriate error
	 *
	 * @param selector The selector to use
	 * @return The list of found WebElements
	 */
	public List<WebElement> findElements(SeleniumDriver seleniumDriver, By selector) {
		try {
			return seleniumDriver.getWebDriver().findElements(selector);
		} catch (NoSuchElementException e) {
			handleWebDriverException(seleniumDriver, e);
		}
		return Collections.emptyList();
	}

	/**
	 * Finds child element of the passed parent, handles the the
	 * NoSuchElementException error and wraps in an appropriate error
	 *
	 * @param parentElement The element to find the children of
	 * @param selector      The selector to use
	 * @return The found WebElement
	 */
	public WebElement findElement(SeleniumDriver seleniumDriver, WebElement parentElement, By selector,
			int timeoutPeriod, int backoffPeriod) {
		try {
			waitForWebElementToBeEnabled(seleniumDriver, parentElement, timeoutPeriod, backoffPeriod);
			Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
					.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
					.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + selector).ignoring(WebDriverException.class);

			wait.until(new NumberOfElementsNotZero(parentElement, selector));
			return parentElement.findElement(selector);
		} catch (NoSuchElementException e) {
			handleWebDriverException(seleniumDriver, e);
		}
		return null;
	}

	public void capture(String fileName, WebDriver driver) {
		boolean folderCreated = false;
		boolean folderExists = false;
		try {
			log.info("Capturing screenshot as {}", fileName);

			TakesScreenshot screenShot = ((TakesScreenshot) driver);

			// Call getScreenshotAs method to create image file
			File screenshotFile = screenShot.getScreenshotAs(OutputType.FILE);

			// Move image file to new destination
			File browserImgFile = new File(fileName);

			if (browserImgFile.getParentFile() != null) {
				folderExists = browserImgFile.getParentFile().exists();
				if (!folderExists) {
					folderCreated = browserImgFile.getParentFile().mkdirs();
				}
			}

			if (folderExists || folderCreated) {

				if (browserImgFile.exists() && Files.deleteIfExists(browserImgFile.toPath())) {
					log.warn("Failed to delete screenshot - {}", browserImgFile.getName());
				}
				FileUtils.copyFile(screenshotFile, browserImgFile);
			}

		} catch (IOException | WebDriverException e) {
			log.warn("Failed to capture screenshot - {}", e);
		}
	}

	/**
	 * Finds child elements of the passed parent, handles the the
	 * NoSuchElementException error and wraps in an appropriate error
	 *
	 * @param parentElement The element to find the children of
	 * @param selector      The selector to use
	 * @return The list of found WebElements
	 */
	public List<WebElement> findElements(SeleniumDriver seleniumDriver, WebElement parentElement, By selector,
			int timeoutPeriod, int backoffPeriod) {
		try {
			waitForWebElementToBeEnabled(seleniumDriver, parentElement, timeoutPeriod, backoffPeriod);
			Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
					.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
					.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + selector).ignoring(WebDriverException.class);

			wait.until(new NumberOfElementsNotZero(parentElement, selector));
			return parentElement.findElements(selector);
		} catch (TimeoutException e) {
			log.info("Failed to find any child elements for parent {} with identifier {}", parentElement, selector);
		}
		return Collections.emptyList();
	}

	public boolean doesChildElementExist(SeleniumDriver seleniumDriver, WebElement parentElement, By selector,
			int timeoutPeriod, int backoffPeriod) {
		try {
			waitForWebElementToBeEnabled(seleniumDriver, parentElement, timeoutPeriod, backoffPeriod);
			Wait<WebDriver> wait = new FluentWait<>(seleniumDriver.getWebDriver())
					.withTimeout(Duration.ofMillis(timeoutPeriod)).pollingEvery(Duration.ofMillis(backoffPeriod))
					.withMessage(FAILED_TO_FIND_ELEMENT_MESSAGE + ": " + selector).ignoring(WebDriverException.class);

			wait.until(new NumberOfElementsNotZero(parentElement, selector));
			return CollectionUtils.isNotEmpty(parentElement.findElements(selector));
		} catch (TimeoutException e) {
			log.info("Failed to find any child elements for parent {} with identifier {}", parentElement, selector);
		}
		return false;
	}
}

class NumberOfElementsNotZero implements ExpectedCondition<Boolean> {
	private WebElement parent;
	private By locator;

	NumberOfElementsNotZero(WebElement parent, By locator) {
		this.parent = parent;
		this.locator = locator;
	}

	@Override
	public Boolean apply(WebDriver driver) {
		List<WebElement> elements = parent.findElements(locator);
		return CollectionUtils.isNotEmpty(elements);
	}
}

class NumberOfElementsToBeEqualTo implements ExpectedCondition<Boolean> {
	private By locator;
	private int numberOfElements;

	NumberOfElementsToBeEqualTo(By locator, int numberOfElements) {
		this.locator = locator;
		this.numberOfElements = numberOfElements;
	}

	@Override
	public Boolean apply(WebDriver driver) {
		List<WebElement> elements = driver.findElements(locator);
		return elements.size() == numberOfElements;
	}
}

class AttributeNotPresent implements ExpectedCondition<Boolean> {
	private WebElement element;
	private String attribute;
	private String value;

	AttributeNotPresent(WebElement element, String attribute, String value) {
		this.element = element;
		this.attribute = attribute;
		this.value = value;
	}

	@Override
	public Boolean apply(WebDriver driver) {
		return !element.getAttribute(attribute).contains(value);
	}
}

class ElementNotMoving implements ExpectedCondition<Boolean> {
	private WebElement element;
	private int originalXPosition;
	private int originalYPosition;

	ElementNotMoving(WebElement element, int originalXPosition, int originalYPosition) {
		this.element = element;
		this.originalXPosition = originalXPosition;
		this.originalYPosition = originalYPosition;
	}

	@Override
	public Boolean apply(WebDriver driver) {
		int elementCurrentX = element.getLocation().getX();
		int elementCurrentY = element.getLocation().getY();

		return ((originalXPosition == elementCurrentX) && (originalYPosition == elementCurrentY));
	}
}

class ElementToBeEnabled implements ExpectedCondition<Boolean> {
	private WebElement element;

	ElementToBeEnabled(WebElement element) {
		this.element = element;
	}

	@Override
	public Boolean apply(WebDriver driver) {
		return (element.isEnabled());
	}
}

class ElementToBeDisplayed implements ExpectedCondition<Boolean> {
	private WebElement element;

	ElementToBeDisplayed(WebElement element) {
		this.element = element;
	}

	@Override
	public Boolean apply(WebDriver driver) {
		return (element.isDisplayed());
	}
}

class TextToNotBePresentInElement implements ExpectedCondition<Boolean> {
	private WebElement element;
	private String text;

	TextToNotBePresentInElement(WebElement element, String text) {
		this.element = element;
		this.text = text;
	}

	@Override
	public Boolean apply(WebDriver driver) {
		return (!element.getText().contains(text));
	}
}
