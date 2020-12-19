package io.github.springsongs.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringContactDTO;
import io.github.springsongs.dto.SpringFriendLinkDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hystrix.SpringFriendLinkServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE",path = "/SpringFriendLink",  fallback = SpringFriendLinkServiceHystrix.class, configuration = SecuringRequestInterceptor.class)
public interface ISpringFriendLinkService {

	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringFriendLinkDTO> listByPage(@RequestBody SpringFriendLinkDTO springAritlceQuery,@RequestParam("page") int page, @RequestParam("size") int size);
	
	@PostMapping(value = "/Detail")
	public ResponseDTO<SpringContactDTO> get(@RequestParam(value = "id", required = true) @NotEmpty(message = "id不能为空") String id);
	
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringFriendLinkDTO viewEntity);

	
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringFriendLinkDTO viewEntity) ;

	
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);
	
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids);
}
