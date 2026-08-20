package com.sjs.backend.controller;

import com.sjs.backend.model.Role;
import com.sjs.backend.model.Usuario;
import com.sjs.backend.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<Usuario> alterarRole(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Usuario usuario = repository.findById(id).orElseThrow();
        usuario.setRole(Role.valueOf(body.get("role")));
        return ResponseEntity.ok(repository.save(usuario));
    }
}
