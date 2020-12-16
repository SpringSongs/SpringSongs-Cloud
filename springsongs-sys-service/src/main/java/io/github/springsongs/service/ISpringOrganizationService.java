package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringOrganizationDTO;

public interface ISpringOrganizationService {
	void deleteByPrimaryKey(String id);

	void insert(SpringOrganizationDTO record);

	SpringOrganizationDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringOrganizationDTO record);

	PageInfo<SpringOrganizationDTO> getAllRecordByPage(SpringOrganizationDTO record,int page,int size);

	void setDeleted(List<String> ids);
	
	void batchSaveExcel(List<String[]> list);

	List<SpringOrganizationDTO> listOrganizationsByParent(String parentId);

	List<SpringOrganizationDTO> listAll();
	
	List<SpringOrganizationDTO> ListAllToTree();
}
