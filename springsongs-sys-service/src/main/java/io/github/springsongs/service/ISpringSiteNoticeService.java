package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringSiteNoticeDTO;

public interface ISpringSiteNoticeService {
	void deleteByPrimaryKey(String id);

	void insert(SpringSiteNoticeDTO record);

	SpringSiteNoticeDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringSiteNoticeDTO record);

	PageInfo<SpringSiteNoticeDTO> getAllRecordByPage(SpringSiteNoticeDTO record,int page,int size);

	void setDeleted(List<String> ids);
	
	void batchSaveExcel(List<String[]> list);
}
