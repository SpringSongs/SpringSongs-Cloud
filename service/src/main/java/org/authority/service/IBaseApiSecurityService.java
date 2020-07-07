package org.authority.service;

import java.util.List;

import org.authority.domain.ApiSecurity;
import org.authority.util.R;


public interface IBaseApiSecurityService {

	void deleteByPrimaryKey(String id);

	void insert(ApiSecurity record);

	ApiSecurity selectByPrimaryKey(String id);

	void updateByPrimaryKey(ApiSecurity record);

	R getAllRecordByPage(ApiSecurity record, int currPage,int size);

	R setDeleted(List<String> ids);
	
	R batchSaveExcel(List<String[]> list);
	
	List<ApiSecurity> listApiSecurityByIds(List<String> ids);
}