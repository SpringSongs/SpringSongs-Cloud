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

import io.github.springsongs.domain.SpringOrganization;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringOrganizationDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringOrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "组织机构管理")
@RestController
@RequestMapping(value = "/SpringOrganization")
public class SpringOrganizationController{

	private static final Logger logger = LoggerFactory.getLogger(SpringOrganizationController.class);

	@Autowired
	private ISpringOrganizationService springOrganizationService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringOrganizationDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringOrganizationDTO> listByPage(@RequestBody SpringOrganizationDTO searchQuery, int page,
			int size) {
		PageInfo<SpringOrganizationDTO> lists = springOrganizationService.getAllRecordByPage(searchQuery, page, size);
		return ReponseResultPageDTO.successed(lists.getList(), lists.getTotal(), ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "获取组织机构", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringOrganizationDTO> get(@NotEmpty(message = "id不能为空") String id) {
		SpringOrganizationDTO entity = springOrganizationService.selectByPrimaryKey(id);
		return ResponseDTO.successed(entity, ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "创建组织机构", notes = "根据SpringOrganizationDTO创建组织机构", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringOrganizationDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringOrganizationDTO viewEntity, HttpServletRequest request) {
		springOrganizationService.insert(viewEntity);
		return ResponseDTO.successed(null, ResultCode.SAVE_SUCCESSED);
	}

	@ApiOperation(value = "修改组织机构", notes = "根据SpringOrganizationDTO修改组织机构", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringOrganizationDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringOrganizationDTO viewEntity,
			HttpServletRequest request) {
		springOrganizationService.updateByPrimaryKey(viewEntity);
		return ResponseDTO.successed(null, ResultCode.UPDATE_SUCCESSED);
	}

	@ApiOperation(value = "删除组织机构", notes = "根据List<String>对象删除组织机构", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "组织机构编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		springOrganizationService.setDeleted(ids);
		return ResponseDTO.successed(null, ResultCode.DELETE_SUCCESSED);
	}

	@ApiOperation(value = "根据上级查询组织机构", notes = "根据上级查询组织机构", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "String", name = "parentId", value = "组织机构编号", required = true)
	@GetMapping(value = "/listOrganizationsByParent")
	public ResponseDTO<List<SpringOrganizationDTO>> getOrganizationsByParent(
			@RequestParam(value = "parentId", required = true) @Valid @NotEmpty(message = "id不能为空") String parentId) {
		List<SpringOrganizationDTO> elementUiTreeDtoList = springOrganizationService
				.listOrganizationsByParent(parentId);
		return ResponseDTO.successed(elementUiTreeDtoList, ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "查询全部组织机构", notes = "查询全部组织机构", response = ResponseDTO.class)
	@GetMapping(value = "/listAllRecord")
	public ResponseDTO<List<SpringOrganizationDTO>> listAllRecord() {
		List<SpringOrganizationDTO> entitys = springOrganizationService.listAll();
		return ResponseDTO.successed(entitys, ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "查询全部组织机构树", notes = "查询全部组织机构树", response = ResponseDTO.class)
	@GetMapping(value = "/ListAllToTree")
	public ResponseDTO<List<SpringOrganizationDTO>> ListAllToTree() {
		List<SpringOrganizationDTO> entitys = springOrganizationService.ListAllToTree();
		return ResponseDTO.successed(entitys, ResultCode.SELECT_SUCCESSED);
	}
}
