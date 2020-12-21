package io.github.springsongs.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.SpringLoginLogDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hystrix.SpringLoginLogServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE",path = "/SpringLoginLog",  fallback = SpringLoginLogServiceHystrix.class, configuration = SecuringRequestInterceptor.class)
public interface ISpringLoginLogService{

	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringLoginLogDTO>> listByPage(@RequestBody SpringLoginLogDTO springAritlceQuery, @RequestParam("page") int page, @RequestParam("size") int size);
}
