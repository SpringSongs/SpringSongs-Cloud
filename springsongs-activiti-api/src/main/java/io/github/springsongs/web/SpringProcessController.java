package io.github.springsongs.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.springsongs.dto.Model;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.service.ISpringProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "流程管理")
@RestController
@RequestMapping(value = "/SpringProcess")
@Validated
public class SpringProcessController {

	@Autowired
	private ISpringProcessService springProcessService;

	@ApiOperation(value = "获取流程管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "category", dataType = "String"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@GetMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<Model>> listByPage(@RequestParam(required = false) String category,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		ReponseResultPageDTO<List<Model>> reponseResultPageDTO = springProcessService.listByPage(category, page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "激活与挂起流程", notes = "根据id激活与挂起流程", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "state", dataType = "String"),
			@ApiImplicitParam(name = "procDefId", dataType = "String"), })
	@PutMapping("/ActiveOrSuspend/{state}")
	public ResponseDTO<String> updateState(@PathVariable("state") String state, @RequestParam String procDefId) {
		ResponseDTO<String> responseDTO = springProcessService.updateState(state, procDefId);
		return responseDTO;
	}

	@ApiOperation(value = "查看流程文件", notes = "根据procDefId，proInsId查看流程文件")
	@ApiImplicitParams({ @ApiImplicitParam(name = "procDefId", dataType = "String"),
			@ApiImplicitParam(name = "proInsId", dataType = "String"),
			@ApiImplicitParam(name = "response", dataType = "HttpServletResponse") })
	@GetMapping(value = "/Resource")
	public void resourceRead(String procDefId, String proInsId, String resType, HttpServletResponse response) {
		springProcessService.resourceRead(procDefId, proInsId, resType);
	}

	@ApiOperation(value = "转模型", notes = "根据procDefId转模型", response = ResponseDTO.class)
	@ApiImplicitParam(name = "procDefId", dataType = "String")
	@PutMapping(value = "/Convert/{procDefId}")
	public ResponseDTO<String> convertToModel(@PathVariable("procDefId") String procDefId) {
		ResponseDTO<String> responseDTO = springProcessService.convertToModel(procDefId);
		return responseDTO;
	}
}
