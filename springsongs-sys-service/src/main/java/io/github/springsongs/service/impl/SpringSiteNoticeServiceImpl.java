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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringSiteNotice;
import io.github.springsongs.dto.SpringSiteNoticeDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringSiteNoticeMapper;
import io.github.springsongs.service.ISpringSiteNoticeService;
import io.github.springsongs.utils.Constant;

@Service
public class SpringSiteNoticeServiceImpl implements ISpringSiteNoticeService {

	static Logger logger = LoggerFactory.getLogger(SpringSiteNoticeServiceImpl.class);

	@Autowired
	private SpringSiteNoticeMapper springSiteNoticeMapper;

	@Override
	public void deleteByPrimaryKey(String id) {
		try {
			springSiteNoticeMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public void insert(SpringSiteNoticeDTO record) {
		record.setId(UUID.randomUUID().toString());
		SpringSiteNotice springSiteNotice = new SpringSiteNotice();
		BeanUtils.copyProperties(record, springSiteNotice);
		try {
			springSiteNoticeMapper.insert(springSiteNotice);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public SpringSiteNoticeDTO selectByPrimaryKey(String id) {
		SpringSiteNotice springSiteNotice = null;
		springSiteNotice = springSiteNoticeMapper.selectByPrimaryKey(id);
		if (null == springSiteNotice) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringSiteNoticeDTO springSiteNoticeDTO = new SpringSiteNoticeDTO();
		BeanUtils.copyProperties(springSiteNotice, springSiteNoticeDTO);
		return springSiteNoticeDTO;
	}

	@Override
	public void updateByPrimaryKey(SpringSiteNoticeDTO record) {
		SpringSiteNotice springSiteNotice = springSiteNoticeMapper.selectByPrimaryKey(record.getId());
		if (null == springSiteNotice) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringSiteNotice SpringSiteNoticeDO = new SpringSiteNotice();
		BeanUtils.copyProperties(record, SpringSiteNoticeDO);
		try {
			springSiteNoticeMapper.updateByPrimaryKey(SpringSiteNoticeDO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public PageInfo<SpringSiteNoticeDTO> getAllRecordByPage(SpringSiteNoticeDTO record,int page ,int size) {
		if (size > Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}

		PageHelper.startPage(page, size);
		Page<SpringSiteNotice> springSiteNotices = springSiteNoticeMapper.listByPage(record);
		List<SpringSiteNoticeDTO> springSiteNoticeDTOs = new ArrayList<>();
		springSiteNotices.stream().forEach(springSiteNotice -> {
			SpringSiteNoticeDTO springSiteNoticeDTO = new SpringSiteNoticeDTO();
			BeanUtils.copyProperties(springSiteNotice, springSiteNoticeDTO);
			springSiteNoticeDTOs.add(springSiteNoticeDTO);
		});
		PageInfo springSiteNoticesPageInfo = new PageInfo<>(springSiteNotices);
		springSiteNoticesPageInfo.setList(springSiteNoticeDTOs);
		return springSiteNoticesPageInfo;
	}

	@Override
	public void setDeleted(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > Constant.MAX_ITEM_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		try {
			springSiteNoticeMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public void batchSaveExcel(List<String[]> list) {

	}

}
