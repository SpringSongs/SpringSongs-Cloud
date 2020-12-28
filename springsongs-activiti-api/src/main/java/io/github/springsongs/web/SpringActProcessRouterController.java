package io.github.springsongs.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringActProcessRouterDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringActProcessRouterService;
import io.github.springsongs.utils.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "工作流路由配置管理")
@RestController
@RequestMapping(value = "/SpringActProcessRouter")
@Validated
public class SpringActProcessRouterController {

	@Autowired
	private ISpringActProcessRouterService springActProcessRouterService;

	@ApiOperation(value = "创建工作流路由", notes = "根据SpringActProcessRouterDTO对象创建工作流路由")
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringActProcessRouterDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringActProcessRouterDTO viewEntity,
			HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springActProcessRouterService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改工作流路由", notes = "根据SpringActProcessRouterDTO对象修改工作流路由", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringActProcessRouterDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> edit(@RequestBody @Valid SpringActProcessRouterDTO viewEntity,
			HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springActProcessRouterService.edit(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "获取工作流路由", notes = "根据procDefKey获取工作流路由", response = ResponseDTO.class)
	@ApiImplicitParam(name = "procDefKey", dataType = "String")
	@GetMapping(value = "/FindSpringActProcessRouterByProcDefKey")
	public ResponseDTO<SpringActProcessRouterDTO> findSpringActProcessRouterByProcDefKey(
			@RequestParam(value = "procDefKey", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) String procDefKey) {
		ResponseDTO<SpringActProcessRouterDTO> responseDTO = springActProcessRouterService
				.findSpringActProcessRouterByProcDefKey(procDefKey);
		return responseDTO;
	}
}
