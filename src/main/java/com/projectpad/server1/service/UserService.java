package com.projectpad.server1.service;

import com.projectpad.server1.entity.User;
import com.projectpad.server1.exception.UserEmailExistsException;
import com.projectpad.server1.exception.UserNameExistsException;
import com.projectpad.server1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void newUserAccount(String username, String pass, String email) {
        boolean nameExists = userRepository.existsByUserName(username);
        boolean emailExits = userRepository.existsByEmail(email);
        if (!nameExists) {
            if (!emailExits) {
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setUserName(username);
                newUser.setPassword(pass);
                userRepository.save(newUser);
            } else {
                throw new UserEmailExistsException("email exists");
            }
        } else {
            throw new UserNameExistsException("userName exists");
        }
    }
}
