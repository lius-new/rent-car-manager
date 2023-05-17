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

    /**
     * 将user_name的形式转换为驼峰命名的形式, 如果没有_，即会直接返回
     */
    public static String mapUnderscoreToCamelCase(String str) {
        int i = str.indexOf('_');

        if (i == -1) {
            return str;
        } else {
            String[] split = str.split("_");
            StringBuilder col = new StringBuilder(split[0]);
            for (int j = 1; j < split.length; j++) {
                char[] chars = split[j].toCharArray();
                chars[0] = String.valueOf(chars[0]).toUpperCase().toCharArray()[0];
                col.append(String.valueOf(chars));
            }
            return col.toString();
        }
    }
}
