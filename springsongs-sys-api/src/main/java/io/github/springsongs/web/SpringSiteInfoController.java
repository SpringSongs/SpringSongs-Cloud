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
import io.github.springsongs.dto.SpringSiteInfoDTO;
import io.github.springsongs.service.ISpringSiteInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "站点信息管理")
@RestController
@RequestMapping(value = "/SpringSiteInfo")
public class SpringSiteInfoController {

	private static final Logger logger = LoggerFactory.getLogger(SpringSiteInfoController.class);
	@Autowired
	private ISpringSiteInfoService SpringSiteInfoService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringSiteInfoDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringSiteInfoDTO>> listByPage(@RequestBody SpringSiteInfoDTO searchQuery,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		ReponseResultPageDTO<List<SpringSiteInfoDTO>> reponseResultPageDTO = SpringSiteInfoService
				.listByPage(searchQuery, page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "获取站点信息", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringSiteInfoDTO> get(@NotEmpty(message = "id不能为空") String id) {
		ResponseDTO<SpringSiteInfoDTO> responseDTO = SpringSiteInfoService.get(id);
		return responseDTO;
	}

	@ApiOperation(value = "创建站点信息", notes = "根据SpringSiteInfoDTO创建站点信息", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringSiteInfoDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringSiteInfoDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = SpringSiteInfoService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改站点信息", notes = "根据SpringSiteInfoDTO修改站点信息", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringSiteInfoDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringSiteInfoDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = SpringSiteInfoService.update(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "删除站点信息", notes = "根据List<String>对象删除站点信息", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "站点信息编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = SpringSiteInfoService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "删除站点信息", notes = "根据List<String>对象删除站点信息", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "站点信息编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = SpringSiteInfoService.setDeleted(ids);
		return responseDTO;
	}
}
