package io.github.springsongs.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import io.github.springsongs.dto.SecurityUser;

@Component
public class AuthenUtil {
	
	public SecurityUser getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser securityUser = null;
		securityUser = (SecurityUser) authentication.getPrincipal();
		return securityUser;
	}
}
