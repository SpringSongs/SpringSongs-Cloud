package io.github.springsongs.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.springsongs.domain.SpringUser;
import io.github.springsongs.dto.LoginRequest;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringUserMapper;
import io.github.springsongs.utils.JwtUtil;

@RestController
@RequestMapping(value = "/SpringUser")
public class SpringUserController {

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
	public ResponseDTO<String> login(@RequestBody LoginRequest loginRequest) {
		jwtUtil = new JwtUtil();
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
				loginRequest.getUsername(), loginRequest.getPassword());
		final Authentication authentication = authenticationManager.authenticate(upToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final SpringUser springUser = springUserMapper.getByUserName(loginRequest.getUsername());
		String token;
		try {
			token = jwtUtil.generateToken(springUser.getId(), springUser.getUserName());
		} catch (IOException e) {
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
		return ResponseDTO.successed(token, ResultCode.LOGIN_SUCCESSED);
	}

	@GetMapping("/Test")
	public ResponseDTO<String> test() {
		return ResponseDTO.successed("OK", ResultCode.LOGIN_SUCCESSED);
	}
}
