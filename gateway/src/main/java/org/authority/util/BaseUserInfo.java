package org.authority.util;

import org.authority.domain.Member;
import org.authority.security.MyUserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseUserInfo {
	public static Member getUser() {
		MyUserPrincipal userDetails = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return userDetails.getBaseEntityUser();
	}
}
