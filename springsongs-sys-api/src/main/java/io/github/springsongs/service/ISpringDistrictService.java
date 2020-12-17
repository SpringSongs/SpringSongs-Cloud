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
import io.github.springsongs.dto.SpringDistrictDTO;
import io.github.springsongs.service.hystrix.SpringDistrictServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", fallback = SpringDistrictServiceHystrix.class)
public interface ISpringDistrictService {

	@PostMapping(value = "/SpringDistrict/ListByPage")
	public ReponseResultPageDTO<SpringDistrictDTO> listByPage(@RequestBody SpringDistrictDTO springAritlceQuery,
			@RequestParam("page") int page, @RequestParam("size") int size);

	@GetMapping(value = "/SpringDistrict/Detail")
	public ResponseDTO<SpringContactDTO> get(@NotEmpty(message = "id不能为空") Long id);

	@PostMapping(value = "/SpringDistrict/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringDistrictDTO viewEntity);

	@PutMapping(value = "/SpringDistrict/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringDistrictDTO viewEntity);

	@PostMapping(value = "/SpringDistrict/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<Long> ids);

	@PostMapping(value = "/SpringDistrict/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<Long> ids);

	@GetMapping(value = "/SpringDistrict/ListSpringDistrictByParentId")
	public ResponseDTO<List<SpringDistrictDTO>> listSpringDistrictByParentId(
			@RequestParam(value = "parentId", required = true) Long parentId);
}
