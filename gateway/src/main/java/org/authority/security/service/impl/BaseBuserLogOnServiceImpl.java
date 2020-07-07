package org.authority.security.service.impl;

import org.authority.security.service.IBaseBuserLogOnService;
import org.authority.util.R;
import org.springframework.stereotype.Component;

@Component
public class BaseBuserLogOnServiceImpl implements IBaseBuserLogOnService {
	@Override
	public R findByUserId(String userId) {
		return R.error(500, "服务器正忙");
	}

}
