package com.afz.lapstore.controller;

import com.afz.lapstore.dto.*;
import com.afz.lapstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRequestDTO requestDTO) {

        return userService.register(requestDTO);
    }

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody UserRequestDTO requestDTO) {

        return userService.login(requestDTO);
    }
}
