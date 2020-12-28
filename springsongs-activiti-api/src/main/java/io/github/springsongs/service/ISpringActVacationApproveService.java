package io.github.springsongs.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringActVacationApproveDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hytrix.SpringActVacationApproveServiceHystrix;

@FeignClient(name = "SPRINGSONGS-ACTIVITI-SERVICE",path="/SpringActVacationApprove",fallback = SpringActVacationApproveServiceHystrix.class,  configuration = SecuringRequestInterceptor.class)
public interface ISpringActVacationApproveService {
	@PostMapping(value = "/CompleteSpringActVacationApprove")
	public ResponseDTO<String> completeSpringActVacationApprove(
			@RequestBody @Valid SpringActVacationApproveDTO viewEntity, @RequestParam(value = "taskId") String taskId);

	@PostMapping(value = "/FindByVacationId")
	public ResponseDTO<List<SpringActVacationApproveDTO>> findByVacationId(
			@RequestParam(value = "vacationId") String vacationId);
}
