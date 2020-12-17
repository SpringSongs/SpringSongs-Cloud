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
import io.github.springsongs.dto.SpringSiteNoticeDTO;
import io.github.springsongs.service.hystrix.SpringSiteNoticeServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", path = "/SpringSiteNotice", fallback = SpringSiteNoticeServiceHystrix.class)
public interface ISpringSiteNoticeService{

	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<SpringSiteNoticeDTO> listByPage(@RequestBody SpringSiteNoticeDTO springAritlceQuery,@RequestParam("page") int page, @RequestParam("size") int size);

	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringSiteNoticeDTO> get(@NotEmpty(message = "id不能为空") String id);

	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringSiteNoticeDTO viewEntity);

	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringSiteNoticeDTO viewEntity);

	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids);
}
