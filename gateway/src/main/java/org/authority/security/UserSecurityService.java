package org.authority.security;

import org.authority.domain.Member;
import org.authority.security.service.IBaseBuserLogOnService;
import org.authority.security.service.IBaseBuserService;
import org.authority.security.service.IBaseRoleService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

@Service
public class UserSecurityService implements UserDetailsService {

	@Autowired
	private IBaseBuserService userDao;

	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member user = null;
		R r = userDao.getByUserName(username);
		if (r.get("code").toString().equalsIgnoreCase("200")) {
			String rvs = JSONArray.toJSONString(r.get("data"));
			user = JSON.parseObject(rvs, Member.class);
		} else {
			throw new UsernameNotFoundException("服务器正忙");
		}
		return new MyUserPrincipal(user);
	}

}
