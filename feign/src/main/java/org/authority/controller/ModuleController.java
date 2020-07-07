package org.authority.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.authority.domain.Module;
import org.authority.service.IModuleService;
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
@RequestMapping(value = "/BaseModule")
public class ModuleController extends BaseController {

	@Autowired
	private IModuleService baseModuleService;

	@PostMapping(value = "ListByPage")
	public R getPage(@RequestBody Module viewEntity, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {

		try {
			R r = baseModuleService.getAllRecordByPage(viewEntity, page, limit);

			return r;
		} catch (Exception e) {
		}
		return new R();
	}

	@PostMapping(value = "/Detail")
	public R get(@RequestParam("id") String id) {
		R r = new R();
		if (StringUtils.isEmpty(id)) {
			r.put("msg", "参数不允许为空");
			r.put("code", 500);
		} else {
			try {
				r = baseModuleService.selectByPrimaryKey(id);

			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Create")
	public R save(@RequestBody Module viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getCode())) {
			r.put("msg", "请填写编码");
			r.put("code", 500);
		} else if (viewEntity.getCode().length() > 45) {
			r.put("msg", "编码长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getTitle())) {
			r.put("msg", "请填写名称");
			r.put("code", 500);
		} else if (viewEntity.getTitle().length() > 45) {
			r.put("msg", "名称长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getSystemId())) {
			r.put("msg", "请填写系统主键");
			r.put("code", 500);
		} else if (viewEntity.getSystemId().length() > 36) {
			r.put("msg", "系统主键长度不能大于36");
			r.put("code", 500);
		} else {
			try {
				viewEntity.setId(UUID.randomUUID().toString());
				viewEntity.setCreatedOn(new Date());
				r = baseModuleService.insert(viewEntity);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Edit")
	public R update(@RequestBody Module viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getCode())) {
			r.put("msg", "请填写编码");
			r.put("code", 500);
		} else if (viewEntity.getCode().length() > 45) {
			r.put("msg", "编码长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getTitle())) {
			r.put("msg", "请填写名称");
			r.put("code", 500);
		} else if (viewEntity.getTitle().length() > 45) {
			r.put("msg", "名称长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getSystemId())) {
			r.put("msg", "请填写系统主键");
			r.put("code", 500);
		} else if (viewEntity.getSystemId().length() > 36) {
			r.put("msg", "系统主键长度不能大于36");
			r.put("code", 500);
		} else {
			try {
				r = baseModuleService.selectByPrimaryKey(viewEntity.getId());
				if (r.get("code").toString().equalsIgnoreCase("200")) {
					String rvs = JSONArray.toJSONString(r.get("data"));
					Module entity = JSON.parseObject(rvs, Module.class);
					if (null == entity) {
						r.put("msg", "信息不存在或者已经被删除!");
						r.put("code", 500);
					} else if (!entity.getEnableEdit()) {
						r.put("msg", "信息不允许编辑!");
						r.put("code", 500);
					} else {
						entity.setCode(viewEntity.getCode());
						entity.setTitle(viewEntity.getTitle());
						entity.setFormName(viewEntity.getFormName());
						entity.setMenuFlag(viewEntity.getMenuFlag());
						entity.setVueUrl(viewEntity.getVueUrl());
						entity.setAngularUrl(viewEntity.getAngularUrl());
						entity.setParentId(viewEntity.getParentId());
						entity.setParentName(viewEntity.getParentName());
						entity.setSortCode(viewEntity.getSortCode());
						entity.setEnableEdit(viewEntity.getEnableEdit());
						entity.setEnableDelete(viewEntity.getEnableDelete());
						entity.setUpdatedOn(new Date());

						r = baseModuleService.updateByPrimaryKey(entity);
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

				if (r.get("code").toString().equals("200")) {
					baseModuleService.setDeleted(ids);
					r.put("msg", "删除成功!");
					r.put("code", 200);
				}
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/GetMenus")
	public R getMenus(@RequestParam(value = "userId", required = true) String userId) {
		R r = new R();
		try {
			r = baseModuleService.listModuleByUserId(userId);
		} catch (Exception e) {
			r.put("msg", "系统错误!");
			r.put("code", 500);
		}
		return r;
	}

	@PostMapping(value = "/GetMenusByParent")
	public R getAreasByParentId(@RequestParam(value = "parentId", required = true) String parentId,
			@RequestParam(value = "systemId", required = true) String systemId) {
		R r = new R();
		if (StringUtils.isEmpty(parentId)) {
			r.put("code", 500);
			r.put("msg", "缺少必要参数");
		} else {
			try {
				r = baseModuleService.getModulesByParentId(parentId, systemId);
			} catch (Exception e) {
				logger.error(e.getMessage());
				r.put("code", 500);
				r.put("msg", "程式错误");
			}
		}
		return r;
	}

}
