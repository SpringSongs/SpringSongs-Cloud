package io.github.springsongs.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.utils.JwtUtil;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

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
		if (null != requestHeader && requestHeader.startsWith(this.tokenHead)) {
			token = requestHeader.substring(this.tokenHead.length());
			if (StringUtils.isEmpty(token)) {
				request.getRequestDispatcher("/SpringUser/Logout").forward(request, response);
			}
			try {
				userName = jwtUtil.getUserNameFromToken(token);
			} catch (Exception e) {
				request.getRequestDispatcher("/SpringUser/Logout").forward(request, response);
			}
		}
		if (!StringUtils.isEmpty(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			if (null != userDetails && !StringUtils.isEmpty(userDetails.getUsername())) {
				if (jwtUtil.validateToken(token, userName)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}else {
					request.getRequestDispatcher("/SpringUser/Logout").forward(request, response);
				}
			}
		}
		filterChain.doFilter(request, response);
	}

}
