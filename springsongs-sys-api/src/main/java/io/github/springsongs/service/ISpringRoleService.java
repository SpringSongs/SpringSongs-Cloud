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

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringRoleDTO;
import io.github.springsongs.service.hystrix.SpringRoleServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", path = "/SpringRole", fallback = SpringRoleServiceHystrix.class)
public interface ISpringRoleService {

	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringRoleDTO> listByPage(@RequestBody SpringRoleDTO springAritlceQuery, @RequestParam("page") int page, @RequestParam("size") int size);

	@PostMapping(value = "ListByUserId/{userId}")
	public ReponseResultPageDTO<SpringRoleDTO> listByUserId(
			@PathVariable(value = "userId", required = true) String userId,@RequestParam("page") int page, @RequestParam("size") int size);

	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringRoleDTO> get(@NotEmpty(message = "id不能为空") String id);

	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringRoleDTO viewEntity);

	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringRoleDTO viewEntity);

	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@PostMapping(value = "/SetUsers/{roleId}")
	public ResponseDTO<String> setUsers(@PathVariable(value = "roleId", required = true) String roleId,
			@RequestParam(value = "ids", required = true) List<String> userIds);

	@PostMapping(value = "/DeleteUsers/{roleId}")
	public ResponseDTO<String> deleteUsers(@PathVariable(value = "roleId", required = true) String roleId,
			@RequestParam(value = "ids", required = true) List<String> userIds);

	@PostMapping(value = "/SetAuthority/{roleId}")
	public ResponseDTO<String> setAuthority(@PathVariable(value = "roleId", required = true) String roleId,
			@RequestParam(value = "moduleIds", required = true) List<String> moduleIds);

	@PostMapping(value = "/SetAuthority")
	public ResponseDTO<String> setAuthorityRoleIdAndModuleId(
			@RequestParam(value = "roleId", required = true) String roleId,
			@RequestParam(value = "moduleId", required = true) String moduleId);

	@PostMapping(value = "/DeleteByRoleIdAndModuleId")
	public ResponseDTO<String> deleteByRoleIdAndModuleId(@RequestParam(value = "roleId", required = true) String roleId,
			@RequestParam(value = "moduleId", required = true) String moduleId);

	@PostMapping(value = "/ListAuthority/{roleId}")
	public ResponseDTO<List<String>> listAuthority(@PathVariable(value = "roleId", required = true) String roleId);
}
