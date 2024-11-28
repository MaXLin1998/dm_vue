package jp.co.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jp.co.demo.model.LoginForm;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static final String SECRET_KEY = "4261656C64756E67AKKKKKKKKKKKKKKKKKGUGGdfsgfudsgfgsdgfsdfdsiuhfiuhds";

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(LoginForm form) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", form.getEmail());
        claims.put("userPsw", form.getPassword());
        return createToken(claims);
    }

    private String createToken(Map<String, Object> claims) {
        System.out.println("Claims inside createToken "+claims);
        return Jwts.builder().claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(this.getSigningKey()).compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
