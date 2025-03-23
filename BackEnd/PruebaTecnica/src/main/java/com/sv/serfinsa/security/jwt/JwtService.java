package com.sv.serfinsa.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.sv.serfinsa.entity.UsuarioPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	private final static Logger LOG = LoggerFactory.getLogger(JwtService.class);
	
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private int expiration;
	
	public Key getKey() {
		byte[] keyBytes=Decoders.BASE64.decode(secret);
	    return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String getToken(Authentication auth) {
		UsuarioPrincipal user = (UsuarioPrincipal) auth.getPrincipal();
		List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		return Jwts.builder()
				.setSubject(user.getUsername())
				.claim("roles",roles)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 180))
				.signWith(getKey(),  SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String getNameUserFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(getKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
			return true;
		} catch (MalformedJwtException e) {
			LOG.error("el formato de token no es correcto "+e.getMessage(), e);
		}
		catch (UnsupportedJwtException  e) {
			LOG.error("Token no soportado "+e.getMessage(), e );
		}
		catch(ExpiredJwtException e) {
			LOG.error("Toke expiro "+e.getMessage(), e);
		}
		catch(IllegalArgumentException e) {
			LOG.error("Token vacio "+e.getMessage(),e);
		}catch (SignatureException e) {
			LOG.error("Firma faida "+e.getMessage(),e);
		}
		return false;
	}
	
}
