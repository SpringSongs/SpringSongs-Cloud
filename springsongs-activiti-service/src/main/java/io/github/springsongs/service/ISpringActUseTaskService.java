package io.github.springsongs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringActUseTaskDTO;

public interface ISpringActUseTaskService {
	void deleteByPrimaryKey(String id);

	void insert(SpringActUseTaskDTO record);
	
	void insert(List<SpringActUseTaskDTO> record);

	SpringActUseTaskDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringActUseTaskDTO record);

	PageInfo<SpringActUseTaskDTO> getAllRecordByPage(SpringActUseTaskDTO record,int page,int size);

	void setDeleted(List<String> ids);

	void batchSaveExcel(List<String[]> list);
	
	List<SpringActUseTaskDTO> listUserTaskByProcDefKey(String procDefKey);

	void initSingleDefinition(String processDefinitionId,String procDefKey );

	void initAllDefinition();

	void setUserToTask(String procDefKey, HttpServletRequest request);
}
