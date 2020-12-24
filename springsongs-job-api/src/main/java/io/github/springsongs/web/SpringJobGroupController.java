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

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringJobGroupDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringJobGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "定时任务组管理")
@RestController
@RequestMapping(value = "/SpringJobGroup")
public class SpringJobGroupController {
	private static final Logger logger = LoggerFactory.getLogger(SpringJobGroupController.class);

	@Autowired
	private ISpringJobGroupService springJobGroupService;

	@ApiOperation(value = "获取定时任务组分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "springJobGroupQuery", dataType = "SpringJobGroupQuery"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringJobGroupDTO>> listByPage(@RequestBody SpringJobGroupDTO springJobGroupQuery,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		ReponseResultPageDTO<List<SpringJobGroupDTO>> reponseResultPageDTO =  springJobGroupService.listByPage(springJobGroupQuery, page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "获取单一定时任务组", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringJobGroupDTO> get(@NotEmpty(message = "id不能为空") String id) {
		ResponseDTO<SpringJobGroupDTO> responseDTO = springJobGroupService.get(id);
		return responseDTO;
	}

	@ApiOperation(value = "创建定时任务组", notes = "根据SpringJobDTO定时任务组", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringJobDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringJobGroupDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springJobGroupService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改定时任务组", notes = "根据SpringJobDTO对象修改定时任务组", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringJobGroupDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringJobGroupDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springJobGroupService.update(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "删除定时任务组", notes = "根据List<String>对象删除定时任务", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "工作流模型分类编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springJobGroupService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "删除定时任务组", notes = "根据List<String>对象删除定时任务", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "工作流模型分类编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springJobGroupService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "查询全部定时任务组", response = ResponseDTO.class)
	@GetMapping(value = "/ListAll")
	public ResponseDTO<List<SpringJobGroupDTO>> listAll() {
		ResponseDTO<List<SpringJobGroupDTO>> responseDTO = springJobGroupService.listAll();
		return responseDTO;
	}
}
