package org.authority.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.authority.domain.ModuleRole;
import org.authority.domain.Role;
import org.authority.domain.MemberRole;
import org.authority.service.IModuleService;
import org.authority.service.IRoleService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

@RestController
@RequestMapping(value = "/BaseRole")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService baseRoleService;

	@Autowired
	private IModuleService baseModuleService;



	@PostMapping(value = "ListByPage")
	public R getPage(@RequestBody Role viewEntity, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {

		try {
			R r = baseRoleService.getAllRecordByPage(viewEntity, page, limit);

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
				r = baseRoleService.selectByPrimaryKey(id);

			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Create")
	public R save(@RequestBody Role viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getTitle())) {
			r.put("msg", "请填写名称");
			r.put("code", 500);
		} else if (viewEntity.getTitle().length() > 45) {
			r.put("msg", "名称长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getDescription())) {
			r.put("msg", "请填写说明");
			r.put("code", 500);
		} else if (viewEntity.getDescription().length() > 45) {
			r.put("msg", "说明长度不能大于45");
			r.put("code", 500);
		} else {
			try {
				viewEntity.setId(UUID.randomUUID().toString());
				viewEntity.setCreatedOn(new Date());
				viewEntity.setDeletedFlag(false);
				r = baseRoleService.insert(viewEntity);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Edit")
	public R update(@RequestBody Role viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getTitle())) {
			r.put("msg", "请填写名称");
			r.put("code", 500);
		} else if (viewEntity.getTitle().length() > 45) {
			r.put("msg", "名称长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getDescription())) {
			r.put("msg", "请填写说明");
			r.put("code", 500);
		} else if (viewEntity.getDescription().length() > 45) {
			r.put("msg", "说明长度不能大于45");
			r.put("code", 500);
		} else {
			try {
				r = baseRoleService.selectByPrimaryKey(viewEntity.getId());
				if (r.get("code").toString().equalsIgnoreCase("200")) {
					String rvs = JSONArray.toJSONString(r.get("data"));
					Role entity = JSON.parseObject(rvs, Role.class);
					if (null == entity) {
						r.put("msg", "信息不存在或者已经被删除!");
						r.put("code", 500);
					} else if (!entity.getEnableEdit()) {
						r.put("msg", "信息不允许编辑!");
						r.put("code", 500);
					} else {
						entity.setDescription(viewEntity.getDescription());
						entity.setEnableEdit(viewEntity.getEnableEdit());
						entity.setEnableDelete(viewEntity.getEnableDelete());
						entity.setUpdatedOn(new Date());

						r = baseRoleService.updateByPrimaryKey(entity);
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

				r = baseRoleService.setDeleted(ids);
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
			try {

				if (r.get("code").toString().equals("200")) {
					// baseRoleService.delete(ids);
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

	@PostMapping(value = "ListByUserId/{userId}")
	public R listByUserId(@PathVariable(value = "userId", required = true) String userId,
			@RequestParam("page") int page, @RequestParam("limit") int limit) {
		R r = new R();
		try {
			r = baseRoleService.listRolesByUserId(userId, page, limit);
		} catch (Exception e) {
			r.put("code", 500);
			r.put("msg", "系数错误");
		}
		return r;
	}

	@PostMapping(value = "/ListAuthority/{roleId}")
	public R listAuthority(@PathVariable(value = "roleId", required = true) String roleId) {
		R r = new R();
		try {
			r = baseModuleService.listModulesByRoleId(roleId);
		} catch (Exception ex) {
			r.put("msg", "系统错误!");
			r.put("code", 500);
		}
		return r;
	}

	@PostMapping(value = "/SetUsers/{roleId}")
	public R setUsers(@PathVariable(value = "roleId", required = true) String roleId,
			@RequestParam(value = "ids", required = true) List<String> userIds, HttpServletRequest request) {
		R r = new R();
		try {
			List<MemberRole> baseUserRoleEntityList = new ArrayList<MemberRole>();
			for (String str : userIds) {
				MemberRole entity = new MemberRole();
				entity.setRoleId(roleId);
				entity.setUserId(str);
				entity.setCreatedOn(new Date());
				baseUserRoleEntityList.add(entity);
			}
			r = baseRoleService.saveUserToRole(baseUserRoleEntityList, roleId);
		} catch (Exception ex) {
			r.put("msg", "系统错误!");
			r.put("code", 500);
		}
		return r;
	}
	
	@PostMapping(value = "/SetAuthority/{roleId}")
	public R setAuthority(@PathVariable(value = "roleId", required = true) String roleId,
			@RequestParam(value = "moduleIds", required = true) List<String> moduleIds, HttpServletRequest request) {
		R r = new R();
		try {
			List<ModuleRole> baseModuleRoleEntityList = new ArrayList<ModuleRole>();
			for (String str : moduleIds) {
				ModuleRole entity = new ModuleRole();
				entity.setId(UUID.randomUUID().toString());
				entity.setRoleId(roleId);
				entity.setModuleId(str);
				entity.setCreatedOn(new Date());
				baseModuleRoleEntityList.add(entity);
			}
			baseModuleService.saveModuleToRole(baseModuleRoleEntityList, roleId);
			r.put("msg", "授权成功!");
			r.put("code", 200);
		} catch (Exception ex) {
			r.put("msg", "系统错误!");
			r.put("code", 500);
		}
		return r;
	}
}
