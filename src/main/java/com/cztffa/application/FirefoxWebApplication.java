package com.cztffa.application;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.cztffa.exception.FrameworkException;
import com.cztffa.webbrowser.BrowserSetupImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirefoxWebApplication extends AbstractWebApplication {

	private Integer timeoutWait = 90;

	@Override
	public WebDriver launch() {
		return launch(timeoutWait);
	}

	@Override
	public WebDriver launch(int implicitWait) {
		BrowserSetupImpl browser = new BrowserSetupImpl();

		try {
			driver = browser.openBrowserFireFox();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(timeoutWait, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(timeoutWait, TimeUnit.SECONDS);

			log.info("Firefox launched");
			return driver;
		} catch (Exception e) {
			throw new FrameworkException("Failed to launch Firefox - {}" + e.getMessage());
		}
	}
}
