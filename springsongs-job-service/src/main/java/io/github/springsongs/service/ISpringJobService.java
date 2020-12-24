package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringJobDTO;

public interface ISpringJobService {

	void deleteByPrimaryKey(String id);

	void insert(SpringJobDTO record);

	SpringJobDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringJobDTO record);

	PageInfo<SpringJobDTO> getAllRecordByPage(SpringJobDTO record,int page,int size);

	void setDeleted(List<String> ids);
	
	void batchSaveExcel(List<String[]> list);
	
	void addTask(SpringJobDTO record);
	
	void updateTask(SpringJobDTO record);
	
	void pauseTask(String taskClassName, String groupCode);
	
	void resumeTask(String taskClassName, String taskGroupCode);
	
	void deleteTask(String taskClassName, String groupCode);
}
