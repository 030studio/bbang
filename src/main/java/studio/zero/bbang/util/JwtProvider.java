package studio.zero.bbang.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import studio.zero.bbang.dto.JwtDTO;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.key}")
    private String jwtKey;
    private static final long accessExpiration = 1000 * 60 * 60;
    private static final long refreshExpiration = 1000 * 60 * 60;
    private static final String accessTokenSub = "login access jwt";
    private static final String refreshTokenSub = "login refresh jwt";

    public String generateAccessToken(String phoneNumber) {
        return JWT.create()
                .withSubject(accessTokenSub)
                .withClaim("phoneNumber", phoneNumber)
                .withExpiresAt(new Date(System.currentTimeMillis() + accessExpiration))
                .sign(Algorithm.HMAC512(jwtKey));
    }

    public String generateRefreshToken() {
        return JWT.create()
                .withSubject(refreshTokenSub)
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshExpiration))
                .sign(Algorithm.HMAC512(jwtKey));
    }

    public JwtDTO generateToken(String phoneNumber) {
        String accessToken = generateAccessToken(phoneNumber);
        String refreshToken = generateRefreshToken();
        return new JwtDTO(accessToken, refreshToken);
    }
}
