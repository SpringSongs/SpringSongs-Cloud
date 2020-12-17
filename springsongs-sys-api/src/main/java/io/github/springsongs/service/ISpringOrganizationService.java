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
import io.github.springsongs.dto.SpringOrganizationDTO;
import io.github.springsongs.service.hystrix.SpringOrganizationServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", path = "/SpringOrganization", fallback = SpringOrganizationServiceHystrix.class)
public interface ISpringOrganizationService {

	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringOrganizationDTO> listByPage(@RequestBody SpringOrganizationDTO springAritlceQuery,
			@RequestParam("page") int page, @RequestParam("size") int size);

	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringOrganizationDTO> get(@NotEmpty(message = "id不能为空") String id);

	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringOrganizationDTO viewEntity);

	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringOrganizationDTO viewEntity);

	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@GetMapping(value = "/listOrganizationsByParent")
	public ResponseDTO<List<SpringOrganizationDTO>> getOrganizationsByParent(
			@RequestParam(value = "parentId", required = true) @Valid @NotEmpty(message = "id不能为空") String parentId);

	@GetMapping(value = "/listAllRecord")
	public ResponseDTO<List<SpringOrganizationDTO>> listAllRecord();

	@GetMapping(value = "/ListAllToTree")
	public ResponseDTO<List<SpringOrganizationDTO>> ListAllToTree();
}
