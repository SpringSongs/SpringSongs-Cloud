package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringAttachmentDTO;

public interface ISpringAttachmentService {

	void deleteByPrimaryKey(String id);

	void insert(SpringAttachmentDTO record);

	SpringAttachmentDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringAttachmentDTO record);

	PageInfo<SpringAttachmentDTO> getAllRecordByPage(SpringAttachmentDTO record,int page,int size);

	void setDeleted(List<String> ids);
	
	void batchSaveExcel(List<String[]> list);
	
	void delete(List<String> ids);
}
