package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringLoginLogDTO;

public interface ISpringLoginLogService {

	void deleteByPrimaryKey(String id);

	void insert(SpringLoginLogDTO record);

	SpringLoginLogDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringLoginLogDTO record);

	PageInfo<SpringLoginLogDTO> getAllRecordByPage(SpringLoginLogDTO SpringLoginLogDTOQuery,int page,int size);

	
	void batchSaveExcel(List<String[]> list);
	
	void delete(List<String> ids);
}
