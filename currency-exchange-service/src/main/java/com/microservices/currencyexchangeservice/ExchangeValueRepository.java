package com.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends
        JpaRepository<ExchangeValue,Long> {

    ExchangeValue findByFromValueAndToValue(String fromValue ,  String toValue);


}
