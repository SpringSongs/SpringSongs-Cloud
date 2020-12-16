package io.github.springsongs.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringUserRole;
import io.github.springsongs.dto.SpringRoleDTO;

public interface ISpringRoleService {
	void deleteByPrimaryKey(String id);

	void insert(SpringRoleDTO record);

	SpringRoleDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringRoleDTO record);

	PageInfo<SpringRoleDTO> getAllRecordByPage(SpringRoleDTO SpringRoleDTOQuery,int page,int size);

	void setDeleted(List<String> ids);

	void batchSaveExcel(List<String[]> list);

	void delete(List<String> ids);

	List<SpringRoleDTO> listByIds(List<String> ids);

	void delete(Map map);

	void saveUserToRole(List<SpringUserRole> baseUserRoleEntityList, String userId);
	
	void deleteUserFromRole(List<String> userIds, String roleId);

	PageInfo<SpringRoleDTO> ListRoleByUserId(String userId,int page,int size);
}
