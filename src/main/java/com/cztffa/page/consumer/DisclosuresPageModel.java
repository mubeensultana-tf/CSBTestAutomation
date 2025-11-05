package com.cztffa.page.consumer;
import com.cztffa.xpath.consumer.DisclosuresPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class DisclosuresPageModel {
    private WebDriver driver;

    public DisclosuresPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = DisclosuresPageXpath.SIGNBTN)
    public WebElement signBtn;
    
    @FindBy(xpath = DisclosuresPageXpath.CONTINUEBTN)
    public WebElement continueBtn;
    
    @FindBy(xpath = DisclosuresPageXpath.ADOPTANDSIGNBTN)
    public WebElement adoptAndSignBtn;
    
    @FindBy(xpath = DisclosuresPageXpath.FINISHBTN)
    public WebElement finishBtn;
    
    @FindBy(xpath = DisclosuresPageXpath.SUBMITBTN)
    public WebElement submitBtn;
    
}
