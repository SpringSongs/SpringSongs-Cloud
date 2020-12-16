package io.github.springsongs.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringResource;
import io.github.springsongs.domain.SpringResourceRole;
import io.github.springsongs.dto.EasyUiMenuDTO;
import io.github.springsongs.dto.EasyUiMenuDTO.MenuAttributes;
import io.github.springsongs.dto.EasyUiTreeMenuDTO;
import io.github.springsongs.dto.ElementUiTreeDTO;
import io.github.springsongs.dto.MenuDTO;
import io.github.springsongs.dto.MenuRouterDTO;
import io.github.springsongs.dto.MenuRouterDTO.Meta;
import io.github.springsongs.dto.MenuRouterTreeDTO;
import io.github.springsongs.dto.ResourceRoleDTO;
import io.github.springsongs.dto.SpringResourceDTO;
import io.github.springsongs.dto.SpringResourceTreeDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringResourceMapper;
import io.github.springsongs.mapper.SpringResourceRoleMapper;
import io.github.springsongs.service.ISpringResourceService;
import io.github.springsongs.utils.Constant;

@Service
public class SpringResourceServiceImpl implements ISpringResourceService {
	static Logger logger = LoggerFactory.getLogger(SpringResourceServiceImpl.class);
	@Autowired
	private SpringResourceMapper springResourceDao;

	@Autowired
	private SpringResourceRoleMapper springResourceRoleDao;

	/**
	 *
	 * 物理删除
	 * 
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void deleteByPrimaryKey(String id) {
		try {
			springResourceDao.deleteByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	/**
	 *
	 * 保存
	 * 
	 * @param record
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void insert(SpringResourceDTO record) {
		record.setId(UUID.randomUUID().toString());
		SpringResource springResource = new SpringResource();
		if (StringUtils.isEmpty(record.getParentId())) {
			record.setParentId("0");
		}
		BeanUtils.copyProperties(record, springResource);
		try {
			springResourceDao.insert(springResource);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	/**
	 *
	 * 获取单项
	 * 
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public SpringResourceDTO selectByPrimaryKey(String id) {
		SpringResource springResource = null;
		try {
			springResource = springResourceDao.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringResourceDTO springResourceDTO = new SpringResourceDTO();
		BeanUtils.copyProperties(springResource, springResourceDTO);
		return springResourceDTO;
	}

	/**
	 *
	 * 更新
	 * 
	 * @param record
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void updateByPrimaryKey(SpringResourceDTO springResourceDTO) {
		SpringResource entity = springResourceDao.selectByPrimaryKey(springResourceDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else if (!entity.getEnableEdit()) {
			throw new SpringSongsException(ResultCode.INFO_CAN_NOT_EDIT);
		} else {
			entity.setCode(springResourceDTO.getCode());
			entity.setTitle(springResourceDTO.getTitle());
			entity.setMenuFlag(springResourceDTO.getMenuFlag());
			entity.setVueUrl(springResourceDTO.getVueUrl());
			entity.setVueIcon(springResourceDTO.getVueIcon());
			entity.setAngularUrl(springResourceDTO.getAngularUrl());
			entity.setParentId(springResourceDTO.getParentId());
			entity.setParentName(springResourceDTO.getParentName());
			entity.setSortCode(springResourceDTO.getSortCode());
			entity.setShowStatus(springResourceDTO.getShowStatus());
			entity.setEnableEdit(springResourceDTO.getEnableEdit());
			entity.setEnableDelete(springResourceDTO.getEnableDelete());
			try {
				springResourceDao.updateByPrimaryKey(entity);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		}
	}

	/**
	 *
	 * 分页查询
	 * 
	 * @param record
	 * @return Page<BaseModuleEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringResourceDTO> getAllRecordByPage(SpringResourceDTO springResourceQuery,int page,int size) {

		if (size > Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}

		PageHelper.startPage(page, size);
		// Pageable pageable = PageRequest.of(currPage - 1, size);
		// return springResourceDao.findAll(specification, pageable);
		List<SpringResource> springResources = springResourceDao.listByPage(springResourceQuery);
		List<SpringResourceDTO> springResourceDTOs = new ArrayList<>();
		springResources.stream().forEach(springResource -> {
			SpringResourceDTO springResourceDTO = new SpringResourceDTO();
			BeanUtils.copyProperties(springResource, springResourceDTO);
			springResourceDTOs.add(springResourceDTO);
		});
		PageInfo springResourcesPageInfo = new PageInfo<>(springResources);
		springResourcesPageInfo.setList(springResourceDTOs);
		return springResourcesPageInfo;
	}

	/**
	 *
	 * 逻辑删除
	 * 
	 * @param record
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void setDeleted(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > 1000) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		List<SpringResource> springResourceList = springResourceDao.getInParentId(ids);
		if (!CollectionUtils.isEmpty(springResourceList)) {
			throw new SpringSongsException(ResultCode.HASED_CHILD_IDS_CANNOT_DELETE);
		}
		List<SpringResource> entityList = springResourceDao.findAllById(ids);
		for (SpringResource entity : entityList) {
			if (entity.getEnableDelete() == false) {
				throw new SpringSongsException(ResultCode.INFO_CAN_NOT_DELETE);
			}
		}
		try {
			springResourceDao.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	/**
	 *
	 * Excel批量保存
	 * 
	 * @param list
	 * @return R
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void batchSaveExcel(List<String[]> list) {

	}

	@Override
	public List<MenuDTO> ListModuleByUserId(String userId) {
		List<SpringResource> modules = springResourceDao.listModuleByUserId(userId);
		return getSoredModules(modules);
	}

	public List<MenuDTO> getSoredModules(List<SpringResource> modules) {
		List<SpringResource> parentModules = modules.stream().filter((SpringResource m) -> m.getParentId().equals("0")
				&& m.getMenuFlag() == true && m.getDeletedStatus() == false).collect(toList());
		List<SpringResource> secondModules = modules.stream().filter((SpringResource m) -> !m.getParentId().equals("0")
				&& m.getMenuFlag() == true && m.getDeletedStatus() == false).collect(toList());
		List<MenuDTO> menuDtoList = this.getSecondModules(parentModules, secondModules);
		return menuDtoList;
	}

	public List<MenuDTO> getSecondModules(List<SpringResource> parentModules, List<SpringResource> secondModules) {
		List<MenuDTO> menuDtoList = new ArrayList<>();
		for (int i = 0; i < parentModules.size(); i++) {
			MenuDTO menuDto = new MenuDTO();
			menuDto.setId(parentModules.get(i).getId().toString());
			menuDto.setIcon("");
			menuDto.setLink(parentModules.get(i).getVueUrl());
			menuDto.setTitle(parentModules.get(i).getTitle());
			menuDto.setCode(parentModules.get(i).getCode());
			menuDto.setIndex(parentModules.get(i).getSortCode());
			menuDtoList.add(menuDto);
			List<MenuDTO> secondMenuDtoList = new ArrayList<>();
			for (SpringResource secondModuleEntity : secondModules) {
				if (parentModules.get(i).getId().equals(secondModuleEntity.getParentId())) {
					MenuDTO secondMenuDto = new MenuDTO();
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
	public void delete(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > Constant.MAX_ITEM_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		List<SpringResource> entityList = springResourceDao.findAllById(ids);
		for (SpringResource entity : entityList) {
			if (entity.getEnableDelete() == false) {
				throw new SpringSongsException(ResultCode.INFO_CAN_NOT_DELETE);
			}
		}
		try {
			springResourceDao.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public List<SpringResourceDTO> listByIds(List<String> ids) {
		List<SpringResource> springResources = springResourceDao.findAllById(ids);
		List<SpringResourceDTO> springResourceDTOs = new ArrayList<>();
		springResources.stream().forEach(springResource -> {
			SpringResourceDTO springResourceDTO = new SpringResourceDTO();
			BeanUtils.copyProperties(springResource, springResourceDTO);
			springResourceDTOs.add(springResourceDTO);
		});
		return springResourceDTOs;
	}

	@Override
	public List<ElementUiTreeDTO> getModulesByParentId(String parentId, String systemId) {
		List<SpringResource> baseModulesEntityList = springResourceDao.getByParentId(parentId, systemId);
		List<ElementUiTreeDTO> elementUiTreeDtoList = new ArrayList<ElementUiTreeDTO>();
		List<String> ids = new ArrayList<String>();
		for (SpringResource entity : baseModulesEntityList) {
			ids.add(entity.getId());
		}
		if (ids.size() > 0) {
			List<SpringResource> baseModulesEntityList1 = springResourceDao.getInParentId(ids);
			for (SpringResource entity : baseModulesEntityList) {
				ElementUiTreeDTO elementUiTreeDto = new ElementUiTreeDTO();
				elementUiTreeDto.setId(entity.getId());
				elementUiTreeDto.setLeaf(true);
				elementUiTreeDto.setName(entity.getTitle());
				for (SpringResource entity1 : baseModulesEntityList1) {
					if (entity.getId().equals(entity1.getParentId())) {
						elementUiTreeDto.setLeaf(false);
						break;
					}
				}
				elementUiTreeDtoList.add(elementUiTreeDto);
			}
		}
		return elementUiTreeDtoList;
	}

	@Override
	public void delete(Map map) {
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String roleId = entry.getKey();
			String moduleId = entry.getValue();
			springResourceRoleDao.delete(roleId, moduleId);
		}
	}

	@Override
	@Transactional
	public void saveModuleToRole(List<SpringResourceRole> baseModuleRoleEntityList, String roleId) {
		springResourceRoleDao.delete(roleId, roleId);
		springResourceRoleDao.saveAll(baseModuleRoleEntityList);
	}

	@Override
	public List<SpringResourceRole> listModulesByRoleId(String roleId) {
		return springResourceRoleDao.listModulesByRoleId(roleId);
	}

	@Override
	public List<ResourceRoleDTO> listAllRoleModules(List<String> roleCode) {
		return springResourceDao.listAllRoleModules(roleCode);
	}

	@Override
	public List<MenuRouterDTO> listResourceByUserId(String userId) {
		// List<MenuRouterTreeDTO> menuRouterTreeDTOList=new ArrayList<>();
		List<SpringResource> springResourceList = springResourceDao.listModuleByUserId(userId);
		final List<MenuRouterDTO> menuRouterDTOList = new ArrayList<>();
		springResourceList.stream().forEach(springResource -> {
			MenuRouterDTO node = new MenuRouterDTO();
			node.setId(springResource.getId());
			node.setParentId(springResource.getParentId());
			Meta metaTitle = new Meta();
			metaTitle.setTitle(springResource.getTitle());
			metaTitle.setIcon(springResource.getVueIcon());
			node.setMeta(metaTitle);
			node.setPath(springResource.getCode());
			node.setName(springResource.getCode());
			node.setComponent(springResource.getVueUrl());
			node.setRedirect("");
			node.setHidden(springResource.getShowStatus());
			menuRouterDTOList.add(node);
		});
		MenuRouterTreeDTO menuRouterTreeDTO = new MenuRouterTreeDTO(menuRouterDTOList);
		List<MenuRouterDTO> menuRouterDTOListTree = new ArrayList<>();
		menuRouterDTOListTree = menuRouterTreeDTO.builTree();
		return menuRouterDTOListTree;
	}

	@Override
	public List<SpringResourceDTO> ListAllToTree(String systemCode) {
		List<SpringResource> springResourceList = springResourceDao.listAllResources(systemCode);
		final List<SpringResourceDTO> springResourceDTOList = new ArrayList<>();
		springResourceList.stream().forEach(springResource -> {
			SpringResourceDTO springResourceDTO = new SpringResourceDTO();
			BeanUtils.copyProperties(springResource, springResourceDTO);
			springResourceDTOList.add(springResourceDTO);
		});
		SpringResourceTreeDTO springResourceTreeDTO = new SpringResourceTreeDTO(springResourceDTOList);
		List<SpringResourceDTO> springResourceDTOTreeList = new ArrayList<>();
		springResourceDTOTreeList = springResourceTreeDTO.builTree();
		return springResourceDTOTreeList;
	}

	@Override
	public List<EasyUiMenuDTO> listEasyUiResourceByUserId(String userId) {
		List<SpringResource> springResourceList = springResourceDao.listModuleByUserId(userId);
		final List<EasyUiMenuDTO> easyUiMenuDTOList = new ArrayList<>();
		springResourceList.stream().forEach(springResource -> {
			EasyUiMenuDTO easyUiMenuDTO = new EasyUiMenuDTO();
			easyUiMenuDTO.setId(springResource.getId());
			easyUiMenuDTO.setParentId(springResource.getParentId());
			easyUiMenuDTO.setIconCls(springResource.getAngularIcon());
			easyUiMenuDTO.setState("open");
			easyUiMenuDTO.setText(springResource.getTitle());
			MenuAttributes attributes = new MenuAttributes();
			attributes.setUrl(springResource.getAngularUrl());
			easyUiMenuDTO.setAttributes(attributes);
			easyUiMenuDTOList.add(easyUiMenuDTO);
		});
		EasyUiTreeMenuDTO easyUiTreeMenuDTO = new EasyUiTreeMenuDTO(easyUiMenuDTOList);
		List<EasyUiMenuDTO> easyUiMenuDTOTEMPList = new ArrayList<>();
		easyUiMenuDTOTEMPList = easyUiTreeMenuDTO.builTree();
		return easyUiMenuDTOTEMPList;
	}

	@Override
	public void saveModuleToRole(String moduleId, String roleId) {
		SpringResourceRole springResourceRole = springResourceRoleDao.findByRoleIdAndModuleId(roleId, moduleId);
		if (null == springResourceRole) {
			SpringResourceRole springResourceRoleDO = new SpringResourceRole();
			springResourceRoleDO.setRoleId(roleId);
			springResourceRoleDO.setModuleId(moduleId);
			springResourceRoleDO.setCreatedOn(new Date());
			springResourceRoleDao.insert(springResourceRoleDO);
		}
	}

	@Override
	public void deleteByRoleIdAndModuleId(String roleId, String moduleId) {
		try {
			springResourceRoleDao.deleteByRoleIdAndModuleId(roleId, moduleId);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}
}
