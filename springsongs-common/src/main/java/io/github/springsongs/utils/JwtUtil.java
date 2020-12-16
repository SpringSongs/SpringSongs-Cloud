package io.github.springsongs.utils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sun.misc.BASE64Decoder;

public class JwtUtil {
	private static final long TOKEN_EXPIRED_TIME = 30 * 24 * 60 * 60;
	public static final String jwtId = "tokenId";
	private static final String JWT_SECRET = "!@#2341231";
	private static BASE64Decoder decoder = new BASE64Decoder();

	public static String createJWT(Map<String, Object> claims, Long time) throws IOException {
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

	public static Claims getClaimsFromToken(String token) throws IOException {
		SecretKey key = generalKey();
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public static SecretKey generalKey() throws IOException {
		String stringKey = JWT_SECRET;
		byte[] encodedKey = decoder.decodeBuffer(stringKey);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}

	public static String generateToken(String userId, String userName) throws IOException {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("userName", userName);
		return createJWT(map, TOKEN_EXPIRED_TIME);
	}

}
