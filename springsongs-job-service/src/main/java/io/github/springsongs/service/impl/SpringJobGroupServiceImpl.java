package io.github.springsongs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringJobGroup;
import io.github.springsongs.dto.SpringJobGroupDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringJobGroupMapper;
import io.github.springsongs.service.ISpringJobGroupService;
import io.github.springsongs.util.AuthenUtil;
import io.github.springsongs.utils.Constant;

@Service
public class SpringJobGroupServiceImpl implements ISpringJobGroupService {

	static Logger logger = LoggerFactory.getLogger(SpringJobGroupServiceImpl.class);

	@Autowired
	private SpringJobGroupMapper springJobGroupMapper;
	
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
			springJobGroupMapper.deleteByPrimaryKey(id);
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
	public void insert(SpringJobGroupDTO springJobGroupDTO) {
		SpringJobGroup springJobGroup =springJobGroupMapper.getByCode(springJobGroupDTO.getCode());
		if (null!=springJobGroup) {
			throw new SpringSongsException(ResultCode.DATA_EXIST);
		}
		springJobGroupDTO.setId(UUID.randomUUID().toString());
		springJobGroupDTO.setCreatedUserId(authenUtil.getUser().getId());
		springJobGroupDTO.setCreatedBy(authenUtil.getUser().getUserName());
		springJobGroupDTO.setCreatedIp(authenUtil.getUser().getIp());
		springJobGroupDTO.setCreatedOn(new Date());
		SpringJobGroup addSpringJobGroup=new SpringJobGroup();
		BeanUtils.copyProperties(springJobGroupDTO, addSpringJobGroup);
		try {
			springJobGroupMapper.insert(addSpringJobGroup);
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
	public SpringJobGroupDTO selectByPrimaryKey(String id) {
		SpringJobGroup springJobGroup = null;
		try {
			springJobGroup = springJobGroupMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringJobGroupDTO springJobGroupDTO = new SpringJobGroupDTO();
		BeanUtils.copyProperties(springJobGroup, springJobGroupDTO);
		return springJobGroupDTO;
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
	public void updateByPrimaryKey(SpringJobGroupDTO springJobGroupDTO) {
		SpringJobGroup entity = springJobGroupMapper.selectByPrimaryKey(springJobGroupDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			entity.setTitle(springJobGroupDTO.getTitle());
			entity.setUpdatedUserId(authenUtil.getUser().getId());
			entity.setUpdatedBy(authenUtil.getUser().getUserName());
			entity.setUpdatedIp(authenUtil.getUser().getIp());
			entity.setUpdatedOn(new Date());
			try {
				springJobGroupMapper.updateByPrimaryKey(entity);
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
	 * @return Page<BaseSpringJobGroupEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringJobGroupDTO> getAllRecordByPage(SpringJobGroupDTO springJobGroupQuery,int page,int size) {
		if (size>Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		List<SpringJobGroup> springJobGroups = springJobGroupMapper.listByPage(springJobGroupQuery);
		List<SpringJobGroupDTO> springJobGroupDTOs = new ArrayList<>();
		springJobGroups.stream().forEach(springJobGroup -> {
			SpringJobGroupDTO springJobGroupDTO = new SpringJobGroupDTO();
			BeanUtils.copyProperties(springJobGroup, springJobGroupDTO);
			springJobGroupDTOs.add(springJobGroupDTO);
		});
		PageInfo pringAritlcesPageInfo = new PageInfo<>(springJobGroups);
		pringAritlcesPageInfo.setList(springJobGroupDTOs);
		return pringAritlcesPageInfo;
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
		try {
			springJobGroupMapper.setDelete(ids);
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
	public List<SpringJobGroupDTO> listAll() {
		List<SpringJobGroup> springJobGroups = springJobGroupMapper.listAll();
		List<SpringJobGroupDTO> springJobGroupDTOs = new ArrayList<>();
		springJobGroups.stream().forEach(springSystem -> {
			SpringJobGroupDTO springJobGroupDTO = new SpringJobGroupDTO();
			BeanUtils.copyProperties(springSystem, springJobGroupDTO);
			springJobGroupDTOs.add(springJobGroupDTO);
		});
		return springJobGroupDTOs;
	}

}
