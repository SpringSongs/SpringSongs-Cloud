package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringDictionaryDTO;

public interface ISpringDictionaryService {
	void deleteByPrimaryKey(String id);

	void insert(SpringDictionaryDTO record);

	SpringDictionaryDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringDictionaryDTO record);

	PageInfo<SpringDictionaryDTO> getAllRecordByPage(SpringDictionaryDTO SpringDictionaryDTOQuery,int page,int size);

	void setDeleted(List<String> ids);
	
	void batchSaveExcel(List<String[]> list);
	
	void delete(List<String> ids);
	
	List<SpringDictionaryDTO> listByIds(List<String> ids);
}
