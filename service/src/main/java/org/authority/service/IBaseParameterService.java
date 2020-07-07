package org.authority.service;

import java.util.List;

import org.authority.domain.Parameter;
import org.authority.util.R;

public interface IBaseParameterService {
	void deleteByPrimaryKey(String id);

	void insert(Parameter record);

	Parameter selectByPrimaryKey(String id);

	void updateByPrimaryKey(Parameter record);

	R getAllRecordByPage(Parameter record, int currPage,int size);

	R setDeleted(List<String> ids);
	
	R batchSaveExcel(List<String[]> list);
	
	List<Parameter> listParametersByIds(List<String> ids);
}
