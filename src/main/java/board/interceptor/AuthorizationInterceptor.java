package board.interceptor;

import java.security.Key;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

	Environment env;
	
	public AuthorizationInterceptor(Environment env) {
		this.env = env;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		// Authorization 요청 헤더가 포함되어 있는지 확인
		if (request.getHeader(HttpHeaders.AUTHORIZATION) == null) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return false;
		}
		
		// Authorization 요청 헤더의 값을 검증
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		String jwt = authorizationHeader.replace("Bearer", "").trim();
		if (!isValidToken(jwt)) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return false;
		}
		
		// 정상적인 토큰과 함께 온 요청
		return true;
	}
	
	private boolean isValidToken(String jwtString) {
		String secret = env.getProperty("token.secret");
		Key hmacKey = new SecretKeySpec(
				Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName()
		);
		
		try {
			Jws<Claims> jwt = Jwts.parserBuilder()
							.setSigningKey(hmacKey)
							.build()
							.parseClaimsJws(jwtString);
			log.debug(jwt.toString());
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
}
