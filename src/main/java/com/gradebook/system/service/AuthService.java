package com.gradebook.system.service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gradebook.system.DTO.AuthRequest;
import com.gradebook.system.DTO.AuthResponse;
import com.gradebook.system.DTO.RegisterRequest;
import com.gradebook.system.model.Teacher;
import com.gradebook.system.repository.TeacherRepository;
import com.gradebook.system.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtTokenProvider;

    public ResponseEntity<String> register(RegisterRequest request) {
        Teacher teacher = new Teacher();
        teacher.setName(request.getName());
        teacher.setEmail(request.getEmail());
        teacher.setPassword(passwordEncoder.encode(request.getPassword()));
  
        teacherRepository.save(teacher);

        return ResponseEntity.ok( "Registration successful");
    }

    public AuthResponse login(AuthRequest request) {
    	authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
    	 Teacher teacher = teacherRepository.findByEmail(request.getEmail())
                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtTokenProvider.generateToken(teacher);
        return new AuthResponse(token, "Login successful");
    }
}