package com.example.playing_aroung_spring.hello_world.hello_world;


import com.example.playing_aroung_spring.hello_world.HelloWorldBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
public class helloWorldController {


    @GetMapping("/hello-world")
    // = @RequestMapping(method= RequestMethod.GET,path = "/hello-world")
    public String helloWorld(){
        return "Hello world";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello world");
    }

    @GetMapping("/hello-world-bean/{name}")
    public HelloWorldBean helloWorldBeanwithParameter(@PathVariable String name){
        return new HelloWorldBean("Hello " + name );
    }
}
