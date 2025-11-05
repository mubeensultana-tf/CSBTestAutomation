package com.cztffa.webbrowser;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrowserSetupImpl implements BrowserSetup {


	public WebDriver openBrowserChrome() {
		return new ChromeBrowserSetupImpl().openBrowser();
	}

	public WebDriver openBrowserFireFox() {
		return new FirefoxBrowserSetupImpl().openBrowser();
	}

	public void killProcess(String processName) {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM " + processName);
			log.info("Killed existing process :" + processName);
		} catch (IOException e) {
			log.error("Unable to kill existing job " + processName);
		}
	}

	@Override
	public WebDriver openBrowser() {
		throw new RuntimeException("Method should not be called directly");
	}
}
