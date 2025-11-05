package com.cztffa.webbrowser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirefoxBrowserSetupImpl implements BrowserSetup {
	

    public WebDriver openBrowser() {
        log.info("Launching FireFox");
//        System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
        System.setProperty("webdriver.firefox.bin","/Applications/firefox.exec");
        WebDriverManager.firefoxdriver().setup();
        String workingDirectory = System.getProperty("user.dir");

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("webdriver.log.file", workingDirectory + "\\automation.log");

        FirefoxOptions option=new FirefoxOptions();
        option.setProfile(profile);
//		option.setBinary("\\Applications\\firefox.exec");


        WebDriver driver = new FirefoxDriver(option);

        log.info("Selenium webdriver details logged in automation.log");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }


}
