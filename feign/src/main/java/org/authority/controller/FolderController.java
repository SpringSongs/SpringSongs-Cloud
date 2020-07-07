package org.authority.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.authority.domain.Folder;
import org.authority.service.IFolderService;
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
@RequestMapping(value = "/BaseFolder")
public class FolderController extends BaseController {

	@Autowired
	private IFolderService baseFolderService;

	@PostMapping(value = "ListByPage")
	public R getPage(@RequestBody Folder viewEntity, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		try {
			R r = baseFolderService.getAllRecordByPage(viewEntity, page, limit);
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
				r = baseFolderService.selectByPrimaryKey(id);

			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Create")
	public R save(@RequestBody Folder viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getTitle())) {
			r.put("msg", "请填写文件夾名称");
			r.put("code", 500);
		} else if (viewEntity.getTitle().length() > 45) {
			r.put("msg", "文件夾名称长度不能大于45");
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
				baseFolderService.insert(viewEntity);
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
	public R update(@RequestBody Folder viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getTitle())) {
			r.put("msg", "请填写文件夾名称");
			r.put("code", 500);
		} else if (viewEntity.getTitle().length() > 45) {
			r.put("msg", "文件夾名称长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getDescription())) {
			r.put("msg", "请填写说明");
			r.put("code", 500);
		} else if (viewEntity.getDescription().length() > 45) {
			r.put("msg", "说明长度不能大于45");
			r.put("code", 500);
		} else {
			try {
				r = baseFolderService.selectByPrimaryKey(viewEntity.getId());
				if (r.get("code").toString().equalsIgnoreCase("200")) {
					String rvs = JSONArray.toJSONString(r.get("data"));
					Folder entity = JSON.parseObject(rvs, Folder.class);
					if (null == entity) {
						r.put("msg", "信息不存在或者已经被删除!");
						r.put("code", 500);
					} else {
						entity.setTitle(viewEntity.getTitle());
						entity.setDescription(viewEntity.getDescription());
						entity.setDictionaryCode(viewEntity.getDictionaryCode());
						entity.setDictionaryName(viewEntity.getDictionaryName());
						entity.setUpdatedOn(new Date());

						r=baseFolderService.updateByPrimaryKey(entity);
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
				baseFolderService.setDeleted(ids);
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
