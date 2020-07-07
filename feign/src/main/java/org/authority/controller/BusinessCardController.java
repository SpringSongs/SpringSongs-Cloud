package org.authority.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.authority.domain.BusinessCard;
import org.authority.service.IBusinessCardService;
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
@RequestMapping(value = "/BaseBusinessCard")
public class BusinessCardController extends BaseController {

	@Autowired
	private IBusinessCardService baseBusinessCardService;

	@PostMapping(value = "ListByPage")
	public R getPage(@RequestBody BusinessCard viewEntity, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		try {
			R r = baseBusinessCardService.getAllRecordByPage(viewEntity, page, limit);
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
				r = baseBusinessCardService.selectByPrimaryKey(id);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Create")
	public R save(@RequestBody BusinessCard viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getCompany())) {
			r.put("msg", "请填写公司");
			r.put("code", 500);
		} else if (viewEntity.getCompany().length() > 45) {
			r.put("msg", "公司长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getTitle())) {
			r.put("msg", "请填写职称");
			r.put("code", 500);
		} else if (viewEntity.getTitle().length() > 45) {
			r.put("msg", "职称长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getUsername())) {
			r.put("msg", "请填写名称");
			r.put("code", 500);
		} else if (viewEntity.getUsername().length() > 45) {
			r.put("msg", "名称长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getEmail())) {
			r.put("msg", "请填写邮箱");
			r.put("code", 500);
		} else if (viewEntity.getEmail().length() > 45) {
			r.put("msg", "邮箱长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getMobile())) {
			r.put("msg", "请填写手机");
			r.put("code", 500);
		} else if (viewEntity.getMobile().length() > 45) {
			r.put("msg", "手机长度不能大于45");
			r.put("code", 500);
		} else {
			try {
				viewEntity.setId(UUID.randomUUID().toString());
				viewEntity.setDeletedFlag(false);
				viewEntity.setCreatedOn(new Date());
				r=baseBusinessCardService.insert(viewEntity);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Edit")
	public R update(@RequestBody BusinessCard viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getCompany())) {
			r.put("msg", "请填写公司");
			r.put("code", 500);
		} else if (viewEntity.getCompany().length() > 45) {
			r.put("msg", "公司长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getTitle())) {
			r.put("msg", "请填写职称");
			r.put("code", 500);
		} else if (viewEntity.getTitle().length() > 45) {
			r.put("msg", "职称长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getUsername())) {
			r.put("msg", "请填写名称");
			r.put("code", 500);
		} else if (viewEntity.getUsername().length() > 45) {
			r.put("msg", "名称长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getEmail())) {
			r.put("msg", "请填写邮箱");
			r.put("code", 500);
		} else if (viewEntity.getEmail().length() > 45) {
			r.put("msg", "邮箱长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getMobile())) {
			r.put("msg", "请填写手机");
			r.put("code", 500);
		} else if (viewEntity.getMobile().length() > 45) {
			r.put("msg", "手机长度不能大于45");
			r.put("code", 500);
		} else {
			try {
				r = baseBusinessCardService.selectByPrimaryKey(viewEntity.getId());
				if (r.get("code").toString().equalsIgnoreCase("200")) {
					String rvs = JSONArray.toJSONString(r.get("data"));
					BusinessCard entity = JSON.parseObject(rvs, BusinessCard.class);
					if (null == entity) {
						r.put("msg", "信息不存在或者已经被删除!");
						r.put("code", 500);
					} else {
						entity.setCompany(viewEntity.getCompany());
						entity.setTitle(viewEntity.getTitle());
						entity.setUsername(viewEntity.getUsername());
						entity.setEmail(viewEntity.getEmail());
						entity.setWeb(viewEntity.getWeb());
						entity.setFax(viewEntity.getFax());
						entity.setQq(viewEntity.getQq());
						entity.setWebchat(viewEntity.getWebchat());
						entity.setMobile(viewEntity.getMobile());
						entity.setTel(viewEntity.getTel());
						entity.setSortCode(viewEntity.getSortCode());
						entity.setUpdatedOn(new Date());
						r=baseBusinessCardService.updateByPrimaryKey(entity);
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
				baseBusinessCardService.setDeleted(ids);
				r.put("msg", "删除成功!");
				r.put("code", 200);
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

				r.put("msg", "删除成功!");
				r.put("code", 200);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}
}
