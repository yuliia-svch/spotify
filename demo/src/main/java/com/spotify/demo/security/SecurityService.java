package com.spotify.demo.security;

public interface SecurityService {

    String findLoggedInUsername();

    void autologin(String username, String password);
}
