package org.authority.security.service;

import org.authority.security.service.impl.BaseBuserLogOnServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTHORITY-SERVICE", fallback = BaseBuserLogOnServiceImpl.class)
public interface IBaseBuserLogOnService {
	@RequestMapping(value = "/BaseBuser/FindByUserId", method = RequestMethod.POST)
	R findByUserId(@RequestParam("userId") String userId);
}
