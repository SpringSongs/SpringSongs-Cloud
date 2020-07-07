package org.authority.service;

import java.util.List;

import org.authority.domain.Comment;
import org.authority.util.R;

public interface IBaseCommentService {
	void deleteByPrimaryKey(String id);

	void insert(Comment record);

	Comment selectByPrimaryKey(String id);

	void updateByPrimaryKey(Comment record);

	R getAllRecordByPage(Comment record, int currPage,int size);

	R setDeleted(List<String> ids);
	
	R batchSaveExcel(List<String[]> list);
}
