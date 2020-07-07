package org.authority.service;

import java.util.List;

import org.authority.domain.Aritlce;
import org.authority.util.R;

public interface IBaseAritlceService {

	void deleteByPrimaryKey(String id);

	void insert(Aritlce record);

	Aritlce selectByPrimaryKey(String id);

	void updateByPrimaryKey(Aritlce record);

	R getAllRecordByPage(Aritlce record, int currPage,int size);

	R setDeleted(List<String> ids);
	
	R batchSaveExcel(List<String[]> list);
}
