package io.github.springsongs.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.springsongs.domain.SpringUserSecurity;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringUserDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hystrix.SpringUserServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", path = "/SpringUser", fallback = SpringUserServiceHystrix.class, configuration = SecuringRequestInterceptor.class)
public interface ISpringUserService {

	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringUserDTO> listByPage(@RequestBody SpringUserDTO springAritlceQuery,@RequestParam("page") int page, @RequestParam("size") int size);

	@PostMapping(value = "/ListByRoleId/{roleId}")
	public ReponseResultPageDTO<SpringUserDTO> listByRoleId(
			@PathVariable(value = "roleId", required = true) String roleId,@RequestParam("page") int page, @RequestParam("size") int size);

	@PostMapping(value = "/Detail")
	public ResponseDTO<SpringUserDTO> get(@RequestParam(value = "id", required = true) @NotEmpty(message = "id不能为空") String id);

	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringUserDTO viewEntity);

	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringUserDTO viewEntity);

	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@PostMapping(value = "/SetPwd")
	public ResponseDTO<String> setPwd(@RequestBody SpringUserSecurity viewEntity);

	@PostMapping(value = "/SetRoles/{userId}")
	public ResponseDTO<String> setUsers(@PathVariable(value = "userId", required = true) String userId,
			@RequestParam(value = "ids", required = true) List<String> roleIds);
}
