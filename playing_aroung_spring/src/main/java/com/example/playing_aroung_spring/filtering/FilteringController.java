package com.example.playing_aroung_spring.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/static-filtering")
    public SomeBean retrieveSomeBean(){
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping("/static-filtering-list")
    public List<SomeBean> retrieveListOfSomeBeans(){
        return Arrays.asList(
                new SomeBean("value1", "value2", "password"),
                new SomeBean("value4", "value5", "password"));
    }

    //!!!DYNAMIC FILTERING!!!
    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue dynamicRetrieveSomeBean(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3");



        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("value1");

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter",  filter);


        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

        mappingJacksonValue.setFilters(filters);




        return mappingJacksonValue;
    }

}
