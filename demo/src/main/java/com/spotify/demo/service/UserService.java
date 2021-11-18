package com.spotify.demo.service;

import com.spotify.demo.dto.UserDTO;
import com.spotify.demo.model.Privilege;
import com.spotify.demo.model.Role;
import com.spotify.demo.model.User;
import com.spotify.demo.repository.PlaylistRepository;
import com.spotify.demo.repository.PrivilegeRepository;
import com.spotify.demo.repository.RoleRepository;
import com.spotify.demo.repository.UserRepository;
import com.spotify.demo.web.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUserAccount(UserDTO accountDto) {
        if (usernameExists(accountDto.getUsername())) {
            throw new UserAlreadyExistsException("There is an account with that email address: " + accountDto.getUsername());
        }
        final User user = new User();

        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEnabled(true);
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        if(userRepository.findByUsername(username) != null) {
            return userRepository.findByUsername(username);
        }
        return null;
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByID(long id) {
        return userRepository.findById(id);
    }

    private boolean usernameExists(final String email) {
        return userRepository.findByUsername(email) != null;
    }
}
