package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringSiteMessageDTO;

public interface ISpringSiteMessageService {
	void deleteByPrimaryKey(String id);

	void insert(SpringSiteMessageDTO record);

	SpringSiteMessageDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringSiteMessageDTO record);

	PageInfo<SpringSiteMessageDTO> getAllRecordByPage(SpringSiteMessageDTO record,int page,int size);

	void setDeleted(List<String> ids);

	void batchSaveExcel(List<String[]> list);

	int countNotReadMessageByUserId(String toUserId);
}
