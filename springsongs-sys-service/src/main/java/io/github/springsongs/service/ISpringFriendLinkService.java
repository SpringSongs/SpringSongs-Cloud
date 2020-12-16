package io.github.springsongs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.SpringFriendLinkDTO;

public interface ISpringFriendLinkService {

	void deleteByPrimaryKey(String id);

	void insert(SpringFriendLinkDTO record);

	SpringFriendLinkDTO selectByPrimaryKey(String id);

	void updateByPrimaryKey(SpringFriendLinkDTO record);

	PageInfo<SpringFriendLinkDTO> getAllRecordByPage(SpringFriendLinkDTO record,int page,int size);

	void setDeleted(List<String> ids);

	void batchSaveExcel(List<String[]> list);
}
