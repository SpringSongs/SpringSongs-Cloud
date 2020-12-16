package io.github.springsongs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringSiteMessage;
import io.github.springsongs.dto.SpringSiteMessageDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringSiteMessageMapper;
import io.github.springsongs.service.ISpringSiteMessageService;
import io.github.springsongs.utils.Constant;

@Service
public class SpringSiteMessageServiceImpl implements ISpringSiteMessageService {

	static Logger logger = LoggerFactory.getLogger(SpringSiteMessageServiceImpl.class);

	@Autowired
	private SpringSiteMessageMapper springSiteMessageMapper;

	@Override
	public void deleteByPrimaryKey(String id) {
		try {
			springSiteMessageMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Async
	@Override
	public void insert(SpringSiteMessageDTO record) {
		record.setId(UUID.randomUUID().toString());
		SpringSiteMessage springSiteMessage = new SpringSiteMessage();
		BeanUtils.copyProperties(record, springSiteMessage);
		try {
			springSiteMessageMapper.insert(springSiteMessage);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public SpringSiteMessageDTO selectByPrimaryKey(String id) {
		SpringSiteMessage springSiteMessage = null;
		springSiteMessage = springSiteMessageMapper.selectByPrimaryKey(id);
		if (null == springSiteMessage) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		if (springSiteMessage.getStatus() != 1) {
			springSiteMessage.setStatus((short) 1);
			springSiteMessageMapper.updateByPrimaryKey(springSiteMessage);
		}
		SpringSiteMessageDTO springSiteMessageDTO = new SpringSiteMessageDTO();
		BeanUtils.copyProperties(springSiteMessage, springSiteMessageDTO);
		return springSiteMessageDTO;
	}

	@Override
	public void updateByPrimaryKey(SpringSiteMessageDTO record) {
		SpringSiteMessage springSiteMessage = springSiteMessageMapper.selectByPrimaryKey(record.getId());
		if (null == springSiteMessage) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringSiteMessage springSiteMessageDO = new SpringSiteMessage();
		BeanUtils.copyProperties(record, springSiteMessageDO);
		try {
			springSiteMessageMapper.updateByPrimaryKey(springSiteMessageDO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public PageInfo<SpringSiteMessageDTO> getAllRecordByPage(SpringSiteMessageDTO record,int page,int size) {
		if (size> Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		List<SpringSiteMessage> springSiteMessages = springSiteMessageMapper.listByPage(record);
		List<SpringSiteMessageDTO> springSiteMessageDTOs = new ArrayList<>();
		springSiteMessages.stream().forEach(springSiteMessage -> {
			SpringSiteMessageDTO springSiteMessageDTO = new SpringSiteMessageDTO();
			BeanUtils.copyProperties(springSiteMessage, springSiteMessageDTO);
			springSiteMessageDTOs.add(springSiteMessageDTO);
		});
		PageInfo springSiteMessagesPageInfo = new PageInfo<>(springSiteMessages);
		springSiteMessagesPageInfo.setList(springSiteMessageDTOs);
		return springSiteMessagesPageInfo;
	}

	@Override
	public void setDeleted(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > Constant.MAX_ITEM_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		try {
			springSiteMessageMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public void batchSaveExcel(List<String[]> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public int countNotReadMessageByUserId(String toUserId) {
		return springSiteMessageMapper.countNotReadMessageByUserId(toUserId);
	}

}
