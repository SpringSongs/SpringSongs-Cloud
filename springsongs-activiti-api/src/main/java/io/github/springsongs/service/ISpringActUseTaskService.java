package io.github.springsongs.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringActUseTaskDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hytrix.SpringActUseTaskServiceHystrix;
@FeignClient(name = "SPRINGSONGS-ACTIVITI-SERVICE",path="/SpringActUseTask",fallback = SpringActUseTaskServiceHystrix.class,  configuration = SecuringRequestInterceptor.class)
public interface ISpringActUseTaskService {
	@PostMapping(value = "/ListUserTaskByProcDefKey")
	public ResponseDTO<List<SpringActUseTaskDTO>> listUserTaskByProcDefKey(
			@RequestParam(value = "procDefKey", required = true) String procDefKey);

	@PostMapping(value = "/InitSingleDefinition")
	public ResponseDTO<String> initSingleDefinition(
			@RequestParam(value = "processDefinitionId", required = true) String processDefinitionId,
			@RequestParam(value = "procDefKey", required = true) String procDefKey);

	@PostMapping(value = "/InitAllDefinition")
	public ResponseDTO<String> initAllDefinition();

	@PostMapping(value = "/SetUserToTask")
	public ResponseDTO<String> setUserToTask(@RequestParam(value = "procDefKey", required = true) String procDefKey);
}
