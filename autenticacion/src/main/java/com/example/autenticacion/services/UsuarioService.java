package com.example.autenticacion.services;

import com.example.autenticacion.models.Usuario;
import com.example.autenticacion.repositories.UsuarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(String id) {
        return usuarioRepository.findById(id).orElse(null); // Devuelve null si no se encuentra
    }
    

    // Método para registrar un nuevo usuario
    public Usuario registrarUsuario(Usuario usuario) {
        // Puedes agregar validaciones de entrada si lo deseas (como verificar si el correo ya existe)
        return usuarioRepository.save(usuario);
    }

    // Método para autenticar un usuario con su correo electrónico y contraseña
    public Usuario autenticarUsuario(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario; // Devuelve el usuario si la autenticación es correcta
        }
        return null; // Devuelve null si la autenticación falla
    }
}
