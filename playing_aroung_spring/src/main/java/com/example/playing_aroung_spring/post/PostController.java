package com.example.playing_aroung_spring.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
//    private final PostDaoService postDaoService;
//
//    @Autowired
//    public PostController(PostDaoService postDaoService) {
//        this.postDaoService = postDaoService;
//    }
//
//    @GetMapping("users/posts")
//    public List<Post> getAllPosts(){
//        return postDaoService.findAll();
//    }
//
//
//    @GetMapping("/user/{id}/posts")
//    public List<Post> getAllPostsForUser(@PathVariable Integer id){
//        return postDaoService.findAllForOneUser(id);
//    }
//
//    @GetMapping("/user/{userId}/posts/{id}")
//    public Post getSpecificPostForUser(@PathVariable Integer userId, @PathVariable Integer id){
//        return postDaoService.findOneForOneUser(userId, id);
//    }
//
//    @PostMapping("/user/{id}/posts")
//    public void createPostForUser(@PathVariable Integer id, @RequestBody Post post){
//        post.setUserId(id);
//        postDaoService.save(post);
//    }



}
