package com.projectpad.server1.service;

import com.projectpad.server1.entity.User;
import com.projectpad.server1.entity.UserPublic;
import com.projectpad.server1.entity.UserToken;
import com.projectpad.server1.exception.UserEmailExistsException;
import com.projectpad.server1.exception.UserNameExistsException;
import com.projectpad.server1.exception.UserNotFound;
import com.projectpad.server1.exception.UserTokenNotFound;
import com.projectpad.server1.repository.UserRepository;
import com.projectpad.server1.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        User u = userRepository.findByUserName(userName).get();
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

    public UserPublic getUserPublicByUserName(String name) {
        User u = userRepository.findByUserName(name)
                .orElseThrow(() -> new UserNotFound("No user with this name!"));
        return new UserPublic(
                u.getId(),
                u.getUserName(),
                u.getEmail(),
                u.getRole(),
                null,
                u.getPic()
        );
    }

    public void deleteUserToken(String token) {
        UserToken userToken = userTokenRepository.findByToken(token);
        try {
            userTokenRepository.deleteByTid(userToken.getTid());
        } catch (Exception e) {
            throw new UserTokenNotFound(String.format("Token %s not found", token));
        }
    }

    public void unauthorizedUser(String token) {
        if (!userTokenRepository.existsByToken(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid auth token");
        }
    }
}
