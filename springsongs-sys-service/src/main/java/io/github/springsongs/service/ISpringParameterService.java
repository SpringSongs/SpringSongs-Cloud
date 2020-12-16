package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringParameterDTO;

public interface ISpringParameterService {
	void deleteByPrimaryKey(String id);

	void insert(SpringParameterDTO record);

	SpringParameterDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringParameterDTO record);

	PageInfo<SpringParameterDTO> getAllRecordByPage(SpringParameterDTO SpringParameterDTOQuery,int page,int size);

	void setDeleted(List<String> ids);
	
	void batchSaveExcel(List<String[]> list);
	
	void delete(List<String> ids);
	
	List<SpringParameterDTO> listByIds(List<String> ids);
}
