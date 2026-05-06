package com.palle.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private String secretKey="";
	
	private  JwtService() {
		try {
			KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			secretKey= Base64.getEncoder().encodeToString(sk.getEncoded());

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String generateToken(String username) {
		Map<String,Object> claims=new HashMap<>();
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+60*60*60*60))
				.signWith(genKey())
				.compact();
	}

	private Key genKey() {
		byte b[]=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(b);
	}
	
	public Claims extractAllClaims(String token) {
	    return Jwts.parserBuilder() //This creates a JWT parser. open and read this token
	            .setSigningKey(genKey()) //This gives the parser secret key. It checks Was this token really created by my application If wrong key → token is fake/invalid.
	            .build() //This finalizes the parser. Parser is ready now
	            
	            /*parseClaimsJws
	             *reads token
	             *verifies signature
	             *checks expiration
	             *checks token structure
	             *If invalid → exception happens
	             *If valid → token is accepted 
	            */
	            .parseClaimsJws(token) 
	        
	            .getBody();
	}
	
	public String extractUsername (String token) {
		return extractAllClaims(token).getSubject();
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		final String usernameExtractedFromToken=extractAllClaims(token).getSubject();
		
		return (usernameExtractedFromToken.equals(userDetails.getUsername())&&isTokenValid(token));
	}
	
	public boolean isTokenValid (String token) {
		return ((new Date()).before(extractAllClaims(token).getExpiration()));
	}
}
