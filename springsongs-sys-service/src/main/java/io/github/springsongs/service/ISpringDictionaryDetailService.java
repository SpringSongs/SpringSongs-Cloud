package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringDictionaryDetailDTO;

public interface ISpringDictionaryDetailService {

	void deleteByPrimaryKey(String id);

	void insert(SpringDictionaryDetailDTO record);

	SpringDictionaryDetailDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringDictionaryDetailDTO record);

	PageInfo<SpringDictionaryDetailDTO> getAllRecordByPage(SpringDictionaryDetailDTO SpringDictionaryDetailDTOQuery,
			int page, int size);

	void setDeleted(List<String> ids);

	void batchSaveExcel(List<String[]> list);

	void delete(List<String> ids);

	public void setDeleteByCode(String code);

	List<SpringDictionaryDetailDTO> listSpringDictionaryDetailByDictionaryCode(String dictionaryCode);
}
