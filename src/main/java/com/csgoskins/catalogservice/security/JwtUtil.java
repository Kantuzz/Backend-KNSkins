package com.csgoskins.catalogservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.csgoskins.catalogservice.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final long expirationMs;

    public JwtUtil(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration-ms}") long expirationMs
    ) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm).build();
        this.expirationMs = expirationMs;
    }

    public String generateToken(Usuario user) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("id", user.getId())
                .withClaim("nombre", user.getNombre())
                .withClaim("role", user.getRole())
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(algorithm);
    }

    /**
     * Valida el token y retorna el JWT decodificado.
     * Lanza excepción si es inválido o expirado.
     */
    public DecodedJWT validateToken(String token) {
        return verifier.verify(token);
    }

    public long getExpirationMs() {
        return expirationMs;
    }
}
