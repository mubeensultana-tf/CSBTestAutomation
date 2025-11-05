package com.cztffa.objects;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Person {

	private String firstName;

	private String lastName;

	private String middleName;

	private String phoneNumber;

	private String email;

	private String ssn;

	private String dob;

	private String motherName;

	private String securityWord;

	private String streetAddress1;

	private String city;

	private String zip;

	private String homePhone;

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
	
	private String ownershipPercentage;
	
	private String submissionId;
}
