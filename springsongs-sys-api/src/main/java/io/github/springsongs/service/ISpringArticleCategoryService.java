package io.github.springsongs.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.springsongs.dto.ElementUiTreeDTO;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringArticleCategoryDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hystrix.SpringArticleCategoryServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", fallback = SpringArticleCategoryServiceHystrix.class, configuration = SecuringRequestInterceptor.class)
public interface ISpringArticleCategoryService {
	@PostMapping(value = "/SpringArticleCategory/ListByPage")
	public ReponseResultPageDTO<SpringArticleCategoryDTO> listByPage(
			@RequestBody SpringArticleCategoryDTO springAritlceQuery,@RequestParam("page") int page, @RequestParam("size") int size);

	@PostMapping(value = "/SpringArticleCategory/Detail")
	public ResponseDTO<String> get(@RequestParam(value = "id", required = true) String id);

	@PostMapping(value = "/SpringArticleCategory/Create")
	public ResponseDTO<String> save(@RequestBody SpringArticleCategoryDTO viewEntity);

	@PutMapping(value = "/SpringArticleCategory/Edit")
	public ResponseDTO<String> update(@RequestBody SpringArticleCategoryDTO viewEntity);

	@PostMapping(value = "/SpringArticleCategory/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@GetMapping(value = "/SpringArticleCategory/GetCategorysByParent")
	public ResponseDTO<ElementUiTreeDTO> getModuleByParentId(
			@RequestParam(value = "parentId", required = true) String parentId);

	@GetMapping(value = "/SpringArticleCategory/listAllRecord")
	public ResponseDTO<SpringArticleCategoryDTO> listAllRecord();

	@GetMapping(value = "/SpringArticleCategory/ListAllToTree")
	public ResponseDTO<SpringArticleCategoryDTO> ListAllToTree();
}
