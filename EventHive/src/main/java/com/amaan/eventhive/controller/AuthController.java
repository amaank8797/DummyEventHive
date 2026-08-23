package com.amaan.eventhive.controller;

import com.amaan.eventhive.dto.RegisterRequestDTO;
import com.amaan.eventhive.dto.UserResponseDTO;
import com.amaan.eventhive.service.AuthService;
import com.amaan.eventhive.dto.LoginRequestDTO;
import com.amaan.eventhive.dto.LoginResponseDTO;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody @Valid RegisterRequestDTO request) {

        return authService.registerUser(
                request.getEmail(),
                request.getPassword()
        );
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO request) {

        return authService.login(request);
    }
}