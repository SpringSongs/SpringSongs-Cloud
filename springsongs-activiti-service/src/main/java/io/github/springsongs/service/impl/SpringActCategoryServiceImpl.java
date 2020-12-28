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

import io.github.springsongs.domain.SpringActCategory;
import io.github.springsongs.dto.SpringActCategoryDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringActCategoryMapper;
import io.github.springsongs.service.ISpringActCategoryService;
import io.github.springsongs.util.AuthenUtil;
import io.github.springsongs.utils.Constant;
@Service
public class SpringActCategoryServiceImpl implements ISpringActCategoryService {

	static Logger logger = LoggerFactory.getLogger(SpringActCategoryServiceImpl.class);

	@Autowired
	private SpringActCategoryMapper springActCategoryMapper;

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
			springActCategoryMapper.deleteByPrimaryKey(id);
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
	public void insert(SpringActCategoryDTO springActCategoryDTO) {
		SpringActCategory springActCategory = springActCategoryMapper.getByCode(springActCategoryDTO.getCategoryCode());
		if (null != springActCategory) {
			throw new SpringSongsException(ResultCode.DATA_EXIST);
		}
		springActCategoryDTO.setId(UUID.randomUUID().toString());
		springActCategoryDTO.setCreatedUserId(authenUtil.getUser().getId());
		springActCategoryDTO.setCreatedBy(authenUtil.getUser().getUserName());
		springActCategoryDTO.setCreatedIp(authenUtil.getUser().getIp());
		springActCategoryDTO.setCreatedOn(new Date());
		SpringActCategory addSpringActCategory = new SpringActCategory();
		BeanUtils.copyProperties(springActCategoryDTO, addSpringActCategory);
		try {
			springActCategoryMapper.insert(addSpringActCategory);
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
	public SpringActCategoryDTO selectByPrimaryKey(String id) {
		SpringActCategory springActCategory = null;
		try {
			springActCategory = springActCategoryMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringActCategoryDTO springActCategoryDTO = new SpringActCategoryDTO();
		BeanUtils.copyProperties(springActCategory, springActCategoryDTO);
		return springActCategoryDTO;
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
	public void updateByPrimaryKey(SpringActCategoryDTO springActCategoryDTO) {
		SpringActCategory entity = springActCategoryMapper.selectByPrimaryKey(springActCategoryDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			entity.setCategoryTitle(springActCategoryDTO.getCategoryTitle());
			entity.setUpdatedUserId(authenUtil.getUser().getId());
			entity.setUpdatedBy(authenUtil.getUser().getUserName());
			entity.setUpdatedIp(authenUtil.getUser().getIp());
			entity.setUpdatedOn(new Date());
			try {
				springActCategoryMapper.updateByPrimaryKey(entity);
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
	 * @return Page<BaseSpringActCategoryEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringActCategoryDTO> getAllRecordByPage(SpringActCategoryDTO record,int page,int size) {
		if (size > Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		List<SpringActCategory> springActCategorys = springActCategoryMapper.listByPage(record);
		List<SpringActCategoryDTO> springJobGroupDTOs = new ArrayList<>();
		springActCategorys.stream().forEach(springActCategory -> {
			SpringActCategoryDTO springActCategoryDTO = new SpringActCategoryDTO();
			BeanUtils.copyProperties(springActCategory, springActCategoryDTO);
			springJobGroupDTOs.add(springActCategoryDTO);
		});
		PageInfo pringAritlcesPageInfo = new PageInfo<>(springActCategorys);
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
			springActCategoryMapper.setDelete(ids);
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
	public List<SpringActCategoryDTO> listAll() {
		List<SpringActCategory> springActCategorys = springActCategoryMapper.listAll();
		List<SpringActCategoryDTO> springActCategoryDTOs = new ArrayList<>();
		springActCategorys.stream().forEach(springActCategory -> {
			SpringActCategoryDTO springActCategoryDTO = new SpringActCategoryDTO();
			BeanUtils.copyProperties(springActCategory, springActCategoryDTO);
			springActCategoryDTOs.add(springActCategoryDTO);
		});
		return springActCategoryDTOs;
	}
}
