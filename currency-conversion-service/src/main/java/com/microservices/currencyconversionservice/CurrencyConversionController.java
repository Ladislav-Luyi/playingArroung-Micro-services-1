package com.microservices.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean retrieveConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        //this is not very convenient option therefore is better to use Feign REST client
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionBean> r = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",CurrencyConversionBean.class, uriVariables);

        CurrencyConversionBean c = r.getBody();


        return new CurrencyConversionBean(1l, c.getFromValue(), c.getToValue(), c.getConversionMultiple(), quantity, quantity.multiply(c.getConversionMultiple()));
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean retrieveConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){



        CurrencyConversionBean c = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);


        return new CurrencyConversionBean(1l, c.getFromValue(), c.getToValue(), c.getConversionMultiple(), quantity, quantity.multiply(c.getConversionMultiple()));
    }
}
