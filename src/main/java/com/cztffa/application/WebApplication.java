package com.cztffa.application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public interface WebApplication {

    /**
     * Launch the application
     *
     * @return Webdriver object
     */
    WebDriver launch();

    /**
     * Launch the application
     *
     * @param implicitWait the ImplicitWait period for the driver
     * @return Webdriver object
     */
    WebDriver launch(int implicitWait);

    /**
     * Navigate to a url
     *
     * @param url The url of the web page
     */
    void navigateToPageUrl(String url);

    /**
     * Shrinks the width of the browser screen by the specified number of pixels
     */
    void shrinkScreenWidth(int width);

    /**
     * Switch to a different web frame
     *
     * @param frameName The frame to swich to
     */
    void switchToFrame(String frameName);

    /**
     * Switch to the parent frame
     */
    void switchToParentFrame();

    /**
     * Switch to the default content frame
     */
    void switchToDefaultContentFrame();

    /**
     *
     * @param webElement
     */
    void waitUntilElementIsClickable(WebElement webElement, int timeout);

    /**
     * Retrieve web actions
     *
     * @return Actions
     */
    Actions retrieveWebActions();

    /**
     * Take a screenshot with a specified file name
     *
     * @param fileName The screenshot file name
     */
    void takeScreenshot(String fileName);

    /**
     * Take a screenshot
     */
    String takeScreenshot();

    /**
     * Clean up the browser
     */
    void cleanup();

    <T> T getModel(Class<T> clazz);
}
