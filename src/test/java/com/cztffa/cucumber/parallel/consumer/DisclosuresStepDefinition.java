package com.cztffa.cucumber.parallel.consumer;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.page.review.ReviewPage;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Slf4j
public class DisclosuresStepDefinition {
    private ReviewPage reviewPage;
    private SeleniumDriver seleniumdriver;
    private BrowserActions browserActions;

    public DisclosuresStepDefinition(ReviewPage reviewPage) {
        this.reviewPage = reviewPage;
        this.browserActions = reviewPage.browserActions;
        this.seleniumdriver = reviewPage.getSeleniumdriver();
    }

    @And("^: I click on signable disclosures button$")
    public void signBusinessDocs() throws InterruptedException {
        WebDriver driver = seleniumdriver.getWebDriver();
//        JavascriptExecutor j = (JavascriptExecutor) driver;
        String mainWindow = driver.getWindowHandle();

        if(seleniumdriver.getWebDriver().getPageSource().contains("Docusign")){
            Thread.sleep(2000);
            reviewPage.spinner();
            List<WebElement> signButtons = driver.findElements(By.xpath("//button[normalize-space()='Sign']"));
            log.info("Found " + signButtons.size() + " sign buttons to click.");
            for (int i = 0; i < signButtons.size(); i++) {
                log.info("Clicking on Sign button " + i);
                Thread.sleep(2000);
                browserActions.scrollToWebElement(seleniumdriver, reviewPage.getDisclosuresPageModel().signBtn);
                Thread.sleep(1000);
                browserActions.clickButton(seleniumdriver, reviewPage.getDisclosuresPageModel().signBtn);
//              WebElement btn = signButtons.get(i);
//              reviewPage.wait(btn);
//              browserActions.scrollToWebElement(seleniumdriver, btn);
//              btn.click();
                Thread.sleep(1000);
                reviewPage.spinner();
                Thread.sleep(13000);
                log.info("Changing current window");
                for (String windowHandle : driver.getWindowHandles()) {
                    if (!windowHandle.equals(mainWindow)) {
                        driver.switchTo().window(windowHandle);
                        log.info("Switched to popup window: " + windowHandle);
                        break;
                    }
                }
                if(seleniumdriver.getWebDriver().getPageSource().contains("Continue")) {
                    log.info("Accepting eConsent");
                    log.info("Clicking on Continue button");
                    browserActions.clickButton(seleniumdriver, reviewPage.getDisclosuresPageModel().continueBtn);
                    Thread.sleep(2000);
                }
                log.info("Signing Documents");
                Thread.sleep(2000); // 🔁 Dynamically click all signature arrow buttons
                List<WebElement> signArrows = driver.findElements(By.xpath("//div[@class='tab-text' and contains(text(),'Sign')]"));
                log.info("Found " + signArrows.size() + " sign arrows to click.");
                for (int k = 0; k < signArrows.size(); k++) {
                    WebElement arrow = signArrows.get(k);
                    reviewPage.wait(arrow);
                    browserActions.scrollToWebElement(seleniumdriver, arrow);
                    arrow.click();
                    Thread.sleep(2000); // Only click Adopt Sign after the first arrow
                    if (k == 0) {
                        reviewPage.wait(reviewPage.getDisclosuresPageModel().adoptAndSignBtn);
                        browserActions.scrollToWebElement(seleniumdriver, reviewPage.getDisclosuresPageModel().adoptAndSignBtn);
                        reviewPage.getDisclosuresPageModel().adoptAndSignBtn.click();
                        Thread.sleep(2000);
                    }
                }
                log.info("Clicking on Finish Button");
                browserActions.clickButton(seleniumdriver, reviewPage.getDisclosuresPageModel().finishBtn);
                if (driver.getWindowHandles().contains(mainWindow)) {
                    driver.switchTo().window(mainWindow);
                    log.info("Switched back to main window: " + mainWindow);
                } else {
                    log.error("Main window no longer exists. Cannot switch back.");
                }
                reviewPage.waitInDisclosures();
                Thread.sleep(1000);
            }
            log.info("Clicking on Submit Application button");
            browserActions.scrollToWebElement(seleniumdriver, reviewPage.getDisclosuresPageModel().submitBtn);
            Thread.sleep(1000);
            reviewPage.wait(reviewPage.getDisclosuresPageModel().submitBtn);
            browserActions.clickButton(seleniumdriver, reviewPage.getDisclosuresPageModel().submitBtn);
        }
    }
}
