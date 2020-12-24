package io.github.springsongs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringJobHistoryDTO;
import io.github.springsongs.service.ISpringJobHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "定时任务运行历史管理")
@RestController
@RequestMapping(value = "/SpringJobHistory")
public class SpringJobHistoryController {

	@Autowired
	private ISpringJobHistoryService springJobHistoryService;

	@ApiOperation(value = "获取任务运行历史分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "springJobGroupQuery", dataType = "SpringJobGroupQuery"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringJobHistoryDTO>> listByPage(@RequestBody SpringJobHistoryDTO springJobHistory,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		ReponseResultPageDTO<List<SpringJobHistoryDTO>> reponseResultPageDTO =  springJobHistoryService.listByPage(springJobHistory, page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "删除任务运行历史", notes = "根据List<String>任务运行历史", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "工作流模型分类编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springJobHistoryService.setDeleted(ids);
		return responseDTO;
	}
}
