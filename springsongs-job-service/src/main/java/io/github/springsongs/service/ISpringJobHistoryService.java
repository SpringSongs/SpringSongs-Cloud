package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringJobHistoryDTO;

public interface ISpringJobHistoryService {
	void deleteByPrimaryKey(String id);

	void insert(SpringJobHistoryDTO record);

	SpringJobHistoryDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringJobHistoryDTO record);

	PageInfo<SpringJobHistoryDTO> getAllRecordByPage(SpringJobHistoryDTO record,int page,int size);

	void setDeleted(List<String> ids);
	
	void batchSaveExcel(List<String[]> list);
}
