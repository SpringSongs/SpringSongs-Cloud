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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringOrganization;
import io.github.springsongs.dto.SpringOrganizationDTO;
import io.github.springsongs.dto.SpringOrganizationTreeDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringOrganizationMapper;
import io.github.springsongs.service.ISpringOrganizationService;
import io.github.springsongs.util.AuthenUtil;
import io.github.springsongs.utils.Constant;

@Service
public class SpringOrganizationServiceImpl implements ISpringOrganizationService {

	static Logger logger = LoggerFactory.getLogger(SpringDictionaryServiceImpl.class);

	@Autowired
	private SpringOrganizationMapper springOrganizationMapper;

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
			springOrganizationMapper.deleteByPrimaryKey(id);
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
	public void insert(SpringOrganizationDTO record) {
		record.setId(UUID.randomUUID().toString());
		record.setCreatedUserId(authenUtil.getUser().getId());
		record.setCreatedBy(authenUtil.getUser().getUserName());
		record.setCreatedIp(authenUtil.getUser().getIp());
		record.setCreatedOn(new Date());
		SpringOrganization SpringOrganization = new SpringOrganization();
		BeanUtils.copyProperties(record, SpringOrganization);
		try {
			springOrganizationMapper.insert(SpringOrganization);
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
	public SpringOrganizationDTO selectByPrimaryKey(String id) {
		SpringOrganization SpringOrganization = null;
		try {
			SpringOrganization = springOrganizationMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringOrganizationDTO springOrganizationDTO = new SpringOrganizationDTO();
		BeanUtils.copyProperties(SpringOrganization, springOrganizationDTO);
		return springOrganizationDTO;
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
	public void updateByPrimaryKey(SpringOrganizationDTO springOrganizationDTO) {
		SpringOrganization entity = springOrganizationMapper.selectByPrimaryKey(springOrganizationDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			entity.setId(springOrganizationDTO.getId());
			entity.setParentId(springOrganizationDTO.getParentId());
			entity.setCode(springOrganizationDTO.getCode());
			entity.setTitle(springOrganizationDTO.getTitle());
			entity.setDeletedStatus(springOrganizationDTO.getDeletedStatus());
			entity.setUpdatedUserId(authenUtil.getUser().getId());
			entity.setUpdatedBy(authenUtil.getUser().getUserName());
			entity.setUpdatedIp(authenUtil.getUser().getIp());
			entity.setUpdatedOn(new Date());
			try {
				springOrganizationMapper.updateByPrimaryKey(entity);
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
	 * @return Page<BaseSpringOrganizationEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringOrganizationDTO> getAllRecordByPage(SpringOrganizationDTO record,int page,int size) {
		if (size> Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		// Pageable pageable = new PageRequest(currPage - 1, size);
		Page<SpringOrganization> springOrganizations = springOrganizationMapper.listByPage(record);
		List<SpringOrganizationDTO> springOrganizationDTOs = new ArrayList<>();
		springOrganizations.stream().forEach(springOrganization -> {
			SpringOrganizationDTO springOrganizationDTO = new SpringOrganizationDTO();
			BeanUtils.copyProperties(springOrganization, springOrganizationDTO);
			springOrganizationDTOs.add(springOrganizationDTO);
		});
		PageInfo springOrganizationsPageInfo = new PageInfo<>(springOrganizations);
		springOrganizationsPageInfo.setList(springOrganizationDTOs);
		return springOrganizationsPageInfo;
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
		} else if (ids.size() > Constant.MAX_ITEM_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		for (String id : ids) {
			List<SpringOrganization> entitis = springOrganizationMapper.listOrganizationByParentId(id);
			if (entitis.size() > 0) {
				throw new SpringSongsException(ResultCode.HASED_CHILD_IDS_CANNOT_DELETE);
			}
		}
		try {
			springOrganizationMapper.setDelete(ids);
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

	/**
	 * 根据上级主键查询组织机构
	 * 
	 * @param parentId
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public List<SpringOrganizationDTO> listOrganizationsByParent(String parentId) {
		List<SpringOrganization> springOrganizations = springOrganizationMapper.listOrganizationByParentId(parentId);
		List<SpringOrganizationDTO> springOrganizationDTOs = new ArrayList<>();
		springOrganizations.stream().forEach(springOrganization -> {
			SpringOrganizationDTO springOrganizationDTO = new SpringOrganizationDTO();
			BeanUtils.copyProperties(springOrganization, springOrganizationDTO);
			springOrganizationDTOs.add(springOrganizationDTO);
		});
		return springOrganizationDTOs;
	}

	@Override
	public List<SpringOrganizationDTO> listAll() {
		List<SpringOrganization> springOrganizations = springOrganizationMapper.selectAll();
		List<SpringOrganizationDTO> springOrganizationDTOs = new ArrayList<>();
		springOrganizations.stream().forEach(springOrganization -> {
			SpringOrganizationDTO springOrganizationDTO = new SpringOrganizationDTO();
			BeanUtils.copyProperties(springOrganization, springOrganizationDTO);
			springOrganizationDTOs.add(springOrganizationDTO);
		});
		return springOrganizationDTOs;
	}

	@Override
	public List<SpringOrganizationDTO> ListAllToTree() {
		List<SpringOrganization> springOrganizations = springOrganizationMapper.selectAll();
		final List<SpringOrganizationDTO> springOrganizationDTOs = new ArrayList<>();
		springOrganizations.stream().forEach(springOrganization -> {
			SpringOrganizationDTO springOrganizationDTO = new SpringOrganizationDTO();
			BeanUtils.copyProperties(springOrganization, springOrganizationDTO);
			springOrganizationDTOs.add(springOrganizationDTO);
		});
		SpringOrganizationTreeDTO springOrganizationTreeDTO = new SpringOrganizationTreeDTO(springOrganizationDTOs);
		List<SpringOrganizationDTO> springOrganizationTreeDTOs = springOrganizationTreeDTO.builTree();
		return springOrganizationTreeDTOs;
	}

}
