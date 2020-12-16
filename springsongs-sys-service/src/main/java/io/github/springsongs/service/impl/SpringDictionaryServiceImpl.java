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

import io.github.springsongs.domain.SpringDictionary;
import io.github.springsongs.dto.SpringDictionaryDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringDictionaryDetailMapper;
import io.github.springsongs.mapper.SpringDictionaryMapper;
import io.github.springsongs.service.ISpringDictionaryService;
import io.github.springsongs.utils.Constant;

@Service
public class SpringDictionaryServiceImpl implements ISpringDictionaryService {

	static Logger logger = LoggerFactory.getLogger(SpringDictionaryServiceImpl.class);

	@Autowired
	private SpringDictionaryMapper springDictionaryMapper;

	@Autowired
	private SpringDictionaryDetailMapper springDictionaryDetailMapper;

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
			springDictionaryMapper.deleteByPrimaryKey(id);
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
	public void insert(SpringDictionaryDTO record) {
		record.setId(UUID.randomUUID().toString());
		SpringDictionary springDictionary = new SpringDictionary();
		BeanUtils.copyProperties(record, springDictionary);
		try {
			springDictionaryMapper.insert(springDictionary);
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
	public SpringDictionaryDTO selectByPrimaryKey(String id) {
		SpringDictionary springDictionary = null;
		try {
			springDictionary = springDictionaryMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringDictionaryDTO springDictionaryDTO = new SpringDictionaryDTO();
		BeanUtils.copyProperties(springDictionary, springDictionaryDTO);
		return springDictionaryDTO;
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
	public void updateByPrimaryKey(SpringDictionaryDTO springDictionaryDTO) {
		SpringDictionary entity = springDictionaryMapper.selectByPrimaryKey(springDictionaryDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else if (!entity.getEnableEdit()) {
			throw new SpringSongsException(ResultCode.INFO_CAN_NOT_EDIT);
		} else {
			entity.setCode(springDictionaryDTO.getCode());
			entity.setTitle(springDictionaryDTO.getTitle());
			entity.setDescription(springDictionaryDTO.getDescription());
			entity.setSortCode(springDictionaryDTO.getSortCode());
			entity.setEnableEdit(springDictionaryDTO.getEnableEdit());
			try {
				springDictionaryMapper.insert(entity);
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
	 * @return Page<BaseDictionaryEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringDictionaryDTO> getAllRecordByPage(SpringDictionaryDTO springDictionaryQuery,
			int page,int size) {
		if (size>Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		List<SpringDictionary> springDictionarys = springDictionaryMapper.listByPage(springDictionaryQuery);
		List<SpringDictionaryDTO> pringDictionaryDTOs = new ArrayList<>();
		springDictionarys.stream().forEach(springDictionary -> {
			SpringDictionaryDTO springDictionaryDTO = new SpringDictionaryDTO();
			BeanUtils.copyProperties(springDictionary, springDictionaryDTO);
			pringDictionaryDTOs.add(springDictionaryDTO);
		});
		PageInfo springDictionarysPageInfo = new PageInfo<>(springDictionarys);
		springDictionarysPageInfo.setList(pringDictionaryDTOs);
		return springDictionarysPageInfo;
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
	@Transactional
	@Override
	public void setDeleted(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > Constant.MAX_ITEM_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		List<String> codes = new ArrayList<String>();
		List<SpringDictionary> entityList = springDictionaryMapper.findInIds(ids);
		for (SpringDictionary entity : entityList) {
			if (entity.getEnableDelete() == false) {
				throw new SpringSongsException(ResultCode.INFO_CAN_NOT_DELETE);
			}
		}
		entityList.stream().forEach(t -> {
			codes.add(t.getCode());
		});
		try {
			springDictionaryDetailMapper.setDeleteByDictionCode(codes);
			springDictionaryMapper.setDelete(ids);
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
			springDictionaryMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public List<SpringDictionaryDTO> listByIds(List<String> ids) {
		List<SpringDictionary> springDictionarys = null;
		try {
			springDictionarys = springDictionaryMapper.findInIds(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
		List<SpringDictionaryDTO> springDictionaryDTOs = new ArrayList<>();
		springDictionarys.stream().forEach(springDictionary -> {
			SpringDictionaryDTO springDictionaryDTO = new SpringDictionaryDTO();
			BeanUtils.copyProperties(springDictionary, springDictionaryDTO);
			springDictionaryDTOs.add(springDictionaryDTO);
		});
		return springDictionaryDTOs;
	}
}
