package io.github.springsongs.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringAritlceDTO;
import io.github.springsongs.service.ISpringAritlceService;
import io.github.springsongs.utils.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "内容管理")
@RestController
@RequestMapping(value = "/SpringArticle")
public class SpringAritlceController {

	private static final Logger logger = LoggerFactory.getLogger(SpringAritlceController.class);

	@Autowired
	private ISpringAritlceService springAritlceService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringAritlceDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringAritlceDTO>> listByPage(@RequestBody SpringAritlceDTO searchQuery, int page,
			int size) {
		ReponseResultPageDTO<List<SpringAritlceDTO>> reponseResultPageDTO = springAritlceService
				.listByPage(searchQuery, page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "获取单一内容管理", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail/{id}")
	public ResponseDTO<SpringAritlceDTO> get(
			@PathVariable(value = "id", required = true) @Valid @NotEmpty(message = "id不能为空") String id) {
		ResponseDTO<SpringAritlceDTO> responseDTO = springAritlceService.get(id);
		return responseDTO;
	}

	@ApiOperation(value = "创建内容管理", notes = "根据SpringAritlceDTO内容管理", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringAritlceDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringAritlceDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springAritlceService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改内容管理", notes = "根据SpringJobDTO对象修改内容管理", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringAritlceDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringAritlceDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springAritlceService.update(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "删除内容管理", notes = "根据List<String>对象删除内容管理", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "内容管理编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids") List<String> ids) {
		ResponseDTO<String> responseDTO = springAritlceService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "删除内容管理", notes = "根据List<String>对象删除内容管理", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "内容管理编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids") List<String> ids) {
		ResponseDTO<String> responseDTO = springAritlceService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "审核内容管理", notes = "根据String对象审核内容管理", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "String", name = "id", value = "内容管理编号", required = true)
	@PutMapping(value = "/Audit/{id}")
	public ResponseDTO<String> audit(
			@PathVariable(value = "id", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) String id) {
		ResponseDTO<String> responseDTO = springAritlceService.audit(id);
		return responseDTO;
	}

	@ApiOperation(value = "热门内容管理", notes = "根据String对象热门内容管理", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "String", name = "id", value = "内容管理编号", required = true)
	@PutMapping(value = "/HotStatus/{id}")
	public ResponseDTO<String> hotStatus(
			@PathVariable(value = "id", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) String id) {
		ResponseDTO<String> responseDTO = springAritlceService.hotStatus(id);
		return responseDTO;
	}

	@ApiOperation(value = "顶置内容管理", notes = "根据String对象顶置内容管理", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "String", name = "id", value = "内容管理编号", required = true)
	@PutMapping(value = "/TopStatus/{id}")
	public ResponseDTO<String> topStatus(
			@PathVariable(value = "id", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) String id) {
		ResponseDTO<String> responseDTO = springAritlceService.topStatus(id);
		return responseDTO;
	}

	@ApiOperation(value = "精华内容管理", notes = "根据String对象精华内容管理", response = ResponseDTO.class)
	@PutMapping(value = "/Featured/{id}")
	public ResponseDTO<String> featured(
			@PathVariable(value = "id", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) String id) {
		ResponseDTO<String> responseDTO = springAritlceService.featured(id);
		return responseDTO;
	}
}
