package com.youzan.ad.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by baimugudu on 2019/4/23
 */
public class Token {

    public static void test1(){
        JwtBuilder builder = Jwts.builder().setId("666666")
                .setSubject("myusername")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "mypassword");
        System.out.println(builder.compact());
    }

    /**
     * io.jsonwebtoken.MalformedJwtException
     */
    public static  void parseJwtTest(){
        String token = "11eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjY2NjYiLCJzdWIiOiJteXVzZXJuYW1lIiwiaWF0IjoxNTU2MDkxNjUwfQ.saifad1DnvflRqo2TiYFr5lStDriyj1TI8QBkLukI3Y";
        Claims claims = Jwts.parser().setSigningKey("mypassword")
                .parseClaimsJws(token).getBody();
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());

    }


    public static void test3(){
        //为了方便测试，我们将过期时间设置为1分钟         
        long now = System.currentTimeMillis();//当前时间         
        long exp = now + 1000*60;//过期时间为1分钟
        JwtBuilder builder = Jwts.builder().setId("666666")
                .setSubject("myusername")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "mypassword")
                .setExpiration(new Date(exp));

        System.out.println(builder.compact());

    }

    /**
     * io.jsonwebtoken.ExpiredJwtException
     */
    public static void test4(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjY2NjYiLCJzdWIiOiJteXVzZXJuYW1lIiwiaWF0IjoxNTU2MDkxODQ4LCJleHAiOjE1NTYwOTE5MDd9.U194gcOI9VJ1SaA4EPTPhvB3p6Y1cNuu9iaLIXBpMoc";
        Claims claims = Jwts.parser().setSigningKey("mypassword")
                .parseClaimsJws(token).getBody();

        System.out.println(claims.getId());
        System.out.println(claims.getSubject());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("签发时间"+simpleDateFormat.format(claims.getIssuedAt()));
        System.out.println("过期时间"+claims.getExpiration());
        System.out.println("当期时间"+simpleDateFormat.format(new Date()));
    }


    public static void main(String[] args) {
        //test1();
      // parseJwtTest();

        //test3();
        test4();
    }

}
