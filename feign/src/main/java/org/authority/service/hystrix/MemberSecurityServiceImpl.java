package org.authority.service.hystrix;

import java.util.List;

import org.authority.domain.MemberSecurity;
import org.authority.service.IMemberSecurityService;
import org.authority.util.R;
import org.springframework.stereotype.Component;
@Component
public class MemberSecurityServiceImpl implements IMemberSecurityService {

	@Override
	public R deleteByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R insert(MemberSecurity record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R selectByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R updateByPrimaryKey(MemberSecurity record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R getAllRecordByPage(MemberSecurity record, int page, int size) {
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
