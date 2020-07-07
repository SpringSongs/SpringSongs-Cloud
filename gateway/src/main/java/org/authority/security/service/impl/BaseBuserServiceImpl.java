package org.authority.security.service.impl;

import java.util.List;

import org.authority.domain.Member;
import org.authority.security.service.IBaseBuserService;
import org.authority.util.R;
import org.springframework.stereotype.Component;
@Component
public class BaseBuserServiceImpl implements IBaseBuserService {

	@Override
	public R deleteByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R insert(Member record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R selectByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R updateByPrimaryKey(Member record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R getAllRecordByPage(Member record, int currPage, int size) {
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
	public R getByUserName(String username) {
		return R.error(500, "服务器正忙");
	}

}
