package org.authority.service.hystrix;

import java.util.List;

import org.authority.domain.Member;
import org.authority.domain.MemberRole;
import org.authority.service.IMemberService;
import org.authority.util.R;
import org.springframework.stereotype.Component;
@Component
public class MemberServiceImpl implements IMemberService {

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
	public R saveUserToRole(List<MemberRole> baseUserRoleEntityList, String userId) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R listUsersByRoleId(String roleId, int page, int limit) {
		return R.error(500, "服务器正忙");
	}

}
