package org.authority.service;

import java.util.List;

import org.authority.domain.Module;
import org.authority.domain.ModuleRole;
import org.authority.dto.ElementUiTreeDto;
import org.authority.dto.MenuDto;
import org.authority.util.R;

public interface IBaseModuleService {
	void deleteByPrimaryKey(String id);

	void insert(Module record);

	Module selectByPrimaryKey(String id);

	void updateByPrimaryKey(Module record);

	R getAllRecordByPage(Module record, int currPage,int size);

	void setDeleted(List<String> ids);
	
	R batchSaveExcel(List<String[]> list);

	List<MenuDto> listModuleByUserId(String userId);

	List<ElementUiTreeDto> getModulesByParentId(String parentId, String systemId);

	R listModulesByRoleId(String roleId);

	void saveModuleToRole(List<ModuleRole> baseModuleRoleEntityList, String roleId);
}

