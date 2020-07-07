package org.authority.service.impl;

import java.util.List;

import org.authority.domain.LoginLog;
import org.authority.mapper.BaseLoginLogMapper;
import org.authority.service.IBaseLoginLogService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BaseLoginLogServiceImpl implements IBaseLoginLogService {

	@Autowired
	private BaseLoginLogMapper baseLoginLogDao;

	
	@Override
	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		baseLoginLogDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(LoginLog record) {
		// TODO Auto-generated method stub
		baseLoginLogDao.insert(record);
	}

	@Override
	public LoginLog selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return baseLoginLogDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(LoginLog record) {
		// TODO Auto-generated method stub
		baseLoginLogDao.updateByPrimaryKey(record);
	}

	@Override
	public R getAllRecordByPage(LoginLog record, int currPage, int size) {
		PageHelper.startPage(currPage, size);
		Page<LoginLog> list = baseLoginLogDao.ListByPage(record);
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
