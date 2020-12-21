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
import io.github.springsongs.dto.SpringDictionaryDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hystrix.SpringDictionaryServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE",fallback = SpringDictionaryServiceHystrix.class, configuration = SecuringRequestInterceptor.class)
public interface ISpringDictionaryService {

	@PostMapping(value = "/SpringDictionary/ListByPage")
	public ReponseResultPageDTO<List<SpringDictionaryDTO>> listByPage(@RequestBody SpringDictionaryDTO springAritlceQuery,
			@RequestParam("page") int page, @RequestParam("size") int size);

	@PostMapping(value = "/SpringDictionary/Detail")
	public ResponseDTO<SpringDictionaryDTO> get(
			@RequestParam(value = "id", required = true) @NotEmpty(message = "id不能为空") String id);

	@PostMapping(value = "/SpringDictionary/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringDictionaryDTO viewEntity);

	@PutMapping(value = "/SpringDictionary/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringDictionaryDTO viewEntity);

	@PostMapping(value = "/SpringDictionary/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@PostMapping(value = "/SpringDictionary/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids);
}