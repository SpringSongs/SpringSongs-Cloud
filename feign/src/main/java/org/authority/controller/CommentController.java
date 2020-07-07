package org.authority.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.authority.domain.Comment;
import org.authority.service.ICommentService;
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
@RequestMapping(value = "/BaseComment")
public class CommentController extends BaseController {

	@Autowired
	private ICommentService baseCommentService;

	@PostMapping(value = "ListByPage")
	public R getPage(@RequestBody Comment viewEntity, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {

		try {
			R r = baseCommentService.getAllRecordByPage(viewEntity, page, limit);

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
				r = baseCommentService.selectByPrimaryKey(id);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Create")
	public R save(@RequestBody Comment viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getContent())) {
			r.put("msg", "请填写评论内容");
			r.put("code", 500);
		} else if (viewEntity.getContent().length() > 2000) {
			r.put("msg", "评论内容长度不能大于2000");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getObjectId())) {
			r.put("msg", "请填写主题主键");
			r.put("code", 500);
		} else if (viewEntity.getObjectId().length() > 36) {
			r.put("msg", "主题主键长度不能大于36");
			r.put("code", 500);
		} else {
			try {
				viewEntity.setId(UUID.randomUUID().toString());
				viewEntity.setCreatedOn(new Date());
				baseCommentService.insert(viewEntity);
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
	public R update(@RequestBody Comment viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getContent())) {
			r.put("msg", "请填写评论内容");
			r.put("code", 500);
		} else if (viewEntity.getContent().length() > 2000) {
			r.put("msg", "评论内容长度不能大于2000");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getObjectId())) {
			r.put("msg", "请填写主题主键");
			r.put("code", 500);
		} else if (viewEntity.getObjectId().length() > 36) {
			r.put("msg", "主题主键长度不能大于36");
			r.put("code", 500);
		} else {
			try {
				r = baseCommentService.selectByPrimaryKey(viewEntity.getId());
				if (r.get("code").toString().equalsIgnoreCase("200")) {
					String rvs = JSONArray.toJSONString(r.get("data"));
					Comment entity = JSON.parseObject(rvs, Comment.class);
					if (null == entity) {
						r.put("msg", "信息不存在或者已经被删除!");
						r.put("code", 500);
					} else {
						entity.setId(viewEntity.getId());
						entity.setContent(viewEntity.getContent());
						entity.setObjectId(viewEntity.getObjectId());
						entity.setAuditFlag(viewEntity.getAuditFlag());
						entity.setSortCode(viewEntity.getSortCode());
						entity.setDeletedFlag(viewEntity.getDeletedFlag());
						entity.setUpdatedOn(new Date());

						r=baseCommentService.updateByPrimaryKey(entity);
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
				baseCommentService.setDeleted(ids);
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
				r = baseCommentService.selectByPrimaryKey(id);
				Comment entity = (Comment) r.get("data");
				if (null == entity) {
					r.put("msg", "信息不存在或者已经被删除!");
					r.put("code", 500);
				} else {
					if (entity.getAuditFlag() == true) {
						entity.setAuditFlag(false);
					} else {
						entity.setAuditFlag(true);
					}
					r=baseCommentService.updateByPrimaryKey(entity);
				}
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}

		}
		return r;
	}
}
