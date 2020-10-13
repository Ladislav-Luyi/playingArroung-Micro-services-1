package com.example.playing_aroung_spring.hello_world;




public class HelloWorldBean {
    final String message;


    public HelloWorldBean(String s) {
        message = s;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
