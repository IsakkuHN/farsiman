package com.github.isakkuhn.farsimanevaluacion.presentation.controllers;

import com.github.isakkuhn.farsimanevaluacion.business.services.UserService;
import com.github.isakkuhn.farsimanevaluacion.business.utils.JwtTokenUtil;
import com.github.isakkuhn.farsimanevaluacion.persistence.entities.UserEntity;
import com.github.isakkuhn.farsimanevaluacion.persistence.repositories.UserRepository;
import com.github.isakkuhn.farsimanevaluacion.presentation.dto.LoginRequest;
import com.github.isakkuhn.farsimanevaluacion.presentation.dto.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        UserEntity user = userRepository.findByUsername(request.username()).orElse(null);
        if(user != null){
            String token = jwtTokenUtil.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(token));
        }
        return ResponseEntity.badRequest().body(new LoginResponse("Invalid username or password"));

    }
}
