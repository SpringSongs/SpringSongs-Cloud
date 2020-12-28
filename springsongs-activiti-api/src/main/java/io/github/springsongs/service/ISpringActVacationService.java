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
import io.github.springsongs.dto.SpringActVacationDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hytrix.SpringActVacationServiceHystrix;

@FeignClient(name = "SPRINGSONGS-ACTIVITI-SERVICE",path="/SpringActVacation",fallback = SpringActVacationServiceHystrix.class,  configuration = SecuringRequestInterceptor.class)
public interface ISpringActVacationService {

	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringActVacationDTO>> listByPage(@RequestBody SpringActVacationDTO springJobQuery,
			@RequestParam("page") int page, @RequestParam("size") int size);

	@PostMapping(value = "/Detail")
	public ResponseDTO<SpringActVacationDTO> get( @RequestParam("id") @NotEmpty(message = "id不能为空") String id);

	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringActVacationDTO viewEntity);

	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringActVacationDTO viewEntity);

	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids") List<String> ids);

	@PostMapping(value = "/SubmitSpringActVacation")
	public ResponseDTO<String> submitSpringActVacation(@RequestBody @Valid SpringActVacationDTO viewEntity);
}
