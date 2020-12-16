package io.github.springsongs.service.impl;

import java.util.ArrayList;
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

import io.github.springsongs.domain.SpringArticleComment;
import io.github.springsongs.dto.SpringArticleCommentDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringArticleCommentMapper;
import io.github.springsongs.service.ISpringArticleCommentService;
import io.github.springsongs.utils.Constant;

@Service
public class SpringArticleCommentServiceImpl implements ISpringArticleCommentService {

	static Logger logger = LoggerFactory.getLogger(SpringArticleCategoryServiceImpl.class);

	@Autowired
	private SpringArticleCommentMapper springArticleCommentMapper;

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
			springArticleCommentMapper.deleteByPrimaryKey(id);
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
	public void insert(SpringArticleCommentDTO record) {
		record.setId(UUID.randomUUID().toString());
		SpringArticleComment springArticleComment = new SpringArticleComment();
		BeanUtils.copyProperties(record, springArticleComment);
		try {
			springArticleCommentMapper.insert(springArticleComment);
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
	public SpringArticleCommentDTO selectByPrimaryKey(String id) {
		SpringArticleComment springArticleComment = null;
		try {
			springArticleComment = springArticleCommentMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
		SpringArticleCommentDTO springArticleCommentDTO = new SpringArticleCommentDTO();
		BeanUtils.copyProperties(springArticleComment, springArticleCommentDTO);
		return springArticleCommentDTO;
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
	public void updateByPrimaryKey(SpringArticleCommentDTO springArticleCommentDTO) {
		SpringArticleComment entity = springArticleCommentMapper.selectByPrimaryKey(springArticleCommentDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			entity.setContent(springArticleCommentDTO.getContent());
			entity.setArticleId(springArticleCommentDTO.getArticleId());
			entity.setAuditFlag(springArticleCommentDTO.getAuditFlag());
			entity.setSortCode(springArticleCommentDTO.getSortCode());
			entity.setDeletedStatus(springArticleCommentDTO.getDeletedStatus());
			try {
				springArticleCommentMapper.updateByPrimaryKey(entity);
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
	 * @return Page<BaseCommentEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringArticleCommentDTO> getAllRecordByPage(SpringArticleCommentDTO springArticleCommentQuery,
			int page,int size) {
		if (size>Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		List<SpringArticleComment> springArticleComments = springArticleCommentMapper.listByPage(springArticleCommentQuery);
		List<SpringArticleCommentDTO> SpringArticleCommentDTOs = new ArrayList<>();
		springArticleComments.stream().forEach(springArticleComment -> {
			SpringArticleCommentDTO springArticleCommentDTO = new SpringArticleCommentDTO();
			BeanUtils.copyProperties(springArticleComment, springArticleCommentDTO);
			SpringArticleCommentDTOs.add(springArticleCommentDTO);
		});
		PageInfo springArticleCommentsPageInfo = new PageInfo<>(springArticleComments);
		springArticleCommentsPageInfo.setList(SpringArticleCommentDTOs);
		return springArticleCommentsPageInfo;
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
		try {
			springArticleCommentMapper.setDelete(ids);
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
		} else if (ids.size() > 1000) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		try {
			springArticleCommentMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public void audit(String id) {
		SpringArticleComment entity = springArticleCommentMapper.selectByPrimaryKey(id);
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			if (entity.getAuditFlag() == true) {
				entity.setAuditFlag(false);
			} else {
				entity.setAuditFlag(true);
			}
			try {
				springArticleCommentMapper.updateByPrimaryKey(entity);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		}

	}
}
