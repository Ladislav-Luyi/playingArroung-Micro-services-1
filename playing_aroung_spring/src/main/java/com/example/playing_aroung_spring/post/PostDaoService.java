package com.example.playing_aroung_spring.post;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDaoService {
//
//    private static List<Post> postList = new ArrayList<>();
//
//    static {
//        postList.add(new Post(1,1,"Ahoj, ako sa mas"));
//        postList.add(new Post(2,1,"heej, odpovedaj!!!"));
//        postList.add(new Post(1,4,"helee!"));
//    }
//
//    public List<Post> findAll(){
//        return postList;
//    }
//
//    public Post save(Post post){
//        if(post.getId() == null)
//            post.setId(postList.size() + 1);
//        postList.add(post);
//
//        return post;
//    }
//
//    public List<Post> findAllForOneUser(int userId){
//        return postList.stream().filter(p -> p.getUserId() == userId)
//                .collect(Collectors.toList());
//    }
//
//    public Post findOneForOneUser(int userId, int id){
//        return postList.stream()
//                .filter(p -> p.getUserId() == userId)
//                .filter(p -> p.getId() == id)
//                .reduce( (a,b) -> { throw new IllegalStateException("Multiple elements found" + a + ", " + b); })
//                .get();
//    }
}
