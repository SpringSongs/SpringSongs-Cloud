package org.authority.service;

import java.util.List;

import org.authority.domain.DictionaryDetail;
import org.authority.util.R;

public interface IBaseDictionaryDetailService {
	void deleteByPrimaryKey(String id);

	void insert(DictionaryDetail record);

	DictionaryDetail selectByPrimaryKey(String id);

	void updateByPrimaryKey(DictionaryDetail record);

	R getAllRecordByPage(DictionaryDetail record, int currPage,int size);

	void setDeleted(List<String> ids);
	
	R batchSaveExcel(List<String[]> list);
}
