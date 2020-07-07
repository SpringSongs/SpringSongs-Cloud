package org.authority.service;

import java.util.List;

import org.authority.domain.Folder;
import org.authority.util.R;

public interface IBaseFolderService {

	void deleteByPrimaryKey(String id);

	void insert(Folder record);

	Folder selectByPrimaryKey(String id);

	void updateByPrimaryKey(Folder record);

	R getAllRecordByPage(Folder record, int currPage, int size);

	void setDeleted(List<String> ids);

	R batchSaveExcel(List<String[]> list);
}
