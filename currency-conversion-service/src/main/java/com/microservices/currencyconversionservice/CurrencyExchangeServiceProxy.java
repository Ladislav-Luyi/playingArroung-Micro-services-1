package com.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//localhost 8000 je currency exchange service
//@FeignClient(name = "currency-conversion-service",url = "localhost:8000")
@FeignClient(name = "currency-conversion-service")
@RibbonClient(name = "currency-conversion-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{fromValue}/to/{toValue}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable String fromValue, @PathVariable String toValue);

}
