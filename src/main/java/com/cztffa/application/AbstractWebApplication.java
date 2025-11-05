package com.cztffa.application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cztffa.exception.FrameworkException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractWebApplication implements WebApplication {

	protected WebDriver driver;

	private File browserImgFile;

	private static final String CAPTURE_PATH = "./Screenshots/capture.png";
	private static final String RP_CAPTURE_PATH = "./Screenshots/rp_capture.png";

	@Override
	public void navigateToPageUrl(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			throw new FrameworkException("Failed to navigate to page " + url + " " + e.getMessage());
		}
	}

	@Override
	public void shrinkScreenWidth(int width) {
		Dimension dimension = new Dimension(driver.manage().window().getSize().width - width,
				driver.manage().window().getSize().height);
		driver.manage().window().setSize(dimension);
	}

	@Override
	public void switchToFrame(String frameName) {
		try {
			driver.switchTo().frame(frameName);
		} catch (Exception e) {
			throw new FrameworkException("Failed to switch to frame " + frameName);
		}
	}

	@Override
	public void switchToParentFrame() {
		try {
			driver.switchTo().parentFrame();
		} catch (Exception e) {
			throw new FrameworkException("Failed to switch to parent frame");
		}
	}

	@Override
	public void switchToDefaultContentFrame() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			throw new FrameworkException("Failed to switch to default content frame");
		}
	}

	@Override
	public void waitUntilElementIsClickable(WebElement webElement, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	@Override
	public Actions retrieveWebActions() {
		return new Actions(driver);
	}

	@Override
	public void cleanup() {
		if (driver != null) {
			log.info("Exiting webdriver...");
			driver.quit();
		}
	}

	@Override
	public <T> T getModel(Class<T> clazz) {
		if (driver == null) {
			throw new FrameworkException("WebDriver was null, has not been created yet");
		}
		return PageFactory.initElements(driver, clazz);
	}

	@Override
	public String takeScreenshot() {
		capture(CAPTURE_PATH);
		return CAPTURE_PATH;
	}

	@Override
	public void takeScreenshot(String filename) {
		capture(filename);
	}

	public boolean deleteScreenshot() {
		try {
			Files.delete(browserImgFile.toPath());
		} catch (IOException e) {
			log.warn("Failed to delete screenshot - {}", browserImgFile.getName());
			return false;
		}

		return true;
	}

	private void capture(String fileName) {
		boolean folderCreated = false;
		boolean folderExists = false;
		String rpFilename = RP_CAPTURE_PATH;
		boolean publishScreenshotToReportPortal = Boolean.parseBoolean(System.getProperty("rp.screenshot", "false"));
		try {
			log.info("Capturing screenshot as {}", fileName);

			TakesScreenshot screenShot = ((TakesScreenshot) driver);

			// Call getScreenshotAs method to create image file
			File screenshotFile = screenShot.getScreenshotAs(OutputType.FILE);

			// Move image file to new destination
			browserImgFile = new File(fileName);
			File reportportalImgFile = new File(rpFilename);

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

				if (reportportalImgFile.exists() && Files.deleteIfExists(reportportalImgFile.toPath())) {
					log.warn("Failed to delete Report Portal screenshot - {}", reportportalImgFile.getName());
				}

				FileUtils.copyFile(screenshotFile, reportportalImgFile);

				if (publishScreenshotToReportPortal) {
					log.info("RP_MESSAGE#FILE#{}#{}", reportportalImgFile, "Captured screenshot");
				}
			} else {
				log.info("Failed to create folder");
			}
		} catch (IOException | WebDriverException e) {
			log.warn("Failed to capture screenshot - {}", e);
		}
	}
}
