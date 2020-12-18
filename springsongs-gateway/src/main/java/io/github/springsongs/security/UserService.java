package io.github.springsongs.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.springsongs.domain.SpringRole;
import io.github.springsongs.domain.SpringUser;
import io.github.springsongs.domain.SpringUserSecurity;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.mapper.SpringRoleMapper;
import io.github.springsongs.mapper.SpringUserMapper;
import io.github.springsongs.mapper.SpringUserSecurityMapper;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private SpringUserMapper springUserMapper;
	@Autowired
	private SpringUserSecurityMapper springUserSecurityMapper;
	@Autowired
	private SpringRoleMapper springRoleMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SpringUser user = springUserMapper.getByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(ResultCode.USER_NOT_FOUND.getMessage());
		}
		List<SpringRole> springRoleList = springRoleMapper.listRoleByUserId(user.getId());
		user.setSpringRoleList(springRoleList);
		SpringUserSecurity springUserSecurity = springUserSecurityMapper.findByUserId(user.getId());
		user.setSpringUserSecurity(springUserSecurity);
		return new UserPrincipal(user);
	}
}
