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
import io.github.springsongs.dto.SpringSiteInfoDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hystrix.SpringSiteInfoServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", fallback = SpringSiteInfoServiceHystrix.class, configuration = SecuringRequestInterceptor.class)
public interface ISpringSiteInfoService {

	@PostMapping(value = "/SpringSiteInfo/ListByPage")
	public ReponseResultPageDTO<List<SpringSiteInfoDTO>> listByPage(@RequestBody SpringSiteInfoDTO springAritlceQuery,
			@RequestParam("page") int page, @RequestParam("size") int size);

	@PostMapping(value = "/SpringSiteInfo/Detail")
	public ResponseDTO<SpringSiteInfoDTO> get(@RequestParam("id") @NotEmpty(message = "id不能为空") String id);

	@PostMapping(value = "/SpringSiteInfo/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringSiteInfoDTO viewEntity);

	@PutMapping(value = "/SpringSiteInfo/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringSiteInfoDTO viewEntity);

	@PostMapping(value = "/SpringSiteInfo/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@PostMapping(value = "/SpringSiteInfo/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids);
}
