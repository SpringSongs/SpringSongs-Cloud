package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringArticleCommentDTO;

public interface ISpringArticleCommentService {

	void deleteByPrimaryKey(String id);

	void insert(SpringArticleCommentDTO record);

	SpringArticleCommentDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringArticleCommentDTO record);

	PageInfo<SpringArticleCommentDTO> getAllRecordByPage(SpringArticleCommentDTO SpringArticleCommentDTOQuery,int page,int size);

	void setDeleted(List<String> ids);
	
	void batchSaveExcel(List<String[]> list);
	
	void delete(List<String> ids);
	
	void audit(String id);
}
