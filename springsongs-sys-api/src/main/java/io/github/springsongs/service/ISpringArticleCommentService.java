package io.github.springsongs.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringArticleCommentDTO;
import io.github.springsongs.service.hystrix.SpringArticleCommentServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", fallback = SpringArticleCommentServiceHystrix.class)
public interface ISpringArticleCommentService {

	@PostMapping(value = "/SpringArticleComment/ListByPage")
	public ReponseResultPageDTO<SpringArticleCommentDTO> listByPage(
			@RequestBody SpringArticleCommentDTO springAritlceQuery, @RequestParam("page") int page, @RequestParam("size") int size);

	@GetMapping(value = "/SpringArticleComment/Detail")
	public ResponseDTO<String> get(@NotEmpty(message = "id不能为空") String id);

	@PostMapping(value = "/SpringArticleComment/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringArticleCommentDTO viewEntity);

	@PutMapping(value = "/SpringArticleComment/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringArticleCommentDTO viewEntity);

	@PostMapping(value = "/SpringArticleComment/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@PostMapping(value = "/SpringArticleComment/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@PutMapping(value = "/SpringArticleComment/Audit/{id}")
	public ResponseDTO<String> audit(@PathVariable(value = "id", required = true) String id);
}
