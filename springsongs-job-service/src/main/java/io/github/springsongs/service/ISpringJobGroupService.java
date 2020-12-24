package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringJobGroupDTO;

public interface ISpringJobGroupService {
	void deleteByPrimaryKey(String id);

	void insert(SpringJobGroupDTO record);

	SpringJobGroupDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringJobGroupDTO record);

	PageInfo<SpringJobGroupDTO> getAllRecordByPage(SpringJobGroupDTO record, int page,int size);

	void setDeleted(List<String> ids);

	void batchSaveExcel(List<String[]> list);
	
	List<SpringJobGroupDTO> listAll();
}
