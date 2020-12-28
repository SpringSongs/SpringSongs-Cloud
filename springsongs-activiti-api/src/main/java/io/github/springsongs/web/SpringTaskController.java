package io.github.springsongs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.SpringTaskDTO;
import io.github.springsongs.service.ISpringTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "工作流任务管理")
@RestController
@RequestMapping(value = "/SpringTask")
@Validated
public class SpringTaskController {

	@Autowired
	private ISpringTaskService springTaskService;

	@ApiOperation(value = "获取待办分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "title", dataType = "String"),
			@ApiImplicitParam(name = "category", dataType = "String"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@GetMapping(value = "/GetTodoTasks")
	public ReponseResultPageDTO<List<SpringTaskDTO>> getTodoTasks(@RequestParam String title,@RequestParam String category,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		ReponseResultPageDTO<List<SpringTaskDTO>> reponseResultPageDTO = springTaskService.getTodoTasks(title, category,
				page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "获取我的申请分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "title", dataType = "String"),
			@ApiImplicitParam(name = "category", dataType = "String"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@GetMapping(value = "/GetTasksByStarter")
	public ReponseResultPageDTO<List<SpringTaskDTO>> getTasksByStarter(@RequestParam String title,@RequestParam String category,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		ReponseResultPageDTO<List<SpringTaskDTO>> reponseResultPageDTO = springTaskService.getTasksByStarter(title,
				category, page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "获取已办分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "title", dataType = "String"),
			@ApiImplicitParam(name = "category", dataType = "String"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@GetMapping(value = "/GetFinishTasks")
	public ReponseResultPageDTO<List<SpringTaskDTO>> getFinishTasks(@RequestParam String title,@RequestParam String category,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		ReponseResultPageDTO<List<SpringTaskDTO>> reponseResultPageDTO = springTaskService.getFinishTasks(title,
				category, page, size);
		return reponseResultPageDTO;
	}
}
