package org.authority.service.impl;

import java.util.List;

import org.authority.domain.Folder;
import org.authority.mapper.BaseFolderMapper;
import org.authority.service.IBaseFolderService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service
@Transactional
public class BaseFolderServiceImpl implements IBaseFolderService {

	@Autowired
	private BaseFolderMapper baseFolderDao;
	
	@Override
	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		baseFolderDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Folder record) {
		// TODO Auto-generated method stub
		baseFolderDao.insert(record);
	}

	@Override
	public Folder selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return baseFolderDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(Folder record) {
		// TODO Auto-generated method stub
		baseFolderDao.updateByPrimaryKey(record);
	}

	@Override
	public R getAllRecordByPage(Folder record, int currPage, int size) {
		PageHelper.startPage(currPage, size);
		Page<Folder> list = baseFolderDao.ListByPage(record);
		R r = new R();
		r.put("data", list.getResult());
		r.put("msg", list.getPageNum());
		r.put("code", 200);
		r.put("count", list.getTotal());
		return r;
	}

	@Override
	public void setDeleted(List<String> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public R batchSaveExcel(List<String[]> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
