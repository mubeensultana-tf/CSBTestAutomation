package com.cztffa.cucumber.parallel;

import java.io.File;
import java.io.IOException;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import com.cztffa.dataproviders.DataCSVExtractor;


@CucumberOptions(features = { "src/test/resources/com/cztffa/cucumber/consumer_digital_parallel" }, glue = {
		"classpath:com.cztffa.cucumber.parallel" }, plugin = { "pretty", "json:target/report/cucumber.json",
				"junit:target/report/cucumber.xml", "html:target/report/cucumber.html",
				"com.cztffa.utilities.MyTestListener",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, monochrome = true)
@RunWith(Cucumber.class)
public class CZTFFAFeatureRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios()  {
		File personalData = new File("src/test/resources/personalData.csv");
		File fundingData = new File("src/test/resources/fundingData.csv");
		File productData = new File("src/test/resources/productData.csv");
		File salesforceData = new File("src/test/resources/salesforceData.csv");
		File businessProductData = new File("src/test/resources/businessProductData.csv");
		File businessDetail=new File("src/test/resources/businessDetail.csv");
		File businessPersonalDetail=new File("src/test/resources/businessPersonalDetail.csv");
		File businessFundingData = new File("src/test/resources/businessFundingData.csv");
        File existingPersonalData = new File("src/test/resources/existingPersonalData.csv");
        File output = new File("src/test/resources/data.json");

		try {
			DataCSVExtractor.applicantDataStore = DataCSVExtractor.readObjectsFromCsv(personalData);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			DataCSVExtractor.fundingDataStore = DataCSVExtractor.readObjectsFromCsv(fundingData);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        try {
            DataCSVExtractor.existingpersonalDataStore = DataCSVExtractor.readObjectsFromCsv(existingPersonalData);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

		try {
			DataCSVExtractor.smbfundingDataStore = DataCSVExtractor.readObjectsFromCsv(businessFundingData);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			DataCSVExtractor.productDataStore = DataCSVExtractor.readObjectsFromCsv(productData);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			DataCSVExtractor.smbproductDataStore = DataCSVExtractor.readObjectsFromCsv(businessProductData);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			DataCSVExtractor.businessDataStore = DataCSVExtractor.readObjectsFromCsv(businessDetail);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			DataCSVExtractor.businessPersonDataStore = DataCSVExtractor.readObjectsFromCsv(businessPersonalDetail);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			DataCSVExtractor.salesforceDataStore = DataCSVExtractor.readObjectsFromCsv(salesforceData);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			DataCSVExtractor.writeAsJson(DataCSVExtractor.applicantDataStore, output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		System.out.println(DataCSVExtractor.applicantDataStore.get(0));
//		for(int i=0;i<DataCSVExtractor.csvStore.size();i++) {
		while(DataCSVExtractor.applicantCount<DataCSVExtractor.applicantDataStore.size()) {
			 super.scenarios();
			 return super.scenarios();
		}
		return super.scenarios();
	}

}
