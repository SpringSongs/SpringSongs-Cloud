package org.authority.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.authority.domain.Dictionary;
import org.authority.service.IDictionaryService;
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
@RequestMapping(value = "/BaseDictionary")
public class DictionaryController extends BaseController {

	@Autowired
	private IDictionaryService baseDictionaryService;

	@PostMapping(value = "ListByPage")
	public R getPage(@RequestBody Dictionary viewEntity, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {

		try {
			R r = baseDictionaryService.getAllRecordByPage(viewEntity, page, limit);

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
				r = baseDictionaryService.selectByPrimaryKey(id);

			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Create")
	public R save(@RequestBody Dictionary viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getCode())) {
			r.put("msg", "请填写编码");
			r.put("code", 500);
		} else if (viewEntity.getCode().length() > 45) {
			r.put("msg", "编码长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getTitle())) {
			r.put("msg", "请填写标题");
			r.put("code", 500);
		} else if (viewEntity.getTitle().length() > 45) {
			r.put("msg", "标题长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getDescription())) {
			r.put("msg", "请填写说明");
			r.put("code", 500);
		} else if (viewEntity.getDescription().length() > 200) {
			r.put("msg", "说明长度不能大于200");
			r.put("code", 500);
		} else {
			try {
				viewEntity.setId(UUID.randomUUID().toString());
				viewEntity.setDeletedFlag(false);
				viewEntity.setCreatedOn(new Date());
				baseDictionaryService.insert(viewEntity);
				r.put("msg", "保存成功!");
				r.put("code", 200);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Edit")
	public R update(@RequestBody Dictionary viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getCode())) {
			r.put("msg", "请填写编码");
			r.put("code", 500);
		} else if (viewEntity.getCode().length() > 45) {
			r.put("msg", "编码长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getTitle())) {
			r.put("msg", "请填写标题");
			r.put("code", 500);
		} else if (viewEntity.getTitle().length() > 45) {
			r.put("msg", "标题长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getDescription())) {
			r.put("msg", "请填写说明");
			r.put("code", 500);
		} else if (viewEntity.getDescription().length() > 200) {
			r.put("msg", "说明长度不能大于200");
			r.put("code", 500);
		} else {
			try {
				r = baseDictionaryService.selectByPrimaryKey(viewEntity.getId());
				if (r.get("code").toString().equalsIgnoreCase("200")) {
					String rvs = JSONArray.toJSONString(r.get("data"));
					Dictionary entity = JSON.parseObject(rvs, Dictionary.class);
					if (null == entity) {
						r.put("msg", "信息不存在或者已经被删除!");
						r.put("code", 500);
					} else if (!entity.getEnableEdit()) {
						r.put("msg", "信息不允许编辑!");
						r.put("code", 500);
					} else {
						entity.setTitle(viewEntity.getTitle());
						entity.setDescription(viewEntity.getDescription());
						entity.setSortCode(viewEntity.getSortCode());
						entity.setEnableEdit(viewEntity.getEnableEdit());
						entity.setUpdatedOn(new Date());

						r=baseDictionaryService.updateByPrimaryKey(entity);
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
				r=baseDictionaryService.setDeleted(ids);
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