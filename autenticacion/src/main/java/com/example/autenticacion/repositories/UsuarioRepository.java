package com.example.autenticacion.repositories;

import com.example.autenticacion.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findByEmail(String email);  // Buscamos por el correo electrónico
}
