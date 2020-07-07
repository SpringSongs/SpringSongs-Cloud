package org.authority.service.impl;

import java.util.List;

import org.authority.domain.Attachment;
import org.authority.mapper.BaseFileMapper;
import org.authority.service.IBaseFileService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BaseFileServiceImpl implements IBaseFileService {

	@Autowired
	private BaseFileMapper baseFileDao;
	
	@Override
	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		baseFileDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Attachment record) {
		// TODO Auto-generated method stub
		baseFileDao.insert(record);
	}

	@Override
	public Attachment selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return baseFileDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(Attachment record) {
		// TODO Auto-generated method stub
		baseFileDao.updateByPrimaryKey(record);
	}

	@Override
	public R getAllRecordByPage(Attachment record, int currPage, int size) {
		PageHelper.startPage(currPage, size);
		Page<Attachment> list = baseFileDao.ListByPage(record);
		R r = new R();
		r.put("data",list.getResult());
		r.put("msg",list.getPageNum());
		r.put("code",200);
		r.put("count",list.getTotal());
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
