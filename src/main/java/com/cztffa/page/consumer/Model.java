package com.cztffa.page.consumer;
import com.cztffa.xpath.consumer.Xpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class Model {
    private WebDriver driver;

    public Model(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
