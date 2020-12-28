package io.github.springsongs.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringActProcessRouter;
import io.github.springsongs.dto.SpringActProcessRouterDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringActProcessRouterMapper;
import io.github.springsongs.service.ISpringActProcessRouterService;
import io.github.springsongs.util.AuthenUtil;

@Service
public class SpringActProcessRouterServiceImpl implements ISpringActProcessRouterService {

	static Logger logger = LoggerFactory.getLogger(SpringActCategoryServiceImpl.class);

	@Autowired
	private SpringActProcessRouterMapper springActProcessRouterMapper;
	
	@Autowired
	private AuthenUtil authenUtil;

	@Override
	public void deleteByPrimaryKey(String id) {
		try {
			springActProcessRouterMapper.deleteByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}

	}

	@Override
	public void insert(SpringActProcessRouterDTO springActProcessRouterDTO) {
		SpringActProcessRouter springActProcessRouter = new SpringActProcessRouter();
		springActProcessRouterDTO.setId(UUID.randomUUID().toString());
		springActProcessRouterDTO.setCreatedUserId(authenUtil.getUser().getId());
		springActProcessRouterDTO.setCreatedBy(authenUtil.getUser().getUserName());
		springActProcessRouterDTO.setCreatedIp(authenUtil.getUser().getIp());
		springActProcessRouterDTO.setCreatedOn(new Date());
		BeanUtils.copyProperties(springActProcessRouterDTO, springActProcessRouter);
		try {
			
			springActProcessRouterMapper.insert(springActProcessRouter);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public SpringActProcessRouterDTO selectByPrimaryKey(String id) {
		SpringActProcessRouter springActProcessRouter = springActProcessRouterMapper.selectByPrimaryKey(id);
		if (null == springActProcessRouter) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringActProcessRouterDTO springActProcessRouterDTO = new SpringActProcessRouterDTO();
		BeanUtils.copyProperties(springActProcessRouter, springActProcessRouterDTO);
		return springActProcessRouterDTO;
	}

	@Override
	public void updateByPrimaryKey(SpringActProcessRouterDTO springActProcessRouterDTO) {
		SpringActProcessRouter springActProcessRouter = springActProcessRouterMapper
				.selectByPrimaryKey(springActProcessRouterDTO.getId());
		if (null == springActProcessRouter) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		try {
			springActProcessRouterDTO.setUpdatedUserId(authenUtil.getUser().getId());
			springActProcessRouterDTO.setUpdatedBy(authenUtil.getUser().getUserName());
			springActProcessRouterDTO.setUpdatedIp(authenUtil.getUser().getIp());
			springActProcessRouterDTO.setUpdatedOn(new Date());
			SpringActProcessRouter springActProcessRouterDO = new SpringActProcessRouter();
			BeanUtils.copyProperties(springActProcessRouterDTO, springActProcessRouterDO);
			springActProcessRouterMapper.updateByPrimaryKey(springActProcessRouterDO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public PageInfo<SpringActProcessRouterDTO> getAllRecordByPage(SpringActProcessRouterDTO record,int page,int size) {
		return null;
	}

	@Override
	public void setDeleted(List<String> ids) {

	}

	@Override
	public void batchSaveExcel(List<String[]> list) {

	}

	@Override
	public SpringActProcessRouterDTO findSpringActProcessRouterByProcDefKey(String procDefKey) {
		SpringActProcessRouter springActProcessRouter = springActProcessRouterMapper
				.findSpringActProcessRouterByProcDefKey(procDefKey);
		if (null == springActProcessRouter) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringActProcessRouterDTO springActProcessRouterDTO = new SpringActProcessRouterDTO();
		BeanUtils.copyProperties(springActProcessRouter, springActProcessRouterDTO);
		return springActProcessRouterDTO;
	}

}
