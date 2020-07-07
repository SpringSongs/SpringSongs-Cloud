package org.authority.service.impl;

import java.util.List;

import org.authority.domain.Aritlce;
import org.authority.mapper.BaseAritlceMapper;
import org.authority.service.IBaseAritlceService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BaseAritlceServiceImpl implements IBaseAritlceService {

	@Autowired
	private BaseAritlceMapper baseAritlceDao;

	@Override
	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		baseAritlceDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Aritlce record) {
		// TODO Auto-generated method stub
		baseAritlceDao.insert(record);
	}

	@Override
	public Aritlce selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return baseAritlceDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(Aritlce record) {
		baseAritlceDao.updateByPrimaryKey(record);

	}

	@Override
	public R getAllRecordByPage(Aritlce record, int currPage, int size) {
		PageHelper.startPage(currPage, size);
		Page<Aritlce> list = baseAritlceDao.ListByPage(record);
		R r = new R();
		r.put("data", list.getResult());
		r.put("msg", list.getPageNum());
		r.put("code", 200);
		r.put("count", list.getTotal());
		return r;
	}

	@Override
	public R setDeleted(List<String> ids) {
		R r = new R();
		try {
			baseAritlceDao.setDeleted(ids);
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
