package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringActCategoryDTO;

public interface ISpringActCategoryService {
	void deleteByPrimaryKey(String id);

	void insert(SpringActCategoryDTO record);
	
	SpringActCategoryDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringActCategoryDTO record);

	PageInfo<SpringActCategoryDTO> getAllRecordByPage(SpringActCategoryDTO record,int page,int size);

	void setDeleted(List<String> ids);
	
	void batchSaveExcel(List<String[]> list);

	List<SpringActCategoryDTO> listAll();
}
