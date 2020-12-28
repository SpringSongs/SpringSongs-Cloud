package io.github.springsongs.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import io.github.springsongs.dto.SpringActCategoryDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringActCategoryService;
import io.github.springsongs.utils.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "工作流模型分类管理")
@RestController
@RequestMapping(value = "/SpringActCategory")
@Validated
public class SpringActCategoryController{
	private static final Logger logger = LoggerFactory.getLogger(SpringActCategoryController.class);

	@Autowired
	private ISpringActCategoryService springActCategoryService;

	@ApiOperation(value = "获取工作流模型分类分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "springActCategoryQuery", dataType = "SpringActCategoryDTO"),
		@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringActCategoryDTO>> listByPage(
			@RequestBody SpringActCategoryDTO springActCategoryQuery,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		PageInfo<SpringActCategoryDTO> lists = springActCategoryService.getAllRecordByPage(springActCategoryQuery,
				page,size);
		return ReponseResultPageDTO.successed(lists.getList(), lists.getTotal(), ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "获取单一工作流模型分类", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@PostMapping(value = "/Detail")
	public ResponseDTO<SpringActCategoryDTO> get(@NotEmpty(message = "id不能为空") String id) {
		SpringActCategoryDTO entity = springActCategoryService.selectByPrimaryKey(id);
		return ResponseDTO.successed(entity, ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "创建工作流模型分类", notes = "根据SpringActCategoryDTO对象创建工作流模型分类", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringActCategoryDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringActCategoryDTO viewEntity, HttpServletRequest request) {
		springActCategoryService.insert(viewEntity);
		return ResponseDTO.successed(null, ResultCode.EXPORT_SUCCESSED);
	}

	@ApiOperation(value = "修改工作流模型分类", notes = "根据SpringActCategoryDTO对象修改工作流模型分类", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringActCategoryDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringActCategoryDTO viewEntity, HttpServletRequest request) {
		springActCategoryService.updateByPrimaryKey(viewEntity);
		return ResponseDTO.successed(null, ResultCode.UPDATE_SUCCESSED);
	}

	@ApiOperation(value = "删除工作流模型分类", notes = "根据List<String>对象删除工作流模型分类", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "工作流模型分类编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(
			@RequestParam(value = "ids", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) List<String> ids) {
		springActCategoryService.setDeleted(ids);
		return ResponseDTO.successed(null, ResultCode.DELETE_SUCCESSED);
	}

	@ApiOperation(value = "删除工作流模型分类", notes = "根据List<String>对象删除工作流模型分类", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "工作流模型分类编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(
			@RequestParam(value = "ids", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) List<String> ids) {
		springActCategoryService.setDeleted(ids);
		return ResponseDTO.successed(null, ResultCode.DELETE_SUCCESSED);
	}

	@ApiOperation(value = "查询全部工作流模型分类", response = ResponseDTO.class)
	@GetMapping(value = "/ListAll")
	public ResponseDTO<List<SpringActCategoryDTO>> listAll() {
		List<SpringActCategoryDTO> SpringActCategoryDTOs = springActCategoryService.listAll();
		return ResponseDTO.successed(SpringActCategoryDTOs, ResultCode.SELECT_SUCCESSED);
	}
}
