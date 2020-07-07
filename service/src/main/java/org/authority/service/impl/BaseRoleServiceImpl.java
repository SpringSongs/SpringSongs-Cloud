package org.authority.service.impl;

import java.util.List;

import org.authority.domain.Role;
import org.authority.domain.MemberRole;
import org.authority.mapper.BaseRoleMapper;
import org.authority.mapper.BaseUserRoleMapper;
import org.authority.service.IBaseRoleService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BaseRoleServiceImpl implements IBaseRoleService {

	@Autowired
	private BaseRoleMapper baseRoleDao;
	
	@Autowired
	private BaseUserRoleMapper baseUserRoleDao;

	@Override
	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		baseRoleDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Role record) {
		// TODO Auto-generated method stub
		baseRoleDao.insert(record);
	}

	@Override
	public Role selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return baseRoleDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(Role record) {
		// TODO Auto-generated method stub
		baseRoleDao.updateByPrimaryKey(record);
	}

	@Override
	public R getAllRecordByPage(Role record, int currPage, int size) {
		PageHelper.startPage(currPage, size);
		Page<Role> list = baseRoleDao.ListByPage(record);
		R r = new R();
		r.put("data", list.getResult());
		r.put("msg", list.getPageNum());
		r.put("code", 200);
		r.put("count", list.getTotal());
		return r;
	}

	@Override
	public R setDeleted(List<String> ids) {
		R r = R.ok("OK");
		List<Role> baseRoleEntityList = baseRoleDao.listInIds(ids);
		for (Role entity : baseRoleEntityList) {
			if (!entity.getEnableDelete()) {
				r.put("code", 500);
				r.put("msg", entity.getTitle() + "不允许删除");
			}
		}
		if (r.get("code").toString().equals("200")) {
			baseRoleDao.setDeleted(ids);
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
	public List<Role> getRolesByUserId(String userId) {
		return baseRoleDao.getRolesByUserId(userId);
	}

	@Override
	public R listRolesByUserId(String userId, int page, int limit) {
		PageHelper.startPage(page, limit);
		Page<Role> list = baseRoleDao.listRolesByUserId(userId);
		R r = new R();
		r.put("data", list.getResult());
		r.put("msg", list.getPageNum());
		r.put("code", 200);
		r.put("count", list.getTotal());
		return r;
	}

	@Override
	public void saveUserToRole(List<MemberRole> baseUserRoleEntityList, String roleId) {
		baseUserRoleDao.deleteByRoleId(roleId);
		baseUserRoleDao.saveAll(baseUserRoleEntityList);
	}

}
