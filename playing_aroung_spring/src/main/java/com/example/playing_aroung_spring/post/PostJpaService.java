package com.example.playing_aroung_spring.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaService extends JpaRepository<Post,Integer> {
}
