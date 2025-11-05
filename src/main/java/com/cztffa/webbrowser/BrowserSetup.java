package com.cztffa.webbrowser;

import org.openqa.selenium.WebDriver;

public interface BrowserSetup {
    /**
     * Open an instance of the browser and return a webdriver to control the browser
     * @return  a webdriver to control the browser
     */
    public WebDriver openBrowser();
}
