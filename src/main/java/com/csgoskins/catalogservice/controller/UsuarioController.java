package com.csgoskins.catalogservice.controller;

import com.csgoskins.catalogservice.dto.UsuarioLoginDto;
import com.csgoskins.catalogservice.dto.UsuarioRegistroDto;
import com.csgoskins.catalogservice.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/registro")
    public String registrar(@RequestBody UsuarioRegistroDto dto) {
        return service.registrar(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioLoginDto dto) {
        return service.login(dto);
    }
}
