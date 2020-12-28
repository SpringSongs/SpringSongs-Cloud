package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringActVacationDTO;

public interface ISpringActVacationService {
	void deleteByPrimaryKey(String id);

	void insert(SpringActVacationDTO record);

	SpringActVacationDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringActVacationDTO record);

	PageInfo<SpringActVacationDTO> getAllRecordByPage(SpringActVacationDTO record,int page,int size);

	void setDeleted(List<String> ids);

	void batchSaveExcel(List<String[]> list);

	public String submitSpringActVacation(SpringActVacationDTO vacation);
}
