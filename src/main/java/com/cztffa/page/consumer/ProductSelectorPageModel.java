package com.cztffa.page.consumer;
import com.cztffa.xpath.consumer.ProductSelectorPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class ProductSelectorPageModel {
    private WebDriver driver;

    public ProductSelectorPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ProductSelectorPageXpath.CONTINUEBUTTON)
    public WebElement continuebutton;
    
}
