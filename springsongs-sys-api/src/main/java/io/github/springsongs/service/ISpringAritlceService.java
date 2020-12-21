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
import io.github.springsongs.dto.SpringAritlceDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hystrix.SpringAritlceServiceHystrix;
import io.github.springsongs.utils.Constant;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", fallback = SpringAritlceServiceHystrix.class, configuration = SecuringRequestInterceptor.class)
public interface ISpringAritlceService {

	@PostMapping(value = "/SpringArticle/ListByPage")
	public ReponseResultPageDTO<List<SpringAritlceDTO>> listByPage(@RequestBody SpringAritlceDTO springAritlceQuery,
			@RequestParam("page") int page, @RequestParam("size") int size);

	@GetMapping(value = "/SpringArticle/Detail/{id}")
	public ResponseDTO<SpringAritlceDTO> get(
			@PathVariable(value = "id", required = true) @Valid @NotEmpty(message = "id不能为空") String id);

	@PostMapping(value = "/SpringArticle/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringAritlceDTO viewEntity);

	@PutMapping(value = "/SpringArticle/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringAritlceDTO viewEntity);

	@PostMapping(value = "/SpringArticle/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids") List<String> ids);

	@PostMapping(value = "/SpringArticle/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids") List<String> ids);

	@PutMapping(value = "/SpringArticle/Audit/{id}")
	public ResponseDTO<String> audit(
			@PathVariable(value = "id", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) String id);

	@PutMapping(value = "/SpringArticle/HotStatus/{id}")
	public ResponseDTO<String> hotStatus(
			@PathVariable(value = "id", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) String id);

	@PutMapping(value = "/SpringArticle/TopStatus/{id}")
	public ResponseDTO<String> topStatus(
			@PathVariable(value = "id", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) String id);

	@PutMapping(value = "/SpringArticle/Featured/{id}")
	public ResponseDTO<String> featured(
			@PathVariable(value = "id", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) String id);
}
