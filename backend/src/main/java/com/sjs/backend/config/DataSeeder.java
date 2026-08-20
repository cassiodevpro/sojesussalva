package com.sjs.backend.config;

import com.sjs.backend.model.Role;
import com.sjs.backend.model.Usuario;
import com.sjs.backend.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final String adminNome;
    private final String adminEmail;
    private final String adminSenha;

    public DataSeeder(
            UsuarioRepository repository,
            PasswordEncoder passwordEncoder,
            @Value("${app.admin.nome}") String adminNome,
            @Value("${app.admin.email}") String adminEmail,
            @Value("${app.admin.senha}") String adminSenha
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.adminNome = adminNome;
        this.adminEmail = adminEmail;
        this.adminSenha = adminSenha;
    }

    @Override
    public void run(String... args) {
        boolean semAdmin = repository.findAll().stream().noneMatch(u -> u.getRole() == Role.ADMIN);
        if (!semAdmin) {
            return;
        }

        Usuario admin = new Usuario();
        admin.setNome(adminNome);
        admin.setEmail(adminEmail);
        admin.setSenha(passwordEncoder.encode(adminSenha));
        admin.setRole(Role.ADMIN);
        repository.save(admin);

        log.info("Admin padrão criado: {} (troque a senha em produção via ADMIN_SENHA)", adminEmail);
    }
}
