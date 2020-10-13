package com.example.playing_aroung_spring.user.DBrepository;


import com.example.playing_aroung_spring.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface UserJpaService extends JpaRepository<User,Integer> {

}
