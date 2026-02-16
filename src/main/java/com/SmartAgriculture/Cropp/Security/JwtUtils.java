// package com.SmartAgriculture.Cropp.Security;

// import java.util.Date;
// import java.security.Key;
// import java.util.Base64.Decoder;

// import javax.crypto.SecretKey;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.ResponseCookie;
// import org.springframework.stereotype.Component;
// import org.springframework.web.util.WebUtils;

// import io.jsonwebtoken.ExpiredJwtException;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.MalformedJwtException;
// import io.jsonwebtoken.UnsupportedJwtException;
// import io.jsonwebtoken.io.Decoders;
// import io.jsonwebtoken.security.Keys;
// import jakarta.servlet.http.Cookie;
// import jakarta.servlet.http.HttpServletRequest;

// @Component
// public class JwtUtils {

//     @Value("${spring.cropp.app.jwtCookies}")
//     private String jwtCookie;

//     @Value("${spring.app.jwtExpirationMs}")
//     private String jwtExpirationMs;

//     @Value("${spring.app.jwtSecret}")
//     private String jwtSecret;

//     private Logger logger = LoggerFactory.getLogger(JwtUtils.class);

//     public String getJwtFromCookie(HttpServletRequest request) {
//         Cookie cookie = WebUtils.getCookie(request, jwtCookie);
//         if (cookie != null) {
//             return cookie.getValue();
//         } else
//             return null;
//     }

//     public String getJwtFromHeader(HttpServletRequest request) {
//         String brearToken = request.getHeader("Authorization");
//         if (brearToken != null && brearToken.startsWith("Bearer")) {
//             return brearToken.substring(7);
//         } else
//             return null;
//     }
//     // public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal){
//     // String jwt=
//     // }

//     public String generateTokenFromUsername(String username) {
//         return Jwts.builder()
//                 .subject(username)
//                 .issuedAt(new Date())
//                 .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
//                 .signWith(key())
//                 .compact();
//     }

//     public String getUsernameFromjwtToken(String token) {
//         return Jwts.parser()
//                 .verifyWith((SecretKey) key())
//                 .build()
//                 .parseSignedClaims(token)
//                 .getPayload().getSubject();
//     }

//     public boolean validateJwtToken(String authToken) {
//         try {
//             Jwts.parser().verifyWith((SecretKey) key())
//                     .build()
//                     .parseSignedClaims(authToken);
//             return true;
//         } catch (MalformedJwtException e) {
//             logger.error("Invalid JWT token: {}", e.getMessage());
//         } catch (ExpiredJwtException e) {
//             logger.error("JWT token is expired: {}", e.getMessage());
//         } catch (UnsupportedJwtException e) {
//             logger.error("JWT token is unsupported: {}", e.getMessage());
//         } catch (IllegalArgumentException e) {
//             logger.error("JWT claims String is empty :{}", e.getMessage());
//         }
//         return false;
//     }

//     private Key key() {
//         return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
//     }
// }
