package org.authority.service;

import java.util.List;

import org.authority.domain.Attachment;
import org.authority.util.R;

public interface IBaseFileService {
	void deleteByPrimaryKey(String id);

	void insert(Attachment record);

	Attachment selectByPrimaryKey(String id);

	void updateByPrimaryKey(Attachment record);

	R getAllRecordByPage(Attachment record, int currPage,int size);

	void setDeleted(List<String> ids);
	
	R batchSaveExcel(List<String[]> list);
}
