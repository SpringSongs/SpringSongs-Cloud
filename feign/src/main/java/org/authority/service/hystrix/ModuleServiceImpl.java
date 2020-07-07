package org.authority.service.hystrix;

import java.util.List;

import org.authority.domain.Module;
import org.authority.domain.ModuleRole;
import org.authority.service.IModuleService;
import org.authority.util.R;
import org.springframework.stereotype.Component;

@Component
public class ModuleServiceImpl implements IModuleService {

	@Override
	public R deleteByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R insert(Module record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R selectByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R updateByPrimaryKey(Module record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R getAllRecordByPage(Module record, int currPage, int size) {
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
	public R listModuleByUserId(String userId) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R getModulesByParentId(String parentId, String systemId) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R listModulesByRoleId(String roleId) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R saveModuleToRole(List<ModuleRole> baseModuleRoleEntityList, String roleId) {
		return R.error(500, "服务器正忙");
	}

}
