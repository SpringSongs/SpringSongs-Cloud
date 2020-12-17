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
import io.github.springsongs.dto.SpringDictionaryDTO;
import io.github.springsongs.service.ISpringDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "字典管理")
@RestController
@RequestMapping(value = "/SpringDictionary")
public class SpringDictionaryController{

	private static final Logger logger = LoggerFactory.getLogger(SpringDictionaryController.class);
	@Autowired
	private ISpringDictionaryService springDictionaryService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringDictionaryDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringDictionaryDTO> listByPage(@RequestBody SpringDictionaryDTO searchQuery, int page,
			int size) {
		ReponseResultPageDTO<SpringDictionaryDTO> reponseResultPageDTO = springDictionaryService.listByPage(searchQuery, page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "获取字典", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringDictionaryDTO> get(@NotEmpty(message = "id不能为空") String id) {
		ResponseDTO<SpringDictionaryDTO> responseDTO = springDictionaryService.get(id);
		return responseDTO;
	}

	@ApiOperation(value = "创建字典", notes = "根据SpringDictionaryDTO创建字典", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringDictionaryDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringDictionaryDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springDictionaryService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改字典", notes = "根据SpringDictionaryDTO修改字典", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringDictionaryDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringDictionaryDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springDictionaryService.update(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "删除字典", notes = "根据List<String>对象删除字典", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "字典编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springDictionaryService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "删除字典", notes = "根据List<String>对象删除字典", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "字典编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springDictionaryService.setDeleted(ids);
		return responseDTO;
	}
}