package io.github.springsongs.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringJobHistoryDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hystrix.SpringJobHistoryServiceHystrix;

@FeignClient(name = "SPRINGSONGS-JOB-SERVICE",path="/SpringJobHistory",fallback = SpringJobHistoryServiceHystrix.class,  configuration = SecuringRequestInterceptor.class)
public interface ISpringJobHistoryService {
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringJobHistoryDTO>> listByPage(@RequestBody SpringJobHistoryDTO springJobHistory,
			@RequestParam("page") int page, @RequestParam("size") int size);

	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);
}
