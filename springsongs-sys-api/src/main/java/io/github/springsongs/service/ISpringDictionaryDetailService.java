package io.github.springsongs.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringDictionaryDetailDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hystrix.SpringDictionaryDetailServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", fallback = SpringDictionaryDetailServiceHystrix.class, configuration = SecuringRequestInterceptor.class)
public interface ISpringDictionaryDetailService {

	@PostMapping(value = "/SpringDictionaryDetail/ListByPage")
	public ReponseResultPageDTO<SpringDictionaryDetailDTO> listByPage(
			@RequestBody SpringDictionaryDetailDTO springAritlceQuery,@RequestParam("page") int page, @RequestParam("size") int size);

	@PostMapping(value = "/SpringDictionaryDetail/Detail")
	public ResponseDTO<SpringDictionaryDetailDTO> get(@RequestParam(value = "id", required = true) @NotEmpty(message = "id不能为空") String id);

	@PostMapping(value = "/SpringDictionaryDetail/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringDictionaryDetailDTO viewEntity);

	@PutMapping(value = "/SpringDictionaryDetail/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringDictionaryDetailDTO viewEntity);

	@PostMapping(value = "/SpringDictionaryDetail/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@PostMapping(value = "/SpringDictionaryDetail/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@GetMapping(value = "/SpringDictionaryDetail/ListSpringDictionaryDetailByDictionaryCode")
	public ResponseDTO<List<SpringDictionaryDetailDTO>> listSpringDictionaryDetailByDictionaryCode(
			@RequestParam(value = "dictionaryCode", required = true) String dictionaryCode);
}