package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringSystemDTO;

public interface ISpringSystemService {
	void deleteByPrimaryKey(String id);

	void insert(SpringSystemDTO record);

	SpringSystemDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringSystemDTO record);

	PageInfo<SpringSystemDTO> getAllRecordByPage(SpringSystemDTO SpringSystemDTOQuery,int page,int size);

	void setDeleted(List<String> ids);

	void batchSaveExcel(List<String[]> list);

	List<SpringSystemDTO> findInIds(List<String> ids);

	List<SpringSystemDTO> ListAll();
}
