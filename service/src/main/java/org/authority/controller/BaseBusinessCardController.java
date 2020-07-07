package org.authority.controller;

import java.util.List;

import org.authority.domain.BusinessCard;
import org.authority.service.IBaseBusinessCardService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api("BaseBusinessCardController相关的api")
@RequestMapping(value = "/BaseBusinessCard")
public class BaseBusinessCardController extends BaseController {

	@Autowired
	private IBaseBusinessCardService baseBusinessCardService;

	@ApiOperation(value = "分页查询", notes = "分页查询", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "record", value = "", required = true, dataType = "BaseBusinessCardEntity"),
			@ApiImplicitParam(name = "page", value = "", required = true, dataType = "int"),
			@ApiImplicitParam(name = "size", value = "", required = true, dataType = "int") })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@PostMapping(value = "/GetAllRecordByPage")
	public R getAllRecordByPage(@RequestBody BusinessCard record,@RequestParam("page")   int page, @RequestParam("size")  int size) {
		R r = new R();
		try {
			r = baseBusinessCardService.getAllRecordByPage(record, page, size);
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
	public R deleteByPrimaryKey(String id) {
		R r = new R();
		try {
			baseBusinessCardService.deleteByPrimaryKey(id);
			r.put("code", 200);
			r.put("msg", "删除成功");
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;
	}

	@ApiOperation(value = "保存个实体", notes = "取单个实体", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "record", value = "", required = true, dataType = "BaseBusinessCardEntity"), })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@PostMapping(value = "/Insert")
	public R insert(@RequestBody BusinessCard record) {
		R r = new R();
		try {
			baseBusinessCardService.insert(record);
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
			BusinessCard entity = baseBusinessCardService.selectByPrimaryKey(id);
			r.put("code", 200);
			r.put("data", entity);
			r.put("msg", "删除成功");
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;
	}

	@ApiOperation(value = "更新个实体", notes = "取单个实体", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "record", value = "", required = true, dataType = "BaseBusinessCardEntity"), })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@PostMapping(value = "/UpdateByPrimaryKey")
	public R updateByPrimaryKey(@RequestBody BusinessCard record) {
		R r = new R();
		try {
			baseBusinessCardService.updateByPrimaryKey(record);
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
			r=baseBusinessCardService.setDeleted(ids);
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;
	}

}
