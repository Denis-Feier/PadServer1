package com.projectpad.server1.controller;

import com.projectpad.server1.entity.AuthRequest;
import com.projectpad.server1.entity.User;
import com.projectpad.server1.entity.UserPublic;
import com.projectpad.server1.exception.UserEmailExistsException;
import com.projectpad.server1.exception.UserNameExistsException;
import com.projectpad.server1.repository.UserRepository;
import com.projectpad.server1.service.UserService;
import com.projectpad.server1.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/home")
    public String welcome() {
        logger.info("Home request");
        return "Welcome to this private page !!";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        logger.info("Get all users request");
        return userRepository.findAll();
    }

    @GetMapping("/user/{name}")
    public User getUser(@PathVariable("name") String name) {
        logger.info("Get user by name: {} request", name);
        return userRepository.findByUserName(name).orElseThrow(() -> {
            logger.error("User with name: {} not found", name);
            return new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("User with name: %s not found", name));
        });
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable("email") String email) {
        logger.info("Get user by email: {} request", email);
        return userRepository.findByEmail(email).orElseThrow(() -> {
            logger.error("User with email: {} not found", email);
            return new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("User with email: %s not found", email));
        });
    }

    @PostMapping("/create/account")
    public UserPublic createUserAccount(@RequestBody User user) {
        logger.info("Create new account request {}", user.toString());
        try {
            userService.newUserAccount(
                    user.getUserName(),
                    passwordEncoder.encode(user.getPassword()),
                    user.getEmail()
            );
        } catch (UserEmailExistsException ex) {
            logger.error("User email {} found", user.getEmail());
            throw new ResponseStatusException(HttpStatus.FOUND, "User email found", ex);
        } catch (UserNameExistsException nx) {
            logger.error("User name {} found", user.getUserName());
            throw new ResponseStatusException(HttpStatus.FOUND, "User name found", nx);
        }
       return userService.getUserPublicByUserName(user.getUserName());
    }

    @PostMapping("/authenticate")
    public UserPublic generateToken(@RequestBody AuthRequest authRequest) {
        logger.info("Authenticate request");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(),
                            authRequest.getPassword())
            );
        } catch (Exception ex) {
            logger.error("Invalid username/password");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "invalid username/password", ex);
        }
        String token = jwtUtil.generateToken(authRequest.getUserName());
        userService.assignToken(authRequest.getUserName(), token);
        User userDb = userService.findByToken(token);
        return new UserPublic(
                userDb.getId(),
                userDb.getUserName(),
                userDb.getEmail(),
                userDb.getRole(),
                token,
                userDb.getPic());
    }

    @GetMapping("/user/token")
    public UserPublic findByToken(@RequestHeader(value = "Authorization") String s) {
        logger.info("Get user by token request");
        String t = s.substring(7);
        User userDb = userService.findByToken(t);
        return new UserPublic(
                userDb.getId(),
                userDb.getUserName(),
                userDb.getEmail(),
                userDb.getRole(),
                t,
                userDb.getPic());
    }
}
