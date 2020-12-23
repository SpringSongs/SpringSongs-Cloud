package io.github.springsongs.service.impl;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringUser;
import io.github.springsongs.domain.SpringUserRole;
import io.github.springsongs.domain.SpringUserSecurity;
import io.github.springsongs.dto.SpringUserDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringUserMapper;
import io.github.springsongs.mapper.SpringUserRoleMapper;
import io.github.springsongs.mapper.SpringUserSecurityMapper;
import io.github.springsongs.service.ISpringUserService;
import io.github.springsongs.util.AuthenUtil;
import io.github.springsongs.utils.Constant;

@Service
public class SpringUserServiceImpl implements ISpringUserService {

	static Logger logger = LoggerFactory.getLogger(SpringRoleServiceImpl.class);

	@Autowired
	private SpringUserMapper springUserMapper;

	@Autowired
	private SpringUserSecurityMapper springUserSecurityMapper;

	@Autowired
	private SpringUserRoleMapper springUserRoleMapper;

	@Autowired
	private ISpringUserService springUserService;
	
	@Autowired
	private AuthenUtil authenUtil;

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
			springUserMapper.deleteByPrimaryKey(id);
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
	@Transactional
	@Override
	public void insert(SpringUserDTO record) {
		record.setId(UUID.randomUUID().toString());
		record.setCreatedUserId(authenUtil.getUser().getId());
		record.setCreatedBy(authenUtil.getUser().getUserName());
		record.setCreatedIp(authenUtil.getUser().getIp());
		record.setCreatedOn(new Date());
		SpringUser springUserDo = springUserMapper.getByUserName(record.getUserName());
		if (null != springUserDo) {
			throw new SpringSongsException(ResultCode.ACCOUNT_HAS_REGISTER);
		}
		SpringUser springUser = new SpringUser();
		BeanUtils.copyProperties(record, springUser);
		try {
			springUserMapper.insert(springUser);
			SpringUserSecurity springUserSecurity = new SpringUserSecurity();
			springUserSecurity.setUserId(springUser.getId());
			springUserSecurity.setPwd(record.getPassword());
			springUserSecurity.setCreatedBy(record.getUserName());
			springUserSecurity.setCreatedUserId(record.getId());
			springUserSecurity.setCreatedIp(record.getCreatedIp());
			springUserSecurity.setCreatedOn(record.getCreatedOn());
			springUserService.setPwd(springUserSecurity);
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
	public SpringUserDTO selectByPrimaryKey(String id) {
		SpringUser springUser = null;
		try {
			springUser = springUserMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringUserDTO springUserDTO = new SpringUserDTO();
		BeanUtils.copyProperties(springUser, springUserDTO);
		return springUserDTO;
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
	public void updateByPrimaryKey(SpringUserDTO springUserDTO) {
		SpringUser entity = springUserMapper.selectByPrimaryKey(springUserDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else if (!entity.getEnableEdit()) {
			throw new SpringSongsException(ResultCode.INFO_CAN_NOT_EDIT);
		} else {
			entity.setTrueName(springUserDTO.getTrueName());
			entity.setEmail(springUserDTO.getEmail());
			entity.setMobile(springUserDTO.getMobile());
			entity.setOrganizationId(springUserDTO.getOrganizationId());
			entity.setOrganizationName(springUserDTO.getOrganizationName());
			entity.setEnableEdit(springUserDTO.getEnableEdit());
			entity.setEnableDelete(springUserDTO.getEnableDelete());
			entity.setUpdatedUserId(authenUtil.getUser().getId());
			entity.setUpdatedBy(authenUtil.getUser().getUserName());
			entity.setUpdatedIp(authenUtil.getUser().getIp());
			entity.setUpdatedOn(new Date());
			try {
				springUserMapper.updateByPrimaryKey(entity);
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
	 * @return Page<BaseBuserEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringUserDTO> getAllRecordByPage(SpringUserDTO springUserQuery, int page, int size) {
		if (size > Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		List<SpringUser> springUsers = springUserMapper.listByPage(springUserQuery);
		List<SpringUserDTO> springUserDTOs = new ArrayList<>();
		springUsers.stream().forEach(springUser -> {
			SpringUserDTO springUserDTO = new SpringUserDTO();
			BeanUtils.copyProperties(springUser, springUserDTO);
			springUserDTOs.add(springUserDTO);
		});
		PageInfo springUsersPageInfo = new PageInfo<>(springUsers);
		springUsersPageInfo.setList(springUserDTOs);
		return springUsersPageInfo;
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
		List<SpringUser> entityList = springUserMapper.findAllById(ids);
		for (SpringUser entity : entityList) {
			if (entity.getEnableDelete() == false) {
				throw new SpringSongsException(ResultCode.INFO_CAN_NOT_DELETE);
			}
		}
		try {
			springUserMapper.setDelete(ids);
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
	public List<SpringUserDTO> listUserByIds(List<String> ids) {
		List<SpringUser> springUsers = springUserMapper.listUserByIds(ids);
		List<SpringUserDTO> springUserDTOs = new ArrayList<>();
		springUsers.stream().forEach(springSystem -> {
			SpringUserDTO springUserDTO = new SpringUserDTO();
			BeanUtils.copyProperties(springSystem, springUserDTO);
			springUserDTOs.add(springUserDTO);
		});
		return springUserDTOs;
	}

	@Override
	public SpringUserDTO getByUserName(String username) {
		SpringUser springUser = springUserMapper.getByUserName(username);
		if (null != springUser) {
			throw new SpringSongsException(ResultCode.ACCOUNT_HAS_REGISTER);
		}
		SpringUserDTO springUserDTO = new SpringUserDTO();
		BeanUtils.copyProperties(springUser, springUserDTO);
		return springUserDTO;
	}

	@Override
	public void setPwd(SpringUserSecurity viewEntity) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		SpringUser entity = springUserMapper.selectByPrimaryKey(viewEntity.getUserId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			SpringUserSecurity baseUserLogOnEntity = springUserSecurityMapper.findByUserId(viewEntity.getUserId());
			if (null != baseUserLogOnEntity) {
				viewEntity.setId(baseUserLogOnEntity.getId());
				viewEntity.setCreatedBy(baseUserLogOnEntity.getCreatedBy());
				viewEntity.setCreatedUserId(baseUserLogOnEntity.getCreatedUserId());
				viewEntity.setCreatedIp(baseUserLogOnEntity.getCreatedIp());
				viewEntity.setCreatedOn(baseUserLogOnEntity.getCreatedOn());
			}
			viewEntity.setPwd(passwordEncoder.encode(viewEntity.getPwd().trim()));
			try {
				if (null == baseUserLogOnEntity) {
					springUserSecurityMapper.insert(viewEntity);
				} else {
					springUserSecurityMapper.updateByPrimaryKey(viewEntity);
				}
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		}
	}

	@Override
	public void delete(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > Constant.MAX_ITEM_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		List<SpringUser> entityList = springUserMapper.findAllById(ids);
		for (SpringUser entity : entityList) {
			if (entity.getEnableDelete() == false) {
				throw new SpringSongsException(ResultCode.INFO_CAN_NOT_DELETE);
			}
		}
		try {
			springUserMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public PageInfo<SpringUserDTO> ListUsersByRoleId(String roleId, int page, int size) {
		PageHelper.startPage(page, size);
		Page<SpringUser> springUsers = springUserMapper.listUsersByRoleId(roleId);
		List<SpringUserDTO> springUserDTOs = new ArrayList<>();
		springUsers.stream().forEach(springUser -> {
			SpringUserDTO springUserDTO = new SpringUserDTO();
			BeanUtils.copyProperties(springUser, springUserDTO);
			springUserDTOs.add(springUserDTO);
		});
		PageInfo springUsersPageInfo = new PageInfo<>(springUsers);
		springUsersPageInfo.setList(springUserDTOs);
		return springUsersPageInfo;
	}

	@Transactional
	@Override
	public void delete(Map map) {
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String roleId = entry.getKey();
			String userId = entry.getValue();
			springUserRoleMapper.deleteByUserIdAndRoleId(userId, roleId);
		}
	}

	@Override
	public void saveUserToRole(List<SpringUserRole> baseUserRoleEntityList, String userId) {
		springUserRoleMapper.deleteByUserId(userId);
		springUserRoleMapper.saveAll(baseUserRoleEntityList);
	}
}