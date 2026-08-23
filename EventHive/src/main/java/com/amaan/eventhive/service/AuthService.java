package com.amaan.eventhive.service;

import com.amaan.eventhive.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.amaan.eventhive.security.JwtUtil;
import com.amaan.eventhive.dto.LoginRequestDTO;
import com.amaan.eventhive.dto.LoginResponseDTO;
import com.amaan.eventhive.dto.UserResponseDTO;
import com.amaan.eventhive.entity.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return null;
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash())) {
            return null;
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponseDTO(token);
    }
    public UserResponseDTO registerUser(String email, String password) {

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(encodedPassword);
        user.setRole("ATTENDEE");

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }
}