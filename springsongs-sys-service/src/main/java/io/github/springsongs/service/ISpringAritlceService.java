package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringAritlceDTO;

public interface ISpringAritlceService {
	void deleteByPrimaryKey(String id);

	void insert(SpringAritlceDTO record);

	SpringAritlceDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringAritlceDTO record);

	PageInfo<SpringAritlceDTO> getAllRecordByPage(SpringAritlceDTO SpringAritlceDTOQuery, int page,int size);

	void setDeleted(List<String> ids);

	void batchSaveExcel(List<String[]> list);

	void delete(List<String> ids);

	void audit(String id);

	void hotStatus(String id);

	void topStatus(String id);

	void featured(String id);
}
