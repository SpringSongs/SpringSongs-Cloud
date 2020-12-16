package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.ElementUiTreeDTO;
import io.github.springsongs.dto.SpringArticleCategoryDTO;

public interface ISpringArticleCategoryService {
	void deleteByPrimaryKey(String id);

	void insert(SpringArticleCategoryDTO record);

	SpringArticleCategoryDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringArticleCategoryDTO record);

	PageInfo<SpringArticleCategoryDTO> getAllRecordByPage(SpringArticleCategoryDTO record,int page,int size);

	void setDeleted(List<String> ids);
	
	void batchSaveExcel(List<String[]> list);
	
	List<ElementUiTreeDTO> getCategoryByParentId(String parentId);
   
	List<SpringArticleCategoryDTO> getByParentId(String parentId);
	
	List<SpringArticleCategoryDTO> listAll();
	
	List<SpringArticleCategoryDTO> ListAllToTree();
}
