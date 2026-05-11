package br.com.isiflix.salutar.security;

import br.com.isiflix.salutar.model.Usuario;
import br.com.isiflix.salutar.security.config.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Component
public class TokenUtil {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtProperties properties;
    private final SecretKey signingKey;
    private final String headerPrefix;

    public TokenUtil (JwtProperties properties){
        this.properties = properties;
        this.signingKey = Keys.hmacShaKeyFor(properties.secret().getBytes(StandardCharsets.UTF_8));
        this.headerPrefix = properties.prefix().endsWith(" ") ? properties.prefix() : properties.prefix() + " ";
    }

    public SalutarToken encode(Usuario usuario) {
        Date agora = new Date();
        Date expiraEm = new Date(agora.getTime() + properties.expiration().toMillis());
        String jws = Jwts.builder()
                .subject(usuario.getLogin())
                .issuer(properties.issuer())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiraEm)
                .signWith(signingKey, Jwts.SIG.HS256)
                .compact();
        return new SalutarToken(headerPrefix + jws);
    }

    public Authentication decode(HttpServletRequest request) throws ServletException, IOException {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        if (header == null || !header.startsWith(headerPrefix)) {
            return null;
        }

        String token = header.substring(headerPrefix.length());

        try {
            Claims claims = Jwts.parser()
                    .verifyWith(signingKey)
                    .requireIssuer(properties.issuer())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String subject = claims.getSubject();
            if (subject == null || subject.isBlank()) {
                return null;
            }

            return new UsernamePasswordAuthenticationToken(subject, null, Collections.emptyList());
        } catch (JwtException ex) {
            return null;
        }
    }
}
