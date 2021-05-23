package com.construction.controllers;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.construction.payload.request.LoginRequest;
import com.construction.payload.request.SignupRequest;
import com.construction.repository.LocationsRepository;
import com.construction.repository.RoleRepository;
import com.construction.repository.UserRepository;
import com.construction.responses.GlobalResponseListData;
import com.construction.security.jwt.JwtUtils;
import com.construction.service.AuthService;



@CrossOrigin(origins = "*")
@RestController
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	LocationsRepository locationRepository;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthService authService;
	
	GlobalResponseListData globalResponseListData;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		return authService.authenticateUser(loginRequest);
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return authService.registerUser(signUpRequest);
	}
}