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
import io.github.springsongs.dto.SpringContactDTO;
import io.github.springsongs.dto.SpringFriendLinkDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringFriendLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "友情链接管理")
@RestController
@RequestMapping(value = "/SpringFriendLink")
public class SpringFriendLinkController {

	private static final Logger logger = LoggerFactory.getLogger(SpringFriendLinkController.class);
	@Autowired
	private ISpringFriendLinkService springFriendLinkService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringFriendLinkDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringFriendLinkDTO> listByPage(@RequestBody SpringFriendLinkDTO searchQuery, int page,
			int size) {
		ReponseResultPageDTO<SpringFriendLinkDTO> reponseResultPageDTO = springFriendLinkService.listByPage(searchQuery, page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "获取友情链接", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringContactDTO> get(@NotEmpty(message = "id不能为空") String id) {
		ResponseDTO<SpringContactDTO> responseDTO = springFriendLinkService.get(id);
		return responseDTO;
	}

	@ApiOperation(value = "创建友情链接", notes = "根据SpringFriendLinkDTO创建友情链接", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringFriendLinkDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringFriendLinkDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springFriendLinkService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改友情链接", notes = "根据SpringFriendLinkDTO修改友情链接", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringFriendLinkDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringFriendLinkDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springFriendLinkService.update(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "删除友情链接", notes = "根据List<String>对象删除友情链接", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "友情链接编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		springFriendLinkService.setDeleted(ids);
		return ResponseDTO.successed(null, ResultCode.DELETE_SUCCESSED);
	}

	@ApiOperation(value = "删除友情链接", notes = "根据List<String>对象删除友情链接", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "友情链接编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springFriendLinkService.setDeleted(ids);
		return responseDTO;
	}
}
