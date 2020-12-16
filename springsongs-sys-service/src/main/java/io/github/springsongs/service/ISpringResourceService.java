package io.github.springsongs.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringResourceRole;
import io.github.springsongs.dto.EasyUiMenuDTO;
import io.github.springsongs.dto.ElementUiTreeDTO;
import io.github.springsongs.dto.MenuDTO;
import io.github.springsongs.dto.MenuRouterDTO;
import io.github.springsongs.dto.ResourceRoleDTO;
import io.github.springsongs.dto.SpringResourceDTO;

public interface ISpringResourceService {
	void deleteByPrimaryKey(String id);

	void insert(SpringResourceDTO record);

	SpringResourceDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringResourceDTO record);

	PageInfo<SpringResourceDTO> getAllRecordByPage(SpringResourceDTO SpringResourceDTOQuery,int page,int size);

	void setDeleted(List<String> ids);

	void batchSaveExcel(List<String[]> list);

	List<MenuDTO> ListModuleByUserId(String userId);

	public List<ResourceRoleDTO> listAllRoleModules(List<String> roleCode);

	void delete(List<String> ids);

	List<SpringResourceDTO> listByIds(List<String> ids);

	List<ElementUiTreeDTO> getModulesByParentId(String parentId, String systemId);

	void delete(Map map);

	void saveModuleToRole(List<SpringResourceRole> baseModuleRoleEntityList, String roleId);

	void saveModuleToRole(String moduleId, String roleId);

	void deleteByRoleIdAndModuleId(String roleId, String moduleId);

	public List<SpringResourceRole> listModulesByRoleId(String roleId);

	public List<MenuRouterDTO> listResourceByUserId(String userId);

	public List<EasyUiMenuDTO> listEasyUiResourceByUserId(String userId);

	List<SpringResourceDTO> ListAllToTree(String systemCode);
}
