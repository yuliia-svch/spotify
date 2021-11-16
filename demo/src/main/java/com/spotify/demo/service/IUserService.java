package com.spotify.demo.service;

import com.spotify.demo.dto.UserDTO;
import com.spotify.demo.model.User;

import java.util.Optional;

public interface IUserService {

    User registerNewUserAccount(UserDTO accountDto);

    User getUser(String username);

    void saveRegisteredUser(User user);

    Optional<User> getUserByID(long id);
}
