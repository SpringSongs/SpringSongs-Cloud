package io.github.springsongs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.SpringTaskDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.SpringTaskService;
import io.github.springsongs.util.AuthenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "工作流任务管理")
@RestController
@RequestMapping(value = "/SpringTask")
@Validated
public class SpringTaskController{

	@Autowired
	private SpringTaskService springTaskService;
	
	@Autowired
	private AuthenUtil authenUtil;

	@ApiOperation(value = "获取待办分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "title", dataType = "String"),
			@ApiImplicitParam(name = "category", dataType = "String"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/GetTodoTasks")
	public ReponseResultPageDTO<List<SpringTaskDTO>> getTodoTasks(String title, String category,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		PageInfo<SpringTaskDTO> lists = springTaskService.getTodoTasks(authenUtil.getUser().getId(), title, category, page,size);
		return ReponseResultPageDTO.successed(lists.getList(), lists.getTotal(), ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "获取我的申请分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "title", dataType = "String"),
			@ApiImplicitParam(name = "category", dataType = "String"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/GetTasksByStarter")
	public ReponseResultPageDTO<List<SpringTaskDTO>> getTasksByStarter(String title, String category,
			@RequestParam("page") int page, @RequestParam("size") int size) {
	PageInfo<SpringTaskDTO> lists = springTaskService.getTasksByStarter(authenUtil.getUser().getId(), title, category, page,size);
	return ReponseResultPageDTO.successed(lists.getList(), lists.getTotal(), ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "获取已办分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "title", dataType = "String"),
			@ApiImplicitParam(name = "category", dataType = "String"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/GetFinishTasks")
	public ReponseResultPageDTO<List<SpringTaskDTO>> getFinishTasks(String title, String category,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		PageInfo<SpringTaskDTO> lists = springTaskService.getFinishTasks(authenUtil.getUser().getId(), title, category, page,size);
		return ReponseResultPageDTO.successed(lists.getList(), lists.getTotal(), ResultCode.SELECT_SUCCESSED);
	}
}
