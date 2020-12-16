package io.github.springsongs.service.impl;

import java.util.ArrayList;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringRole;
import io.github.springsongs.domain.SpringUserRole;
import io.github.springsongs.dto.SpringRoleDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringRoleMapper;
import io.github.springsongs.mapper.SpringUserRoleMapper;
import io.github.springsongs.service.ISpringRoleService;
import io.github.springsongs.utils.Constant;

@Service
public class SpringRoleServiceImpl implements ISpringRoleService {
	static Logger logger = LoggerFactory.getLogger(SpringRoleServiceImpl.class);

	@Autowired
	private SpringRoleMapper springRoleMapper;

	@Autowired
	private SpringUserRoleMapper springUserRoleMapper;

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
			springRoleMapper.deleteByPrimaryKey(id);
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
	public void insert(SpringRoleDTO record) {
		record.setId(UUID.randomUUID().toString());
		SpringRole springRole = new SpringRole();
		BeanUtils.copyProperties(record, springRole);
		try {
			springRoleMapper.insert(springRole);
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
	public SpringRoleDTO selectByPrimaryKey(String id) {
		SpringRole springRole = null;
		try {
			springRole = springRoleMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringRoleDTO springRoleDTO = new SpringRoleDTO();
		BeanUtils.copyProperties(springRole, springRoleDTO);
		return springRoleDTO;
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
	public void updateByPrimaryKey(SpringRoleDTO springRoleDTO) {
		SpringRole entity = springRoleMapper.selectByPrimaryKey(springRoleDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else if (!entity.getEnableEdit()) {
			throw new SpringSongsException(ResultCode.INFO_CAN_NOT_EDIT);
		} else {
			entity.setTitle(springRoleDTO.getTitle());
			entity.setDescription(springRoleDTO.getDescription());
			entity.setEnableEdit(springRoleDTO.getEnableEdit());
			entity.setEnableDelete(springRoleDTO.getEnableDelete());
			try {
				springRoleMapper.updateByPrimaryKey(entity);
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
	 * @return Page<BaseRoleEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringRoleDTO> getAllRecordByPage(SpringRoleDTO springRoleQuery,int page,int size) {
		if (size> Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		List<SpringRole> springRoles = springRoleMapper.listByPage(springRoleQuery);
		List<SpringRoleDTO> springRoleDTOs = new ArrayList<>();
		springRoles.stream().forEach(springRole -> {
			SpringRoleDTO springRoleDTO = new SpringRoleDTO();
			BeanUtils.copyProperties(springRole, springRoleDTO);
			springRoleDTOs.add(springRoleDTO);
		});
		PageInfo springRolesPageInfo = new PageInfo<>(springRoles);
		springRolesPageInfo.setList(springRoleDTOs);
		return springRolesPageInfo;
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
		List<SpringRole> entityList = springRoleMapper.findAllById(ids);
		for (SpringRole entity : entityList) {
			if (entity.getEnableDelete() == false) {
				throw new SpringSongsException(ResultCode.INFO_CAN_NOT_DELETE);
			}
		}
		try {
			springRoleMapper.setDelete(ids);
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
	public void delete(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > Constant.MAX_ITEM_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		List<SpringRole> entityList = springRoleMapper.findAllById(ids);
		for (SpringRole entity : entityList) {
			if (entity.getEnableDelete() == false) {
				throw new SpringSongsException(ResultCode.INFO_CAN_NOT_DELETE);
			}
		}
		try {
			springRoleMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}

	}

	@Override
	public List<SpringRoleDTO> listByIds(List<String> ids) {
		List<SpringRole> springRoles = springRoleMapper.findAllById(ids);
		List<SpringRoleDTO> springRoleDTOs = new ArrayList<>();
		springRoles.stream().forEach(springRole -> {
			SpringRoleDTO springRoleDTO = new SpringRoleDTO();
			BeanUtils.copyProperties(springRole, springRoleDTO);
			springRoleDTOs.add(springRoleDTO);
		});
		return springRoleDTOs;
	}

	@Transactional
	@Override
	public void delete(Map map) {
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String userId = entry.getKey();
			String roleId = entry.getValue();
			springUserRoleMapper.deleteByUserIdAndRoleId(userId, roleId);
		}
	}

	@Override
	@Transactional
	public void saveUserToRole(List<SpringUserRole> baseUserRoleEntityList, String roleId) {
		springUserRoleMapper.deleteByRoleId(roleId);
		springUserRoleMapper.saveAll(baseUserRoleEntityList);
	}

	@Override
	public PageInfo<SpringRoleDTO> ListRoleByUserId(String userId,int page,int size) {
		PageHelper.startPage(page, size);
		List<SpringRole> springRoles = springRoleMapper.ListRoleByUserId(userId);
		List<SpringRoleDTO> springRoleDTOs = new ArrayList<>();
		springRoles.stream().forEach(springRole -> {
			SpringRoleDTO springRoleDTO = new SpringRoleDTO();
			BeanUtils.copyProperties(springRole, springRoleDTO);
			springRoleDTOs.add(springRoleDTO);
		});
		PageInfo springRolesPageInfo = new PageInfo<>(springRoles);
		springRolesPageInfo.setList(springRoleDTOs);
		return springRolesPageInfo;
	}

	@Transactional
	@Override
	public void deleteUserFromRole(List<String> userIds, String roleId) {
		for (String userId : userIds) {
			springUserRoleMapper.deleteByUserIdAndRoleId(userId, roleId);
		}
	}
}
