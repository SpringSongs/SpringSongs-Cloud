package io.github.springsongs.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import io.github.springsongs.dto.ElementUiTreeDTO;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringArticleCategoryDTO;
import io.github.springsongs.service.ISpringArticleCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "内容分类管理")
@RestController
@RequestMapping(value = "/SpringArticleCategory")
public class SpringArticleCategoryController{

	private static final Logger logger = LoggerFactory.getLogger(SpringArticleCategoryController.class);

	@Autowired
	private ISpringArticleCategoryService springArticleCategoryService;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringArticleCategoryDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringArticleCategoryDTO>> listByPage(@RequestBody SpringArticleCategoryDTO searchQuery, int page,
			int size) {
		ReponseResultPageDTO<List<SpringArticleCategoryDTO>>  reponseResultPageDTO = springArticleCategoryService.listByPage(searchQuery, page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "获取单一内容分类管理", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringArticleCategoryDTO> get(@RequestParam(value = "id", required = true) String id) {
		ResponseDTO<SpringArticleCategoryDTO> responseDTO =springArticleCategoryService.get(id);
		return responseDTO;
	}

	@ApiOperation(value = "创建内容分类", notes = "根据SpringArticleCategoryDTO内容分类", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringAritlceDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody SpringArticleCategoryDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO =springArticleCategoryService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改内容分类", notes = "根据SpringArticleCategoryDTO对象修改内容分类", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringAritlceDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody SpringArticleCategoryDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO =springArticleCategoryService.update(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "删除内容分类", notes = "根据List<String>对象删除内容分类", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "内容分类编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO =springArticleCategoryService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "根据上级获取内容分类", notes = "根据String上级获取内容分类", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "parentId", name = "ids", value = "内容分类编号", required = true)
	@GetMapping(value = "/GetCategorysByParent")
	public ResponseDTO<List<ElementUiTreeDTO>> getModuleByParentId(
			@RequestParam(value = "parentId", required = true) String parentId) {
		ResponseDTO<List<ElementUiTreeDTO>> responseDTO =springArticleCategoryService.getModuleByParentId(parentId);
		return responseDTO;
	}

	@ApiOperation(value = "获取全部内容分类", notes = "全部内容分类", response = ResponseDTO.class)
	@GetMapping(value = "/listAllRecord")
	public ResponseDTO<SpringArticleCategoryDTO> listAllRecord() {
		ResponseDTO<SpringArticleCategoryDTO> responseDTO = springArticleCategoryService.listAllRecord();
		return responseDTO;
	}

	@ApiOperation(value = "查询全部组织机构树", notes = "查询全部组织机构树", response = ResponseDTO.class)
	@GetMapping(value = "/ListAllToTree")
	public ResponseDTO<SpringArticleCategoryDTO> ListAllToTree() {
		ResponseDTO<SpringArticleCategoryDTO> responseDTO = springArticleCategoryService.ListAllToTree();
		return responseDTO;
	}
}
