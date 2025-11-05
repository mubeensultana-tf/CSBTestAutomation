package com.cztffa.exception;

import com.cztffa.application.WebApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UXException extends RuntimeException implements ScreenshotEnabledException {

	private static final long serialVersionUID = 1L;

	private String screenshotPath;


	public UXException(WebApplication webApplication, String message) {
		super(message);
		log.error(message);

		this.screenshotPath = webApplication.takeScreenshot();
		log.info("Finished the attempt to capture a screenshot");
	}

	public UXException(WebApplication webApplication, String message, String testMethod) {
		super(message);
		log.error(message);

		webApplication.takeScreenshot("./Screenshots/"+testMethod+".png");
		log.info("Finished the attempt to capture a screenshot");
	}

	@Override
	public String getScreenshotPath() {
		return screenshotPath;
	}

}
