package io.github.springsongs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringDistrict;
import io.github.springsongs.dto.SpringDistrictDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringDistrictMapper;
import io.github.springsongs.service.ISpringDistrictService;
import io.github.springsongs.util.AuthenUtil;
import io.github.springsongs.utils.Constant;

@Service
public class SpringDistrictServiceImpl implements ISpringDistrictService {

	static Logger logger = LoggerFactory.getLogger(SpringDistrictServiceImpl.class);

	@Autowired
	private SpringDistrictMapper springDistrictMapper;
	
	@Autowired
	private AuthenUtil authenUtil;
	

	@Override
	public void deleteByPrimaryKey(Long id) {
		try {
			springDistrictMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public void insert(SpringDistrictDTO record) {
		SpringDistrict springDistrict = new SpringDistrict();
		record.setCreatedUserId(authenUtil.getUser().getId());
		record.setCreatedBy(authenUtil.getUser().getUserName());
		record.setCreatedIp(authenUtil.getUser().getIp());
		record.setCreatedOn(new Date());
		BeanUtils.copyProperties(record, springDistrict);
		try {
			springDistrictMapper.insert(springDistrict);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public SpringDistrictDTO selectByPrimaryKey(Long id) {
		SpringDistrict springDistrict = null;
		springDistrict = springDistrictMapper.selectByPrimaryKey(id);
		if (null == springDistrict) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringDistrictDTO springDistrictDTO = new SpringDistrictDTO();
		BeanUtils.copyProperties(springDistrict, springDistrictDTO);
		return springDistrictDTO;
	}

	@Override
	public void updateByPrimaryKey(SpringDistrictDTO record) {
		SpringDistrict springDistrict = springDistrictMapper.selectByPrimaryKey(record.getId());
		if (null == springDistrict) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringDistrict springDistrictDO = new SpringDistrict();
		record.setUpdatedUserId(authenUtil.getUser().getId());
		record.setUpdatedBy(authenUtil.getUser().getUserName());
		record.setUpdatedIp(authenUtil.getUser().getIp());
		record.setUpdatedOn(new Date());
		BeanUtils.copyProperties(record, springDistrictDO);
		try {
			springDistrictMapper.updateByPrimaryKey(springDistrictDO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public PageInfo<SpringDistrictDTO> getAllRecordByPage(SpringDistrictDTO record, int page, int size) {
		if (size > Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		List<SpringDistrict> springDistricts = springDistrictMapper.listByPage(record);
		List<SpringDistrictDTO> pringDictionaryDTOs = new ArrayList<>();
		springDistricts.stream().forEach(springDistrict -> {
			SpringDistrictDTO springDistrictDTO = new SpringDistrictDTO();
			BeanUtils.copyProperties(springDistrict, springDistrictDTO);
			pringDictionaryDTOs.add(springDistrictDTO);
		});
		PageInfo springDistrictsPageInfo = new PageInfo<>(springDistricts);
		springDistrictsPageInfo.setList(pringDictionaryDTOs);
		return springDistrictsPageInfo;
	}

	@Override
	public void setDeleted(List<Long> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > Constant.MAX_ITEM_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		try {
			springDistrictMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}

	}

	@Override
	public void batchSaveExcel(List<String[]> list) {

	}

	@Override
	public List<SpringDistrictDTO> listSpringDistrictByParentId(Long parentId) {
		List<SpringDistrict> springDistrictList = springDistrictMapper.listSpringDistrictByParentId(parentId);
		List<SpringDistrictDTO> springDistrictDTOList = new ArrayList<>();
		springDistrictList.stream().forEach(springDistrict -> {
			SpringDistrictDTO springDistrictDTO = new SpringDistrictDTO();
			BeanUtils.copyProperties(springDistrict, springDistrictDTO);
			springDistrictDTOList.add(springDistrictDTO);
		});
		return springDistrictDTOList;
	}

}
