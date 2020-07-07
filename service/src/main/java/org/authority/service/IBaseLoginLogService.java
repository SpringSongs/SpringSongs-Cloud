package org.authority.service;

import java.util.List;

import org.authority.domain.LoginLog;
import org.authority.util.R;

public interface IBaseLoginLogService {
	void deleteByPrimaryKey(String id);

	void insert(LoginLog record);

	LoginLog selectByPrimaryKey(String id);

	void updateByPrimaryKey(LoginLog record);

	R getAllRecordByPage(LoginLog record, int currPage,int size);

	void setDeleted(List<String> ids);
	
	R batchSaveExcel(List<String[]> list);
}
