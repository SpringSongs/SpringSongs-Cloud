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

import io.github.springsongs.dto.EasyUiMenuDTO;
import io.github.springsongs.dto.ElementUiTreeDTO;
import io.github.springsongs.dto.MenuDTO;
import io.github.springsongs.dto.MenuRouterDTO;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringResourceDTO;
import io.github.springsongs.service.ISpringResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "资源管理")
@RestController
@RequestMapping(value = "/SpringResource")
public class SpringResourceController{

	private static final Logger logger = LoggerFactory.getLogger(SpringResourceController.class);

	@Autowired
	private ISpringResourceService springResourceService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringResourceDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringResourceDTO>> listByPage(@RequestBody SpringResourceDTO searchQuery, int page,
			int size) {
		ReponseResultPageDTO<List<SpringResourceDTO>> reponseResultPageDTO = springResourceService.listByPage(searchQuery, page, size);
		return reponseResultPageDTO;
	}
	@ApiOperation(value = "获取资源", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringResourceDTO> get(@NotEmpty(message = "id不能为空") String id) {

		ResponseDTO<SpringResourceDTO> responseDTO = springResourceService.get(id);
		return responseDTO;
	}

	@ApiOperation(value = "创建资源", notes = "根据SpringResourceDTO创建资源", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringAttachmentDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringResourceDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springResourceService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改资源", notes = "根据SpringAttachmentDTO修改资源", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringResourceDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringResourceDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springResourceService.update(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "删除资源", notes = "根据List<String>对象删除资源", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "资源编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springResourceService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "获取资源菜单", notes = "获取资源菜单", response = ResponseDTO.class)
	@GetMapping(value = "/GetMenus")
	public ResponseDTO<List<MenuDTO>> getMenus() {
		ResponseDTO<List<MenuDTO>>  responseDTO= springResourceService.getMenus();
		return responseDTO;
	}

	@ApiOperation(value = "获取资源菜单路由", notes = "获取资源菜单路由", response = ResponseDTO.class)
	@GetMapping(value = "/GetRouters")
	public ResponseDTO<List<MenuRouterDTO>> getRouters() {
		ResponseDTO<List<MenuRouterDTO>>  responseDTO= springResourceService.getRouters();
		return responseDTO;
	}

	@ApiOperation(value = "获取EASYUI菜单", notes = "获取EASYUI菜单", response = ResponseDTO.class)
	@PostMapping(value = "/GetEasyUIMenu")
	public ResponseDTO<List<EasyUiMenuDTO>> getEasyUIMenu() {
		ResponseDTO<List<EasyUiMenuDTO>>  responseDTO= springResourceService.getEasyUIMenu();
		return responseDTO;
	}

	@ApiOperation(value = "根据上级查询资源", notes = "根据上级查询资源", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "parentId", dataType = "String"),
			@ApiImplicitParam(name = "systemId", dataType = "String"), })
	@GetMapping(value = "/GetMenusByParent")
	public ResponseDTO<List<ElementUiTreeDTO>> getModuleByParentId(
			@RequestParam(value = "parentId", required = true) String parentId,
			@RequestParam(value = "systemId", required = true) String systemId) {
		ResponseDTO<List<ElementUiTreeDTO>>  responseDTO= springResourceService.getModuleByParentId(parentId, systemId);
		return responseDTO;
	}

	@ApiOperation(value = "查询资源树", notes = "查询资源树", response = ResponseDTO.class)
	@GetMapping(value = "/ListAllToTree")
	public ResponseDTO<List<SpringResourceDTO>> ListAllToTree(
			@RequestParam(value = "systemId", required = true) String systemId) {
		ResponseDTO<List<SpringResourceDTO>>  responseDTO= springResourceService.ListAllToTree(systemId);
		return responseDTO;
	}

}
