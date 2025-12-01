package com.csgoskins.catalogservice.service;

import com.csgoskins.catalogservice.dto.UsuarioLoginDto;
import com.csgoskins.catalogservice.dto.UsuarioRegistroDto;
import com.csgoskins.catalogservice.model.Usuario;
import com.csgoskins.catalogservice.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public String registrar(UsuarioRegistroDto dto) {
        if (repo.findByEmail(dto.getEmail()).isPresent()) {
            return "El correo ya está registrado.";
        }

        Usuario u = new Usuario();
        u.setNombre(dto.getNombre());
        u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword()); // si después quieres, lo hashamos

        repo.save(u);

        return "Usuario registrado con éxito.";
    }

    public String login(UsuarioLoginDto dto) {
        return repo.findByEmail(dto.getEmail())
                .map(u -> {
                    if (u.getPassword().equals(dto.getPassword())) {
                        return "Login correcto.";
                    } else {
                        return "Contraseña incorrecta.";
                    }
                })
                .orElse("Usuario no encontrado.");
    }
}
