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

import io.github.springsongs.domain.SpringFriendLink;
import io.github.springsongs.dto.SpringFriendLinkDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringFriendLinkMapper;
import io.github.springsongs.service.ISpringFriendLinkService;
import io.github.springsongs.utils.Constant;

@Service
public class SpringFriendLinkServiceImpl implements ISpringFriendLinkService {

	static Logger logger = LoggerFactory.getLogger(SpringFriendLinkServiceImpl.class);

	@Autowired
	private SpringFriendLinkMapper springFriendLinkMapper;

	@Override
	public void deleteByPrimaryKey(String id) {
		try {
			springFriendLinkMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public void insert(SpringFriendLinkDTO record) {
		record.setId(UUID.randomUUID().toString());
		SpringFriendLink springFriendLink = new SpringFriendLink();
		BeanUtils.copyProperties(record, springFriendLink);
		try {
			springFriendLinkMapper.insert(springFriendLink);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}

	}

	@Override
	public SpringFriendLinkDTO selectByPrimaryKey(String id) {
		SpringFriendLink springFriendLink = null;
		springFriendLink = springFriendLinkMapper.selectByPrimaryKey(id);
		if (null == springFriendLink) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringFriendLinkDTO springFriendLinkDTO = new SpringFriendLinkDTO();
		BeanUtils.copyProperties(springFriendLink, springFriendLinkDTO);
		return springFriendLinkDTO;
	}

	@Override
	public void updateByPrimaryKey(SpringFriendLinkDTO record) {
		SpringFriendLink springFriendLink = springFriendLinkMapper.selectByPrimaryKey(record.getId());
		if (null == springFriendLink) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringFriendLink springFriendLinkDO = new SpringFriendLink();
		BeanUtils.copyProperties(record, springFriendLinkDO);
		try {
			springFriendLinkMapper.updateByPrimaryKey(springFriendLinkDO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public PageInfo<SpringFriendLinkDTO> getAllRecordByPage(SpringFriendLinkDTO record,int page,int size) {
		if (size> Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}

		PageHelper.startPage(page, size);
		Page<SpringFriendLink> springFriendLinks = springFriendLinkMapper.listByPage(record);
		List<SpringFriendLinkDTO> springFriendLinkDTOs = new ArrayList<>();
		springFriendLinks.stream().forEach(springFriendLink -> {
			SpringFriendLinkDTO springFriendLinkDTO = new SpringFriendLinkDTO();
			BeanUtils.copyProperties(springFriendLink, springFriendLinkDTO);
			springFriendLinkDTOs.add(springFriendLinkDTO);
		});
		PageInfo springFriendLinksPageInfo = new PageInfo<>(springFriendLinks);
		springFriendLinksPageInfo.setList(springFriendLinkDTOs);
		return springFriendLinksPageInfo;
	}

	@Override
	public void setDeleted(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > Constant.MAX_ITEM_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		try {
			springFriendLinkMapper.setDelete(ids);
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
