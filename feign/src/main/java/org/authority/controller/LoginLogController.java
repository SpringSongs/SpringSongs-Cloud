package org.authority.controller;

import org.authority.domain.LoginLog;
import org.authority.service.ILoginLogService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/BaseLoginLog")
public class LoginLogController extends BaseController {

	@Autowired
	private ILoginLogService baseLoginLogService;

	@PostMapping(value = "ListByPage")
	public R getPage(@RequestBody LoginLog viewEntity, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {

		try {
			R r = baseLoginLogService.getAllRecordByPage(viewEntity, page, limit);

			return r;
		} catch (Exception e) {
		}
		return new R();
	}

}
