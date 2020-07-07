package org.authority.service.hystrix;

import java.util.List;

import org.authority.domain.Role;
import org.authority.domain.MemberRole;
import org.authority.service.IRoleService;
import org.authority.util.R;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements IRoleService {

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
	public R listRolesByUserId(String userId, int page, int limit) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R saveUserToRole(List<MemberRole> baseUserRoleEntityList, String roleId) {
		return R.error(500, "服务器正忙");
	}

}
