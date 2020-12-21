package io.github.springsongs.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import io.github.springsongs.dto.SecurityUser;
import io.github.springsongs.mapper.SpringUserMapper;

@Component
public class AuthenUtil {
	@Autowired
	private SpringUserMapper springUserMapper;

	public SecurityUser getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser securityUser = null;
		securityUser = (SecurityUser) authentication.getPrincipal();
		return securityUser;
	}
}
