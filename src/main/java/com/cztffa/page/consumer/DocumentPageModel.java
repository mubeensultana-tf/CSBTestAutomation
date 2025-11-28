package com.cztffa.page.consumer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentPageModel {

    private WebDriver driver;

    public DocumentPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[normalize-space()='Next']")
    public WebElement documentNextButton;
}


