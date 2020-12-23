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

import io.github.springsongs.domain.SpringAttachment;
import io.github.springsongs.dto.SpringAttachmentDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.service.ISpringAttachmentService;
import io.github.springsongs.util.AuthenUtil;
import io.github.springsongs.utils.Constant;

@Service
public class SpringAttachmentServiceImpl implements ISpringAttachmentService {

	static Logger logger = LoggerFactory.getLogger(SpringAttachmentCategoryServiceImpl.class);

	@Autowired
	private io.github.springsongs.mapper.SpringAttachmentMapper springAttachmentMapper;

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
			springAttachmentMapper.deleteByPrimaryKey(id);
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
	public void insert(SpringAttachmentDTO record) {
		record.setId(UUID.randomUUID().toString());
		record.setCreatedUserId(authenUtil.getUser().getId());
		record.setCreatedBy(authenUtil.getUser().getUserName());
		record.setCreatedIp(authenUtil.getUser().getIp());
		record.setCreatedOn(new Date());
		SpringAttachment springAttachment = new SpringAttachment();
		BeanUtils.copyProperties(record, springAttachment);
		try {
			springAttachmentMapper.insert(springAttachment);
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
	public SpringAttachmentDTO selectByPrimaryKey(String id) {
		SpringAttachment springAttachment = null;
		try {
			springAttachment = springAttachmentMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringAttachmentDTO springAttachmentDTO = new SpringAttachmentDTO();
		BeanUtils.copyProperties(springAttachment, springAttachmentDTO);
		return springAttachmentDTO;
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
	public void updateByPrimaryKey(SpringAttachmentDTO springAttachmentDTO) {
		SpringAttachment entity = springAttachmentMapper.selectByPrimaryKey(springAttachmentDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		entity.setDescription(springAttachmentDTO.getDescription());
		entity.setUpdatedUserId(authenUtil.getUser().getId());
		entity.setUpdatedBy(authenUtil.getUser().getUserName());
		entity.setUpdatedIp(authenUtil.getUser().getIp());
		entity.setUpdatedOn(new Date());
		try {
			springAttachmentMapper.updateByPrimaryKey(entity);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	/**
	 *
	 * 分页查询
	 * 
	 * @param record
	 * @return Page<BaseFileEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringAttachmentDTO> getAllRecordByPage(SpringAttachmentDTO record, int page, int size) {
		if (size > Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		List<SpringAttachment> springAttachments = springAttachmentMapper.listByPage(record);
		List<SpringAttachmentDTO> springAttachmentDTOs = new ArrayList<>();
		springAttachments.stream().forEach(springAttachment -> {
			SpringAttachmentDTO springAttachmentDTO = new SpringAttachmentDTO();
			BeanUtils.copyProperties(springAttachment, springAttachmentDTO);
			springAttachmentDTOs.add(springAttachmentDTO);
		});
		PageInfo springAttachmentsPageInfo = new PageInfo<>(springAttachments);
		springAttachmentsPageInfo.setList(springAttachmentDTOs);
		return springAttachmentsPageInfo;
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
			springAttachmentMapper.setDelete(ids);
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
		try {
			springAttachmentMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}
}
