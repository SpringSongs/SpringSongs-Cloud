package io.github.springsongs.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.EasyUiMenuDTO;
import io.github.springsongs.dto.ElementUiTreeDTO;
import io.github.springsongs.dto.MenuDTO;
import io.github.springsongs.dto.MenuRouterDTO;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringResourceDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringResourceService;
import io.github.springsongs.util.AuthenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "资源管理")
@RestController
@RequestMapping(value = "/SpringResource")
public class SpringResourceController {

	private static final Logger logger = LoggerFactory.getLogger(SpringResourceController.class);
	@Autowired
	private AuthenUtil authenUtil;
	@Autowired
	private ISpringResourceService springResourceService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringResourceDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringResourceDTO> listByPage(@RequestBody SpringResourceDTO searchQuery, int page,
			int size) {
		PageInfo<SpringResourceDTO> lists = springResourceService.getAllRecordByPage(searchQuery, page, size);
		return ReponseResultPageDTO.successed(lists.getList(), lists.getTotal(), ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "获取资源", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@PostMapping(value = "/Detail")
	public ResponseDTO<SpringResourceDTO> get(@NotEmpty(message = "id不能为空") String id) {
		SpringResourceDTO entity = springResourceService.selectByPrimaryKey(id);
		return ResponseDTO.successed(entity, ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "创建资源", notes = "根据SpringResourceDTO创建资源", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringAttachmentDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringResourceDTO viewEntity, HttpServletRequest request) {
		springResourceService.insert(viewEntity);
		return ResponseDTO.successed(null, ResultCode.SAVE_SUCCESSED);
	}

	@ApiOperation(value = "修改资源", notes = "根据SpringAttachmentDTO修改资源", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringResourceDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringResourceDTO viewEntity, HttpServletRequest request) {
		springResourceService.updateByPrimaryKey(viewEntity);
		return ResponseDTO.successed(null, ResultCode.UPDATE_SUCCESSED);
	}

	@ApiOperation(value = "删除资源", notes = "根据List<String>对象删除资源", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "资源编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		springResourceService.setDeleted(ids);
		return ResponseDTO.successed(null, ResultCode.DELETE_SUCCESSED);
	}

	@ApiOperation(value = "获取资源菜单", notes = "获取资源菜单", response = ResponseDTO.class)
	@GetMapping(value = "/GetMenus")
	public ResponseDTO<List<MenuDTO>> getMenus() {
		// TODO
		List<MenuDTO> menuList = springResourceService.ListModuleByUserId("");
		return ResponseDTO.successed(menuList, ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "获取资源菜单路由", notes = "获取资源菜单路由", response = ResponseDTO.class)
	@GetMapping(value = "/GetRouters")
	public ResponseDTO<List<MenuRouterDTO>> getRouters() {
		List<MenuRouterDTO> menuRouterDTOs = springResourceService.listResourceByUserId(authenUtil.getUser().getId());
		return ResponseDTO.successed(menuRouterDTOs, ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "获取EASYUI菜单", notes = "获取EASYUI菜单", response = ResponseDTO.class)
	@PostMapping(value = "/GetEasyUIMenu")
	public ResponseDTO<List<EasyUiMenuDTO>> getEasyUIMenu() {
		// TODO
		List<EasyUiMenuDTO> easyUiMenuDTO = springResourceService.listEasyUiResourceByUserId("");
		return ResponseDTO.successed(easyUiMenuDTO, ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "根据上级查询资源", notes = "根据上级查询资源", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "parentId", dataType = "String"),
			@ApiImplicitParam(name = "systemId", dataType = "String"), })
	@GetMapping(value = "/GetMenusByParent")
	public ResponseDTO<List<ElementUiTreeDTO>> getModuleByParentId(
			@RequestParam(value = "parentId", required = true) String parentId,
			@RequestParam(value = "systemId", required = true) String systemId) {
		List<ElementUiTreeDTO> elementUiTreeDtoList = springResourceService.getModulesByParentId(parentId, systemId);
		return ResponseDTO.successed(elementUiTreeDtoList, ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "查询资源树", notes = "查询资源树", response = ResponseDTO.class)
	@PostMapping(value = "/ListAllToTree")
	public ResponseDTO<List<SpringResourceDTO>> ListAllToTree(
			@RequestParam(value = "systemId", required = true) String systemId) {
		List<SpringResourceDTO> entitys = springResourceService.ListAllToTree(systemId);
		return ResponseDTO.successed(entitys, ResultCode.SELECT_SUCCESSED);
	}

}
