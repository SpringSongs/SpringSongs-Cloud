package org.authority.service.impl;

import java.util.List;

import org.authority.domain.ApiSecurity;
import org.authority.domain.Parameter;
import org.authority.mapper.BaseParameterMapper;
import org.authority.service.IBaseParameterService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BaseParameterServiceImpl implements IBaseParameterService {

	@Autowired
	private BaseParameterMapper baseParameterDao;
	
	@Override
	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		baseParameterDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Parameter record) {
		// TODO Auto-generated method stub
		baseParameterDao.insert(record);
	}

	@Override
	public Parameter selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return baseParameterDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(Parameter record) {
		// TODO Auto-generated method stub
		baseParameterDao.updateByPrimaryKey(record);
	}

	@Override
	public R getAllRecordByPage(Parameter record, int currPage, int size) {
		PageHelper.startPage(currPage, size);
		Page<Parameter> list = baseParameterDao.ListByPage(record);
		R r = new R();
		r.put("data",list.getResult());
		r.put("msg",list.getPageNum());
		r.put("code",200);
		r.put("count",list.getTotal());
		return r;
	}

	@Override
	public R setDeleted(List<String> ids) {
		R r = R.ok("成功");
		List<Parameter> baseParameterEntityList = this.listParametersByIds(ids);
		for (Parameter entity : baseParameterEntityList) {
			if (entity.getEnableDelete() == false) {
				r.put("code", "500");
				r.put("msg", entity.getTitle() + "不允许删除");
				break;
			}
		}
		if (r.get("code").toString().equals("200")) {
			baseParameterDao.setDeleted(ids);
			r.put("code", "200");
			r.put("msg", "成功删除数据");
		}
		return r;

	}

	@Override
	public R batchSaveExcel(List<String[]> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Parameter> listParametersByIds(List<String> ids) {
		return baseParameterDao.listParametersByIds(ids);
	}

}
