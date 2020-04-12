package com.projectpad.server1.service;

import com.projectpad.server1.entity.User;
import com.projectpad.server1.entity.UserToken;
import com.projectpad.server1.exception.UserEmailExistsException;
import com.projectpad.server1.exception.UserNameExistsException;
import com.projectpad.server1.repository.UserRepository;
import com.projectpad.server1.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTokenRepository userTokenRepository;

    public void newUserAccount(String username, String pass, String email) {
        boolean nameExists = userRepository.existsByUserName(username);
        boolean emailExits = userRepository.existsByEmail(email);
        if (!nameExists) {
            if (!emailExits) {
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setUserName(username);
                newUser.setPassword(pass);
                newUser.setRole("user");
                userRepository.save(newUser);
            } else {
                throw new UserEmailExistsException("email exists");
            }
        } else {
            throw new UserNameExistsException("userName exists");
        }
    }

    public User assignToken(String userName, String token) {
        User u = userRepository.findByUserName(userName);
        UserToken t = new UserToken();
        t.setToken(token);
        List<UserToken> ut = u.getTokens();
        ut.add(t);
        return userRepository.save(u);
    }

    public User findByToken(String token) {
        UserToken t = userTokenRepository.findByToken(token);
        return userRepository.findByTokens(t);
    }
}
