package com.cztffa.objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Product {
	PREMIER_CERTIFICATE("PremierCertificate",
			"//*[text() = 'Premier Certificate']/parent::div[@class='unit-text-wrapper text-center pt-4']/following-sibling::div[@class='unit-text-wrapper text-center mt-4 product-btn-wrapper']/descendant::button[contains(text(), 'Add to Cart')]"),
	PREMIER_CHECKING("PremierChecking",
			"//*[text() = 'Premier Checking']/parent::div[@class='unit-text-wrapper text-center pt-4']/following-sibling::div[@class='unit-text-wrapper text-center mt-4 product-btn-wrapper']/descendant::button[contains(text(), 'Add to Cart')]"),
	PREMIER_SAVING("PremierSaving",
			"//*[text() = 'Premier Savings']/parent::div[@class='unit-text-wrapper text-center pt-4']/following-sibling::div[@class='unit-text-wrapper text-center mt-4 product-btn-wrapper']/descendant::button[contains(text(), 'Add to Cart')]"),
	BASIC_SAVINGS("BasicSavings",
			"//*[text() = 'Basic Savings']/parent::div[@class='unit-text-wrapper text-center pt-4']/following-sibling::div[@class='unit-text-wrapper text-center mt-4 product-btn-wrapper']/descendant::button[contains(text(), 'Add to Cart')]"),
	BASIC_CHECKING("BasicChecking",
			"//*[text() = 'Basic Checking']/parent::div[@class='unit-text-wrapper text-center pt-4']/following-sibling::div[@class='unit-text-wrapper text-center mt-4 product-btn-wrapper']/descendant::button[contains(text(), 'Add to Cart')]"),
	CHECKING_SILVER("CheckingSilver",
			"//*[text() = 'Checking - Silver']/parent::div[@class='unit-text-wrapper text-center pt-4']/following-sibling::div[@class='unit-text-wrapper text-center mt-4 product-btn-wrapper']/descendant::button[contains(text(), 'Add to Cart')]"),
	PERSONAL_CHECKING("PersonalChecking",
			"//*[text() = 'Personal Checking']/parent::div[@class='unit-text-wrapper text-center pt-4']/following-sibling::div[@class='unit-text-wrapper text-center mt-4 product-btn-wrapper']/descendant::button[contains(text(), 'Add to Cart')]"),
	SAVING_PLATINUM("SavingPlatinum",
			"//*[text() = 'Saving - Platinum']/parent::div[@class='unit-text-wrapper text-center pt-4']/following-sibling::div[@class='unit-text-wrapper text-center mt-4 product-btn-wrapper']/descendant::button[contains(text(), 'Add to Cart')]"),
	Docusign_Premier_Checking("DocusignPremierChecking",
			"//*[text() = 'Docusign Premier Checking']/parent::div[@class='unit-text-wrapper text-center pt-4']/following-sibling::div[@class='unit-text-wrapper text-center mt-4 product-btn-wrapper']/descendant::button[contains(text(), 'Add to Cart')]"),
	BUSINESS_SAVING("BusinessSaving",
			"(//tf-space-product-card[.//text()[contains(., 'Business Savings')]]//button[contains(text(), 'Add to Cart')])[1]"),
	HIGH_YIELD_CHECKING("highYieldChecking",
			"//div[contains(text(), 'High Yield Checking')]/parent::div[@class='card-body']/following-sibling::div[@class='card-footer']/descendant::button[contains(text(), 'Add to Cart')]"),
	EVERYDAY_CHECKING("everyDayChacking",
			"//div[contains(text(), 'Everyday Checking')]/parent::div[@class='card-body']/following-sibling::div[@class='card-footer']/descendant::button[contains(text(), 'Add to Cart')]"),
	MONEY_MARKET("moneyMarket",
			"//div[contains(text(), 'Money Market')]/parent::div[@class='card-body']/following-sibling::div[@class='card-footer']/descendant::button[contains(text(), 'Add to Cart')]"),
	HIGH_YIELD_MONEY_MARKET("highYieldMoneyMarket",
			"//div[contains(text(), 'High Yield Money Market')]/parent::div[@class='card-body']/following-sibling::div[@class='card-footer']/descendant::button[contains(text(), 'Add to Cart')]"),
	MONTH12_SHARE_CERTIFICATE_CYBER_PROMO("month12ShareCertificate",
			"//div[contains(text(), '12-Month Share Certificate Cyber Promo')]/parent::div[@class='card-body']/following-sibling::div[@class='card-footer']/descendant::button[contains(text(), 'Add to Cart')]"),
	MONTH6_SHARE_CERTIFICATE("month6ShareCertificate",
			"//div[contains(text(), '6-Month Share Certificate')]/parent::div[@class='card-body']/following-sibling::div[@class='card-footer']/descendant::button[contains(text(), 'Add to Cart')]"),
	MONTH12_SHARE_CERTIFICATE("month12Share",
			"//div[contains(text(), '12-Month Share Certificate')]/parent::div[@class='card-body']/following-sibling::div[@class='card-footer']/descendant::button[contains(text(), 'Add to Cart')]"),
	JUMP_START_SAVINGS("jumpStart",
			"//div[contains(text(), 'Jump Start Savings')]/parent::div[@class='card-body']/following-sibling::div[@class='card-footer']/descendant::button[contains(text(), 'Add to Cart')]"),
	SECONDARY_SHARE("secondaryShare",
			"//div[contains(text(), 'Secondary Share')]/parent::div[@class='card-body']/following-sibling::div[@class='card-footer']/descendant::button[contains(text(), 'Add to Cart')]"),
	BUSINESS_CHECKING("BusinessChecking",
			"(//tf-space-product-card[.//text()[contains(., 'Business Checking')]]//button[contains(text(), 'Add to Cart')])[1]"),
	//			"//div[contains(text(), 'Business Checking')]/parent::div[@class='card-body']/following-sibling::div[@class='card-footer']/descendant::button[contains(text(), 'Add to Cart')]");
	BUSINESS_CD("BusinessCD",
			"(//tf-space-product-card[.//text()[contains(., 'Business CD')]]//button[contains(text(), 'Add to Cart')])[1]"),

	Docusign_Business_Checking("DocusignBusinessChecking",
			"(//tf-space-product-card[.//text()[contains(., 'Docusign Business Checking')]]//button[contains(text(), 'Add to Cart')])[1]");

	private String name;

	private String xpath;

	public static Product getByName(String name) {
		if (name.equalsIgnoreCase("PremierChecking")) {
			return PREMIER_CHECKING;
		} else if (name.equalsIgnoreCase("PremierCertificate")) {
			return PREMIER_CERTIFICATE;
		} else if (name.equalsIgnoreCase("PremierSaving")) {
			return PREMIER_SAVING;
		} else if (name.equalsIgnoreCase("BasicSavings")) {
			return BASIC_SAVINGS;
		} else if (name.equalsIgnoreCase("BasicChecking")) {
			return BASIC_CHECKING;
		} else if (name.equalsIgnoreCase("CheckingSilver")) {
			return CHECKING_SILVER;
		} else if (name.equalsIgnoreCase("PersonalChecking")) {
			return PERSONAL_CHECKING;
		} else if (name.equalsIgnoreCase("SavingPlatinum")) {
			return SAVING_PLATINUM;
		} else if (name.equalsIgnoreCase("DocusignPremierChecking")) {
			return Docusign_Premier_Checking;
		} else if (name.equalsIgnoreCase("everyDayChacking")) {
			return EVERYDAY_CHECKING;
		} else if (name.equalsIgnoreCase("moneyMarket")) {
			return MONEY_MARKET;
		} else if (name.equalsIgnoreCase("month12ShareCertificate")) {
			return MONTH12_SHARE_CERTIFICATE_CYBER_PROMO;
		} else if (name.equalsIgnoreCase("month6ShareCertificate")) {
			return MONTH6_SHARE_CERTIFICATE;
		} else if (name.equalsIgnoreCase("month12Share")) {
			return MONTH12_SHARE_CERTIFICATE;
		} else if (name.equalsIgnoreCase("jumpStart")) {
			return JUMP_START_SAVINGS;
		} else if (name.equalsIgnoreCase("BusinessSaving")) {
			return BUSINESS_SAVING;
		} else if (name.equalsIgnoreCase("BusinessChecking")) {
			return BUSINESS_CHECKING;
		} else if (name.equalsIgnoreCase("BusinessCD")) {
			return BUSINESS_CD;
		}
		else if (name.equalsIgnoreCase("DocusignBusinessChecking")) {
			return Docusign_Business_Checking;
		}

		return SECONDARY_SHARE;
	}

}
