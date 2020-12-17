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

import io.github.springsongs.dto.EasyUiMenuDTO;
import io.github.springsongs.dto.ElementUiTreeDTO;
import io.github.springsongs.dto.MenuDTO;
import io.github.springsongs.dto.MenuRouterDTO;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringResourceDTO;
import io.github.springsongs.service.hystrix.SpringResourceServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", path = "/SpringRole", fallback = SpringResourceServiceHystrix.class)
public interface ISpringResourceService {

	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringResourceDTO> listByPage(@RequestBody SpringResourceDTO springAritlceQuery,
			@RequestParam("page") int page, @RequestParam("size") int size);

	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringResourceDTO> get(@NotEmpty(message = "id不能为空") String id);

	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringResourceDTO viewEntity);

	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringResourceDTO viewEntity);

	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@GetMapping(value = "/GetMenus")
	public ResponseDTO<List<MenuDTO>> getMenus();

	@GetMapping(value = "/GetRouters")
	public ResponseDTO<List<MenuRouterDTO>> getRouters();

	@PostMapping(value = "/GetEasyUIMenu")
	public ResponseDTO<List<EasyUiMenuDTO>> getEasyUIMenu();

	@GetMapping(value = "/GetMenusByParent")
	public ResponseDTO<List<ElementUiTreeDTO>> getModuleByParentId(
			@RequestParam(value = "parentId", required = true) String parentId,
			@RequestParam(value = "systemId", required = true) String systemId);

	@GetMapping(value = "/ListAllToTree")
	public ResponseDTO<List<SpringResourceDTO>> ListAllToTree(
			@RequestParam(value = "systemId", required = true) String systemId);

}
