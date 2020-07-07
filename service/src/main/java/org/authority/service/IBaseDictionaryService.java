package org.authority.service;

import java.util.List;

import org.authority.domain.Dictionary;
import org.authority.util.R;

public interface IBaseDictionaryService {

	void deleteByPrimaryKey(String id);

	void insert(Dictionary record);

	Dictionary selectByPrimaryKey(String id);

	void updateByPrimaryKey(Dictionary record);

	R getAllRecordByPage(Dictionary record, int currPage,int size);

	R setDeleted(List<String> ids);
	
	R batchSaveExcel(List<String[]> list);
}
