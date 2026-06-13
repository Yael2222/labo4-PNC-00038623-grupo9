package com.server.app.services;

import com.server.app.config.JsonWebToken;
import com.server.app.dto.auth.LoginDto;
import com.server.app.dto.auth.LoginResponseDto;
import com.server.app.dto.auth.RegisterDto;
import com.server.app.entities.Role;
import com.server.app.entities.User;
import com.server.app.exceptions.UnauthorizedException;
import com.server.app.repositories.RoleRepository;
import com.server.app.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JsonWebToken jwt;

    public LoginResponseDto login(LoginDto dto){

        System.out.println("USERNAME RECIBIDO: " + dto.getUsername());

        User user = userRepository.findUserByUsername(dto.getUsername())
                .orElseThrow(() ->
                        new UnauthorizedException("Credenciales inválidas"));

        System.out.println("USUARIO ENCONTRADO: " + user.getUsername());

        System.out.println("PASSWORD ENVIADO: " + dto.getPassword());
        System.out.println("PASSWORD DB: " + user.getPassword());

        boolean match = encoder.matches(dto.getPassword(), user.getPassword());

        System.out.println("MATCH: " + match);

        if(!match){
            throw new UnauthorizedException("Credenciales inválidas");
        }

        String token = jwt.createToken(user);

        System.out.println("TOKEN GENERADO: " + token);

        return new LoginResponseDto(
                token,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().getName()
        );

    }

    public User register(RegisterDto dto){

        if(userRepository.existsByUsername(dto.getUsername())){
            throw new RuntimeException("Username ya existe");
        }

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email ya existe");
        }

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() ->
                        new RuntimeException("Rol USER no existe"));

        User user = User.builder()
                .username(dto.getUsername())
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .role(role)
                .build();

        return userRepository.save(user);
    }
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)  // ✅ Cambiado de findByEmail a findUserByEmail
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

}
