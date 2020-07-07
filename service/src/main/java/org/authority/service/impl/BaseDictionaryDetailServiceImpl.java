package org.authority.service.impl;

import java.util.List;

import org.authority.domain.DictionaryDetail;
import org.authority.mapper.BaseDictionaryDetailMapper;
import org.authority.service.IBaseDictionaryDetailService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BaseDictionaryDetailServiceImpl implements IBaseDictionaryDetailService {

	@Autowired
	private BaseDictionaryDetailMapper baseDictionaryDetailDao;

	@Override
	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		baseDictionaryDetailDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(DictionaryDetail record) {
		// TODO Auto-generated method stub
		baseDictionaryDetailDao.insert(record);
	}

	@Override
	public DictionaryDetail selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return baseDictionaryDetailDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(DictionaryDetail record) {
		// TODO Auto-generated method stub
		baseDictionaryDetailDao.updateByPrimaryKey(record);
	}

	@Override
	public R getAllRecordByPage(DictionaryDetail record, int currPage,
			int size) {
		PageHelper.startPage(currPage, size);
		Page<DictionaryDetail> list = baseDictionaryDetailDao.ListByPage(record);
		R r = new R();
		r.put("data",list.getResult());
		r.put("msg",list.getPageNum());
		r.put("code",200);
		r.put("count",list.getTotal());
		return r;
	}

	@Override
	public void setDeleted(List<String> ids) {
		baseDictionaryDetailDao.setDeleted(ids);

	}

	@Override
	public R batchSaveExcel(List<String[]> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
