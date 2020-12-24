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
import io.github.springsongs.service.ISpringContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "名片管理")
@RestController
@RequestMapping(value = "/SpringContact")
public class SpringContactController {

	private static final Logger logger = LoggerFactory.getLogger(SpringContactController.class);

	@Autowired
	private ISpringContactService springContactService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringContactDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringContactDTO>> listByPage(@RequestBody SpringContactDTO searchQuery,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		ReponseResultPageDTO<List<SpringContactDTO>> reponseResultPageDTO = springContactService.listByPage(searchQuery,
				page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "获取名片", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringContactDTO> get(@NotEmpty(message = "id不能为空") String id) {
		ResponseDTO<SpringContactDTO> responseDTO = springContactService.get(id);
		return responseDTO;
	}

	@ApiOperation(value = "创建名片", notes = "根据SpringContactDTO名片", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringContactDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringContactDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springContactService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改名片", notes = "根据SpringAttachmentDTO修改名片", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringContactDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringContactDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springContactService.update(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "删除名片", notes = "根据List<String>对象删除名片", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "名片编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springContactService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "删除名片", notes = "根据List<String>对象删除名片", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "名片编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springContactService.setDeleted(ids);
		return responseDTO;
	}
}
