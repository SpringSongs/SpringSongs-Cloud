package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringAttachmentCategoryDTO;

public interface ISpringAttachmentCategoryService {
	void deleteByPrimaryKey(String id);

	void insert(SpringAttachmentCategoryDTO record);

	SpringAttachmentCategoryDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringAttachmentCategoryDTO record);

	PageInfo<SpringAttachmentCategoryDTO> getAllRecordByPage(SpringAttachmentCategoryDTO record,int page,int size);

	void setDeleted(List<String> ids);

	void batchSaveExcel(List<String[]> list);
	
	void delete(List<String> ids);
	
	List<SpringAttachmentCategoryDTO> listSpringAttachmentCategoryByParentId(String parentId);

}
