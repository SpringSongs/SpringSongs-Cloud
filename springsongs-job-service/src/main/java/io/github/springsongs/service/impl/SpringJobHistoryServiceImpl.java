package io.github.springsongs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringJobHistory;
import io.github.springsongs.dto.SpringJobHistoryDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringJobHistoryMapper;
import io.github.springsongs.service.ISpringJobHistoryService;
import io.github.springsongs.utils.Constant;

@Service
public class SpringJobHistoryServiceImpl implements ISpringJobHistoryService {

	static Logger logger = LoggerFactory.getLogger(SpringJobHistoryServiceImpl.class);

	@Autowired
	private SpringJobHistoryMapper springJobHistoryMapper;

	@Override
	public void deleteByPrimaryKey(String id) {
		try {
			springJobHistoryMapper.deleteByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public void insert(SpringJobHistoryDTO springJobHistoryDTO) {
		SpringJobHistory springJobHistory = new SpringJobHistory();
		BeanUtils.copyProperties(springJobHistoryDTO, springJobHistory);
		try {
			springJobHistoryMapper.insert(springJobHistory);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public SpringJobHistoryDTO selectByPrimaryKey(String id) {
		SpringJobHistory springJobHistory = null;
		try {
			springJobHistory = springJobHistoryMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringJobHistoryDTO springJobHistoryDTO = new SpringJobHistoryDTO();
		BeanUtils.copyProperties(springJobHistory, springJobHistoryDTO);
		return springJobHistoryDTO;
	}

	@Override
	public void updateByPrimaryKey(SpringJobHistoryDTO record) {

	}

	@Override
	public PageInfo<SpringJobHistoryDTO> getAllRecordByPage(SpringJobHistoryDTO record,int page,int size) {
		if (size> Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		List<SpringJobHistory> springJobHistorys = springJobHistoryMapper.listByPage(record);
		List<SpringJobHistoryDTO> springJobHistoryDTOs = new ArrayList<>();
		springJobHistorys.stream().forEach(springJobHistory -> {
			SpringJobHistoryDTO springJobHistoryDTO = new SpringJobHistoryDTO();
			BeanUtils.copyProperties(springJobHistory, springJobHistoryDTO);
			springJobHistoryDTOs.add(springJobHistoryDTO);
		});
		PageInfo pringAritlcesPageInfo = new PageInfo<>(springJobHistorys);
		pringAritlcesPageInfo.setList(springJobHistoryDTOs);
		return pringAritlcesPageInfo;
	}

	@Override
	public void setDeleted(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > 1000) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		try {
			springJobHistoryMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public void batchSaveExcel(List<String[]> list) {
		// TODO Auto-generated method stub

	}

}
