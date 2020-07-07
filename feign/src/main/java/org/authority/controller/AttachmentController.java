package org.authority.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.authority.domain.Attachment;
import org.authority.service.IAttachmentService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

@RestController
@RequestMapping(value = "/BaseFile")
public class AttachmentController extends BaseController {

	@Autowired
	private IAttachmentService baseFileService;

	@Value("${web.upload.path}")
	private String uploadPath;

	@PostMapping(value = "ListByPage")
	public R getPage(@RequestBody Attachment viewEntity, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		try {
			R r = baseFileService.getAllRecordByPage(viewEntity, page, limit);

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
				r = baseFileService.selectByPrimaryKey(id);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Create")
	public R save(@RequestBody Attachment viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getFolderId())) {
			r.put("msg", "请填写文件夾主键");
			r.put("code", 500);
		} else if (viewEntity.getFolderId().length() > 36) {
			r.put("msg", "文件夾主键长度不能大于36");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getFolderName())) {
			r.put("msg", "请填写文件夹名称");
			r.put("code", 500);
		} else if (viewEntity.getFolderName().length() > 45) {
			r.put("msg", "文件夹名称长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getPath())) {
			r.put("msg", "请填写文件路径");
			r.put("code", 500);
		} else if (viewEntity.getPath().length() > 300) {
			r.put("msg", "文件路径长度不能大于300");
			r.put("code", 500);
		} else {
			try {
				viewEntity.setId(UUID.randomUUID().toString());
				viewEntity.setCreatedOn(new Date());
				r=baseFileService.insert(viewEntity);
			} catch (Exception e) {
				r.put("msg", "系统错误!");
				r.put("code", 500);
			}
		}
		return r;
	}

	@PostMapping(value = "/Edit")
	public R update(@RequestBody Attachment viewEntity, HttpServletRequest request) {
		R r = new R();
		if (StringUtils.isEmpty(viewEntity.getFolderId())) {
			r.put("msg", "请填写文件夾主键");
			r.put("code", 500);
		} else if (viewEntity.getFolderId().length() > 36) {
			r.put("msg", "文件夾主键长度不能大于36");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getFolderName())) {
			r.put("msg", "请填写文件夹名称");
			r.put("code", 500);
		} else if (viewEntity.getFolderName().length() > 45) {
			r.put("msg", "文件夹名称长度不能大于45");
			r.put("code", 500);
		} else if (StringUtils.isEmpty(viewEntity.getPath())) {
			r.put("msg", "请填写文件路径");
			r.put("code", 500);
		} else if (viewEntity.getPath().length() > 300) {
			r.put("msg", "文件路径长度不能大于300");
			r.put("code", 500);
		} else {
			try {
				r = baseFileService.selectByPrimaryKey(viewEntity.getId());
				if (r.get("code").toString().equalsIgnoreCase("200")) {
					String rvs = JSONArray.toJSONString(r.get("data"));
					Attachment entity = JSON.parseObject(rvs, Attachment.class);
					if (null == entity) {
						r.put("msg", "信息不存在或者已经被删除!");
						r.put("code", 500);
					} else {
						entity.setDescription(viewEntity.getDescription());
						entity.setUpdatedOn(new Date());

						r=baseFileService.updateByPrimaryKey(entity);
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
				r=baseFileService.setDeleted(ids);
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

	@PostMapping(value = "/Upload")
	public R importExcel(MultipartFile file, HttpServletRequest request) {
		R r = new R();
		String originalFileName = file.getOriginalFilename();
		String fileName = UUID.randomUUID().toString()
				+ originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
		File f = new File(uploadPath + fileName);
		try {
			file.transferTo(f);
			r.put("msg", "上传成功!");
			r.put("data", fileName);
			r.put("code", HttpServletResponse.SC_OK);
		} catch (IllegalStateException e) {
			logger.error(e.getMessage());
			r.put("msg", "系统错误!");
			r.put("code", HttpServletResponse.SC_BAD_REQUEST);
		} catch (IOException e) {
			logger.error(e.getMessage());
			r.put("msg", "系统错误!");
			r.put("code", HttpServletResponse.SC_BAD_REQUEST);
		}
		return r;
	}
}
