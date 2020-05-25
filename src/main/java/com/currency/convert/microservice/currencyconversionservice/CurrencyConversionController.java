package com.currency.convert.microservice.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Validated
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyConverterProxyService proxy;
	
/*	@GetMapping(path="/getConvertedValue/countryCode/{countryCode}/amount/{amount}")
	public BigDecimal getConvertedValue(@PathVariable("countryCode") @NotEmpty @Pattern(regexp = "[A-Z]{3}") String countryCode,
										@PathVariable("amount") @NotNull @Pattern(regexp = "(\\d*\\.)?\\d+") String amount) {
		Map<String,String> resultMap=new HashMap<>();
		resultMap.put("countryCode", countryCode);
		
		ResponseEntity<CurrencyConversionBean> responseEntity=new RestTemplate().getForEntity("http://localhost:8082/getConversionFactor/countryCode/{countryCode}", CurrencyConversionBean.class,resultMap);
		CurrencyConversionBean response=responseEntity.getBody();
		BigDecimal amt=new BigDecimal(amount);
		return amt.multiply(response.getConversionFactor());
	}*/
	
	@GetMapping("/getConvertedValue/countryCode/{countryCode}/amount/{amount}")
	public BigDecimal getConvertedValueFeign(@PathVariable("countryCode") @NotEmpty @Pattern(regexp = "[A-Z]{3}") String countryCode,
											 @PathVariable("amount") @NotNull @Pattern(regexp = "(\\d*\\.)?\\d+") String amount) {
		
		BigDecimal response=proxy.getConversionFactor(countryCode);
		BigDecimal amt=new BigDecimal(amount);
		return amt.multiply(response);
	}
}
