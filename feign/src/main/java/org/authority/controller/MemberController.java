package org.authority.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.authority.domain.Member;
import org.authority.domain.MemberRole;
import org.authority.service.IMemberService;
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
@RequestMapping(value = "/BaseBuser")
public class MemberController extends BaseController {

	@Autowired
	private IMemberService baseBuserService;

	@PostMapping(value = "ListByPage")
	public R getPage(@RequestBody Member viewEntity, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		R r = new R();
		try {
			r = baseBuserService.getAllRecordByPage(viewEntity, page, limit);
			return r;
		} catch (Exception e) {
			r.put("code", 500);
			r.put("msg", "系数错误");
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
				r = baseBuserService.selectByPrimaryKey(id);

			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Create")
	public R save(@RequestBody Member viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getUsername())) {
			r.put("msg", "请填写用户名");
			r.put("code", 500);
		} else if (viewEntity.getUsername().length() > 45) {
			r.put("msg", "用户名长度不能大于45");
			r.put("code", 500);
		} else {
			try {
				viewEntity.setId(UUID.randomUUID().toString());
				baseBuserService.insert(viewEntity);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Edit")
	public R update(@RequestBody Member viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getUsername())) {
			r.put("msg", "请填写用户名");
			r.put("code", 500);
		} else if (viewEntity.getUsername().length() > 45) {
			r.put("msg", "用户名长度不能大于45");
			r.put("code", 500);
		} else {
			try {
				r = baseBuserService.selectByPrimaryKey(viewEntity.getId());
				if (r.get("code").toString().equalsIgnoreCase("200")) {
					String rvs = JSONArray.toJSONString(r.get("data"));
					Member entity = JSON.parseObject(rvs, Member.class);
					if (null == entity) {
						r.put("msg", "信息不存在或者已经被删除!");
						r.put("code", 500);
					} else if (!entity.getEnableEdit()) {
						r.put("msg", "信息不允许编辑!");
						r.put("code", 500);
					} else {
						entity.setTruename(viewEntity.getTruename());
						entity.setEnableEdit(viewEntity.getEnableEdit());
						entity.setEnableDelete(viewEntity.getEnableDelete());
						entity.setUpdatedOn(new Date());

						baseBuserService.updateByPrimaryKey(entity);
						r.put("msg", "保存成功!");
						r.put("code", 200);
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
				r = baseBuserService.setDeleted(ids);
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
					baseBuserService.setDeleted(ids);
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

	@PostMapping(value = "/SetRoles/{userId}")
	public R setUsers(@PathVariable(value = "userId", required = true) String userId,
			@RequestParam(value = "ids", required = true) List<String> roleIds, HttpServletRequest request) {
		R r = new R();
		try {
			List<MemberRole> baseUserRoleEntityList = new ArrayList<MemberRole>();
			for (String str : roleIds) {
				MemberRole entity = new MemberRole();
				entity.setId(UUID.randomUUID().toString());
				entity.setRoleId(str);
				entity.setUserId(userId);
				entity.setCreatedOn(new Date());
				baseUserRoleEntityList.add(entity);
			}
			r = baseBuserService.saveUserToRole(baseUserRoleEntityList, userId);
		} catch (Exception ex) {
			r.put("msg", "系统错误!");
			r.put("code", 500);
		}
		return r;
	}

	@PostMapping(value = "ListByRoleId/{roleId}")
	public R listByRoleId(@PathVariable(value = "roleId", required = true) String roleId,
			@RequestParam("page") int page, @RequestParam("limit") int limit) {
		R r = new R();
		try {
			r = baseBuserService.listUsersByRoleId(roleId, page, limit);
		} catch (Exception e) {
			r.put("code", 500);
			r.put("msg", "系数错误");
		}
		return r;
	}

}
