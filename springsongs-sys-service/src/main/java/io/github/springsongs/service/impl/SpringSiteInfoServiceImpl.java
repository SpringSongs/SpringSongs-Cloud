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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringSiteInfo;
import io.github.springsongs.dto.SpringSiteInfoDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringSiteInfoMapper;
import io.github.springsongs.service.ISpringSiteInfoService;
import io.github.springsongs.util.AuthenUtil;
import io.github.springsongs.utils.Constant;

@Service
public class SpringSiteInfoServiceImpl implements ISpringSiteInfoService {

	static Logger logger = LoggerFactory.getLogger(SpringFriendLinkServiceImpl.class);

	@Autowired
	private SpringSiteInfoMapper springSiteInfoMapper;

	@Autowired
	private AuthenUtil authenUtil;

	@Override
	public void deleteByPrimaryKey(String id) {
		try {
			springSiteInfoMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public void insert(SpringSiteInfoDTO record) {
		record.setId(UUID.randomUUID().toString());
		record.setCreatedUserId(authenUtil.getUser().getId());
		record.setCreatedBy(authenUtil.getUser().getUserName());
		record.setCreatedIp(authenUtil.getUser().getIp());
		record.setCreatedOn(new Date());
		SpringSiteInfo springSiteInfo = new SpringSiteInfo();
		BeanUtils.copyProperties(record, springSiteInfo);
		try {
			springSiteInfoMapper.insert(springSiteInfo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public SpringSiteInfoDTO selectByPrimaryKey(String id) {
		SpringSiteInfo springSiteInfo = null;
		springSiteInfo = springSiteInfoMapper.selectByPrimaryKey(id);
		if (null == springSiteInfo) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringSiteInfoDTO springSiteInfoDTO = new SpringSiteInfoDTO();
		BeanUtils.copyProperties(springSiteInfo, springSiteInfoDTO);
		return springSiteInfoDTO;
	}

	@Override
	public void updateByPrimaryKey(SpringSiteInfoDTO record) {
		SpringSiteInfo springSiteInfo = springSiteInfoMapper.selectByPrimaryKey(record.getId());
		if (null == springSiteInfo) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		record.setUpdatedUserId(authenUtil.getUser().getId());
		record.setUpdatedBy(authenUtil.getUser().getUserName());
		record.setUpdatedIp(authenUtil.getUser().getIp());
		record.setUpdatedOn(new Date());
		SpringSiteInfo springSiteInfoDO = new SpringSiteInfo();
		BeanUtils.copyProperties(record, springSiteInfoDO);
		try {
			springSiteInfoMapper.updateByPrimaryKey(springSiteInfoDO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public PageInfo<SpringSiteInfoDTO> getAllRecordByPage(SpringSiteInfoDTO record, int page, int size) {
		if (size > Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}

		PageHelper.startPage(page, size);
		Page<SpringSiteInfo> springSiteInfos = springSiteInfoMapper.listByPage(record);
		List<SpringSiteInfoDTO> springSiteInfoDTOs = new ArrayList<>();
		springSiteInfos.stream().forEach(springSiteInfo -> {
			SpringSiteInfoDTO springSiteInfoDTO = new SpringSiteInfoDTO();
			BeanUtils.copyProperties(springSiteInfo, springSiteInfoDTO);
			springSiteInfoDTOs.add(springSiteInfoDTO);
		});
		PageInfo springSiteInfosPage = new PageInfo<>(springSiteInfos);
		springSiteInfosPage.setList(springSiteInfoDTOs);
		return springSiteInfosPage;
	}

	@Override
	public void setDeleted(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > Constant.MAX_ITEM_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		try {
			springSiteInfoMapper.setDelete(ids);
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
