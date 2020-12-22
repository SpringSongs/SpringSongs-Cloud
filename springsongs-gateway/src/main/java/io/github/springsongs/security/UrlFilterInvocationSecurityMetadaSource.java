package io.github.springsongs.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import io.github.springsongs.dto.UriRoleCode;
import io.github.springsongs.mapper.SpringResourceMapper;
import io.github.springsongs.util.SecurityUtils;

@Component
public class UrlFilterInvocationSecurityMetadaSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private SpringResourceMapper springResourceMapper;

	@Autowired
	private SecurityUtils securityUtils;

	String requestUrl = "";

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

		requestUrl = ((FilterInvocation) object).getRequestUrl();
		List<UriRoleCode> mpoduleRoleList = springResourceMapper.listAllRoleModules(securityUtils.getAuth());
		List<String> roles = new ArrayList<String>();

		mpoduleRoleList.stream().forEach(moduleRoleDto -> {
			if (null == moduleRoleDto.getVueUrl()) {
				moduleRoleDto.setVueUrl("");
			}
			if (!StringUtils.isEmpty(moduleRoleDto.getVueUrl())
					&& requestUrl.contains(moduleRoleDto.getVueUrl())) {
				roles.add(moduleRoleDto.getTitle());
			}
			if (requestUrl.equals("/error") || requestUrl.equals("/") || requestUrl.equals("")
					|| requestUrl.contains("/csrf")) {
				roles.add(moduleRoleDto.getTitle());
			}
//			if (requestUrl.contains("/SpringUser/Invalidate")||requestUrl.contains("/Login")) {
//				roles.add(moduleRoleDto.getCode());
//			}
//			if (antPathMatchc.match(requestUrl, moduleRoleDto.getNavigateUrl())
//					&& !StringUtils.isEmpty(moduleRoleDto.getCode())) {
//				roles.add(moduleRoleDto.getCode());
//			}	
		});

		List<String> resourceRoleDTOList = roles.stream().distinct().collect(Collectors.toList());
		if (!CollectionUtils.isEmpty(resourceRoleDTOList)) {
			String[] str = new String[resourceRoleDTOList.size()];
			for (int i = 0; i < resourceRoleDTOList.size(); i++) {
				str[i] = roles.get(i);
			}
			return SecurityConfig.createList(str);
		}

		return SecurityConfig.createList("ROLE_LOGIN");
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

}
