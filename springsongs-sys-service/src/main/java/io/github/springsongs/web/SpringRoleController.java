package io.github.springsongs.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringResourceRole;
import io.github.springsongs.domain.SpringUserRole;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringRoleDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringResourceService;
import io.github.springsongs.service.ISpringRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "角色管理")
@RestController
@RequestMapping(value = "/SpringRole")
public class SpringRoleController{

	private static final Logger logger = LoggerFactory.getLogger(SpringRoleController.class);

	@Autowired
	private ISpringRoleService springRoleService;

	@Autowired
	private ISpringResourceService springResourceService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringRoleDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringRoleDTO> listByPage(@RequestBody SpringRoleDTO searchQuery, int page,
			int size) {
		PageInfo<SpringRoleDTO> lists = springRoleService.getAllRecordByPage(searchQuery, page, size);
		return ReponseResultPageDTO.successed(lists.getList(), lists.getTotal(), ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "根据用户查询角色分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", dataType = "String"),
		@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int")  })
	@PostMapping(value = "ListByUserId/{userId}")
	public ReponseResultPageDTO<SpringRoleDTO> listByUserId(
			@PathVariable(value = "userId", required = true) String userId,
			int page,
			int size) {
		PageInfo<SpringRoleDTO> lists = springRoleService.ListRoleByUserId(userId, page,size);
		return ReponseResultPageDTO.successed(lists.getList(), lists.getTotal(), ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "获取角色", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@PostMapping(value = "/Detail")
	public ResponseDTO<SpringRoleDTO> get(@NotEmpty(message = "id不能为空") String id) {
		SpringRoleDTO entity = springRoleService.selectByPrimaryKey(id);
		return ResponseDTO.successed(entity, ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "创建角色", notes = "根据SpringRoleDTO附件创建角色", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringRoleDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringRoleDTO viewEntity, HttpServletRequest request) {
		springRoleService.insert(viewEntity);
		return ResponseDTO.successed(null, ResultCode.SAVE_SUCCESSED);
	}

	@ApiOperation(value = "修改角色", notes = "根据SpringRoleDTO修改附件", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringRoleDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringRoleDTO viewEntity, HttpServletRequest request) {
		springRoleService.updateByPrimaryKey(viewEntity);
		return ResponseDTO.successed(null, ResultCode.UPDATE_SUCCESSED);
	}

	@ApiOperation(value = "删除角色", notes = "根据List<String>对象删除角色", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "角色编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		springRoleService.setDeleted(ids);
		return ResponseDTO.successed(null, ResultCode.DELETE_SUCCESSED);
	}

	@ApiOperation(value = "删除角色", notes = "根据List<String>对象删除角色", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "角色编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		springRoleService.setDeleted(ids);
		return ResponseDTO.successed(null, ResultCode.DELETE_SUCCESSED);
	}

	@ApiOperation(value = "分配角色用户", notes = "分配用户角色", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleId", dataType = "String"),
			@ApiImplicitParam(name = "userIds", dataType = "List<String>"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest") })
	@PostMapping(value = "/SetUsers/{roleId}")
	public ResponseDTO<String> setUsers(@PathVariable(value = "roleId", required = true) String roleId,
			@RequestParam(value = "ids", required = true) List<String> userIds, HttpServletRequest request) {
		List<SpringUserRole> baseUserRoleEntityList = new ArrayList<SpringUserRole>();
		for (String str : userIds) {
			SpringUserRole entity = new SpringUserRole();
			entity.setRoleId(roleId);
			entity.setUserId(str);
			baseUserRoleEntityList.add(entity);
		}
		springRoleService.saveUserToRole(baseUserRoleEntityList, roleId);
		return ResponseDTO.successed(null, ResultCode.SAVE_SUCCESSED);
	}

	@ApiOperation(value = "删除角色用户", notes = "删除用户角色", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleId", dataType = "String"),
			@ApiImplicitParam(name = "userIds", dataType = "List<String>"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest") })
	@PostMapping(value = "/DeleteUsers/{roleId}")
	public ResponseDTO<String> deleteUsers(@PathVariable(value = "roleId", required = true) String roleId,
			@RequestParam(value = "ids", required = true) List<String> userIds, HttpServletRequest request) {
		springRoleService.deleteUserFromRole(userIds, roleId);
		return ResponseDTO.successed(null, ResultCode.SAVE_SUCCESSED);
	}

	@ApiOperation(value = "分配资源角色权限", notes = "分配资源角色权限", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleId", dataType = "String"),
			@ApiImplicitParam(name = "moduleIds", dataType = "List<String>"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest") })
	@PostMapping(value = "/SetAuthority/{roleId}")
	public ResponseDTO<String> setAuthority(@PathVariable(value = "roleId", required = true) String roleId,
			@RequestParam(value = "moduleIds", required = true) List<String> moduleIds, HttpServletRequest request) {
		List<SpringResourceRole> baseModuleRoleEntityList = new ArrayList<SpringResourceRole>();
		for (String str : moduleIds) {
			SpringResourceRole entity = new SpringResourceRole();
			entity.setRoleId(roleId);
			entity.setModuleId(str);
			baseModuleRoleEntityList.add(entity);
		}
		springResourceService.saveModuleToRole(baseModuleRoleEntityList, roleId);
		return ResponseDTO.successed(null, ResultCode.SAVE_SUCCESSED);
	}

	@ApiOperation(value = "分配资源角色权限", notes = "分配资源角色权限", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleId", dataType = "String"),
			@ApiImplicitParam(name = "moduleId", dataType = "String"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest") })
	@PostMapping(value = "/SetAuthority")
	public ResponseDTO<String> setAuthorityRoleIdAndModuleId(
			@RequestParam(value = "roleId", required = true) String roleId,
			@RequestParam(value = "moduleId", required = true) String moduleId, HttpServletRequest request) {
		springResourceService.saveModuleToRole(moduleId, roleId);
		return ResponseDTO.successed(null, ResultCode.SAVE_SUCCESSED);
	}

	@ApiOperation(value = "删除资源角色权限", notes = "分配资源角色", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleId", dataType = "String"),
			@ApiImplicitParam(name = "moduleId", dataType = "String"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest") })
	@PostMapping(value = "/DeleteByRoleIdAndModuleId")
	public ResponseDTO<String> deleteByRoleIdAndModuleId(@RequestParam(value = "roleId", required = true) String roleId,
			@RequestParam(value = "moduleId", required = true) String moduleId, HttpServletRequest request) {
		springResourceService.deleteByRoleIdAndModuleId(moduleId, roleId);
		return ResponseDTO.successed(null, ResultCode.DELETE_SUCCESSED);
	}

	@ApiOperation(value = "根据角色查资源", notes = "根据roleId对象查资源", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "String", name = "roleId", value = "角色编号", required = true)
	@PostMapping(value = "/ListAuthority/{roleId}")
	public ResponseDTO<List<String>> listAuthority(@PathVariable(value = "roleId", required = true) String roleId) {

		List<SpringResourceRole> baseModuleRoleEntityList = springResourceService.listModulesByRoleId(roleId);
		List<String> moduleIds = new ArrayList<String>();
		baseModuleRoleEntityList.stream().forEach(item -> {
			moduleIds.add(item.getModuleId());
		});
		return ResponseDTO.successed(moduleIds, ResultCode.SELECT_SUCCESSED);
	}
}
