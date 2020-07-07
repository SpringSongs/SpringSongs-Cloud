package org.authority.service.impl;

import java.util.List;

import org.authority.domain.Role;
import org.authority.domain.Member;
import org.authority.domain.MemberSecurity;
import org.authority.domain.MemberRole;
import org.authority.mapper.BaseRoleMapper;
import org.authority.mapper.BaseUserLogOnMapper;
import org.authority.mapper.BaseUserMapper;
import org.authority.mapper.BaseUserRoleMapper;
import org.authority.service.IBaseBuserService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BaseBuserServiceImpl implements IBaseBuserService {

	@Autowired
	private BaseUserMapper baseBuserDao;

	@Autowired
	private BaseRoleMapper baseRoleDao;

	@Autowired
	private BaseUserLogOnMapper baseUserLogOnDao;

	@Autowired
	private BaseUserRoleMapper baseUserRoleDao;

	@Override
	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		baseBuserDao.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Member record) {
		// TODO Auto-generated method stub
		baseBuserDao.insert(record);
	}

	@Override
	public Member selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return baseBuserDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(Member record) {
		// TODO Auto-generated method stub
		baseBuserDao.updateByPrimaryKey(record);
	}

	@Override
	public R getAllRecordByPage(Member record, int currPage, int size) {
		PageHelper.startPage(currPage, size);
		Page<Member> list = baseBuserDao.ListByPage(record);
		R r = new R();
		r.put("data", list.getResult());
		r.put("msg", list.getPageNum());
		r.put("code", 200);
		r.put("count", list.getTotal());
		return r;
	}

	@Override
	public R setDeleted(List<String> ids) {
		R r = R.ok("成功");
		List<Member> baseUserEntityList = this.listUsersByIds(ids);
		for (Member userEntity : baseUserEntityList) {
			if (userEntity.getEnableDelete() == false) {
				r.put("code", "500");
				r.put("msg", userEntity.getUsername() + "不允许删除");
				break;
			}
		}
		if (r.get("code").toString().equals("200")) {
			baseBuserDao.setDeleted(ids);
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
	public R getByUserName(String username) {
		R r = R.ok("OK");
		Member userEntity = baseBuserDao.getByUserName(username);
		if (null != userEntity) {
			List<Role> baseRoleEntityList = baseRoleDao.getRolesByUserId(userEntity.getId());
			userEntity.setRoleList(baseRoleEntityList);
			MemberSecurity baseUserLogOnEntity = baseUserLogOnDao.findPwdByUserId(userEntity.getId());
			userEntity.setBaseUserLogOnEntity(baseUserLogOnEntity);
			r.put("code", 200);
			r.put("data", userEntity);
			r.put("msg", "获取成功");
		} else {
			r.put("code", 500);
			r.put("data", null);
			r.put("msg", "获取失败");
		}
		return r;
	}

	@Override
	public MemberSecurity findPwdByUserId(String userId) {
		// TODO Auto-generated method stub
		return baseUserLogOnDao.findPwdByUserId(userId);
	}

	@Override
	public List<Member> listUsersByIds(List<String> ids) {
		return baseBuserDao.listUsersByIds(ids);
	}

	@Override
	public void saveUserToRole(List<MemberRole> baseUserRoleEntityList, String userId) {
		baseUserRoleDao.deleteByUserId(userId);
		baseUserRoleDao.saveAll(baseUserRoleEntityList);
	}

	@Override
	public R listUsersByRoleId(String roleId, int page, int limit) {
		PageHelper.startPage(page, limit);
		Page<Member> list = baseBuserDao.listUserByRoleId(roleId);
		R r = new R();
		r.put("data", list.getResult());
		r.put("msg", list.getPageNum());
		r.put("code", 200);
		r.put("count", list.getTotal());
		return r;
	}

}
