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
import io.github.springsongs.dto.SpringParameterDTO;
import io.github.springsongs.service.hystrix.SpringParameterServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", path = "/SpringParameter", fallback =SpringParameterServiceHystrix.class)
public interface ISpringParameterService{

	
	
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringParameterDTO> listByPage(@RequestBody SpringParameterDTO springAritlceQuery, @RequestParam("page") int page, @RequestParam("size") int size);
	
	@PostMapping(value = "/Detail")
	public ResponseDTO<SpringParameterDTO> get(@RequestParam(value = "id", required = true) @NotEmpty(message = "id不能为空") String id);

	
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringParameterDTO viewEntity);

	
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringParameterDTO viewEntity);

	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids);
}
