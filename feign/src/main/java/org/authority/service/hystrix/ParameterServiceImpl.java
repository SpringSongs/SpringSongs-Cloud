package org.authority.service.hystrix;

import java.util.List;

import org.authority.domain.Parameter;
import org.authority.service.IParameterService;
import org.authority.util.R;
import org.springframework.stereotype.Component;

@Component
public class ParameterServiceImpl implements IParameterService {

	@Override
	public R deleteByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R insert(Parameter record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R selectByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R updateByPrimaryKey(Parameter record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R getAllRecordByPage(Parameter record, int currPage, int size) {
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
