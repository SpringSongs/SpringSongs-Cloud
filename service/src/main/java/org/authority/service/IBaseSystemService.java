package org.authority.service;

import java.util.List;

import org.authority.domain.System;
import org.authority.util.R;

public interface IBaseSystemService {

	void deleteByPrimaryKey(String id);

	void insert(System record);

	System selectByPrimaryKey(String id);

	void updateByPrimaryKey(System record);

	R getAllRecordByPage(System record, int currPage, int size);

	R setDeleted(List<String> ids);

	R batchSaveExcel(List<String[]> list);

	List<System> listAll();
}
