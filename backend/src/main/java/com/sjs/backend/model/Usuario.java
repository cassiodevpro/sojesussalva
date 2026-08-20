package com.sjs.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
       @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    @JsonIgnore
    private String senha;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public Usuario() {
    }

    public Usuario(String email, Long id, String nome, String senha, String telefone) {
        this.email = email;
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

}
