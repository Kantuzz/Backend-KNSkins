package com.csgoskins.catalogservice.service;

import com.csgoskins.catalogservice.model.Usuario;
import com.csgoskins.catalogservice.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // ==========================
    // LISTAR USUARIOS
    // ==========================
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    // ==========================
    // REGISTRO
    // ==========================
    public Map<String, Object> registrar(String nombre, String email, String password) {

        Map<String, Object> resp = new HashMap<>();

        if (usuarioRepository.findByEmail(email).isPresent()) {
            resp.put("status", "ERROR");
            resp.put("message", "El correo ya está registrado.");
            return resp;
        }

        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setEmail(email);
        u.setPassword(sha256(password));
        u.setRole("USER");

        usuarioRepository.save(u);

        resp.put("status", "OK");
        resp.put("message", "Usuario registrado exitosamente.");
        return resp;
    }

    // ==========================
    // LOGIN
    // ==========================
    public Map<String, Object> login(String email, String password) {

        return usuarioRepository.findByEmail(email)
                .map(u -> {
                    Map<String, Object> resp = new HashMap<>();

                    if (!u.getPassword().equals(sha256(password))) {
                        resp.put("status", "ERROR");
                        resp.put("message", "Contraseña incorrecta.");
                        return resp;
                    }

                    resp.put("status", "OK");
                    resp.put("id", u.getId());
                    resp.put("email", u.getEmail());
                    resp.put("nombre", u.getNombre());
                    resp.put("role", u.getRole());
                    return resp;
                })
                .orElseGet(() -> {
                    Map<String, Object> resp = new HashMap<>();
                    resp.put("status", "ERROR");
                    resp.put("message", "Usuario no encontrado.");
                    return resp;
                });
    }

    // ==========================
    // HACER ADMIN
    // ==========================
    public Map<String, Object> makeAdmin(Long id) {
        Map<String, Object> res = new HashMap<>();

        Usuario u = usuarioRepository.findById(id).orElse(null);
        if (u == null) {
            res.put("status", "ERROR");
            res.put("message", "Usuario no encontrado.");
            return res;
        }

        u.setRole("ADMIN");
        usuarioRepository.save(u);

        res.put("status", "OK");
        return res;
    }

    // ==========================
    // HASH SHA256
    // ==========================
    private String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
