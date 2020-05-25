package com.currency.convert.microservice.currencyconversionservice;

public class CountryCodeDTO {

	private String countryCode;
	
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

    public String toString() {
    	return "[countryCode="+countryCode+"]";
    }



	
}
