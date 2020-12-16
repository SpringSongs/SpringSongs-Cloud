package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringContactDTO;

public interface ISpringContactService {

	void deleteByPrimaryKey(String id);

	void insert(SpringContactDTO record);

	SpringContactDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringContactDTO record);

	PageInfo<SpringContactDTO> getAllRecordByPage(SpringContactDTO SpringContactDTOQuery,int page,int size);

	void setDeleted(List<String> ids);

	void batchSaveExcel(List<String[]> list);

	void delete(List<String> ids);
}
