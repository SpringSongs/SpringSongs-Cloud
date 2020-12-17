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
import io.github.springsongs.dto.SpringContactDTO;
import io.github.springsongs.service.hystrix.SpringContactServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", fallback = SpringContactServiceHystrix.class)
public interface ISpringContactService{


	@PostMapping(value = "/SpringContact/ListByPage")
	public ReponseResultPageDTO<SpringContactDTO> listByPage(@RequestBody SpringContactDTO springAritlceQuery, @RequestParam("page") int page, @RequestParam("size") int size);

	
	@PostMapping(value = "/SpringContact/Detail")
	public ResponseDTO<SpringContactDTO> get(@RequestParam(value = "id", required = true) @NotEmpty(message = "id不能为空") String id);

	
	@PostMapping(value = "/SpringContact/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringContactDTO viewEntity);

	
	@PutMapping(value = "/SpringContact/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringContactDTO viewEntity);

	
	@PostMapping(value = "/SpringContact/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	
	@PostMapping(value = "/SpringContact/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids);
}
