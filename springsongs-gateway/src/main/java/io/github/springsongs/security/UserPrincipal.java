package io.github.springsongs.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.github.springsongs.domain.SpringUser;

public class UserPrincipal implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7035053597478440701L;
	private SpringUser springUser;

	public UserPrincipal(SpringUser springUser) {
		super();
		this.springUser = springUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> auths = new ArrayList<>();
		springUser.getSpringRoleList().stream().forEach(springRole -> {
			auths.add(new SimpleGrantedAuthority(springRole.getTitle()));
		});
		return auths;
	}

	@Override
	public String getPassword() {
		return springUser.getSpringUserSecurity().getPwd();
	}

	@Override
	public String getUsername() {
		return springUser.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
