package com.cztffa.objects;

import static com.cztffa.objects.Product.EVERYDAY_CHECKING;
import static com.cztffa.objects.Product.HIGH_YIELD_CHECKING;
import static com.cztffa.objects.Product.HIGH_YIELD_MONEY_MARKET;
import static com.cztffa.objects.Product.JUMP_START_SAVINGS;
import static com.cztffa.objects.Product.MONEY_MARKET;
import static com.cztffa.objects.Product.MONTH12_SHARE_CERTIFICATE;
import static com.cztffa.objects.Product.MONTH12_SHARE_CERTIFICATE_CYBER_PROMO;
import static com.cztffa.objects.Product.MONTH6_SHARE_CERTIFICATE;
import static com.cztffa.objects.Product.SECONDARY_SHARE;

public enum ProductTab {

	SHARE_CERTIFICATE(MONTH12_SHARE_CERTIFICATE_CYBER_PROMO, MONTH6_SHARE_CERTIFICATE,
			MONTH12_SHARE_CERTIFICATE),
	CHECKING(HIGH_YIELD_CHECKING, EVERYDAY_CHECKING),
	SAVINGS(JUMP_START_SAVINGS, SECONDARY_SHARE),
	MONEYMARKET(MONEY_MARKET, HIGH_YIELD_MONEY_MARKET);

	private Product[] products;

	private ProductTab(Product... products) {
		this.products = products;
	}

	public Product[] getProducts() {
		return products;
	}

}
