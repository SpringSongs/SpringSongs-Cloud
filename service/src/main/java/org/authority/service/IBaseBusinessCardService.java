package org.authority.service;

import java.util.List;

import org.authority.domain.BusinessCard;
import org.authority.util.R;

public interface IBaseBusinessCardService {

	void deleteByPrimaryKey(String id);

	void insert(BusinessCard record);

	BusinessCard selectByPrimaryKey(String id);

	void updateByPrimaryKey(BusinessCard record);

	R getAllRecordByPage(BusinessCard record, int currPage,int size);

	R setDeleted(List<String> ids);
	
	R batchSaveExcel(List<String[]> list);
}
