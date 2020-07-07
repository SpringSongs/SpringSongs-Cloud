package org.authority.security.service.impl;

import java.util.List;

import org.authority.domain.Role;
import org.authority.security.service.IBaseRoleService;
import org.authority.util.R;
import org.springframework.stereotype.Component;

@Component
public class BaseRoleServiceImpl implements IBaseRoleService {

	@Override
	public R deleteByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R insert(Role record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R updateByPrimaryKey(Role record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R getAllRecordByPage(Role record, int currPage, int size) {
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

	@Override
	public R getRolesByUserId(String id) {
		return R.error(500, "服务器正忙");
	}

}
