package io.github.springsongs.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.SpringTaskDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hytrix.SpringTaskServiceHystrix;

@FeignClient(name = "SPRINGSONGS-ACTIVITI-SERVICE",path="/SpringTask",fallback = SpringTaskServiceHystrix.class,  configuration = SecuringRequestInterceptor.class)
public interface ISpringTaskService {
	@PostMapping(value = "/GetTodoTasks")
	public ReponseResultPageDTO<List<SpringTaskDTO>> getTodoTasks(@RequestParam("title") String title,
			@RequestParam("category") String category, @RequestParam("page") int page, @RequestParam("size") int size);

	@PostMapping(value = "/GetTasksByStarter")
	public ReponseResultPageDTO<List<SpringTaskDTO>> getTasksByStarter(@RequestParam("title") String title,
			@RequestParam("category") String category, @RequestParam("page") int page, @RequestParam("size") int size);

	@PostMapping(value = "/GetFinishTasks")
	public ReponseResultPageDTO<List<SpringTaskDTO>> getFinishTasks(@RequestParam("title") String title,
			@RequestParam("category") String category, @RequestParam("page") int page, @RequestParam("size") int size);
}
