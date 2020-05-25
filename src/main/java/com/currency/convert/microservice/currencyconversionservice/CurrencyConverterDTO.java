package com.currency.convert.microservice.currencyconversionservice;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CurrencyConverterDTO {
	@NotEmpty(message = "From Country Code must be specified")
	@Pattern(regexp = "[A-Z]{3}", message = "From Country Code is not valid")
	private String fromCountryCode;
	
	@NotEmpty(message = "To Country Code must be specified")
	@Pattern(regexp = "[A-Z]{3}", message = "To Country Code is not valid")
	private String toCountryCode;
	
	@NotNull(message = "Amount must be specified")
	@Pattern(regexp = "(\\d*\\.)?\\d+", message = "Amount is not valid")
	private String amount;

	public String getFromCountryCode() {
		return fromCountryCode;
	}

	public void setFromCountryCode(String fromCountryCode) {
		this.fromCountryCode = fromCountryCode;
	}

	public String getToCountryCode() {
		return toCountryCode;
	}

	public void setToCountryCode(String toCountryCode) {
		this.toCountryCode = toCountryCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	

	
}
