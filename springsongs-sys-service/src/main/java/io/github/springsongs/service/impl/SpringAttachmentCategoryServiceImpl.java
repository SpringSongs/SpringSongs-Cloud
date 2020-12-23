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

import io.github.springsongs.domain.SpringAttachmentCategory;
import io.github.springsongs.dto.SpringAttachmentCategoryDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringAttachmentCategoryMapper;
import io.github.springsongs.service.ISpringAttachmentCategoryService;
import io.github.springsongs.util.AuthenUtil;
import io.github.springsongs.utils.Constant;

@Service
public class SpringAttachmentCategoryServiceImpl implements ISpringAttachmentCategoryService {

	static Logger logger = LoggerFactory.getLogger(SpringAttachmentCategoryServiceImpl.class);

	@Autowired
	private SpringAttachmentCategoryMapper springAttachmentCategoryMapper;

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
			springAttachmentCategoryMapper.deleteByPrimaryKey(id);
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
	public void insert(SpringAttachmentCategoryDTO record) {
		record.setId(UUID.randomUUID().toString());
		record.setCreatedUserId(authenUtil.getUser().getId());
		record.setCreatedBy(authenUtil.getUser().getUserName());
		record.setCreatedIp(authenUtil.getUser().getIp());
		record.setCreatedOn(new Date());
		SpringAttachmentCategory SpringAttachmentCategory = new SpringAttachmentCategory();
		BeanUtils.copyProperties(record, SpringAttachmentCategory);
		try {
			springAttachmentCategoryMapper.insert(SpringAttachmentCategory);
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
	public SpringAttachmentCategoryDTO selectByPrimaryKey(String id) {
		SpringAttachmentCategoryDTO springAttachmentCategoryDTO = new SpringAttachmentCategoryDTO();
		SpringAttachmentCategory springAttachmentCategory = springAttachmentCategoryMapper.selectByPrimaryKey(id);
		BeanUtils.copyProperties(springAttachmentCategory, springAttachmentCategoryDTO);
		return springAttachmentCategoryDTO;
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
	public void updateByPrimaryKey(SpringAttachmentCategoryDTO springAttachmentCategoryDTO) {
		SpringAttachmentCategory entity = springAttachmentCategoryMapper.selectByPrimaryKey(springAttachmentCategoryDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			entity.setTitle(springAttachmentCategoryDTO.getTitle());
			entity.setDescription(springAttachmentCategoryDTO.getDescription());
			entity.setDictionaryCode(springAttachmentCategoryDTO.getDictionaryCode());
			entity.setDictionaryName(springAttachmentCategoryDTO.getDictionaryName());
			entity.setUpdatedUserId(authenUtil.getUser().getId());
			entity.setUpdatedBy(authenUtil.getUser().getUserName());
			entity.setUpdatedIp(authenUtil.getUser().getIp());
			entity.setUpdatedOn(new Date());
			try {
				springAttachmentCategoryMapper.updateByPrimaryKey(entity);
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
	 * @return Page<BaseFolderEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringAttachmentCategoryDTO> getAllRecordByPage(SpringAttachmentCategoryDTO record, int page,int size) {
		if (size>Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		List<SpringAttachmentCategory> springAttachmentCategorys = springAttachmentCategoryMapper.listByPage(record);
		List<SpringAttachmentCategoryDTO> springAttachmentCategoryDTOs = new ArrayList<>();
		springAttachmentCategorys.stream().forEach(springAttachmentCategory -> {
			SpringAttachmentCategoryDTO springAttachmentCategoryDTO = new SpringAttachmentCategoryDTO();
			BeanUtils.copyProperties(springAttachmentCategory, springAttachmentCategoryDTO);
			springAttachmentCategoryDTOs.add(springAttachmentCategoryDTO);
		});
		PageInfo springAttachmentCategorysPageInfo = new PageInfo<>(springAttachmentCategorys);
		springAttachmentCategorysPageInfo.setList(springAttachmentCategoryDTOs);
		return springAttachmentCategorysPageInfo;
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
		for (String id : ids) {
			List<SpringAttachmentCategory> springAttachmentCategorys = springAttachmentCategoryMapper
					.listSpringAttachmentCategoryByParentId(id);
			if (!springAttachmentCategorys.isEmpty()) {
				throw new SpringSongsException(ResultCode.HASED_CHILD_IDS_CANNOT_DELETE);
			}
		}
		try {
			springAttachmentCategoryMapper.setDelete(ids);
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
		for (String id : ids) {
			List<SpringAttachmentCategory> springAttachmentCategorys = springAttachmentCategoryMapper
					.listSpringAttachmentCategoryByParentId(id);
			if (springAttachmentCategorys.isEmpty()) {
				throw new SpringSongsException(ResultCode.HASED_CHILD_IDS_CANNOT_DELETE);
			}
		}
		try {
			springAttachmentCategoryMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public List<SpringAttachmentCategoryDTO> listSpringAttachmentCategoryByParentId(String parentId) {
		List<SpringAttachmentCategory> springAttachmentCategorys = springAttachmentCategoryMapper
				.listSpringAttachmentCategoryByParentId(parentId);
		List<SpringAttachmentCategoryDTO> springAttachmentCategoryDTOs = new ArrayList<>();
		springAttachmentCategoryDTOs.stream().forEach(springSystem -> {
			SpringAttachmentCategoryDTO springAttachmentCategoryDTO = new SpringAttachmentCategoryDTO();
			BeanUtils.copyProperties(springSystem, springAttachmentCategoryDTO);
			springAttachmentCategoryDTOs.add(springAttachmentCategoryDTO);
		});
		return springAttachmentCategoryDTOs;
	}

}
