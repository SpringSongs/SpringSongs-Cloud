package io.github.springsongs.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.springsongs.dto.LoginRequest;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringUserMapper;
import io.github.springsongs.security.UserPrincipal;
import io.github.springsongs.utils.IpKit;
import io.github.springsongs.utils.JwtUtil;

@RestController
@RequestMapping(value = "/SpringUser")
public class SpringUserController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.tokeanHead}")
	private String tokenHead;

	private JwtUtil jwtUtil;

	@Autowired
	private SpringUserMapper springUserMapper;

	@PostMapping(value = "/Login")
	public ResponseDTO<String> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest request) {
		jwtUtil = new JwtUtil();
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
				loginRequest.getUsername(), loginRequest.getPassword());
		final UserPrincipal springUser = (UserPrincipal) userDetailsService.loadUserByUsername(upToken.getName());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String currentPassWord = springUser.getPassword();
		String formPassWord = upToken.getCredentials().toString();
		if (springUser != null && passwordEncoder.matches(formPassWord, currentPassWord)) {
			final Authentication authentication = authenticationManager.authenticate(upToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token;
			try {
				StringBuilder sb = new StringBuilder();
				Collection<? extends GrantedAuthority> springRoleList = springUser.getAuthorities();
				springRoleList.stream().forEach(springRole -> {
					sb.append(springRole.getAuthority()).append(",");
				});
				token = jwtUtil.generateToken(springUser.getId(), springUser.getUsername(), sb.toString(),
						IpKit.getRealIp(request));
			} catch (IOException e) {
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
			return ResponseDTO.successed(token, ResultCode.LOGIN_SUCCESSED);
		} else {
			return ResponseDTO.successed("", ResultCode.LOGIN_FAIL);
		}
	}

	@PostMapping(value = "/RefreshToken")
	public ResponseDTO<String> refresh(@RequestParam(value = "oldToken") String oldToken, HttpServletRequest request) throws IOException {
		jwtUtil = new JwtUtil();
		String token = oldToken.substring(tokenHead.length());
		String username = jwtUtil.getUserNameFromToken(token);
		final UserPrincipal springUser = (UserPrincipal) userDetailsService.loadUserByUsername(username);
		if (springUser != null) {
			StringBuilder sb = new StringBuilder();
			Collection<? extends GrantedAuthority> springRoleList = springUser.getAuthorities();
			springRoleList.stream().forEach(springRole -> {
				sb.append(springRole.getAuthority()).append(",");
			});
			token = jwtUtil.generateToken(springUser.getId(), springUser.getUsername(), sb.toString(),
					IpKit.getRealIp(request));
			return ResponseDTO.successed(token, ResultCode.LOGIN_SUCCESSED);
		} else {
			return ResponseDTO.successed("", ResultCode.LOGIN_FAIL);
		}
	}

	@PostMapping(value = "/Logout")
	public ResponseDTO<String> sessionHasedGone() {
		return ResponseDTO.successed("", ResultCode.LOGIN_FAIL);
	}
}
