package io.github.springsongs.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
	public List<String> getAuth() {
		List<String> auths = new ArrayList<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<SimpleGrantedAuthority> SimpleGrantedAuthoritys = (List<SimpleGrantedAuthority>) authentication
				.getAuthorities();
		for (SimpleGrantedAuthority simple : SimpleGrantedAuthoritys) {
			auths.add(simple.getAuthority());
		}
		return auths;
	}
}
