package io.github.springsongs.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.springsongs.dto.SecurityUser;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.utils.JwtUtil;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.tokeanHead}")
	private String tokenHead;

	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		jwtUtil = new JwtUtil();
		final String requestHeader = request.getHeader(this.tokenHeader);
		String token = "";
		String userName = "";
		String userId = "";
		String roles = "";
		if (null != requestHeader && requestHeader.startsWith(this.tokenHead)) {
			token = requestHeader.substring(this.tokenHead.length());
			if (StringUtils.isEmpty(token)) {
				throw new SpringSongsException(ResultCode.LOGIN_FAIL);
			}
			SecurityUser securityUser = new SecurityUser();
			List<SimpleGrantedAuthority> auths = new ArrayList<>();
			try {
				userName = jwtUtil.getUserNameFromToken(token);
				userId = jwtUtil.getUserIdFromToken(token);
				roles = jwtUtil.getRolesFromToken(token);
				securityUser.setId(userId);
				securityUser.setUserName(userName);
				String[] roleArr = roles.split(",");
				for (String code : roleArr) {
					auths.add(new SimpleGrantedAuthority(code));
				}
			} catch (Exception e) {
				throw new SpringSongsException(ResultCode.LOGIN_FAIL);
			}
			if (!StringUtils.isEmpty(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
				if (jwtUtil.validateToken(token, userName)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							securityUser, null, auths);
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} else {
					throw new SpringSongsException(ResultCode.LOGIN_FAIL);
				}
			}
		} else {
			throw new SpringSongsException(ResultCode.LOGIN_FAIL);
		}
		filterChain.doFilter(request, response);
	}

}
