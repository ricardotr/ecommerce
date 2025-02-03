package com.demo.ecommerce.security.controller;

import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecommerce.security.CustomUserDetails;
import com.demo.ecommerce.security.dto.LoginRequest;
import com.demo.ecommerce.security.jwt.JwtUtil;

@RestController
@RequestMapping("/auth/v1")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthController(@Lazy AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public Map<String, String> authenticate(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String jwt = jwtUtil.generateToken((CustomUserDetails) userDetails);

        return Map.of("token", jwt);
    }
}
