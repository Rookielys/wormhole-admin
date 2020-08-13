package com.haha.wormholeadmin.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String securityKey = "dRg09WApvGjKvo67Hz4D47iw06KcFX38ce5kcNRn0A4=";

    public static String createToken(Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        // 过期时间30min
        long expiration = now + 30 * 60 * 1000;
        return Jwts.builder().addClaims(claims)
                .setIssuedAt(new Date(now)).setExpiration(new Date(expiration))
                .signWith(SignatureAlgorithm.HS256, securityKey).compact();
    }

    public static String parseToken(String token) {
        return (String)Jwts.parserBuilder().setSigningKey(securityKey).build().parseClaimsJws(token).getBody().get("username");
    }

    /**
     * 生成key
     * @param args
     */
    public static void main(String[] args) {
        /*
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(secretString);*/
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("username", "lian");
        String token = createToken(claims);
        System.out.println(parseToken(token+"ooo"));
    }

}
