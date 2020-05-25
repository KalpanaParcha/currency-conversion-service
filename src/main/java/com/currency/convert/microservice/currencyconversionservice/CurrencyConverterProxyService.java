package com.currency.convert.microservice.currencyconversionservice;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="manage-currency-conversion-factor")
@RibbonClient(name = "manage-currency-conversion-factor")
public interface CurrencyConverterProxyService {
	@GetMapping("/getConversionFactor/countryCode/{countryCode}")
	public BigDecimal getConversionFactor(@PathVariable("countryCode") @NotEmpty @Pattern(regexp = "[A-Z]{3}") String countryCode) ;
}
