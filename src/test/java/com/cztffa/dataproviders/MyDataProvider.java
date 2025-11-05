package com.cztffa.dataproviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.cztffa.objects.Person;
import com.cztffa.util.JacksonUtils;

public class MyDataProvider {

	@DataProvider(name = "getPersonInfo", parallel = false)
	public Person[] getPersonInfo() throws IOException {
		return JacksonUtils.deserializeJson("person_details.json", Person[].class);
	}

	@DataProvider(name = "getInvalidPersonDetails", parallel = false)
	public Person[] getInvalidPersonDetails() throws IOException {
		return JacksonUtils.deserializeJson("invalid_person_details.json", Person[].class);
	}

	@DataProvider(name = "getJointApplicantInfo", parallel = false)
	public Person[] getJointApplicantInfo() throws IOException {
		return JacksonUtils.deserializeJson("joint_applicant.json", Person[].class);
	}

	@DataProvider(name = "getPersonValidationdetails", parallel = false)
	public Person[] getPersonValidationdetails() throws IOException {
		return JacksonUtils.deserializeJson("person_details_validation.json", Person[].class);
	}

	@DataProvider(name = "getPersonCheckingTabDetails", parallel = false)
	public Person[] getPersonCheckingTabDetails() throws IOException {
		return JacksonUtils.deserializeJson("person_details_checking_tab.json", Person[].class);
	}

}
