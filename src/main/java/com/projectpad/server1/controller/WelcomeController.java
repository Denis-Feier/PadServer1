package com.projectpad.server1.controller;

import com.projectpad.server1.entity.AuthRequest;
import com.projectpad.server1.entity.User;
import com.projectpad.server1.exception.UserCredentialsInvalidException;
import com.projectpad.server1.exception.UserEmailExistsException;
import com.projectpad.server1.exception.UserNameExistsException;
import com.projectpad.server1.repository.UserRepository;
import com.projectpad.server1.service.UserService;
import com.projectpad.server1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to this private page !!";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{name}")
    public User getUser(@PathVariable("name") String name) {
        User u = userRepository.findByUserName(name);
        System.out.println(u);
        return u;
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable("email") String email) {
        User u = userRepository.findByEmail(email);
        System.out.println(u);
        return u;
    }

    @PostMapping("/create/account")
    public void createUserAccount(@RequestBody User user) {
        try {
            userService.newUserAccount(
                    user.getUserName(),
                    user.getPassword(),
                    user.getEmail()
            );
        } catch (UserEmailExistsException ex) {
            throw new ResponseStatusException(HttpStatus.FOUND, "User email found", ex);
        } catch (UserNameExistsException nx) {
            throw new ResponseStatusException(HttpStatus.FOUND, "User name found", nx);
        }
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "invalid username/password", ex);
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
