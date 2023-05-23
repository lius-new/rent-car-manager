package com.lius.common;

import com.lius.controller.ExceptionAdvice;
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
        Claims claims = Jwts.claims()
                .setId(user.getId() + "")
                .setSubject(user.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000)); // 过期时间

        claims.put("role", user.getUserRole()); // 添加role

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .compact();
    }

    public static User parseToken(String token) throws ExceptionAdvice.CheckTokenException {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();

        String id = claims.getId();
        String userName = claims.getSubject();
        String userRole = (String) claims.get("role");

        // (id,userName,userROle)获取不到直接抛出错误
        if (id.equals("null") || userName.equals("null") || userRole.equals("null"))
            throw new ExceptionAdvice.CheckTokenException();
        return new User(Integer.valueOf(id), userName, userRole);
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
