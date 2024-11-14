package com.example.autenticacion.controllers;

import com.example.autenticacion.models.Usuario;
import com.example.autenticacion.services.UsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioService.obtenerTodosUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable String id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/registrar")
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }

    // Endpoint para autenticar un usuario
    @PostMapping("/login")
    public Usuario autenticarUsuario(@RequestParam String email, @RequestParam String password) {
        return usuarioService.autenticarUsuario(email, password);
    }
}

