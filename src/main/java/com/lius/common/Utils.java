package com.lius.common;

import com.lius.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Utils {

    private static final String signKey = "my-key-is-hello-java-lius";

    /**
     * 生成token
     *
     * @return token
     */
    public static String generateToken(User user) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(user.getId() + "") // 用户id
                .setSubject(user.getUserName()) // 用户名
                .setIssuedAt(new Date()) // 登录时间
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(new Date().getTime() + 86400000)); // 过期时间

        return jwtBuilder.compact();
    }

    public static void parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();

        System.out.println("用户id:" + claims.getId());
        System.out.println("用户名:" + claims.getSubject());
        System.out.println("登录时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                format(claims.getIssuedAt()));
        System.out.println("过期时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                format(claims.getExpiration()));
    }
}
