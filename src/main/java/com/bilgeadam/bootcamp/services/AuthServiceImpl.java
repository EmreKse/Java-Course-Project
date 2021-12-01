package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.core.exceptions.BootCampException;
import com.bilgeadam.bootcamp.models.EnumRole;
import com.bilgeadam.bootcamp.models.Role;
import com.bilgeadam.bootcamp.models.User;
import com.bilgeadam.bootcamp.payload.request.LoginRequest;
import com.bilgeadam.bootcamp.payload.request.SignupRequest;
import com.bilgeadam.bootcamp.payload.response.JwtResponse;
import com.bilgeadam.bootcamp.repository.RoleRepository;
import com.bilgeadam.bootcamp.repository.UserRepository;
import com.bilgeadam.bootcamp.security.jwt.JwtUtils;
import com.bilgeadam.bootcamp.security.services.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService{

    final AuthenticationManager authenticationManager;

    final UserRepository userRepository;

    final RoleRepository roleRepository;

    final JwtUtils jwtUtils;

    final PasswordEncoder encoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, JwtUtils jwtUtils, PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public void registerUser(SignupRequest signUpRequest) throws BootCampException {
        if (userRepository.existsByUsername(signUpRequest.getUsername()) ||
                userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BootCampException("Username is already taken!");
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                Role adminRole = roleRepository.findByName(EnumRole.getRole(role))
                        .orElseThrow(() -> new RuntimeException("Role is not found."));
                roles.add(adminRole);
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
    }
}
