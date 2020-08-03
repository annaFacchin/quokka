package it.objectmethod.jpa.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import it.objectmethod.jpa.model.User;
import it.objectmethod.jpa.repository.UserRepo;

@Service
public class JwtAuthService {

	@Value("${jwt.secret}")
	private String key;

	@Autowired
	UserRepo repo;

	public String authenticate(String username, String password) {
		String token = null;
		User user = repo.findByUsernameAndPassword(username, password);
		if (user != null) {
			token = generateToken(user);
		}
		return token;
	}

	public boolean checkTokenValidity(String token) {
		boolean valid = false;
		if (token != null) {
			try {
				Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
				Set<Entry<String, Object>> entries = claims.entrySet();
				for (Entry<String, Object> e : entries) {
					System.out.println("key: [" + e.getKey() + "] value: [" + e.getValue() + "]");
				}
				valid = true;
			} catch (ExpiredJwtException e) {
				System.out.println("TOKEN SCADUTO: " + e);

			} catch (IllegalArgumentException i) {
				System.out.println("TOKEN NON PRESENTE: " + i);

			} catch (SignatureException s) {
				System.out.println("TOKEN ERRATO: " + s);
			}
		}
		return valid;
	}

	public String generateToken(User user) {

		Map<String, Object> claims = new HashMap<>();

		Integer id = user.getId();
		String username = user.getUsername();
		String password = user.getPassword();

		claims.put("id", id);
		claims.put("username", username);
		claims.put("password", password);

		Header<?> header = Jwts.header();
		header.setType("JWT");
		LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
		LocalDateTime expLdt = now.plusDays(3);

		Date iatDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
		Date expDate = Date.from(expLdt.atZone(ZoneId.systemDefault()).toInstant());

		String jwtToken = Jwts.builder().setHeader((Map<String, Object>) header).setClaims(claims)
				.setSubject("user_jwt_token").setIssuedAt(iatDate).setExpiration(expDate)
				.signWith(SignatureAlgorithm.HS256, key).compact();

		return jwtToken;

	}

}
