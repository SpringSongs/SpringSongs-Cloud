package io.github.springsongs.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringAttachmentDTO;
import io.github.springsongs.interceptor.SecuringRequestInterceptor;
import io.github.springsongs.service.hystrix.SpringAttachmentServiceHystrix;

@FeignClient(name = "SPRINGSONGS-SYS-SERVICE", fallback = SpringAttachmentServiceHystrix.class, configuration = SecuringRequestInterceptor.class)
public interface ISpringAttachmentService {
	
	
	@PostMapping(value = "/SpringAttachment/ListByPage")
	public ReponseResultPageDTO<SpringAttachmentDTO> listByPage(@RequestBody SpringAttachmentDTO springAritlceQuery, @RequestParam("page") int page, @RequestParam("size") int size);

	
	@PostMapping(value = "/SpringAttachment/Detail")
	public ResponseDTO<SpringAttachmentDTO> get(@RequestParam(value = "id", required = true) @NotEmpty(message = "id不能为空") String id);

	
	@PostMapping(value = "/SpringAttachment/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringAttachmentDTO viewEntity);
	
	@PutMapping(value = "/SpringAttachment/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringAttachmentDTO viewEntity);
	
	@PostMapping(value = "/SpringAttachment/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids);

	
	@PostMapping(value = "/SpringAttachment/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids);

	
	@PostMapping(value = "/SpringAttachment/Upload")
	public ResponseDTO<String> upload(MultipartFile file);
}
