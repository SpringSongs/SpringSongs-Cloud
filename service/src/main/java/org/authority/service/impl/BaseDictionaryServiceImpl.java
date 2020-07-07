package org.authority.service.impl;

import java.util.List;

import org.authority.domain.DictionaryDetail;
import org.authority.domain.Dictionary;
import org.authority.mapper.BaseDictionaryDetailMapper;
import org.authority.mapper.BaseDictionaryMapper;
import org.authority.service.IBaseDictionaryService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BaseDictionaryServiceImpl implements IBaseDictionaryService {

	@Autowired
	private BaseDictionaryMapper baseDictionaryDao;
	
	@Autowired
	private BaseDictionaryDetailMapper BaseDictionaryDetailDao;
	
	@Override
	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		baseDictionaryDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Dictionary record) {
		// TODO Auto-generated method stub
		baseDictionaryDao.insert(record);
	}

	@Override
	public Dictionary selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return baseDictionaryDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(Dictionary record) {
		// TODO Auto-generated method stub
		baseDictionaryDao.updateByPrimaryKey(record);
	}

	@Override
	public R getAllRecordByPage(Dictionary record, int currPage, int size) {
		PageHelper.startPage(currPage, size);
		Page<Dictionary> list = baseDictionaryDao.ListByPage(record);
		R r = new R();
		r.put("data",list.getResult());
		r.put("msg",list.getPageNum());
		r.put("code",200);
		r.put("count",list.getTotal());
		return r;
	}

	@Override
	public R setDeleted(List<String> ids) {
		R r=R.ok("OK");
		List<Dictionary> baseDictionaryEntityList=baseDictionaryDao.listInIds(ids);
		for(Dictionary entity:baseDictionaryEntityList) {
			List<DictionaryDetail> baseDictionaryDetailEntityList=BaseDictionaryDetailDao.listByCode(entity.getCode());
			if (!CollectionUtils.isEmpty(baseDictionaryDetailEntityList)) {
				r.put("code", 500);
				r.put("msg", entity.getTitle()+"存在子节点不允许删除");
				break;
			}
		}
		if (r.get("code").toString().equals("200")) {
			baseDictionaryDao.setDeleted(ids);
			for(Dictionary entity:baseDictionaryEntityList) {
				BaseDictionaryDetailDao.deleteByCode(entity.getCode());
			}
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

}
