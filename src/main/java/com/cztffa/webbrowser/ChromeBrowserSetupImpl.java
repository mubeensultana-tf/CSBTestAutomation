package com.cztffa.webbrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ChromeBrowserSetupImpl implements BrowserSetup {

	@Override
	public WebDriver openBrowser() {
		WebDriverManager.chromedriver().clearResolutionCache();
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.bin", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		ChromeOptions options = new ChromeOptions();
		log.info("Launching chrome driver");

		options.addArguments("profile-directory=terafinainc.com");
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--do-not-ignore-autocomplete-off");
		options.addArguments("--allow-running-insecure-content");
		options.addArguments("--allow-no-sandbox-job");
		options.addArguments("--allow-outdated-plugins");
		options.addArguments("--new-window");
		options.addArguments("--disable-gpu");
		options.addArguments("--enable-logging");
		options.addArguments("--v=1");
		options.setAcceptInsecureCerts(true);
		return new ChromeDriver(options);
	}
}