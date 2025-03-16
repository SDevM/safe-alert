package com.sdevm.safe_alert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdevm.safe_alert.models.Login;
import com.sdevm.safe_alert.models.ResponseResult;
import com.sdevm.safe_alert.models.User;
import com.sdevm.safe_alert.models.UserRepository;
import com.sdevm.safe_alert.services.ActiveUserRepo;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActiveUserRepo activeUserRepo;

    @PostMapping("/auth")
    public ResponseResult login(@RequestBody Login login, HttpSession session) {
        User user = userRepository.findByIdentifier(login.getIdentifier());
        if (BCrypt.checkpw(login.getPassword(), user.getPassword())) {
            activeUserRepo.addUser(session.getId(), user);
            return new ResponseResult(true, "Login successful");
        } else {
            return new ResponseResult(false, "Invalid credentials");
        }
    }

    @GetMapping("/auth")
    public ResponseResult checkAuth(HttpSession session) {
        if (activeUserRepo.getUser(session.getId()) != null) {
            return new ResponseResult(true, "User is authenticated");
        } else {
            return new ResponseResult(false, "User is not authenticated");
        }
    }

    @DeleteMapping("/auth")
    public ResponseResult logout(HttpSession session) {
        activeUserRepo.removeUser(session.getId());
        return new ResponseResult(true, "Logout successful");
    }
}
