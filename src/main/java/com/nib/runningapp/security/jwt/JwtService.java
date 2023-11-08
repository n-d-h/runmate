package com.nib.runningapp.security.jwt;

import com.nib.runningapp.dtos.GoogleUserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "28482B4D6251655468576D597133743677397A24432646294A404E635266556A";

    public GoogleUserDTO parseJwtToken(String jwtToken){
//        jwtToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImY1ZjRiZjQ2ZTUyYjMxZDliNjI0OWY3MzA5YWQwMzM4NDAwNjgwY2QiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI4ODUzNTIxNzU0MDktOGJibTZmdWE4amJnMjJ0MXBuYmkyOWN2ZDNpOWdjcmQuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI4ODUzNTIxNzU0MDktOGJibTZmdWE4amJnMjJ0MXBuYmkyOWN2ZDNpOWdjcmQuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTU4NTA2OTUwMjcyMTc3OTAxMDkiLCJoZCI6ImZwdC5lZHUudm4iLCJlbWFpbCI6ImFuaG52aHNlMTYyMDgyQGZwdC5lZHUudm4iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6InY3Z0pvZVhaUHZMRFE3b09udnRZVmciLCJuYW1lIjoiTmd1eWVuIFZ1IEhhaSBBbmggKEsxNiBIQ00pIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FDZzhvY0paLXB5b2xHLUZrcDJVeUQxTTZYU0RUaW0tZkVNSlNMR0RDSy1JbHg4VWJLVT1zOTYtYyIsImdpdmVuX25hbWUiOiJOZ3V5ZW4gVnUgSGFpIEFuaCIsImZhbWlseV9uYW1lIjoiKEsxNiBIQ00pIiwibG9jYWxlIjoiZW4tR0IiLCJpYXQiOjE2OTg3MDk2NjYsImV4cCI6MTY5ODcxMzI2Nn0.ipS4R2FySTkBX4VKX83epqz8TQJha2_JnW1xck6JsFWIoWxrm6eLPhf1F_r32X-C1ed1xjVByM8kTrNm_UvVZVc4SG8YDHXBrHbCYbJvlJB0c0ByIR_52egN4kO0ddNs9S57fwlBOajMpxL_Ug0XJEmE3_diHZnkAv2rtFMPWpde5SgU-dVmMnh4M_jR1_jOhaSKCyBDEShtTCmpWg1eNhxZZIRzEp84R0j7JmBjJWv1R6m2SyjA_HnN2XR1EShznCuF6I0PyM7ZsTuZF4xuEDTSwVLOpN9FYyyeVMzpOaUPZp7pPJV3E94k4yxLN14kJpNn_m9KwEJRbTLYsNVAKA";

        // Split the JWT into its parts
        String[] parts = jwtToken.split("\\.");

        String header = new String(Base64.decodeBase64(parts[0]));

        String payload = new String(Base64.decodeBase64(parts[1]));

        JSONObject headerJson = new JSONObject(header);
        JSONObject payloadJson = new JSONObject(payload);

        String name = payloadJson.getString("name");
        String email = payloadJson.getString("email");
        String audId = payloadJson.getString("aud");
        audId = audId.substring(0, audId.indexOf("-"));
        Long id = Long.parseLong(audId);
        String picture = payloadJson.getString("picture");

        // apply it to GoogleUserDTO
        GoogleUserDTO googleUserDTO = new GoogleUserDTO();
        googleUserDTO.setDisplayName(name);
        googleUserDTO.setEmail(email);
        googleUserDTO.setId(id);
        googleUserDTO.setPhotoUrl(picture);

        return googleUserDTO;
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

//    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
//    }
//
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername())) && !isTokenValid(token);
//    }

    private boolean isTokenValid(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
