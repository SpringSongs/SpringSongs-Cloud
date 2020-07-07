package org.authority.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.authority.domain.Parameter;
import org.authority.service.IParameterService;
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
@RequestMapping(value = "/BaseParameter")
public class ParameterController extends BaseController {

	@Autowired
	private IParameterService baseParameterService;

	@PostMapping(value = "ListByPage")
	public R getPage(@RequestBody Parameter viewEntity, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		try {
			R r = baseParameterService.getAllRecordByPage(viewEntity, page, limit);

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
				r = baseParameterService.selectByPrimaryKey(id);

			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Create")
	public R save(@RequestBody Parameter viewEntity, HttpServletRequest request) {
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
		} else if (StringUtils.isEmpty(viewEntity.getContent())) {
			r.put("msg", "请填写值");
			r.put("code", 500);
		} else if (viewEntity.getContent().length() > 45) {
			r.put("msg", "值长度不能大于45");
			r.put("code", 500);
		} else {
			try {
				viewEntity.setId(UUID.randomUUID().toString());
				viewEntity.setDeletedFlag(false);
				viewEntity.setCreatedOn(new Date());
				r=baseParameterService.insert(viewEntity);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Edit")
	public R update(@RequestBody Parameter viewEntity, HttpServletRequest request) {
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
		} else if (StringUtils.isEmpty(viewEntity.getContent())) {
			r.put("msg", "请填写值");
			r.put("code", 500);
		} else if (viewEntity.getContent().length() > 45) {
			r.put("msg", "值长度不能大于45");
			r.put("code", 500);
		} else {
			try {
				r = baseParameterService.selectByPrimaryKey(viewEntity.getId());
				if (r.get("code").toString().equalsIgnoreCase("200")) {
					String rvs = JSONArray.toJSONString(r.get("data"));
					Parameter entity = JSON.parseObject(rvs, Parameter.class);
					if (null == entity) {
						r.put("msg", "信息不存在或者已经被删除!");
						r.put("code", 500);
					} else if (!entity.getEnableEdit()) {
						r.put("msg", "信息不允许编辑!");
						r.put("code", 500);
					} else {
						entity.setTitle(viewEntity.getTitle());
						entity.setContent(viewEntity.getContent());
						entity.setSortCode(viewEntity.getSortCode());
						entity.setEnableEdit(viewEntity.getEnableEdit());
						entity.setEnableDelete(viewEntity.getEnableDelete());
						entity.setUpdatedOn(new Date());

						r = baseParameterService.updateByPrimaryKey(entity);
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
					r=baseParameterService.setDeleted(ids);
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
					// baseParameterService.delete(ids);
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
}
