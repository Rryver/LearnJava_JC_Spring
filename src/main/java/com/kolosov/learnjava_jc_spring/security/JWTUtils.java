package com.kolosov.learnjava_jc_spring.security;

import com.kolosov.learnjava_jc_spring.dto.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTUtils {

    // Ключ шифрования для JWT
    private SecretKey secretKey;

    // Время действия токена в миллисекундах (24 часа)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public JWTUtils() {
        // Строка, используемая для создания секретного ключа
        String secretString = "5JzoMbk6E5qIqHSuBTgeQCARtUsxAkBiHwdjXOSW8kWdXzYmP3X51C0";
        byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
        this.secretKey = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * Метод для генерации JWT токена на основе данных пользователя
     */
    public TokenResponse generateToken(UserDetails userDetails){
        Date expiredAtDate = Date.from(Instant.now().plusMillis(EXPIRATION_TIME));
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(expiredAtDate)
                .signWith(secretKey)
                .compact();

        return new TokenResponse(token, expiredAtDate.toInstant().getEpochSecond());
    }

    // Метод для генерации токена обновления (refresh token) с дополнительными данными
//    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails){
//
//    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // Метод для извлечения имени пользователя из токена
    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build().parseClaimsJws(token);

        return claimsTFunction.apply(claimsJws.getBody());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
