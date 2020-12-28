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

import io.github.springsongs.domain.SpringActModel;
import io.github.springsongs.dto.Model;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hytrix.SpringActModelServiceHystrix;
import io.github.springsongs.utils.Constant;

@FeignClient(name = "SPRINGSONGS-ACTIVITI-SERVICE",path="/SpringActModel",fallback = SpringActModelServiceHystrix.class,  configuration = SecuringRequestInterceptor.class)
public interface ISpringActModelService {
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<Model>> listByPage(@RequestBody SpringActModel springActModelQuery,
			@RequestParam("page") int page, @RequestParam("size") int size);

	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringActModel viewEntity);

	@PutMapping(value = "/Edit/{id}")
	public ResponseDTO<String> edit(@RequestBody @Valid SpringActModel viewEntity,
			@PathVariable(value = "id", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) String id);

	@PostMapping(value = "/SetDeleted/{id}")
	public ResponseDTO<String> delete(
			@PathVariable(value = "ids", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) List<String> ids);

	@PutMapping("/Deploy/{id}")
	public ResponseDTO<String> deploy(
			@PathVariable(value = "id", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) String id);

	@GetMapping(value = "Export/{modelId}")
	public void export(@PathVariable("modelId") String modelId);
}