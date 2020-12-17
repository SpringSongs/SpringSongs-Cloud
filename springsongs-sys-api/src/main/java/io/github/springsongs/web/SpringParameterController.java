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
import io.github.springsongs.dto.SpringParameterDTO;
import io.github.springsongs.service.ISpringParameterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "参数管理")
@RestController
@RequestMapping(value = "/SpringParameter")
public class SpringParameterController{

	private static final Logger logger = LoggerFactory.getLogger(SpringParameterController.class);

	@Autowired
	private ISpringParameterService springParameterService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringParameterDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringParameterDTO> listByPage(@RequestBody SpringParameterDTO searchQuery, int page,
			int size) {
		ReponseResultPageDTO<SpringParameterDTO> reponseResultPageDTO =springParameterService.listByPage(searchQuery, page, size);
		return reponseResultPageDTO;
	}
	@ApiOperation(value = "获取参数", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringParameterDTO> get(@NotEmpty(message = "id不能为空") String id) {
		ResponseDTO<SpringParameterDTO> responseDTO = springParameterService.get(id);
		return responseDTO;
	}

	@ApiOperation(value = "创建参数", notes = "根据SpringParameterDTO创建参数", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringAttachmentDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringParameterDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springParameterService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改参数", notes = "根据SpringParameterDTO修改参数", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringAttachmentDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringParameterDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springParameterService.update(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "删除参数", notes = "根据List<String>对象删除参数", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "参数编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springParameterService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "删除参数", notes = "根据List<String>对象删除参数", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "参数编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springParameterService.setDeleted(ids);
		return responseDTO;
	}
}
