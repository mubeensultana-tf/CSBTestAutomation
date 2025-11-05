package com.cztffa.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.cztffa.application.WebApplication;
import com.cztffa.application.WebApplicationEnum;
import com.cztffa.exception.UXException;

import java.util.List;

public interface SeleniumDriver {

	void launchApplication(String url);

	boolean browserExists();

	/**
	 * Launches the passed URL
	 *
	 * @param webApplicationType The browser type to use
	 * @param url                The url to launch
	 */
	void launch(WebApplicationEnum webApplicationType, String url);

	/**
	 * Launches the passed URL
	 *
	 * @param webApplicationType The browser type to use
	 * @param url                The url to launch
	 * @param implicitWait       the ImplicitWait period for the browser driver
	 */
	void launch(WebApplicationEnum webApplicationType, String url, int implicitWait);

	/**
	 * Shrinks the width of the browser screen by the specified number of pixels
	 */
	void shrinkScreenWidth(int width);

	/**
	 * Log out of application
	 */
	void logout();

	/**
	 * Navigates to specified url page
	 *
	 * @param url The url for the page
	 */
	void navigateToPage(String url);

	/**
	 * Switch to a frame
	 *
	 * @param frameName the name of the frame to switch to
	 */
	void switchToFrame(String frameName);

	/**
	 * Swith to the parent frame
	 */
	void switchToParentFrame();

	/**
	 * Swith to the default content
	 */
	void switchToDefaultContentFrame();

	/**
	 *
	 * @param webElement
	 * @param timeout
	 */
	void waitUntilElementIsClickable(WebElement webElement, int timeout);

	/**
	 * Move focus to an element
	 */
	Actions retrieveWebActions();

	/**
	 * Handle exceptions in Stan
	 *
	 * @param testMethod
	 * @param wde        WebDriverException
	 * @return UXException
	 */
	UXException exceptionHandler(String testMethod, WebDriverException wde);

	/**
	 * Handle exceptions in Stan
	 *
	 * @param e Exception
	 * @return UXException
	 */
	UXException exceptionHandler(Exception e);

	/**
	 * Handle exceptions in Stan
	 *
	 * @param message The exception message
	 * @return UXException
	 */
	UXException exceptionHandler(String message);

	/**
	 * Handle exceptions in Stan
	 *
	 * @param e       The exception
	 * @param message The exception message
	 * @return UXException
	 */
	UXException exceptionHandler(Exception e, String message);

	/**
	 * Handle exceptions in Stan
	 *
	 * @param e       The exception
	 * @param message The exception message
	 */
	void exceptionIgnored(Exception e, String message);

	WebDriver getWebDriver();

	/**
	 * Perform a screen capture.
	 *
	 * @return The location of the screen capture.
	 */
	String capture();

	/**
	 * Check if the WebApplication has been launched or not
	 *
	 * @return true or false
	 */
	boolean isLaunched();

	String getWindowHandles();

	void switchTo(WebDriver webDriver, String windowHandle);

	WebApplication getWebApplication(WebApplicationEnum webApplicationType);


	default List<WebElement> findElements(By by) {
		return getWebDriver().findElements(by);
	}
	}
