package org.authority.service.hystrix;

import java.util.List;

import org.authority.domain.DictionaryDetail;
import org.authority.service.IDictionaryDetailService;
import org.authority.util.R;
import org.springframework.stereotype.Component;

@Component
public class DictionaryDetailServiceImpl implements IDictionaryDetailService {

	@Override
	public R deleteByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R insert(DictionaryDetail record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R selectByPrimaryKey(String id) {
		return R.error(500,"服务器正忙");
	}

	@Override
	public R updateByPrimaryKey(DictionaryDetail record) {
		return R.error(500,"服务器正忙");
	}

	@Override
	public R getAllRecordByPage(DictionaryDetail record, int currPage, int size) {
		return R.error(500,"服务器正忙");
	}

	@Override
	public R setDeleted(List<String> ids) {
		return R.error(500,"服务器正忙");
	}

	@Override
	public R batchSaveExcel(List<String[]> list) {
		return R.error(500,"服务器正忙");
	}

}
