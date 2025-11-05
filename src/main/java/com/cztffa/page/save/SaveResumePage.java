//package com.cztffa.page.save;
//
//import java.util.List;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//
//import com.cztffa.application.WebApplicationEnum;
//import com.cztffa.objects.Person;
//import com.cztffa.objects.Product;
//import com.cztffa.page.base.BasePage;
//
//public class SaveResumePage extends BasePage {
//
//	public void saveResumePage(Person person, Product... products)
//			throws InterruptedException {
//
//		browserActions.enterText(getSeleniumdriver(), getSaveResumePageModel().gmailUserName, "yterafinan@gmail.com");
//		browserActions.clickButton(getSeleniumdriver(), getSaveResumePageModel().signinSubmit);
//		Thread.sleep(5000);
//		browserActions.enterText(getSeleniumdriver(), getSaveResumePageModel().gmailPassword, "abcd@1234");
//		Thread.sleep(4000);
//		browserActions.clickButton(getSeleniumdriver(), getSaveResumePageModel().signinSubmit);
//		Thread.sleep(5000);
//
//		List<WebElement> unreademail = browserActions.findElements(getSeleniumdriver(), By.className("zE"));
//
//		for (WebElement element : unreademail) {
//			if (element.getText().contains("cztffaui Site Guest U.")) {
//				element.click();
//				break;
//			}
//		}
//		Thread.sleep(5000);
//		browserActions.clickButton(getSeleniumdriver(), getSaveResumePageModel().clickHereLink);
//		Thread.sleep(12000);
//		browserActions.switchToNewTab(getSeleniumdriver());
//		browserActions.clickButton(getSeleniumdriver(), getSaveResumePageModel().loginWithCredentials);
//		Thread.sleep(2000);
//		browserActions.enterText(getSeleniumdriver(), getSaveResumePageModel().lastNameTextBox, "Reddy");
//		browserActions.enterText(getSeleniumdriver(), getSaveResumePageModel().lastFourSSNTextBox, "1232");
//		browserActions.enterText(getSeleniumdriver(), getSaveResumePageModel().primaryEmailTextBox, "yterafinan@gmail.com");
//		browserActions.clickButton(getSeleniumdriver(), getSaveResumePageModel().loginButton);
//		Thread.sleep(10000);
//
//	}
//
//	public SaveResumePageModel getSaveResumePageModel() {
//		return getSeleniumdriver().getWebApplication(WebApplicationEnum.CHROME).getModel(SaveResumePageModel.class);
//	}
//
//}
