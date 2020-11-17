package com.microservices.currencyexchangeservice;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ExchangeValue {

    @Id
    private Long id;


    private String fromValue;


    private String toValue;
    private BigDecimal conversionMultiple;


    @Transient
    private int port;

    protected ExchangeValue(){

    }

    public ExchangeValue(Long id, String fromValue, String toValue, BigDecimal conversionMultiple) {
        this.id = id;
        this.fromValue = fromValue;
        this.toValue = toValue;
        this.conversionMultiple = conversionMultiple;
    }

    public Long getId() {
        return id;
    }

    public String getFromValue() {
        return fromValue;
    }

    public String getToValue() {
        return toValue;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
