package com.cosmoIntl.LiquibaseTest1.controller;


import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.AuthRequestDto;
import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.RefreshTokenRequestDto;
import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.RoleRequest;
import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.UserRequest;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.JwtResponseDto;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.UserResponse;
import com.cosmoIntl.LiquibaseTest1.entity.RefreshToken;
import com.cosmoIntl.LiquibaseTest1.entity.UserRole;
import com.cosmoIntl.LiquibaseTest1.service.JwtService;
import com.cosmoIntl.LiquibaseTest1.service.RefreshTokenService;
import com.cosmoIntl.LiquibaseTest1.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest) {
        try {
            UserResponse userResponse = userService.saveUser(userRequest);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            throw new RuntimeException("Error saving user: " + e.getMessage(), e);
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<JwtResponseDto> authenticateAndGetToken(@Valid @RequestBody AuthRequestDto authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );

        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            JwtResponseDto jwtResponse = JwtResponseDto.builder()
                    .accessToken(jwtService.generateToken(authRequestDTO.getUsername()))
                    .token(refreshToken.getToken())
                    .build();
            return ResponseEntity.ok(jwtResponse);
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @PostMapping("/refreshToken")
    @ResponseBody
    public ResponseEntity<JwtResponseDto> refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDTO) {
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo.getUsername());
                    JwtResponseDto jwtResponse = JwtResponseDto.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequestDTO.getToken())
                            .build();
                    return ResponseEntity.ok(jwtResponse);
                })
                .orElseThrow(() -> new RuntimeException("Invalid or expired refresh token"));
    }

    @GetMapping("/getall")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> userResponses = userService.getAllUser();
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/retrieve/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }


    @DeleteMapping("/delete/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully with ID: " + id);
    }

    @PostMapping("/role/add")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserRole> addRole(@RequestBody RoleRequest roleRequest) {
        try {
            UserRole role = userService.addRole(roleRequest);
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @DeleteMapping("/roles/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        try {
            userService.deleteRoleById(id);
            return ResponseEntity.ok("Role deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
