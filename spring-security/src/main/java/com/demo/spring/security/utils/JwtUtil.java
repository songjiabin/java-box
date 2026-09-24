package com.demo.spring.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtException;

import java.nio.charset.StandardCharsets;
import java.util.Date;

// JWT生成工具
public class JwtUtil {

    // JWT 签名密钥；正式项目应从配置中心或环境变量读取，不能直接使用示例密钥。
    private static final String SECRET_KEY = "java-box-spring-security-demo-secret-key-2026";

    // JWT 有效期，当前设置为 30 分钟。
    private static final long EXPIRATION_MILLIS = 30 * 60 * 1000L;

    /**
     * 根据用户 ID 创建 JWT，供前端后续请求携带。
     *
     * @param userId 用户唯一标识，会写入 JWT 的 subject 字段
     * @return 签名后的 JWT 字符串
     */
    public static String createJWT(String userId) {
        // 记录 Token 创建时间，用于判断签发时间和有效期。
        Date issuedAt = new Date();
        // 计算 Token 失效时间，避免 Token 永久有效。
        Date expiration = new Date(issuedAt.getTime() + EXPIRATION_MILLIS);

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    /**
     * 解析 JWT 并获取其中保存的用户 ID，同时校验签名和有效期。
     *
     * @param token 待解析的 JWT 字符串
     * @return Token 中保存的用户 ID
     * @throws JwtException Token 签名错误、格式错误或已经过期时抛出
     */
    public static String getUserId(String token) {
        // 解析过程会校验签名，并自动检查 expiration 是否已过期。
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token);
        // subject 保存登录时写入的用户 ID。
        return claimsJws.getBody().getSubject();
    }

    /**
     * 判断 JWT 是否可以被当前密钥正常解析。
     *
     * @param token 待校验的 JWT 字符串
     * @return Token 有效返回 true，格式错误、签名错误或过期返回 false
     */
    public static boolean isValid(String token) {
        // 空 Token 直接判定为无效，避免进入解析器造成无意义异常。
        if (token == null || token.isBlank()) {
            return false;
        }

        try {
            getUserId(token);
            return true;
        } catch (JwtException | IllegalArgumentException exception) {
            return false;
        }
    }
}
