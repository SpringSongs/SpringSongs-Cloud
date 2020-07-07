package org.authority.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.authority.domain.Aritlce;
import org.authority.service.IAritlceService;
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
@RequestMapping(value = "/BaseAritlce")
public class AritlceController extends BaseController {

	@Autowired(required = true)
	private IAritlceService baseAritlceService;

	@PostMapping(value = "ListByPage")
	public R getPage(@RequestBody Aritlce viewEntity, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		try {
			R r = baseAritlceService.getAllRecordByPage(viewEntity, page, limit);

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
				r = baseAritlceService.selectByPrimaryKey(id);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Create")
	public R save(@RequestBody Aritlce viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getTitle())) {
			r.put("msg", "请填写标题");
			r.put("code", 500);
		} else if (viewEntity.getTitle().length() > 200) {
			r.put("msg", "标题长度不能大于200");
			r.put("code", 500);
		} else {
			try {
				viewEntity.setId(UUID.randomUUID().toString());
				viewEntity.setDeletedFlag(false);
				viewEntity.setAuditFlag(false);
				viewEntity.setCreatedOn(new Date());
				r=baseAritlceService.insert(viewEntity);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Edit")
	public R update(@RequestBody Aritlce viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getTitle())) {
			r.put("msg", "请填写标题");
			r.put("code", 500);
		} else if (viewEntity.getTitle().length() > 200) {
			r.put("msg", "标题长度不能大于200");
			r.put("code", 500);
		} else {
			try {
				r = baseAritlceService.selectByPrimaryKey(viewEntity.getId());
				if (r.get("code").toString().equalsIgnoreCase("200")) {
					String rvs = JSONArray.toJSONString(r.get("data"));
					Aritlce entity = JSON.parseObject(rvs, Aritlce.class);
					if (null == entity) {
						r.put("msg", "信息不存在或者已经被删除!");
						r.put("code", 500);
					} else {
						entity.setTitle(viewEntity.getTitle());
						entity.setContents(viewEntity.getContents());
						entity.setAuthor(viewEntity.getAuthor());
						entity.setComeFrom(viewEntity.getComeFrom());
						entity.setComeFromLink(viewEntity.getComeFromLink());
						entity.setUpdatedOn(new Date());
						baseAritlceService.updateByPrimaryKey(entity);
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
				baseAritlceService.setDeleted(ids);
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

	@PostMapping(value = "/Audit/{id}")
	public R audit(@PathVariable(value = "id", required = true) String id) {
		R r = new R();
		if (StringUtils.isEmpty(id)) {
			r.put("msg", "参数不允许为空!");
			r.put("code", 500);
		} else {
			try {
				r=baseAritlceService.audit(id);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}

		}
		return r;
	}
}
