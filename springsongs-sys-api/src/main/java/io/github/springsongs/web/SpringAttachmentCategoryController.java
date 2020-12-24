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
import io.github.springsongs.dto.SpringAttachmentCategoryDTO;
import io.github.springsongs.service.ISpringAttachmentCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "附件分类管理")
@RestController
@RequestMapping(value = "/SpringAttachmentCategory")
public class SpringAttachmentCategoryController {

	private static final Logger logger = LoggerFactory.getLogger(SpringAttachmentCategoryController.class);

	@Autowired
	private ISpringAttachmentCategoryService springAttachmentCategoryService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringAttachmentCategoryDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringAttachmentCategoryDTO>> listByPage(
			@RequestBody SpringAttachmentCategoryDTO searchQuery, @RequestParam("page") int page,
			@RequestParam("size") int size) {
		ReponseResultPageDTO<List<SpringAttachmentCategoryDTO>> reponseResultPageDTO = springAttachmentCategoryService
				.listByPage(searchQuery, page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "获取附件分类", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringAttachmentCategoryDTO> get(@NotEmpty(message = "id不能为空") String id) {
		ResponseDTO<SpringAttachmentCategoryDTO> responseDTO = springAttachmentCategoryService.get(id);
		return responseDTO;
	}

	@ApiOperation(value = "创建附件分类", notes = "根据SpringAttachmentCategoryDTO附件分类", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringAttachmentCategoryDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringAttachmentCategoryDTO viewEntity,
			HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springAttachmentCategoryService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改附件分类", notes = "根据SpringAttachmentCategoryDTO对象附件分类", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringArticleCommentDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringAttachmentCategoryDTO viewEntity,
			HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springAttachmentCategoryService.update(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "删除附件分类", notes = "根据List<String>对象删除附件分类", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "内容评论编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springAttachmentCategoryService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "删除附件分类", notes = "根据List<String>对象删除附件分类", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "内容评论编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springAttachmentCategoryService.setDeleted(ids);
		return responseDTO;
	}
}
