package com.example.playing_aroung_spring.user.hashRepository;

import com.example.playing_aroung_spring.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> userList = new ArrayList<>();

    private static int userCount = 4;

    static {
        userList.add(new User(1,"Adam", new Date()));
        userList.add(new User(2,"Peto", new Date()));
        userList.add(new User(3,"Katka", new Date()));
        userList.add(new User(4,"Dodo", new Date()));
    }

    public List<User> findAll(){
        return userList;
    }

    public User save(User user){
        if(user.getId() == null)
            user.setId(++userCount);
        userList.add(user);

        return user;
    }

    public User findOne(int id){
        return userList.stream().filter(u -> u.getId() == id)
                .reduce( (a,b) -> { throw new IllegalStateException("Multiple elements found" + a + ", " + b); })
                .get();
    }

    public void remove(int id){
        userList.removeIf(u -> u.getId().equals(id));
    }


}
