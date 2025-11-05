package com.cztffa.xpath.business;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Product {

  BUSINESSSAVING("BusinessSaving",
      "(//tf-space-product-card[.//text()[contains(., 'Business Savings')]]//button[contains(text(), 'Add to Cart')])[1]"),
  BUSINESSCHECKING("BusinessChecking",
      "//div[12]//tf-space-product-card[1]//div[1]//div[1]//div[2]//button[2]"),
  BUSINESSCD("BusinessCD",
      "(//tf-space-product-card[.//text()[contains(., 'Business CD')]]//button[contains(text(), 'Add to Cart')])[1]"),
  DOCUSIGNBUSINESSCHECKING("DocusignBusinessChecking",
      "(//tf-space-product-card[.//text()[contains(., 'Docusign Business Checking')]]//button[contains(text(), 'Add to Cart')])[1]");

  private String name;

  private String xpath;

  public static Product getByName(String name) {

      if (name.equalsIgnoreCase("BusinessSaving")) {
      return BUSINESSSAVING;}
      else if (name.equalsIgnoreCase("BusinessChecking")) {
      return BUSINESSCHECKING;}
      else if (name.equalsIgnoreCase("BusinessCD")) {
      return BUSINESSCD;}
      else if (name.equalsIgnoreCase("DocusignBusinessChecking")) {
      return DOCUSIGNBUSINESSCHECKING;}
      return null;
  }
}
