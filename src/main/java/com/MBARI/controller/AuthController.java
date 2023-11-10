package com.MBARI.controller;

import com.MBARI.dto.*;
import com.MBARI.entity.RoleEntity;
import com.MBARI.entity.UserEntity;
import com.MBARI.repository.RoleRepository;
import com.MBARI.repository.UserRepository;
import com.MBARI.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1.1/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    /**
     * Register new user.
     * If the username already exists, return error message.
     */
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        RoleEntity role = roleRepository.findByRoleName(registerDto.getRoleName());
        user.setRole(role);

        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmailAddress(registerDto.getEmailAddress());

        userRepository.save(user);

        return new ResponseEntity<>("User registered success", HttpStatus.OK);
    }

    /**
     * Login.
     * If the username and password are correct, set Tokens in cookie.
     */
    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        TokenDto tokens = jwtGenerator.generateToken(authentication);

        Cookie accessTokenCookie = new Cookie("accessToken", tokens.getAccessToken());
        accessTokenCookie.setHttpOnly(true);
        //accessTokenCookie.setSecure(true); // HTTPS
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = new Cookie("refreshToken", tokens.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true);
        //refreshTokenCookie.setSecure(true); // HTTPS
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            String role = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", "));

            LoginResponse loginResponse = new LoginResponse();
            System.out.println(username + " "  + role);
            loginResponse.setUsername(username);
            loginResponse.setRole(role);

            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/logout")
    public ResponseEntity<LoginResponse> logout(HttpServletResponse response) {
        // 既存のトークンをクリアするために新しいCookieを作成
        Cookie accessTokenCookie = new Cookie("accessToken", null);
        accessTokenCookie.setMaxAge(0); // 有効期限を0に設定
        //accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = new Cookie("refreshToken", null);
        refreshTokenCookie.setMaxAge(0); // 有効期限を0に設定
        //refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<TokenDto> refreshAccessToken(HttpServletRequest request) {
        String refreshToken = request.getHeader("Refresh-Token");
        if (refreshToken != null && jwtGenerator.validateToken(refreshToken)) {
            String username = jwtGenerator.getUsernameFromJWT(refreshToken);
            // Assuming we have a method to authenticate by username without password
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, null);
            // Assume that we set the authentication in the context here to use it in generateToken
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            TokenDto newTokens = jwtGenerator.generateToken(authenticationToken);
            return ResponseEntity.ok(newTokens);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
