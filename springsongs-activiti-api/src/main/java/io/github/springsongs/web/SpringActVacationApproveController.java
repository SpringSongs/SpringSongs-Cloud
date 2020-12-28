package io.github.springsongs.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringActVacationApproveDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringActVacationApproveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "请假流程审批管理")
@RestController
@RequestMapping(value = "/SpringActVacationApprove")
public class SpringActVacationApproveController {
	@Autowired
	private ISpringActVacationApproveService springActVacationApproveService;

	@ApiOperation(value = "完成请假流程审批", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringActVacationApproveDTO"),
			@ApiImplicitParam(name = "taskId", dataType = "String"), })
	@PostMapping(value = "/CompleteSpringActVacationApprove")
	public ResponseDTO<String> completeSpringActVacationApprove(
			@RequestBody @Valid SpringActVacationApproveDTO viewEntity, @RequestParam(value = "taskId") String taskId,
			HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springActVacationApproveService.completeSpringActVacationApprove(viewEntity,
				taskId);
		return responseDTO;
	}

	@ApiOperation(value = "查询请假流程", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "vacationId", dataType = "String"), })
	@GetMapping(value = "/FindByVacationId")
	public ResponseDTO<List<SpringActVacationApproveDTO>> findByVacationId(
			@RequestParam(value = "vacationId") String vacationId) {
		ResponseDTO<List<SpringActVacationApproveDTO>> responseDTO = springActVacationApproveService
				.findByVacationId(vacationId);
		return responseDTO;
	}
}
