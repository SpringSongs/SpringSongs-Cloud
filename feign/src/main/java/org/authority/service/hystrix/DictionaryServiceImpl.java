package org.authority.service.hystrix;

import java.util.List;

import org.authority.domain.Dictionary;
import org.authority.service.IDictionaryService;
import org.authority.util.R;
import org.springframework.stereotype.Component;

@Component
public class DictionaryServiceImpl implements IDictionaryService {

	@Override
	public R deleteByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R insert(Dictionary record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R selectByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R updateByPrimaryKey(Dictionary record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R getAllRecordByPage(Dictionary record, int currPage, int size) {
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
