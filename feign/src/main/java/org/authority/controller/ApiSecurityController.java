package org.authority.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.authority.domain.ApiSecurity;
import org.authority.service.IApiSecurityService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

@RestController
@RequestMapping(value = "/BaseApiSecurity")
public class ApiSecurityController extends BaseController {
	@Autowired
	private IApiSecurityService baseApiSecurityService;

	@PostMapping(value = "ListByPage")
	public R getPage(@RequestBody ApiSecurity viewEntity, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		R r = new R();
		try {
			r = baseApiSecurityService.getAllRecordByPage(viewEntity, page, limit);
			return r;
		} catch (Exception e) {
		}
		return r;
	}

	@PostMapping(value = "/Detail")
	public R get(@RequestParam("id") String id) {
		R r = new R();
		if (StringUtils.isEmpty(id)) {
			r.put("msg", "参数不允许为空");
			r.put("code", 500);
		} else {
			try {
				r = baseApiSecurityService.selectByPrimaryKey(id);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Create")
	public R save(@RequestBody ApiSecurity viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getAppkey())) {
			r.put("msg", "请填写编码");
			r.put("code", 500);
		} else if (viewEntity.getAppkey().length() > 45) {
			r.put("msg", "编码长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getAppsecurity())) {
			r.put("msg", "请填写名称");
			r.put("code", 500);
		} else if (viewEntity.getAppsecurity().length() > 45) {
			r.put("msg", "名称长度不能大于45");
			r.put("code", 500);
		} else {
			try {
				viewEntity.setId(UUID.randomUUID().toString());
				viewEntity.setDeletedFlag(false);
				viewEntity.setCreatedOn(new Date());
				r=baseApiSecurityService.insert(viewEntity);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Edit")
	public R update(@RequestBody ApiSecurity viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getAppkey())) {
			r.put("msg", "请填写编码");
			r.put("code", 500);
		} else if (viewEntity.getAppkey().length() > 45) {
			r.put("msg", "编码长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getAppsecurity())) {
			r.put("msg", "请填写名称");
			r.put("code", 500);
		} else if (viewEntity.getAppsecurity().length() > 45) {
			r.put("msg", "名称长度不能大于45");
			r.put("code", 500);
		} else {
			try {
				r = baseApiSecurityService.selectByPrimaryKey(viewEntity.getId());
				if (r.get("code").toString().equalsIgnoreCase("200")) {
					String rvs = JSONArray.toJSONString(r.get("data"));
					ApiSecurity entity = JSON.parseObject(rvs, ApiSecurity.class);
					if (null == entity) {
						r.put("msg", "信息不存在或者已经被删除!");
						r.put("code", 500);
					} else if (!entity.getEnableEdit()) {
						r.put("msg", "信息不允许编辑!");
						r.put("code", 500);
					} else {
						entity.setAppkey(viewEntity.getAppkey());
						entity.setAppsecurity(viewEntity.getAppsecurity());
						entity.setSortCode(viewEntity.getSortCode());
						entity.setEnableEdit(viewEntity.getEnableEdit());
						entity.setEnableDelete(viewEntity.getEnableDelete());
						entity.setUpdatedOn(new Date());
						r=baseApiSecurityService.updateByPrimaryKey(entity);
					}
				}
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/SetDeleted")
	public R setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {

		R r = new R();
		if (CollectionUtils.isEmpty(ids)) {
			r.put("msg", "参数不允许为空!");
			r.put("code", 500);
		} else {
			try {
				r=baseApiSecurityService.setDeleted(ids);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Deleted")
	public R deleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		R r = new R();
		if (CollectionUtils.isEmpty(ids)) {
			r.put("msg", "参数不允许为空!");
			r.put("code", 500);
		} else {

		}
		return r;
	}
}
