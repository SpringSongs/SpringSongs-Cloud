package org.authority.service;

import java.util.List;

import org.authority.domain.Role;
import org.authority.domain.MemberRole;
import org.authority.util.R;

public interface IBaseRoleService {

	void deleteByPrimaryKey(String id);

	void insert(Role record);

	Role selectByPrimaryKey(String id);

	void updateByPrimaryKey(Role record);

	R getAllRecordByPage(Role record, int currPage,int size);

	R setDeleted(List<String> ids);
	
	R batchSaveExcel(List<String[]> list);

	List<Role> getRolesByUserId(String userId);

	R listRolesByUserId(String userId, int page, int limit);

	void saveUserToRole(List<MemberRole> baseUserRoleEntityList, String roleId);
}
