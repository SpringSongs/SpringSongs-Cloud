package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringSiteInfoDTO;

public interface ISpringSiteInfoService {
	void deleteByPrimaryKey(String id);

	void insert(SpringSiteInfoDTO record);

	SpringSiteInfoDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringSiteInfoDTO record);

	PageInfo<SpringSiteInfoDTO> getAllRecordByPage(SpringSiteInfoDTO record,int page,int size);

	void setDeleted(List<String> ids);
	
	void batchSaveExcel(List<String[]> list);
}
