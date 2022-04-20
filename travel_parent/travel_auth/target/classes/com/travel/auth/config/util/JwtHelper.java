package com.travel.auth.config.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @FileName: JwtHelper
 * @Description: 实现Jwt
 */
public class JwtHelper {

    @Value("${jwt.expiration}")
    public long expiration;//token超时时间

    @Value("${jwt.secret}")
    public static String base64Security;

    /**
     * 解析token
     * @param jsonWebToken
     * @return
     */
    public static Claims parseToken(String jsonWebToken) {

        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                .parseClaimsJws(jsonWebToken).getBody();
        return claims;

    }

    /**
     * 新建token

     * @param audience
     * @param issuer

     * @return
     */
    public  String createToken(String audience,
                               String issuer) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // 添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);

        // 添加Token签发时间
        builder.setIssuedAt(now);
        // 添加Token过期时间
        if (expiration >= 0) {
            long expMillis = nowMillis + expiration;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        // 生成JWT
        return builder.compact();
    }

    /**
     * 刷新令牌
     *
     * @param claims
     * @return
     */
    public String refreshToken(Claims claims) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // 添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setIssuer((String) claims.get("iss")).setAudience((String) claims.get("aud"))
                .signWith(signatureAlgorithm, signingKey);

        // 添加Token签发时间
        builder.setIssuedAt(now);
        // 添加Token过期时间
        if (expiration >= 0) {
            long expMillis = nowMillis + expiration;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        // 生成Token
        return builder.compact();
    }
}
