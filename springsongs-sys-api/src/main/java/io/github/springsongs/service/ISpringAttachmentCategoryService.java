package io.github.springsongs.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringAttachmentCategoryDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hystrix.SpringAttachmentCategoryServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", fallback = SpringAttachmentCategoryServiceHystrix.class, configuration = SecuringRequestInterceptor.class)
public interface ISpringAttachmentCategoryService {

	@PostMapping(value = "/SpringAttachmentCategory/ListByPage")
	public ReponseResultPageDTO<List<SpringAttachmentCategoryDTO>> listByPage(
			@RequestBody SpringAttachmentCategoryDTO springAritlceQuery, @RequestParam("page") int page, @RequestParam("size") int size);

	@PostMapping(value = "/SpringAttachmentCategory/Detail")
	public ResponseDTO<SpringAttachmentCategoryDTO> get(@RequestParam(value = "id", required = true) @NotEmpty(message = "id不能为空") String id);

	@PostMapping(value = "/SpringAttachmentCategory/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringAttachmentCategoryDTO viewEntity);

	@PutMapping(value = "/SpringAttachmentCategory/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringAttachmentCategoryDTO viewEntity);

	@PostMapping(value = "/SpringAttachmentCategory/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@PostMapping(value = "/SpringAttachmentCategory/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids);
}
