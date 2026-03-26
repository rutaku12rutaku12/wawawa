package web.global.jwt;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "my-secret-key";
    private final long EXP = 1000 * 60 * 60; // 1시간

    // 🔥 토큰 생성 (userId 기반)
    public String createToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXP))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    // 🔥 userId 추출
    public Long getUserId(String token) {
        return Long.parseLong(
                Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject()
        );
    }

    // 🔥 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}