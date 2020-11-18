package com.microservices.currencyexchangeservice;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository repository;
    //http://localhost:8000/currency-exchange/from/EUR/to/USD/
    @GetMapping("/currency-exchange/from/{fromValue}/to/{toValue}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String fromValue, @PathVariable String toValue){
        ExchangeValue exchangeValue =  repository.findByFromValueAndToValue(fromValue,toValue);
        exchangeValue.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port"))) );
        return exchangeValue;
    }


}
