package com.example.playing_aroung_spring.user;

import com.example.playing_aroung_spring.post.Post;
import com.example.playing_aroung_spring.post.PostDaoService;
import com.example.playing_aroung_spring.user.hashRepository.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
public class UserController {
    private final UserDaoService userDaoService;

    @Autowired
    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return  userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUserWithId(@PathVariable int id){
        User user = userDaoService.findOne(id);

        //HATEOAS -- potom mas ked urobis request nieco ako links
        EntityModel<User> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkTo =
                linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));
        //HATEOAS

        //return user;
        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user){
        User savedUser = userDaoService.save(user);
        Logger.getLogger(String.valueOf(getClass())).info(savedUser.toString() + " was created");

        //to properly show response 201 and path in the header
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/users/{id}")
    public void removeUser(@PathVariable Integer id){
        userDaoService.remove(id);
    }





}
