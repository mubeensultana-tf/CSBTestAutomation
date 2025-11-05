package com.cztffa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.driver.SeleniumDriver;

public class ApplicantUtil extends BrowserActions {

	public ApplicantUtil() {
		super("");
	}

	public WebElement getWebElement(SeleniumDriver seleniumDriver,  String xpath, int position){
		return findElement(seleniumDriver, By.xpath(String.format(xpath, position)));
	}
	
	public WebElement getWebElement(SeleniumDriver seleniumDriver,  String xpath, String value){
		return findElement(seleniumDriver, By.xpath(String.format(xpath, value)));
	}

}
