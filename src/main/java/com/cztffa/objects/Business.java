package com.cztffa.objects;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Business {

	private String businessName;

	private String businessType;

	private String phoneNumber;

	private String email;

	private String ssn;

	private String tinNumber;

	private String doe;

	private String idType;

	private String securityWord;

	private String streetAddress1;

	private String city;

	private String zip;

	private String naicsCode;

	private String workPhone;

	private String occupation;

	private String employer;

	private String identificationNumber;

	private String issueDate;

	private String suffix;

	private String expiryDate;

	private String citizenShip;

	private String prefferedContactMethod;

	private String employmentStatus;

	private String prefferedId;

	private String membershipQualify;

	private String memberName;

	private String relationship;

	private List<Applicant> applicants;

	private Validation validation;

	private String submissionId;

}
