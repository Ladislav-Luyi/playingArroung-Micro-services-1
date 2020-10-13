package com.example.playing_aroung_spring.user;

import com.example.playing_aroung_spring.post.Post;
import com.example.playing_aroung_spring.post.PostJpaService;
import com.example.playing_aroung_spring.user.DBrepository.UserJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
public class UserJPAController {
    private final UserJpaService userDaoService;
    private final PostJpaService postJpaService;

    @Autowired
    public UserJPAController(UserJpaService userDaoService, PostJpaService postJpaService) {
        this.userDaoService = userDaoService;
        this.postJpaService = postJpaService;
    }




    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return  userDaoService.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<Optional<User>> getUserWithId(@PathVariable int id){
        Optional<User> user = userDaoService.findById(id);

        //HATEOAS -- potom mas ked urobis request nieco ako links
        EntityModel<Optional<User>> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkTo =
                linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));
        //HATEOAS

        //return user;
        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity createUser(@Valid @RequestBody User user){
        User savedUser = userDaoService.save(user);
        Logger.getLogger(String.valueOf(getClass())).info(savedUser.toString() + " was created");

        //to properly show response 201 and path in the header
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/jpa/users/{id}")
    public void removeUser(@PathVariable Integer id){
        userDaoService.deleteById(id);
    }

    @GetMapping("/jpa/user/{id}/posts")
    public List<Post> getAllPostsForUser(@PathVariable Integer id){
        return userDaoService.findById(id)
                .orElseThrow(RuntimeException::new)
                .getPosts();

    }

    @GetMapping("/jpa/user/{userId}/posts/{id}")
    public Post getSpecificPostForUser(@PathVariable Integer userId, @PathVariable Integer id){
        return userDaoService
                .findById(userId )
                .orElseThrow(RuntimeException::new)
                .getPosts()
                .stream()
                .filter(p -> p.getId().equals(id))
                .reduce( (a,b) -> { throw new IllegalStateException("Multiple elements found" + a + ", " + b); })
                .get();
    }

    @PostMapping("/user/{id}/posts")
    public void createPostForUser(@PathVariable Integer id, @RequestBody Post post){
//        User user = null;
//        if ( userDaoService.findById(id).isPresent())
//             user =  userDaoService.findById(id).get();
//        post.setUser(user);
//        postJpaService.save(post);

        userDaoService.findById(id)
                .ifPresent(u -> {
                    post.setUser(u);
                    postJpaService.save(post); });
    }




}
