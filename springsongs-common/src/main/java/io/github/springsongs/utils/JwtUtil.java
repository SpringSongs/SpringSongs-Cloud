package io.github.springsongs.utils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import sun.misc.BASE64Decoder;

public class JwtUtil {
	private static final long TOKEN_EXPIRED_TIME = 30 * 24 * 60 * 60;
	public static final String jwtId = "tokenId";
	private static final String JWT_SECRET = "!@#2341231";
	private static BASE64Decoder decoder = new BASE64Decoder();
	private Clock clock = DefaultClock.INSTANCE;

	public String createJWT(Map<String, Object> claims, Long time) throws IOException {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		Date now = new Date(System.currentTimeMillis());

		SecretKey secretKey = generalKey();
		long nowMillis = System.currentTimeMillis();
		JwtBuilder builder = Jwts.builder().setClaims(claims).setId(jwtId).setIssuedAt(now).signWith(signatureAlgorithm,
				secretKey);
		if (time >= 0) {
			long expMillis = nowMillis + time;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		return builder.compact();
	}

	public Claims getClaimsFromToken(String token) throws IOException {
		SecretKey key = generalKey();
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public SecretKey generalKey() throws IOException {
		String stringKey = JWT_SECRET;
		byte[] encodedKey = decoder.decodeBuffer(stringKey);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}

	public String generateToken(String userId, String userName,String roles,String ip) throws IOException {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("userName", userName);
		map.put("roles", roles);
		map.put("ip", ip);
		map.put("sub", userName);
		return createJWT(map, TOKEN_EXPIRED_TIME);
	}

	public String getUserNameFromToken(String token) throws IOException {
		Claims claims = this.getClaimsFromToken(token);
		return claims.get("userName").toString();
	}

	public String getUserIdFromToken(String token) throws IOException {
		Claims claims = this.getClaimsFromToken(token);
		return claims.get("userId").toString();
	}
	
	public String getIPFromToken(String token) throws IOException {
		Claims claims = this.getClaimsFromToken(token);
		return claims.get("ip").toString();
	}
	
	public String getRolesFromToken(String token) throws IOException {
		Claims claims = this.getClaimsFromToken(token);
		return claims.get("roles").toString();
	}

	public boolean isTokenExpired(String token) throws IOException {
		Claims claims = this.getClaimsFromToken(token);
		final Date expiration = claims.getExpiration();
		return expiration.before(clock.now());
	}

	public boolean validateToken(String token, String userName) throws IOException {
		final String userNameTemp = this.getUserNameFromToken(token);
		if (userName.equals(userNameTemp) && !this.isTokenExpired(token)) {
			return true;
		} else {
			return false;
		}
	}

	public String refreshToken(String token) throws IOException {
		SecretKey secretKey = generalKey();
		Date now = new Date(System.currentTimeMillis());
		Claims claims = this.getClaimsFromToken(token);
		claims.setIssuedAt(now);
		long nowMillis = System.currentTimeMillis();
		JwtBuilder builder = Jwts.builder().setClaims(claims).setId(jwtId).signWith(SignatureAlgorithm.HS256,
				secretKey);
		long expMillis = nowMillis + TOKEN_EXPIRED_TIME;
		Date exp = new Date(nowMillis);
		builder.setExpiration(exp);
		return builder.compact();
	}
}
