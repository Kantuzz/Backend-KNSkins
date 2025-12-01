package com.csgoskins.catalogservice.controller;

import com.csgoskins.catalogservice.service.UsuarioService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // =====================
    // REGISTRO
    // =====================
    @PostMapping("/registro")
    public Map<String, Object> registro(@RequestBody RegistroDTO dto) {
        return usuarioService.registrar(dto.getNombre(), dto.getEmail(), dto.getPassword());
    }

    // =====================
    // LOGIN
    // =====================
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginDTO dto) {
        return usuarioService.login(dto.getEmail(), dto.getPassword());
    }

    // =====================
    // DTOs
    // =====================
    @Data
    public static class RegistroDTO {
        private String nombre;
        private String email;
        private String password;
    }

    @Data
    public static class LoginDTO {
        private String email;
        private String password;
    }
}
