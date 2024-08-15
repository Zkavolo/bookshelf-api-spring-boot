package API.Bookshelf.service.impl;

import API.Bookshelf.exception.InvalidRequestException;
import API.Bookshelf.model.UserEntity;
import API.Bookshelf.repository.UserRepository;
import API.Bookshelf.security.JwtService;
import API.Bookshelf.service.UserService;
import API.Bookshelf.util.DTO.user.LoginRequestDTO;
import API.Bookshelf.util.DTO.user.LoginResponseDTO;
import API.Bookshelf.util.DTO.user.RegisterRequestDTO;
import API.Bookshelf.util.DTO.user.RegisterResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.sql.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public RegisterResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new InvalidRequestException("This email already has an account!");
        }

        if (userRepository.findByName(request.getName()).isPresent()) {
            throw new InvalidRequestException("This name has already been taken!");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        var user = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(new Date(System.currentTimeMillis()))
                .updatedAt(new Date(System.currentTimeMillis()))
                .build();

        userRepository.insertTo(user);

        RegisterResponseDTO response = RegisterResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();

        return response;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidRequestException("This Account is not registered!"));

        var jwtToken = jwtService.generateToken(user);

        LoginResponseDTO response = LoginResponseDTO.builder()
                .token(jwtToken)
                .build();

        return response;
    }
}
