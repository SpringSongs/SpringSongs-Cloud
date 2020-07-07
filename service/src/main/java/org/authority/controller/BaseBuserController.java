package org.authority.controller;

import java.util.List;

import org.authority.domain.Member;
import org.authority.domain.MemberSecurity;
import org.authority.domain.MemberRole;
import org.authority.service.IBaseBuserService;
import org.authority.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api("BaseBuserController相关的api")
@RequestMapping(value = "/BaseBuser")
public class BaseBuserController extends BaseController {

	@Autowired
	private IBaseBuserService baseBuserService;

	@ApiOperation(value = "分页查询", notes = "分页查询", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "record", value = "", required = true, dataType = "BaseAritlceEntity"),
			@ApiImplicitParam(name = "page", value = "", required = true, dataType = "int"),
			@ApiImplicitParam(name = "size", value = "", required = true, dataType = "int") })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@PostMapping(value = "/GetAllRecordByPage")
	public R getAllRecordByPage(@RequestBody Member record, @RequestParam("page") int page,
			@RequestParam("size") int size) {
		R r = new R();
		try {
			r = baseBuserService.getAllRecordByPage(record, page, size);
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
	public R deleteByPrimaryKey(@RequestParam(value = "id", required = true) String id) {
		R r = new R();
		try {
			baseBuserService.deleteByPrimaryKey(id);
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
			r = baseBuserService.setDeleted(ids);
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;
	}

	@ApiOperation(value = "保存个实体", notes = "取单个实体", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "record", value = "", required = true, dataType = "BaseUserEntity"), })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@PostMapping(value = "/Insert")
	public R insert(@RequestBody Member record) {
		R r = new R();
		try {
			baseBuserService.insert(record);
			r.put("code", 200);
			r.put("msg", "删除成功");
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
			Member entity = baseBuserService.selectByPrimaryKey(id);
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
			@ApiImplicitParam(name = "record", value = "", required = true, dataType = "BaseUserEntity"), })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@PostMapping(value = "/UpdateByPrimaryKey")
	public R updateByPrimaryKey(@RequestBody Member record) {
		R r = new R();
		try {
			baseBuserService.updateByPrimaryKey(record);
			r.put("code", 200);
			r.put("msg", "删除成功");
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;

	}

	@ApiOperation(value = "查询用户", notes = "取单个实体", response = String.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "", required = true, dataType = "String"), })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@PostMapping(value = "/GetByUserName")
	R getByUserName(@RequestParam("username") String username) {
		R r = new R();
		try {
			r= baseBuserService.getByUserName(username);
			
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;
	}

	@ApiOperation(value = "查询用户密码", notes = "取单个实体", response = String.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "", required = true, dataType = "String"), })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@PostMapping(value = "/FindByUserId")
	R findPwdByUserId(@RequestParam("userId") String userId) {
		R r = new R();
		try {
			MemberSecurity entity = baseBuserService.findPwdByUserId(userId);
			r.put("code", 200);
			r.put("data", entity);
			r.put("msg", "删除成功");
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;
	}

	@ApiOperation(value = "分配角色", notes = "分配角色", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "baseUserRoleEntityList", value = "", required = true, dataType = "List<BaseUserRoleEntity>"),
			@ApiImplicitParam(name = "userId", value = "", required = true, dataType = "String") })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@RequestMapping(value = "/SaveUserToRole", method = RequestMethod.POST)
	R saveUserToRole(@RequestBody List<MemberRole> baseUserRoleEntityList,
			@RequestParam("userId") String userId) {
		R r = new R();
		try {
			baseBuserService.saveUserToRole(baseUserRoleEntityList, userId);
			r.put("code", 200);
			r.put("msg", "保存成功");
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;
	}

	@ApiOperation(value = "查询角色", notes = "查询角色", response = String.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleId", value = "", required = true, dataType = "String"),
			@ApiImplicitParam(name = "page", value = "", required = true, dataType = "int"),
			@ApiImplicitParam(name = "limit", value = "", required = true, dataType = "int") })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"), @ApiResponse(code = 500, message = "服务器不能完成请求") })
	@RequestMapping(value = "/ListUsersByRoleId", method = RequestMethod.POST)
	R listUsersByRoleId(@RequestParam("roleId") String roleId, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		R r = new R();
		try {
			r = baseBuserService.listUsersByRoleId(roleId, page, limit);
		} catch (Exception ex) {
			r.put("code", 500);
			r.put("msg", "系统错误");
		}
		return r;
	}
}
