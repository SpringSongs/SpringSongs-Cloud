package org.authority.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import org.authority.domain.Module;
import org.authority.domain.ModuleRole;
import org.authority.dto.ElementUiTreeDto;
import org.authority.dto.MenuDto;
import org.authority.mapper.BaseModuleMapper;
import org.authority.mapper.BaseModuleRoleMapper;
import org.authority.service.IBaseModuleService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BaseModuleServiceImpl implements IBaseModuleService {

	@Autowired
	private BaseModuleMapper baseModuleDao;
	
	@Autowired
	private BaseModuleRoleMapper baseModuleRoleEntityDao;

	@Override
	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		baseModuleDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Module record) {
		// TODO Auto-generated method stub
		baseModuleDao.insert(record);
	}

	@Override
	public Module selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return baseModuleDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(Module record) {
		// TODO Auto-generated method stub
		baseModuleDao.updateByPrimaryKey(record);
	}

	@Override
	public R getAllRecordByPage(Module record, int currPage, int size) {
		PageHelper.startPage(currPage, size);
		Page<Module> list = baseModuleDao.ListByPage(record);
		R r = new R();
		r.put("data", list.getResult());
		r.put("msg", list.getPageNum());
		r.put("code", 200);
		r.put("count", list.getTotal());
		return r;
	}

	@Override
	public void setDeleted(List<String> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public R batchSaveExcel(List<String[]> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuDto> listModuleByUserId(String userId) {
		List<Module> modules = baseModuleDao.listModuleByUserId(userId);
		List<MenuDto> menuDtoList=getSoredModules(modules);
		return menuDtoList;
	}
	
	public List<MenuDto> getSoredModules(List<Module> modules) {
		List<Module> parentModules = modules.stream()
				.filter((Module m) -> m.getParentId().equals("0") && m.getMenuFlag() == true
						&& m.getDeletedFlag() == false)
				.collect(toList());
		List<Module> secondModules = modules.stream()
				.filter((Module m) -> !m.getParentId().equals("0") && m.getMenuFlag() == true
						&& m.getDeletedFlag() == false)
				.collect(toList());
		List<MenuDto> menuDtoList = this.getSecondModules(parentModules, secondModules);
		return menuDtoList;
	}

	public List<MenuDto> getSecondModules(List<Module> parentModules, List<Module> secondModules) {
		List<MenuDto> menuDtoList = new ArrayList<>();
		for (int i = 0; i < parentModules.size(); i++) {
			MenuDto menuDto = new MenuDto();
			menuDto.setId(parentModules.get(i).getId().toString());
			menuDto.setIcon("");
			menuDto.setLink(parentModules.get(i).getVueUrl());
			menuDto.setTitle(parentModules.get(i).getTitle());
			menuDto.setCode(parentModules.get(i).getCode());
			menuDto.setIndex(parentModules.get(i).getSortCode());
			menuDtoList.add(menuDto);
			List<MenuDto> secondMenuDtoList = new ArrayList<>();
			for (Module secondModuleEntity : secondModules) {
				if (parentModules.get(i).getId().equals(secondModuleEntity.getParentId())) {
					MenuDto secondMenuDto = new MenuDto();
					secondMenuDto.setId(secondModuleEntity.getId().toString());
					secondMenuDto.setIcon("");
					secondMenuDto.setLink(secondModuleEntity.getVueUrl());
					secondMenuDto.setTitle(secondModuleEntity.getTitle());
					secondMenuDto.setCode(secondModuleEntity.getCode());
					secondMenuDto.setIndex(secondModuleEntity.getSortCode());
					secondMenuDtoList.add(secondMenuDto);
				}
			}
			menuDtoList.get(i).setMenuDtoList(secondMenuDtoList);
		}

		return menuDtoList;
	}

	@Override
	public List<ElementUiTreeDto> getModulesByParentId(String parentId, String systemId) {
		List<Module> baseModulesEntityList = baseModuleDao.getByParentId(parentId, systemId);
		List<ElementUiTreeDto> elementUiTreeDtoList = new ArrayList<ElementUiTreeDto>();
		List<String> ids = new ArrayList<String>();
		for (Module entity : baseModulesEntityList) {
			ids.add(entity.getId());
		}
		List<Module> baseModulesEntityList1 = baseModuleDao.getInParentId(ids);
		for (Module entity : baseModulesEntityList) {
			ElementUiTreeDto elementUiTreeDto = new ElementUiTreeDto();
			elementUiTreeDto.setId(entity.getId());
			elementUiTreeDto.setLeaf(true);
			elementUiTreeDto.setName(entity.getTitle());
			for (Module entity1 : baseModulesEntityList1) {
				if (entity.getId().equals(entity1.getParentId())) {
					elementUiTreeDto.setLeaf(false);
					break;
				}
			}
			elementUiTreeDtoList.add(elementUiTreeDto);
		}
		return elementUiTreeDtoList;
	}

	@Override
	public R listModulesByRoleId(String roleId) {
		R r=new R();
		List<ModuleRole> baseModuleRoleEntityList= baseModuleRoleEntityDao.listModulesByRoleId(roleId);
		List<String> moduleIds = new ArrayList<String>();
		baseModuleRoleEntityList.stream().forEach(item -> {
			moduleIds.add(item.getModuleId());
		});
		r.put("msg", "授权成功!");
		r.put("code", 200);
		r.put("data", moduleIds);
		return r;
	}

	@Override
	public void saveModuleToRole(List<ModuleRole> baseModuleRoleEntityList, String roleId) {
		baseModuleRoleEntityDao.deleteByRoleId(roleId);
		baseModuleRoleEntityDao.saveAll(baseModuleRoleEntityList);
	}

}
