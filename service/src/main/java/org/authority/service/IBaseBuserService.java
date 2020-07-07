package org.authority.service;

import java.util.List;

import org.authority.domain.Member;
import org.authority.domain.MemberSecurity;
import org.authority.domain.MemberRole;
import org.authority.util.R;

public interface IBaseBuserService {

	void deleteByPrimaryKey(String id);

	void insert(Member record);

	Member selectByPrimaryKey(String id);

	void updateByPrimaryKey(Member record);

	R getAllRecordByPage(Member record, int currPage,int size);

	R setDeleted(List<String> ids);
	
	R batchSaveExcel(List<String[]> list);

	R getByUserName(String username);

	MemberSecurity findPwdByUserId(String userId);
	
	List<Member> listUsersByIds(List<String> ids);
	
	void saveUserToRole(List<MemberRole> baseUserRoleEntityList, String userId);

	R listUsersByRoleId(String roleId, int page, int limit);
	
	
}
