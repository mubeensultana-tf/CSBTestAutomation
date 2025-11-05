package com.cztffa.cucumber.parallel.smb;

import com.cztffa.browseractions.BrowserActions;
import com.cztffa.driver.SeleniumDriver;
import com.cztffa.page.review.ReviewPage;
import com.cztffa.page.review.SmbReviewPage;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.extern.slf4j.Slf4j;

@Slf4j

public class SmbSignableDisclosuresStepDefinition {
    private SmbReviewPage smbReviewPage;
    private SeleniumDriver seleniumdriver;
    private BrowserActions browserActions;

    public SmbSignableDisclosuresStepDefinition(SmbReviewPage smbReviewPage) {
        this.smbReviewPage = smbReviewPage;
        this.browserActions = smbReviewPage.browserActions;
        this.seleniumdriver = smbReviewPage.getSeleniumdriver();
    }

    @And(": I click on signable disclosures button for smb")
    public void iClickOnSignableDisclosuresButtonForSmb() throws InterruptedException {
        smbReviewPage.waitForSpinnerToDisappear();
        smbReviewPage.waitForInnerSpinnersToDisappear(seleniumdriver.getWebDriver());
        WebDriver driver = seleniumdriver.getWebDriver();
        String mainWindow = driver.getWindowHandle();
        if (seleniumdriver.getWebDriver().getPageSource().contains("Docusign")) {
            Thread.sleep(4000);
            browserActions.scrollToWebElement(seleniumdriver, smbReviewPage.getSmbDisclosurePageModel().signBtn);
            log.info("Clicking on Sign button");
            Thread.sleep(1000);
            List<WebElement> signButtons = seleniumdriver.findElements(By.xpath("//button[normalize-space()='Sign']"));
            int buttonCount = signButtons.size();
            log.info("Size of button=" + buttonCount);

            for (int i = 0; i < buttonCount; i++) {

                browserActions.scrollToWebElement(seleniumdriver, smbReviewPage.getSmbDisclosurePageModel().signBtn);
                Thread.sleep(2500);
                smbReviewPage.wait(smbReviewPage.getSmbDisclosurePageModel().signBtn);
                browserActions.clickButton(seleniumdriver, smbReviewPage.getSmbDisclosurePageModel().signBtn);

                    Thread.sleep(1000);
                    log.info("Sign in button Selected");
                    smbReviewPage.waitForSpinnerToDisappear();
                    Thread.sleep(10000);
                smbReviewPage.waitForInnerSpinnersToDisappear(seleniumdriver.getWebDriver());
                    log.info("Entering for loop");
                    for (String windowHandle : driver.getWindowHandles()) {
                        if (!windowHandle.equals(mainWindow)) {
                            driver.switchTo().window(windowHandle);
                            log.info("Switched to popup window: " + windowHandle);
                            break;
                        }
                    }

                    log.info("Accepting eConsent");
                    log.info("Clicking on Continue button");
                    browserActions.clickButton(seleniumdriver, smbReviewPage.getSmbDisclosurePageModel().continueBtn);
                    Thread.sleep(2000);
                    log.info("Clicking on sign button");

                    Thread.sleep(2000); // 🔁 Dynamically click all signature arrow buttons

                    WebElement element1 = driver.findElement(By.xpath("//div[@class='tab-text' and contains(text(),'Sign')]"));
                    browserActions.scrollToWebElement(seleniumdriver, element1);
                    Thread.sleep(2000);
                    browserActions.clickApply(driver, element1);
                    Thread.sleep(2000);
                    log.info("Selecting AdoptAndSignBtn");
                    smbReviewPage.waitUntilVisibleAndClickable(seleniumdriver.getWebDriver(),smbReviewPage.getSmbDisclosurePageModel().adoptAndSignBtn);
                    browserActions.scrollToWebElement(seleniumdriver, smbReviewPage.getSmbDisclosurePageModel().adoptAndSignBtn);
                    Thread.sleep(2000);
                smbReviewPage.wait(smbReviewPage.getSmbDisclosurePageModel().adoptAndSignBtn);

                smbReviewPage.getSmbDisclosurePageModel().adoptAndSignBtn.click();
                    Thread.sleep(2000);

                    int index = 2;
                    while (true) {
                        try {
                            log.info("Inside while");
                            String signBox = "(//div[@class='tab-text' and contains(text(),'Sign')])[" + index + "]";
                            log.info(signBox);
                            WebElement signElement;
                            try {
                                signElement = seleniumdriver.getWebDriver().findElement(By.xpath(signBox));
                                log.info("Inside If");
                                browserActions.scrollToWebElement(seleniumdriver, signElement);
                                Thread.sleep(2000);
                                browserActions.clickButton(seleniumdriver, signElement);
                                Thread.sleep(3000);
                                index++;
                            } catch (Exception e) {
                                break;
                            }
                        } catch (NoSuchElementException e) {
                            log.info("No more checkboxes found. Exiting loop.");
                            break;
                        }
                    }

                    log.info("Clicking on Finish Button");
                    browserActions.clickButton(seleniumdriver, smbReviewPage.getSmbDisclosurePageModel().finishBtn);
                    Thread.sleep(2000);
                    if (driver.getWindowHandles().contains(mainWindow)) {
                        driver.switchTo().window(mainWindow);
                        log.info("Switched back to main window: " + mainWindow);
                    } else {
                        log.error("Main window no longer exists. Cannot switch back.");
                    }

                    Thread.sleep(18000);
                }

                smbReviewPage.waitInDisclosures();
                log.info("Clicking on Submit Application button");
                browserActions.scrollToWebElement(seleniumdriver, smbReviewPage.getSmbDisclosurePageModel().submitBtn);
                Thread.sleep(1500);
                smbReviewPage.wait(smbReviewPage.getSmbDisclosurePageModel().submitBtn);
                browserActions.clickButton(seleniumdriver, smbReviewPage.getSmbDisclosurePageModel().submitBtn);
            }

        }


    }


