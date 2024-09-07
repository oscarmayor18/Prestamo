//package com.prestamos.microemploye.security;
//
//import io.jsonwebtoken.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JwtTokenProvider {
//
//    // Clave secreta usada para firmar y verificar los tokens JWT
//	@Value("tuClaveSecretaMuyLargaYCompleja1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ")
//    private String jwtSecret;
//
//    // Extrae el nombre de usuario (email) del token JWT
//    public String getUsernameFromJWT(String token) {
//        // Parsea el token JWT y extrae las claims (datos)
//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody();
//
//        // Retorna el subject del token, que en este caso es el email del usuario
//        return claims.getSubject();
//    }
//
//    // Valida si un token JWT es válido
//    public boolean validateToken(String authToken) {
//        try {
//            // Intenta parsear el token. Si es exitoso, el token es válido
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//            return true;
//        } catch (SignatureException ex) {
//            // La firma del token no coincide
//        } catch (MalformedJwtException ex) {
//            // El token no tiene el formato correcto de JWT
//        } catch (ExpiredJwtException ex) {
//            // El token ha expirado
//        } catch (UnsupportedJwtException ex) {
//            // El token no es compatible con la versión actual de JWT
//        } catch (IllegalArgumentException ex) {
//            // El token está vacío o es nulo
//        }
//        // Si se captura cualquier excepción, el token no es válido
//        return false;
//    }
//}