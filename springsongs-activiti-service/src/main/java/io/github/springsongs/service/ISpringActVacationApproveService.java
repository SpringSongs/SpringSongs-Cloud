package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringActVacationApproveDTO;

public interface ISpringActVacationApproveService {
	void deleteByPrimaryKey(String id);

	void insert(SpringActVacationApproveDTO record);

	SpringActVacationApproveDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringActVacationApproveDTO record);

	PageInfo<SpringActVacationApproveDTO> getAllRecordByPage(SpringActVacationApproveDTO record,int page,int size);

	void setDeleted(List<String> ids);
	
	void batchSaveExcel(List<String[]> list);
	
	public void completeSpringActVacationApprove(SpringActVacationApproveDTO record,String taskId);
	
	public void completeTask(String taskId, String auditStr);
	
	public List<SpringActVacationApproveDTO> findByVacationId(String vacationId);
}
