package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringActProcessRouterDTO;

public interface ISpringActProcessRouterService {
	void deleteByPrimaryKey(String id);

	void insert(SpringActProcessRouterDTO record);

	SpringActProcessRouterDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringActProcessRouterDTO record);

	PageInfo<SpringActProcessRouterDTO> getAllRecordByPage(SpringActProcessRouterDTO record, int page,int size);

	void setDeleted(List<String> ids);

	void batchSaveExcel(List<String[]> list);

	SpringActProcessRouterDTO findSpringActProcessRouterByProcDefKey(String procDefKey);
}
