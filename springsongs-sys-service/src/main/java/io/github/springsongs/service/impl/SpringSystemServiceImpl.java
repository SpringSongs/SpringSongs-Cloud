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

import io.github.springsongs.domain.SpringSystem;
import io.github.springsongs.dto.SpringSystemDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringSystemMapper;
import io.github.springsongs.service.ISpringSystemService;
import io.github.springsongs.utils.Constant;

@Service
public class SpringSystemServiceImpl implements ISpringSystemService {

	static Logger logger = LoggerFactory.getLogger(SpringRoleServiceImpl.class);

	@Autowired
	private SpringSystemMapper springSystemMapper;

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
			springSystemMapper.deleteByPrimaryKey(id);
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
	public void insert(SpringSystemDTO record) {
		record.setId(UUID.randomUUID().toString());
		SpringSystem springSystem = new SpringSystem();
		BeanUtils.copyProperties(record, springSystem);
		try {
			springSystemMapper.insert(springSystem);
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
	public SpringSystemDTO selectByPrimaryKey(String id) {
		SpringSystem springSystem = null;
		try {
			springSystem = springSystemMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringSystemDTO springSystemDTO = new SpringSystemDTO();
		BeanUtils.copyProperties(springSystem, springSystemDTO);
		return springSystemDTO;
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
	public void updateByPrimaryKey(SpringSystemDTO springSystemDTO) {
		SpringSystem entity = springSystemMapper.selectByPrimaryKey(springSystemDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			entity.setCode(springSystemDTO.getCode());
			entity.setTitle(springSystemDTO.getTitle());
			entity.setDescription(springSystemDTO.getDescription());
			entity.setEnableDelete(springSystemDTO.getEnableDelete());
			entity.setEnableEdit(springSystemDTO.getEnableEdit());
			try {
				springSystemMapper.updateByPrimaryKey(entity);
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
	 * @return Page<SpringSystem>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringSystemDTO> getAllRecordByPage(SpringSystemDTO springSystemQuery,int page,int size) {
		if (size>Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		
		PageHelper.startPage(page, size);
		List<SpringSystem> springSystems = springSystemMapper.listByPage(springSystemQuery);
		List<SpringSystemDTO> springSystemDTOs = new ArrayList<>();
		springSystems.stream().forEach(springSystem -> {
			SpringSystemDTO springSystemDTO = new SpringSystemDTO();
			BeanUtils.copyProperties(springSystem, springSystemDTO);
			springSystemDTOs.add(springSystemDTO);
		});
		PageInfo springSystemsPageInfo = new PageInfo<>(springSystems);
		springSystemsPageInfo.setList(springSystemDTOs);
		return springSystemsPageInfo;
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
		List<SpringSystem> entityList = springSystemMapper.findAllById(ids);
		for (SpringSystem entity : entityList) {
			if (entity.getEnableDelete() == false) {
				throw new SpringSongsException(ResultCode.INFO_CAN_NOT_DELETE);
			}
		}
		try {
			springSystemMapper.setDelete(ids);
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
	public List<SpringSystemDTO> findInIds(List<String> ids) {
		List<SpringSystem> springSystems = springSystemMapper.findInIds(ids);
		List<SpringSystemDTO> springSystemDTOs = new ArrayList<>();
		springSystems.stream().forEach(springSystem -> {
			SpringSystemDTO springSystemDTO = new SpringSystemDTO();
			BeanUtils.copyProperties(springSystem, springSystemDTO);
			springSystemDTOs.add(springSystemDTO);
		});
		return springSystemDTOs;
	}



	@Override
	public List<SpringSystemDTO> ListAll() {
		List<SpringSystem> springSystems = springSystemMapper.selectAll();
		List<SpringSystemDTO> springSystemDTOs = new ArrayList<>();
		springSystems.stream().forEach(springSystem -> {
			SpringSystemDTO springSystemDTO = new SpringSystemDTO();
			BeanUtils.copyProperties(springSystem, springSystemDTO);
			springSystemDTOs.add(springSystemDTO);
		});
		return springSystemDTOs;
	}

}
