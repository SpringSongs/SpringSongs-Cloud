package io.github.springsongs.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringUserRole;
import io.github.springsongs.domain.SpringUserSecurity;
import io.github.springsongs.dto.SpringUserDTO;

public interface ISpringUserService {

	void deleteByPrimaryKey(String id);

	void insert(SpringUserDTO record);

	SpringUserDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringUserDTO record);

	PageInfo<SpringUserDTO> getAllRecordByPage(SpringUserDTO SpringUserDTOQuery,int page,int size);

	void setDeleted(List<String> ids);

	void batchSaveExcel(List<String[]> list);

	List<SpringUserDTO> listUserByIds(List<String> ids);

	SpringUserDTO getByUserName(String username);

	void setPwd(SpringUserSecurity entity);

	void delete(List<String> ids);

	public PageInfo<SpringUserDTO> ListUsersByRoleId(String roleId, int page, int size);

	void delete(Map map);

	void saveUserToRole(List<SpringUserRole> baseUserRoleEntityList, String userId);
}
