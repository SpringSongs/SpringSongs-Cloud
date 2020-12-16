package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringDistrictDTO;

public interface ISpringDistrictService {
	void deleteByPrimaryKey(Long id);

	void insert(SpringDistrictDTO record);

	SpringDistrictDTO selectByPrimaryKey(Long id);

	void updateByPrimaryKey(SpringDistrictDTO record);

	PageInfo<SpringDistrictDTO> getAllRecordByPage(SpringDistrictDTO record,int page,int size);

	void setDeleted(List<Long> ids);

	void batchSaveExcel(List<String[]> list);
	
	List<SpringDistrictDTO> listSpringDistrictByParentId(Long parentId);
}
