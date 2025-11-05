package com.cztffa.xpath.consumer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Product {

  SIMPLECHECKING("SimpleChecking",
      "//tfcz-space-product-card[.//div[text()='Simple Checking']]//button[contains(text(), 'Add to Cart')]"),
  SIMPLESAVINGS("SimpleSavings",
      "//tfcz-space-product-card[.//div[text()='Simple Savings']]//button[contains(text(), 'Add to Cart')]"),
  CHECKOUTBTN("checkOutBtn",
      "//button[normalize-space()='Checkout']");

  private String name;

  private String xpath;

  public static Product getByName(String name) {

      if (name.equalsIgnoreCase("SimpleChecking")) {
      return SIMPLECHECKING;}
      else if (name.equalsIgnoreCase("SimpleSavings")) {
      return SIMPLESAVINGS;}
      else if (name.equalsIgnoreCase("checkOutBtn")) {
      return CHECKOUTBTN;}
      return null;
  }
}
