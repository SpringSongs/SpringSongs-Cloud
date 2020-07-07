package org.authority.service.impl;

import java.util.List;

import org.authority.domain.ApiSecurity;
import org.authority.domain.Member;
import org.authority.mapper.BaseApiSecurityMapper;
import org.authority.service.IBaseApiSecurityService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class BaseApiSecurityServiceImpl implements IBaseApiSecurityService {

	@Autowired
	private BaseApiSecurityMapper baseApiSecurityDao;

	/**
	 *
	 * 物理删除
	 * 
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void deleteByPrimaryKey(String id) {
		baseApiSecurityDao.deleteByPrimaryKey(id);

	}

	/**
	 *
	 * 保存
	 * 
	 * @param record
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void insert(ApiSecurity record) {
		baseApiSecurityDao.insert(record);

	}

	/**
	 *
	 * 获取单项
	 * 
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public ApiSecurity selectByPrimaryKey(String id) {
		return baseApiSecurityDao.selectByPrimaryKey(id);
	}

	/**
	 *
	 * 更新
	 * 
	 * @param record
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void updateByPrimaryKey(ApiSecurity record) {
		baseApiSecurityDao.updateByPrimaryKey(record);
	}

	/**
	 *
	 * 分页查询
	 * 
	 * @param record
	 * @return Page<BaseApiSecurityEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public R getAllRecordByPage(ApiSecurity record, int currPage, int size) {
		PageHelper.startPage(currPage, size);
		Page<ApiSecurity> list = baseApiSecurityDao.ListByPage(record);
		R r = new R();
		r.put("data", list.getResult());
		r.put("msg", list.getPageNum());
		r.put("code", 200);
		r.put("count", list.getTotal());
		return r;
	}

	/**
	 *
	 * 逻辑删除
	 * 
	 * @param record
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public R setDeleted(List<String> ids) {
		R r = R.ok("成功");
		List<ApiSecurity> baseApiSecurityEntityList = this.listApiSecurityByIds(ids);
		for (ApiSecurity entity : baseApiSecurityEntityList) {
			if (entity.getEnableDelete() == false) {
				r.put("code", "500");
				r.put("msg", entity.getAppkey() + "不允许删除");
				break;
			}
		}
		if (r.get("code").toString().equals("200")) {
			baseApiSecurityDao.setDeleted(ids);
			r.put("code", "200");
			r.put("msg", "成功删除数据");
		}
		return r;
	}

	/**
	 *
	 * Excel批量保存
	 * 
	 * @param list
	 * @return R
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public R batchSaveExcel(List<String[]> list) {
		return new R();
	}

	@Override
	public List<ApiSecurity> listApiSecurityByIds(List<String> ids) {
		return baseApiSecurityDao.listAppSecuritysByIds(ids);
	}
}
