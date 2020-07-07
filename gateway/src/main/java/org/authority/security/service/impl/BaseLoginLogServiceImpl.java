package org.authority.security.service.impl;

import java.util.List;

import org.authority.domain.LoginLog;
import org.authority.security.service.IBaseLoginLogService;
import org.authority.util.R;
import org.springframework.stereotype.Component;

@Component
public class BaseLoginLogServiceImpl implements IBaseLoginLogService {

	@Override
	public R deleteByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R insert(LoginLog record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R selectByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R updateByPrimaryKey(LoginLog record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R getAllRecordByPage(LoginLog record, int currPage, int size) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R setDeleted(List<String> ids) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R batchSaveExcel(List<String[]> list) {
		return R.error(500,"服务器正忙");
	}

}
