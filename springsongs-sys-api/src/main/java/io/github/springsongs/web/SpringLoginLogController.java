package io.github.springsongs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.SpringLoginLogDTO;
import io.github.springsongs.service.ISpringLoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "登录日志管理")
@RestController
@RequestMapping(value = "/SpringLoginLog")
public class SpringLoginLogController{

	private static final Logger logger = LoggerFactory.getLogger(SpringLoginLogController.class);

	@Autowired
	private ISpringLoginLogService springLoginLogService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringLoginLogDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringLoginLogDTO> listByPage(@RequestBody SpringLoginLogDTO searchQuery, int page,
			int size) {
		ReponseResultPageDTO<SpringLoginLogDTO> reponseResultPageDTO =springLoginLogService.listByPage(searchQuery, page, size);
		return reponseResultPageDTO;
	}

}
