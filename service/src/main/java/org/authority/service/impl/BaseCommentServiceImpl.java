package org.authority.service.impl;

import java.util.List;

import org.authority.domain.Comment;
import org.authority.mapper.BaseCommentMapper;
import org.authority.service.IBaseCommentService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BaseCommentServiceImpl implements IBaseCommentService {

	@Autowired
	private BaseCommentMapper baseCommentDao;
	
	@Override
	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		baseCommentDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Comment record) {
		// TODO Auto-generated method stub
		baseCommentDao.insert(record);
	}

	@Override
	public Comment selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return baseCommentDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(Comment record) {
		// TODO Auto-generated method stub
		baseCommentDao.updateByPrimaryKey(record);
	}

	@Override
	public R getAllRecordByPage(Comment record, int currPage, int size) {
		PageHelper.startPage(currPage, size);
		Page<Comment> list = baseCommentDao.ListByPage(record);
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
			baseCommentDao.setDeleted(ids);
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
