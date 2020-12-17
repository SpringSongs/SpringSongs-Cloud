package io.github.springsongs.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringUserRole;
import io.github.springsongs.domain.SpringUserSecurity;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringUserDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringResourceService;
import io.github.springsongs.service.ISpringUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/SpringUser")
public class SpringUserController {

	private static final Logger logger = LoggerFactory.getLogger(SpringUserController.class);

	@Autowired
	private ISpringUserService springUserService;

	@Autowired
	private ISpringResourceService springResourceService;


	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringUserDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringUserDTO> listByPage(@RequestBody SpringUserDTO searchQuery, int page,
			int size) {
		PageInfo<SpringUserDTO> lists = springUserService.getAllRecordByPage(searchQuery, page, size);
		return ReponseResultPageDTO.successed(lists.getList(), lists.getTotal(), ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "根据角色查询用户分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleId", dataType = "String"),
		@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByRoleId/{roleId}")
	public ReponseResultPageDTO<SpringUserDTO> listByRoleId(
			@PathVariable(value = "roleId", required = true) String roleId,
			int page,
			int size) {
		PageInfo<SpringUserDTO> lists = springUserService.ListUsersByRoleId(roleId, page,size);
		return ReponseResultPageDTO.successed(lists.getList(), lists.getTotal(), ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "获取用户", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringUserDTO> get(@NotEmpty(message = "id不能为空") String id) {
		SpringUserDTO entity = springUserService.selectByPrimaryKey(id);
		return ResponseDTO.successed(entity, ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "创建用户", notes = "根据SpringUserDTO创建用户", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringUserDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringUserDTO viewEntity, HttpServletRequest request) {
		springUserService.insert(viewEntity);
		return ResponseDTO.successed(null, ResultCode.SAVE_SUCCESSED);
	}

	
	@ApiOperation(value = "修改用户", notes = "根据SpringUserDTO修改用户", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringUserDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringUserDTO viewEntity, HttpServletRequest request) {
		springUserService.updateByPrimaryKey(viewEntity);
		return ResponseDTO.successed(null, ResultCode.UPDATE_SUCCESSED);
	}

	@ApiOperation(value = "删除用户", notes = "根据List<String>对象删除用户", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "用户编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		springUserService.setDeleted(ids);
		return ResponseDTO.successed(null, ResultCode.DELETE_SUCCESSED);
	}

	@ApiOperation(value = "删除用户", notes = "根据List<String>对象删除用户", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "用户编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids) {

		springUserService.setDeleted(ids);
		return ResponseDTO.successed(null, ResultCode.DELETE_SUCCESSED);
	}

	@ApiOperation(value = "设置用户密码", notes = "根据SpringUserSecurity设置用户密码", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringUserSecurity"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/SetPwd")
	public ResponseDTO<String> setPwd(@RequestBody SpringUserSecurity viewEntity, HttpServletRequest request) {

		if (StringUtils.isEmpty(viewEntity.getUserId())) {
			return ResponseDTO.successed(null, ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (StringUtils.isEmpty(viewEntity.getPwd())) {
			return ResponseDTO.successed(null, ResultCode.PASSWORD_CAN_NOT_EMPTY);
		} else {
			springUserService.setPwd(viewEntity);
			return ResponseDTO.successed(null, ResultCode.SAVE_SUCCESSED);
		}
	}

	@ApiOperation(value = "分配用户角色", notes = "分配用户角色", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", dataType = "String"),
			@ApiImplicitParam(name = "roleIds", dataType = "List<String>"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest") })
	@PostMapping(value = "/SetRoles/{userId}")
	public ResponseDTO<String> setUsers(@PathVariable(value = "userId", required = true) String userId,
			@RequestParam(value = "ids", required = true) List<String> roleIds, HttpServletRequest request) {
		List<SpringUserRole> baseUserRoleEntityList = new ArrayList<SpringUserRole>();
		for (String str : roleIds) {
			SpringUserRole entity = new SpringUserRole();
			entity.setRoleId(str);
			entity.setUserId(userId);
			baseUserRoleEntityList.add(entity);
		}
		springUserService.saveUserToRole(baseUserRoleEntityList, userId);
		return ResponseDTO.successed(null, ResultCode.SAVE_SUCCESSED);
	}
}
