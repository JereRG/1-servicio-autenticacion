package com.example.autenticacion.controllers;

import com.example.autenticacion.models.Usuario;
import com.example.autenticacion.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

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
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        // Registrar al usuario
        Usuario usuarioRegistrado = usuarioService.registrarUsuario(usuario);

        // Devolver un objeto JSON con el id del usuario
        return ResponseEntity.ok().body(Collections.singletonMap("id", usuarioRegistrado.getId()));
    }

    // Endpoint para autenticar un usuario
    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody Usuario credenciales) {
        // Autenticar al usuario utilizando las credenciales
        Usuario usuarioAutenticado = usuarioService.autenticarUsuario(credenciales.getEmail(), credenciales.getPassword());

        // Verificar si el usuario fue encontrado y la autenticación fue exitosa
        if (usuarioAutenticado == null) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        // Si el usuario se autentica correctamente, devolvemos el id
        return ResponseEntity.ok(usuarioAutenticado.getId()); // Solo el id del usuario
    }
}
