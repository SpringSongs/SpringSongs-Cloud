package org.authority.service.hystrix;

import java.util.List;

import org.authority.domain.ApiSecurity;
import org.authority.service.IApiSecurityService;
import org.authority.util.R;
import org.springframework.stereotype.Component;

@Component
public class ApiSecurityServiceImpl implements IApiSecurityService {

	@Override
	public R deleteByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R insert(ApiSecurity record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R selectByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R updateByPrimaryKey(ApiSecurity record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R getAllRecordByPage(ApiSecurity record, int currPage, int size) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R setDeleted(List<String> ids) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R batchSaveExcel(List<String[]> list) {
		return R.error(500, "服务器正忙");
	}

}
