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

import io.github.springsongs.domain.SpringDictionaryDetail;
import io.github.springsongs.dto.SpringDictionaryDetailDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringDictionaryDetailMapper;
import io.github.springsongs.service.ISpringDictionaryDetailService;
import io.github.springsongs.utils.Constant;

@Service
public class SpringDictionaryDetailServiceImpl implements ISpringDictionaryDetailService {

	static Logger logger = LoggerFactory.getLogger(SpringContactServiceImpl.class);

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
			springDictionaryDetailMapper.deleteByPrimaryKey(id);
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
	public void insert(SpringDictionaryDetailDTO record) {
		record.setId(UUID.randomUUID().toString());
		SpringDictionaryDetail springDictionaryDetail = new SpringDictionaryDetail();
		BeanUtils.copyProperties(record, springDictionaryDetail);
		try {
			springDictionaryDetailMapper.insert(springDictionaryDetail);
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
	public SpringDictionaryDetailDTO selectByPrimaryKey(String id) {
		SpringDictionaryDetail springDictionaryDetail = null;
		try {
			springDictionaryDetail = springDictionaryDetailMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringDictionaryDetailDTO springDictionaryDetailDTO = new SpringDictionaryDetailDTO();
		BeanUtils.copyProperties(springDictionaryDetail, springDictionaryDetailDTO);
		return springDictionaryDetailDTO;
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
	public void updateByPrimaryKey(SpringDictionaryDetailDTO springDictionaryDetailDTO) {
		SpringDictionaryDetail entity = springDictionaryDetailMapper.selectByPrimaryKey(springDictionaryDetailDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			entity.setDictionaryCode(springDictionaryDetailDTO.getDictionaryCode());
			entity.setParentId(springDictionaryDetailDTO.getParentId());
			entity.setDetailCode(springDictionaryDetailDTO.getDetailCode());
			entity.setDetailName(springDictionaryDetailDTO.getDetailName());
			entity.setDetailValue(springDictionaryDetailDTO.getDetailValue());
			entity.setDescription(springDictionaryDetailDTO.getDescription());
			entity.setChildIds(springDictionaryDetailDTO.getChildIds());
			entity.setSortCode(springDictionaryDetailDTO.getSortCode());
			entity.setEnableEdit(springDictionaryDetailDTO.getEnableEdit());
			entity.setEnableDelete(springDictionaryDetailDTO.getEnableDelete());
			entity.setDeletedStatus(springDictionaryDetailDTO.getDeletedStatus());
			try {
				springDictionaryDetailMapper.insert(entity);
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
	 * @return Page<BaseDictionaryDetailEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringDictionaryDetailDTO> getAllRecordByPage(SpringDictionaryDetailDTO springDictionaryDetailQuery,
			int page,int size) {
		if (size>Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		List<SpringDictionaryDetail> springDictionaryDetails = springDictionaryDetailMapper.listByPage(springDictionaryDetailQuery);
		List<SpringDictionaryDetailDTO> springDictionaryDetailDTOs = new ArrayList<>();
		springDictionaryDetails.stream().forEach(springDictionaryDetail -> {
			SpringDictionaryDetailDTO springDictionaryDetailDTO = new SpringDictionaryDetailDTO();
			BeanUtils.copyProperties(springDictionaryDetail, springDictionaryDetailDTO);
			springDictionaryDetailDTOs.add(springDictionaryDetailDTO);
		});
		PageInfo springDictionaryDetailsPageInfo = new PageInfo<>(springDictionaryDetails);
		springDictionaryDetailsPageInfo.setList(springDictionaryDetailDTOs);
		return springDictionaryDetailsPageInfo;
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
			springDictionaryDetailMapper.setDelete(ids);
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
		} else if (ids.size() > Constant.MAX_ITEM_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		try {
			springDictionaryDetailMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
	}

	@Override
	public void setDeleteByCode(String code) {
		try {
			springDictionaryDetailMapper.setDeleteByCode(code);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
	}

	@Override
	public List<SpringDictionaryDetailDTO> listSpringDictionaryDetailByDictionaryCode(String dictionaryCode) {
		List<SpringDictionaryDetailDTO> springDictionaryDetailDTOList=new ArrayList<>();
		List<SpringDictionaryDetail> springDictionaryDetailList=springDictionaryDetailMapper.listSpringDictionaryDetailByDictionaryCode(dictionaryCode);
		springDictionaryDetailList.stream().forEach(springDictionaryDetail->{
			SpringDictionaryDetailDTO springDictionaryDetailDTO=new SpringDictionaryDetailDTO();
			BeanUtils.copyProperties(springDictionaryDetail, springDictionaryDetailDTO);
			springDictionaryDetailDTOList.add(springDictionaryDetailDTO);
		});
		return springDictionaryDetailDTOList;
	}
}
