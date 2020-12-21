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
import io.github.springsongs.dto.SpringDictionaryDetailDTO;
import io.github.springsongs.service.ISpringDictionaryDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "字典明细管理")
@RestController
@RequestMapping(value = "/SpringDictionaryDetail")
public class SpringDictionaryDetailController{

	private static final Logger logger = LoggerFactory.getLogger(SpringDictionaryDetailController.class);

	@Autowired
	private ISpringDictionaryDetailService springDictionaryDetailService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringDictionaryDetailDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringDictionaryDetailDTO>> listByPage(@RequestBody SpringDictionaryDetailDTO searchQuery, int page,
			int size) {
		ReponseResultPageDTO<List<SpringDictionaryDetailDTO>> reponseResultPageDTO = springDictionaryDetailService.listByPage(searchQuery, page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "获取字典明细", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringDictionaryDetailDTO> get(@NotEmpty(message = "id不能为空") String id) {
		ResponseDTO<SpringDictionaryDetailDTO> responseDTO = springDictionaryDetailService.get(id);
		return responseDTO;
	}

	@ApiOperation(value = "创建字典明细", notes = "根据SpringDictionaryDetailDTO创建字典明细", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringDictionaryDetailDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringDictionaryDetailDTO viewEntity,
			HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springDictionaryDetailService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改字典明细", notes = "根据SpringDictionaryDetailDTO修改字典明细", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringDictionaryDetailDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringDictionaryDetailDTO viewEntity,
			HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springDictionaryDetailService.update(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "删除字典明细", notes = "根据List<String>对象删除字典明细", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "字典明细编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springDictionaryDetailService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "删除字典明细", notes = "根据List<String>对象删除字典明细", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "字典明细编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springDictionaryDetailService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "根据DictionaryCode查询字典明细", notes = "根据DictionaryCode查询字典明细", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "String", name = "dictionaryCode", value = "字典编码", required = true)
	@GetMapping(value = "/ListSpringDictionaryDetailByDictionaryCode")
	public ResponseDTO<List<SpringDictionaryDetailDTO>> listSpringDictionaryDetailByDictionaryCode(
			@RequestParam(value = "dictionaryCode", required = true) String dictionaryCode) {
		ResponseDTO<List<SpringDictionaryDetailDTO>> responseDTO= springDictionaryDetailService
				.listSpringDictionaryDetailByDictionaryCode(dictionaryCode);
		return responseDTO;
	}
}