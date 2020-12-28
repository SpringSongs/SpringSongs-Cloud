package io.github.springsongs.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.springsongs.dto.Model;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hytrix.SpringProcessServiceHystrix;

@FeignClient(name = "SPRINGSONGS-ACTIVITI-SERVICE",path="/SpringProcess",fallback = SpringProcessServiceHystrix.class,  configuration = SecuringRequestInterceptor.class)
public interface ISpringProcessService {
	@GetMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<Model>> listByPage(@RequestParam(required = false) String category,
			@RequestParam("page") int page, @RequestParam("size") int size);

	@PutMapping("/ActiveOrSuspend/{state}")
	public ResponseDTO<String> updateState(@PathVariable("state") String state, @RequestParam String procDefId);

	@PostMapping(value = "/Resource")
	public void resourceRead(@RequestParam String procDefId,@RequestParam  String proInsId,@RequestParam  String resType);

	@PutMapping(value = "/Convert/{procDefId}")
	public ResponseDTO<String> convertToModel(@PathVariable("procDefId") String procDefId);
}
