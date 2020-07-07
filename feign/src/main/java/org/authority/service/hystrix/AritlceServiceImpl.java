package org.authority.service.hystrix;

import java.util.List;

import org.authority.domain.Aritlce;
import org.authority.service.IAritlceService;
import org.authority.util.R;
import org.springframework.stereotype.Component;

@Component
public class AritlceServiceImpl implements IAritlceService {

	@Override
	public R deleteByPrimaryKey(String id) {
		return R.error(500,"服务器正忙");
	}

	@Override
	public R insert(Aritlce record) {
		return R.error(500,"服务器正忙");
	}

	@Override
	public R selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R updateByPrimaryKey(Aritlce record) {
		return R.error(500,"服务器正忙");
	}

	@Override
	public R getAllRecordByPage(Aritlce record, int currPage, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R setDeleted(List<String> ids) {
		return R.error(500,"服务器正忙");
	}

	@Override
	public R batchSaveExcel(List<String[]> list) {
		return R.error(500,"服务器正忙");
	}

	@Override
	public R audit(String id) {
		return R.error(500,"服务器正忙");
	}

	

}
