package com.deltacode.kcb.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        String userName = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationInMs);


        return Jwts.builder( )
                .setSubject(userName)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    //get username from token
    public String getUserNameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    //validate jwt token
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature.");
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.out.println("Expired JWT token.");
        } catch (io.jsonwebtoken.UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token.");
        } catch (io.jsonwebtoken.MalformedJwtException e) {
            System.out.println("Invalid JWT token.");
        }
        return false;
    }
    //validate token initial
    //  public boolean validateToken(String token) {
    //        try {
    //            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
    //            return true;
    //        } catch (io.jsonwebtoken.ExpiredJwtException e) {
    //            return false;
    //        }
    //    }


}
