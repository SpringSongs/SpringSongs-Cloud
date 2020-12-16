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

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringContactDTO;
import io.github.springsongs.dto.SpringDistrictDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringDistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "行政区域管理")
@RestController
@RequestMapping(value = "/SpringDistrict")
public class SpringDistrictController{

	private static final Logger logger = LoggerFactory.getLogger(SpringDistrictController.class);
	@Autowired
	private ISpringDistrictService SpringDistrictService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "springAritlceQuery", dataType = "SpringAritlceQuery"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringDistrictDTO> listByPage(@RequestBody SpringDistrictDTO springAritlceQuery, int page,
			int size) {
		PageInfo<SpringDistrictDTO> lists = SpringDistrictService.getAllRecordByPage(springAritlceQuery, page, size);
		return ReponseResultPageDTO.successed(lists.getList(), lists.getTotal(), ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "获取行政区域", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringContactDTO> get(@NotEmpty(message = "id不能为空") Long id) {
		SpringDistrictDTO entity = SpringDistrictService.selectByPrimaryKey(id);
		return ResponseDTO.successed(entity, ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "创建行政区域", notes = "根据SpringDistrictDTO创建行政区域", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringDistrictDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringDistrictDTO viewEntity, HttpServletRequest request) {
		SpringDistrictService.insert(viewEntity);
		return ResponseDTO.successed(null, ResultCode.SAVE_SUCCESSED);
	}

	@ApiOperation(value = "修改行政区域", notes = "根据SpringDistrictDTO修改行政区域", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringDistrictDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringDistrictDTO viewEntity, HttpServletRequest request) {
		SpringDistrictService.updateByPrimaryKey(viewEntity);
		return ResponseDTO.successed(null, ResultCode.UPDATE_SUCCESSED);
	}

	@ApiOperation(value = "删除行政区域", notes = "根据List<String>对象删除行政区域", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "行政区域编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<Long> ids) {
		SpringDistrictService.setDeleted(ids);
		return ResponseDTO.successed(null, ResultCode.DELETE_SUCCESSED);
	}

	@ApiOperation(value = "删除行政区域", notes = "根据List<String>对象删除行政区域", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "行政区域编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<Long> ids) {
		SpringDistrictService.setDeleted(ids);
		return ResponseDTO.successed(null, ResultCode.DELETE_SUCCESSED);
	}

	@ApiOperation(value = "根据上级节点查找", notes = "根据上级parentId节点查找", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "Long", name = "parentId", value = "行政区域编号", required = true)
	@GetMapping(value = "/ListSpringDistrictByParentId")
	public ResponseDTO<List<SpringDistrictDTO>> listSpringDistrictByParentId(
			@RequestParam(value = "parentId", required = true) Long parentId) {
		List<SpringDistrictDTO> springDistrictDTOList = SpringDistrictService.listSpringDistrictByParentId(parentId);
		return ResponseDTO.successed(springDistrictDTOList, ResultCode.SELECT_SUCCESSED);
	}
}
