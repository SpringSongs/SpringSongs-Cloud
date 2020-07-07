package org.authority.controller;

import java.util.List;

import org.authority.domain.Aritlce;
import org.authority.service.IBaseAritlceService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api("BaseAritlceController相关的api")
@RequestMapping(value = "/BaseAritlce")
public class BaseAritlceController  extends BaseController {

	@Autowired(required=true)
	private IBaseAritlceService baseAritlceService;

	@ApiOperation(value = "分页查询", notes = "分页查询", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "record", value = "", required = true, dataType = "BaseAritlceEntity"),
			@ApiImplicitParam(name = "page", value = "", required = true, dataType = "int"),
			@ApiImplicitParam(name = "size", value = "", required = true, dataType = "int") })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@PostMapping(value = "/GetAllRecordByPage")
	public R getAllRecordByPage(@RequestBody Aritlce record,@RequestParam("page") int page,@RequestParam("size") int size) {
		R r = new R();
		try {
			r = baseAritlceService.getAllRecordByPage(record, page, size);
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;
	}
	
	@ApiOperation(value = "删除个实体", notes = "取单个实体", response = String.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "", required = true, dataType = "String"), })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@PostMapping(value = "/DeleteByPrimaryKey")
	public R deleteByPrimaryKey(@RequestParam("id") String id) {
		R r = new R();
		try {
			baseAritlceService.deleteByPrimaryKey(id);
			r.put("code", 200);
			r.put("msg", "删除成功");
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;
	}
	
	@ApiOperation(value = "删除个实体", notes = "删除个实体", response = String.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "", required = true, dataType = "List<String>"), })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@PostMapping(value = "/SetDeleted")
	public R setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		R r = new R();
		try {
			r=baseAritlceService.setDeleted(ids);
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;
	}

	@ApiOperation(value = "保存个实体", notes = "取单个实体", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "record", value = "", required = true, dataType = "BaseAritlceEntity"), })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@PostMapping(value = "/Insert")
	public R insert(@RequestBody Aritlce record) {
		R r = new R();
		try {
			baseAritlceService.insert(record);
			r.put("code", 200);
			r.put("msg", "保存成功");
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;
	}

	@ApiOperation(value = "取单个实体", notes = "取单个实体", response = String.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "", required = true, dataType = "String"), })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@PostMapping(value = "/SelectByPrimaryKey")
	public R selectByPrimaryKey(@RequestParam("id") String id) {
		R r = new R();
		try {
			Aritlce entity = baseAritlceService.selectByPrimaryKey(id);
			r.put("code", 200);
			r.put("data", entity);
			r.put("msg", "获取成功");
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;
	}

	@ApiOperation(value = "更新个实体", notes = "取单个实体", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "record", value = "", required = true, dataType = "BaseAritlceEntity"), })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@PostMapping(value = "/UpdateByPrimaryKey")
	public R updateByPrimaryKey(@RequestBody Aritlce record) {
		R r = new R();
		try {
			baseAritlceService.updateByPrimaryKey(record);
			r.put("code", 200);
			r.put("msg", "更新成功");
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
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
				Aritlce entity = baseAritlceService.selectByPrimaryKey(id);
				if (null == entity) {
					r.put("msg", "信息不存在或者已经被删除!");
					r.put("code", 500);
				} else {
					if (entity.getAuditFlag() == true) {
						entity.setAuditFlag(false);
					} else {
						entity.setAuditFlag(true);
					}
					baseAritlceService.updateByPrimaryKey(entity);
					r.put("msg", "保存成功!");
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
