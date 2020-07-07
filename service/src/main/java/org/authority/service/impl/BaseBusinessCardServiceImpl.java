package org.authority.service.impl;

import java.util.List;

import org.authority.domain.BusinessCard;
import org.authority.mapper.BaseBusinessCardMapper;
import org.authority.service.IBaseBusinessCardService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BaseBusinessCardServiceImpl implements IBaseBusinessCardService {

	@Autowired
	private BaseBusinessCardMapper baseBusinessCardDao;
	@Override
	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		baseBusinessCardDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(BusinessCard record) {
		// TODO Auto-generated method stub
		baseBusinessCardDao.insert(record);
	}

	@Override
	public BusinessCard selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return baseBusinessCardDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(BusinessCard record) {
		// TODO Auto-generated method stub
		baseBusinessCardDao.updateByPrimaryKey(record);
	}

	@Override
	public R getAllRecordByPage(BusinessCard record, int currPage, int size) {
		PageHelper.startPage(currPage, size);
		Page<BusinessCard> list = baseBusinessCardDao.ListByPage(record);
		R r = new R();
		r.put("data",list.getResult());
		r.put("msg",list.getPageNum());
		r.put("code",200);
		r.put("count",list.getTotal());
		return r;
	}

	@Override
	public R setDeleted(List<String> ids) {
		R r = new R();
		try {
			baseBusinessCardDao.setDeleted(ids);
			r.put("code", 200);
			r.put("msg", "成功删除");
		} catch (Exception e) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;
	}

	@Override
	public R batchSaveExcel(List<String[]> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
