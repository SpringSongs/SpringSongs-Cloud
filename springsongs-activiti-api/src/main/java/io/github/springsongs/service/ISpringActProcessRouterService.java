package io.github.springsongs.service;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringActProcessRouterDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hytrix.SpringActProcessRouterServiceHystrix;
import io.github.springsongs.utils.Constant;

@FeignClient(name = "SPRINGSONGS-ACTIVITI-SERVICE",path="/SpringActProcessRouter",fallback = SpringActProcessRouterServiceHystrix.class,  configuration = SecuringRequestInterceptor.class)
public interface ISpringActProcessRouterService {
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringActProcessRouterDTO viewEntity);

	@PutMapping(value = "/Edit")
	public ResponseDTO<String> edit(@RequestBody @Valid SpringActProcessRouterDTO viewEntity);

	@PostMapping(value = "/FindSpringActProcessRouterByProcDefKey")
	public ResponseDTO<SpringActProcessRouterDTO> findSpringActProcessRouterByProcDefKey(
			@RequestParam(value = "procDefKey", required = true) @Valid @NotEmpty(message = Constant.PARAMETER_NOT_NULL_ERROR) String procDefKey);
}
