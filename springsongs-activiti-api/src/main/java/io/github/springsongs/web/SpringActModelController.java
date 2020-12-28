package io.github.springsongs.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.springsongs.domain.SpringActModel;
import io.github.springsongs.dto.Model;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.service.ISpringActModelService;
import io.github.springsongs.utils.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "工作流模型管理")
@RestController
@RequestMapping(value = "/SpringActModel")
@Validated
public class SpringActModelController {

	private static final Logger logger = LoggerFactory.getLogger(SpringActModelController.class);


	@Autowired
	private ISpringActModelService springActModelService;

	@ApiOperation(value = "获取工作流模型分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "springActModelQuery", dataType = "SpringActModel"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<Model>> listByPage(@RequestBody SpringActModel springActModelQuery,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		ReponseResultPageDTO<List<Model>> reponseResultPageDTO = springActModelService.listByPage(springActModelQuery,
				page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "创建工作流模型", notes = "根据SpringActModel对象创建工作流模型", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringActModel"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringActModel viewEntity) {
		ResponseDTO<String> responseDTO = springActModelService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改工作流模型", notes = "根据SpringActModel对象修改工作流模型", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringActModel"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit/{id}")
	public ResponseDTO<String> edit(@RequestBody @Valid SpringActModel viewEntity,
			@PathVariable(value = "id", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) String id) {
		ResponseDTO<String> responseDTO = springActModelService.edit(viewEntity, id);
		return responseDTO;
	}

	@ApiOperation(value = "删除工作流模型", notes = "根据List<String>对象删除工作流模型", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "String", name = "id", value = "工作流模型编号", required = true)
	@PostMapping(value = "/SetDeleted/{id}")
	public ResponseDTO<String> delete(
			@PathVariable(value = "ids", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) List<String> ids) {
		ResponseDTO<String> responseDTO = springActModelService.delete(ids);
		return responseDTO;
	}

	@ApiOperation(value = "发布工作流模型", notes = "根据id对象发布工作流模型", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "String", name = "id", value = "工作流模型编号", required = true)
	@PutMapping("/Deploy/{id}")
	public ResponseDTO<String> deploy(
			@PathVariable(value = "id", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) String id) {
		ResponseDTO<String> responseDTO = springActModelService.deploy(id);
		return responseDTO;
	}

	@ApiOperation(value = "导出工作流模型XML", notes = "根据modelId对象导出工作流模型XML")
	@ApiImplicitParams({ @ApiImplicitParam(name = "modelId", dataType = "String"),
			@ApiImplicitParam(name = "response", dataType = "HttpServletResponse"), })
	@GetMapping(value = "Export/{modelId}")
	public void export(@PathVariable("modelId") String modelId, HttpServletResponse response) {
		springActModelService.export(modelId);
	}
}
