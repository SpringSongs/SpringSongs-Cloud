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

import io.github.springsongs.domain.SpringParameter;
import io.github.springsongs.dto.SpringParameterDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringParameterMapper;
import io.github.springsongs.service.ISpringParameterService;
import io.github.springsongs.utils.Constant;

@Service
@Transactional
public class SpringParameterServiceImpl implements ISpringParameterService {
	static Logger logger = LoggerFactory.getLogger(SpringDictionaryServiceImpl.class);
	@Autowired
	private SpringParameterMapper springParameterMapper;

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
			springParameterMapper.deleteByPrimaryKey(id);
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
	public void insert(SpringParameterDTO record) {
		record.setId(UUID.randomUUID().toString());
		SpringParameter springParameter = new SpringParameter();
		BeanUtils.copyProperties(record, springParameter);
		try {
			springParameterMapper.insert(springParameter);
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
	public SpringParameterDTO selectByPrimaryKey(String id) {
		SpringParameter springParameter = null;
		try {
			springParameter = springParameterMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringParameterDTO springParameterDTO = new SpringParameterDTO();
		BeanUtils.copyProperties(springParameter, springParameterDTO);
		return springParameterDTO;
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
	public void updateByPrimaryKey(SpringParameterDTO springParameterDTO) {
		SpringParameter entity = springParameterMapper.selectByPrimaryKey(springParameterDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else if (!entity.getEnableEdit()) {
			throw new SpringSongsException(ResultCode.INFO_CAN_NOT_EDIT);
		} else {
			entity.setCode(springParameterDTO.getCode());
			entity.setK(springParameterDTO.getK());
			entity.setV(springParameterDTO.getV());
			entity.setSortCode(springParameterDTO.getSortCode());
			entity.setEnableEdit(springParameterDTO.getEnableEdit());
			entity.setEnableDelete(springParameterDTO.getEnableDelete());
			try {
				springParameterMapper.updateByPrimaryKey(entity);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
			}
		}
	}

	/**
	 *
	 * 分页查询
	 * 
	 * @param record
	 * @return Page<BaseParameterEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringParameterDTO> getAllRecordByPage(SpringParameterDTO springParameterQuery,int page,int size) {
		if (size > Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		List<SpringParameter> springParameters = springParameterMapper.listByPage(springParameterQuery);
		List<SpringParameterDTO> springParameterDTOs = new ArrayList<>();
		springParameters.stream().forEach(springParameter -> {
			SpringParameterDTO springParameterDTO = new SpringParameterDTO();
			BeanUtils.copyProperties(springParameter, springParameterDTO);
			springParameterDTOs.add(springParameterDTO);
		});
		PageInfo springParametersPageInfo = new PageInfo<>(springParameters);
		springParametersPageInfo.setList(springParameterDTOs);
		return springParametersPageInfo;
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
		List<SpringParameter> entityList = springParameterMapper.listByIds(ids);
		for (SpringParameter entity : entityList) {
			if (entity.getEnableDelete() == false) {
				throw new SpringSongsException(ResultCode.INFO_CAN_NOT_DELETE);
			}
		}
		try {
			springParameterMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
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
		List<SpringParameter> entityList = springParameterMapper.listByIds(ids);
		for (SpringParameter entity : entityList) {
			if (entity.getEnableDelete() == false) {
				throw new SpringSongsException(ResultCode.INFO_CAN_NOT_DELETE);
			}
		}
		try {
			springParameterMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
	}

	@Override
	public List<SpringParameterDTO> listByIds(List<String> ids) {
		List<SpringParameter> springParameters = springParameterMapper.listByIds(ids);
		List<SpringParameterDTO> springParameterDTOs = new ArrayList<>();
		springParameters.stream().forEach(springParameter -> {
			SpringParameterDTO springParameterDTO = new SpringParameterDTO();
			BeanUtils.copyProperties(springParameter, springParameterDTO);
			springParameterDTOs.add(springParameterDTO);
		});
		return springParameterDTOs;
	}
}
