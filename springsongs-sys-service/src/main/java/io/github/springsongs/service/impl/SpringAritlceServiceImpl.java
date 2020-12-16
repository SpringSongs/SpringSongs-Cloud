package io.github.springsongs.service.impl;

import java.util.ArrayList;
import java.util.List;
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

import io.github.springsongs.domain.SpringAritlce;
import io.github.springsongs.dto.SpringAritlceDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringAritlceMapper;
import io.github.springsongs.service.ISpringAritlceService;
import io.github.springsongs.utils.Constant;

@Service
public class SpringAritlceServiceImpl implements ISpringAritlceService {

	static Logger logger = LoggerFactory.getLogger(SpringAritlceServiceImpl.class);

	@Autowired
	private SpringAritlceMapper springAritlceMapper;

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
			springAritlceMapper.deleteByPrimaryKey(id);
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
	public void insert(SpringAritlceDTO record) {
		record.setId(UUID.randomUUID().toString());
		SpringAritlce springAritlce = new SpringAritlce();
		BeanUtils.copyProperties(record, springAritlce);
		try {
			springAritlceMapper.insert(springAritlce);
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
	public SpringAritlceDTO selectByPrimaryKey(String id) {
		SpringAritlce springAritlce;
		try {
			springAritlce = springAritlceMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringAritlceDTO springAritlceDTO = new SpringAritlceDTO();
		BeanUtils.copyProperties(springAritlce, springAritlceDTO);
		return springAritlceDTO;
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
	public void updateByPrimaryKey(SpringAritlceDTO springAritlceDTO) {
		SpringAritlce entity = springAritlceMapper.selectByPrimaryKey(springAritlceDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			entity.setCategoryId(springAritlceDTO.getCategoryId());
			entity.setCategoryTitle(springAritlceDTO.getCategoryTitle());
			entity.setColor(springAritlceDTO.getColor());
			entity.setTag(springAritlceDTO.getTag());
			entity.setKeyword(springAritlceDTO.getKeyword());
			entity.setTitle(springAritlceDTO.getTitle());
			entity.setSummary(springAritlceDTO.getSummary());
			entity.setContents(springAritlceDTO.getContents());
			entity.setAuthorId(springAritlceDTO.getAuthorId());
			entity.setAuthor(springAritlceDTO.getAuthor());
			entity.setAuthorUrl(springAritlceDTO.getAuthorUrl());
			entity.setStatus(springAritlceDTO.getStatus());
			entity.setLink(springAritlceDTO.getLink());
			entity.setReadCount(springAritlceDTO.getReadCount());
			entity.setLikeCount(springAritlceDTO.getLikeCount());
			entity.setCollectCount(springAritlceDTO.getCollectCount());
			entity.setShareCount(springAritlceDTO.getShareCount());
			entity.setTopStatus(springAritlceDTO.getTopStatus());
			entity.setHotStatus(springAritlceDTO.getHotStatus());
			entity.setFeatured(springAritlceDTO.getFeatured());
			entity.setCommentCount(springAritlceDTO.getCommentCount());
			entity.setSortOrder(springAritlceDTO.getSortOrder());
			entity.setComeFrom(springAritlceDTO.getComeFrom());
			entity.setComeFromLink(springAritlceDTO.getComeFromLink());
			try {
				springAritlceMapper.updateByPrimaryKey(entity);
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
	 * @return Page<BaseAritlceEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringAritlceDTO> getAllRecordByPage(SpringAritlceDTO springAritlceQuery,int page,int size) {
		if (size > Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		List<SpringAritlce> pringAritlces = springAritlceMapper.listByPage(springAritlceQuery);
		List<SpringAritlceDTO> springAritlceDTOs = new ArrayList<>();
		pringAritlces.stream().forEach(springAritlce -> {
			SpringAritlceDTO springAritlceDTO = new SpringAritlceDTO();
			BeanUtils.copyProperties(springAritlce, springAritlceDTO);
			springAritlceDTOs.add(springAritlceDTO);
		});
		PageInfo pringAritlcesPageInfo = new PageInfo<>(pringAritlces);
		pringAritlcesPageInfo.setList(springAritlceDTOs);
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
	@Transactional
	public void setDeleted(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > 1000) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		try {
			springAritlceMapper.setDelete(ids);
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
			springAritlceMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public void audit(String id) {
		SpringAritlce entity = springAritlceMapper.selectByPrimaryKey(id);
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			if (entity.getStatus() == true) {
				entity.setStatus(false);
			} else {
				entity.setStatus(true);
			}
			try {
				springAritlceMapper.updateByPrimaryKey(entity);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		}
	}

	@Override
	public void hotStatus(String id) {
		SpringAritlce entity = springAritlceMapper.selectByPrimaryKey(id);
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			if (entity.getHotStatus() == true) {
				entity.setHotStatus(false);
			} else {
				entity.setHotStatus(true);
			}
			try {
				springAritlceMapper.updateByPrimaryKey(entity);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		}
	}

	@Override
	public void topStatus(String id) {
		SpringAritlce entity = springAritlceMapper.selectByPrimaryKey(id);
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			if (entity.getTopStatus() == true) {
				entity.setTopStatus(false);
			} else {
				entity.setTopStatus(true);
			}
			try {
				springAritlceMapper.updateByPrimaryKey(entity);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		}
	}

	@Override
	public void featured(String id) {
		SpringAritlce entity = springAritlceMapper.selectByPrimaryKey(id);
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			if (entity.getFeatured() == true) {
				entity.setFeatured(false);
			} else {
				entity.setFeatured(true);
			}
			try {
				springAritlceMapper.updateByPrimaryKey(entity);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		}
	}
}
