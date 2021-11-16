package com.spotify.demo.repository;

import com.spotify.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
