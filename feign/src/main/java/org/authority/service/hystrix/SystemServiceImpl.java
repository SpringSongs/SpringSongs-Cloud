package org.authority.service.hystrix;

import java.util.List;

import org.authority.domain.System;
import org.authority.service.ISystemService;
import org.authority.util.R;
import org.springframework.stereotype.Component;

@Component
public class SystemServiceImpl implements ISystemService {

	@Override
	public R deleteByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R insert(System record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R selectByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R updateByPrimaryKey(System record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R getAllRecordByPage(System record, int currPage, int size) {
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
	public R listAll() {
		return R.error(500, "服务器正忙");
	}

}
