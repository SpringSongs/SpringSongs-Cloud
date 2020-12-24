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
import io.github.springsongs.dto.SpringJobDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hystrix.SpringJobServiceHystrix;

@FeignClient(name = "SPRINGSONGS-JOB-SERVICE",path="/SpringJob",fallback = SpringJobServiceHystrix.class,  configuration = SecuringRequestInterceptor.class)
public interface ISpringJobService {
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringJobDTO>> listByPage(@RequestBody SpringJobDTO springJobQuery,
			@RequestParam("page") int page, @RequestParam("size") int size);

	@PostMapping(value = "/Detail")
	public ResponseDTO<SpringJobDTO> get(@RequestParam(value = "id", required = true) @NotEmpty(message = "id不能为空") String id);

	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringJobDTO viewEntity);

	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringJobDTO viewEntity);

	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids);

	@PostMapping(value = "/AddTask")
	public ResponseDTO<String> addTask(@RequestBody @Valid SpringJobDTO viewEntity);

	@PutMapping(value = "/UpdateTask")
	public ResponseDTO<String> updateTask(@RequestBody @Valid SpringJobDTO viewEntity);

	@PutMapping(value = "/PauseTask")
	public ResponseDTO<String> pauseTask(@RequestParam(value = "taskClassName", required = true) String taskClassName,
			@RequestParam(value = "groupCode", required = true) String groupCode);

	@PutMapping(value = "/ResumeTask")
	public ResponseDTO<String> resumeTask(@RequestParam(value = "taskClassName", required = true) String taskClassName,
			@RequestParam(value = "groupCode", required = true) String groupCode);

	@PostMapping(value = "/DeleteTask")
	public ResponseDTO<String> deleteTask(@RequestParam(value = "taskClassName", required = true) String taskClassName,
			@RequestParam(value = "groupCode", required = true) String groupCode);
}
