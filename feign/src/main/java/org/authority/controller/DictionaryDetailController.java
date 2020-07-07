package org.authority.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.authority.domain.DictionaryDetail;
import org.authority.service.IDictionaryDetailService;
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
@RequestMapping(value = "/BaseDictionaryDetail")
public class DictionaryDetailController extends BaseController {

	@Autowired
	private IDictionaryDetailService baseDictionaryDetailService;

	@PostMapping(value = "ListByPage")
	public R getPage(@RequestBody DictionaryDetail viewEntity, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {

		try {
			R r = baseDictionaryDetailService.getAllRecordByPage(viewEntity, page, limit);

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
				r = baseDictionaryDetailService.selectByPrimaryKey(id);

			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Create")
	public R save(@RequestBody DictionaryDetail viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getDictionaryCode())) {
			r.put("msg", "请填写字典主表编码");
			r.put("code", 500);
		} else if (viewEntity.getDictionaryCode().length() > 45) {
			r.put("msg", "字典主表编码长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getDetailCode())) {
			r.put("msg", "请填写编码");
			r.put("code", 500);
		} else if (viewEntity.getDetailCode().length() > 45) {
			r.put("msg", "编码长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getDetailName())) {
			r.put("msg", "请填写名称");
			r.put("code", 500);
		} else if (viewEntity.getDetailName().length() > 45) {
			r.put("msg", "名称长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getDetailValue())) {
			r.put("msg", "请填写值");
			r.put("code", 500);
		} else if (viewEntity.getDetailValue().length() > 45) {
			r.put("msg", "值长度不能大于45");
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
				viewEntity.setCreatedOn(new Date());
				viewEntity.setDeletedFlag(false);
				r=baseDictionaryDetailService.insert(viewEntity);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Edit")
	public R update(@RequestBody DictionaryDetail viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getDictionaryCode())) {
			r.put("msg", "请填写字典主表编码");
			r.put("code", 500);
		} else if (viewEntity.getDictionaryCode().length() > 45) {
			r.put("msg", "字典主表编码长度不能大于45");
			r.put("code", 500);
		}else if (StringUtils.isEmpty(viewEntity.getDetailCode())) {
			r.put("msg", "请填写编码");
			r.put("code", 500);
		} else if (viewEntity.getDetailCode().length() > 45) {
			r.put("msg", "编码长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getDetailName())) {
			r.put("msg", "请填写名称");
			r.put("code", 500);
		} else if (viewEntity.getDetailName().length() > 45) {
			r.put("msg", "名称长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getDetailValue())) {
			r.put("msg", "请填写值");
			r.put("code", 500);
		} else if (viewEntity.getDetailValue().length() > 45) {
			r.put("msg", "值长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getDescription())) {
			r.put("msg", "请填写说明");
			r.put("code", 500);
		} else if (viewEntity.getDescription().length() > 200) {
			r.put("msg", "说明长度不能大于200");
			r.put("code", 500);
		} else {
			try {
				r = baseDictionaryDetailService.selectByPrimaryKey(viewEntity.getId());
				if (r.get("code").toString().equalsIgnoreCase("200")) {
					String rvs = JSONArray.toJSONString(r.get("data"));
					DictionaryDetail entity = JSON.parseObject(rvs, DictionaryDetail.class);
					if (null == entity) {
						r.put("msg", "信息不存在或者已经被删除!");
						r.put("code", 500);
					} else {
						entity.setId(viewEntity.getId());
						entity.setDictionaryCode(viewEntity.getDictionaryCode());
						entity.setParentId(viewEntity.getParentId());
						entity.setDetailCode(viewEntity.getDetailCode());
						entity.setDetailName(viewEntity.getDetailName());
						entity.setDetailValue(viewEntity.getDetailValue());
						entity.setDescription(viewEntity.getDescription());
						entity.setChildIds(viewEntity.getChildIds());
						entity.setSortCode(viewEntity.getSortCode());
						entity.setEnableEdit(viewEntity.getEnableEdit());
						entity.setEnableDelete(viewEntity.getEnableDelete());
						entity.setDeletedFlag(viewEntity.getDeletedFlag());
						entity.setUpdatedOn(new Date());

						r=baseDictionaryDetailService.updateByPrimaryKey(entity);
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
				baseDictionaryDetailService.setDeleted(ids);
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