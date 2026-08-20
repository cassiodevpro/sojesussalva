package com.sjs.backend.controller;

import com.sjs.backend.dto.AuthResponse;
import com.sjs.backend.dto.LoginRequest;
import com.sjs.backend.dto.RegisterRequest;
import com.sjs.backend.model.Role;
import com.sjs.backend.model.Usuario;
import com.sjs.backend.repository.UsuarioRepository;
import com.sjs.backend.security.JwtService;
import com.sjs.backend.security.UsuarioDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsuarioDetailsService usuarioDetailsService;
    private final JwtService jwtService;

    public AuthController(
            UsuarioRepository repository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            UsuarioDetailsService usuarioDetailsService,
            JwtService jwtService
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.usuarioDetailsService = usuarioDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setTelefone(request.getTelefone());
        usuario.setRole(Role.USER);
        repository.save(usuario);

        UserDetails userDetails = usuarioDetailsService.loadUserByUsername(usuario.getEmail());
        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(token, usuario.getNome(), usuario.getEmail(), usuario.getRole()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha inválidos.");
        }

        Usuario usuario = repository.findByEmail(request.getEmail()).orElseThrow();
        UserDetails userDetails = usuarioDetailsService.loadUserByUsername(usuario.getEmail());
        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token, usuario.getNome(), usuario.getEmail(), usuario.getRole()));
    }
}
