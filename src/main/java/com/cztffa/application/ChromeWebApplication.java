package com.cztffa.application;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.cztffa.exception.FrameworkException;
import com.cztffa.webbrowser.BrowserSetupImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChromeWebApplication extends AbstractWebApplication {

    private Integer timeoutWait = 90;
    private Integer smallTimeoutWait = 5;

    @Override
    public WebDriver launch() {
        return launch(smallTimeoutWait);
    }

    @Override
    public WebDriver launch(int implicitWait) {
        BrowserSetupImpl browser = new BrowserSetupImpl();

        try {
            driver = browser.openBrowserChrome();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(timeoutWait, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(timeoutWait, TimeUnit.SECONDS);

            log.info("Chrome launched");
            return driver;
        } catch (Exception e) {
            throw new FrameworkException("Failed to launch Chrome - {}" + e.getMessage());
        }
    }
}
