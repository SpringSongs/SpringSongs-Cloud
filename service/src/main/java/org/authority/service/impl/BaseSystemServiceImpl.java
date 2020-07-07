package org.authority.service.impl;

import java.util.List;

import org.authority.domain.System;
import org.authority.mapper.BaseSystemMapper;
import org.authority.service.IBaseSystemService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BaseSystemServiceImpl implements IBaseSystemService {

	@Autowired
	private BaseSystemMapper baseSystemDao;

	@Override
	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		baseSystemDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(System record) {
		// TODO Auto-generated method stub
		baseSystemDao.insert(record);
	}

	@Override
	public System selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return baseSystemDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(System record) {
		// TODO Auto-generated method stub
		baseSystemDao.updateByPrimaryKey(record);
	}

	@Override
	public R getAllRecordByPage(System record, int currPage, int size) {
		// TODO Auto-generated method stub
		PageHelper.startPage(currPage, size);
		Page<System> list = baseSystemDao.ListByPage(record);
		R r = new R();
		r.put("data", list.getResult());
		r.put("msg", list.getPageNum());
		r.put("code", 200);
		r.put("count", list.getTotal());
		return r;
	}

	@Override
	public R setDeleted(List<String> ids) {
		R r = R.ok("ok");
		List<System> baseSystemEntityList = baseSystemDao.listInIds(ids);
		for (System entity : baseSystemEntityList) {
			if (!entity.getEnableDelete()) {
				r.put("code", 500);
				r.put("msg", entity.getTitle() + "不允删除");
				break;
			}
		}
		if (r.get("code").toString().equals("200")) {
			baseSystemDao.setDeleted(ids);
			r.put("code", 200);
			r.put("msg", "删除成功");
		}
		return r;
	}

	@Override
	public R batchSaveExcel(List<String[]> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<System> listAll() {
		return baseSystemDao.listAll();
	}

}
