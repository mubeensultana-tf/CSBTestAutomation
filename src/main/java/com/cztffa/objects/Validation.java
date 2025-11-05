package com.cztffa.objects;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Validation {

	private boolean skipQualifyButton;

	private boolean skipCitizenshipDropDown;

	private boolean skipStateDropdown;

	private boolean skipEmploymentStatusDropdown;

	private boolean skipPreferredContactDropdown;

	private boolean skipStateIssuedDropdown;

	private boolean skipMemberDiligenceOptions;

	private boolean skipQualifyCountry;

}
