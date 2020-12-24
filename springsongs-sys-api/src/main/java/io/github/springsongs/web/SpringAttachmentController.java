package io.github.springsongs.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringAttachmentDTO;
import io.github.springsongs.service.ISpringAttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "附件管理")
@RestController
@RequestMapping(value = "/SpringAttachment")
public class SpringAttachmentController {

	private static final Logger logger = LoggerFactory.getLogger(SpringAttachmentController.class);

	@Autowired
	private ISpringAttachmentService springAttachmentService;

	@Value("${web.upload.path}")
	private String uploadPath;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringAttachmentDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringAttachmentDTO>> listByPage(@RequestBody SpringAttachmentDTO searchQuery,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		ReponseResultPageDTO<List<SpringAttachmentDTO>> reponseResultPageDTO = springAttachmentService
				.listByPage(searchQuery, page, size);
		return reponseResultPageDTO;
	}

	@ApiOperation(value = "获取附件", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@GetMapping(value = "/Detail")
	public ResponseDTO<SpringAttachmentDTO> get(@NotEmpty(message = "id不能为空") String id) {
		ResponseDTO<SpringAttachmentDTO> responseDTO = springAttachmentService.get(id);
		return responseDTO;
	}

	@ApiOperation(value = "创建附件", notes = "根据SpringAttachmentDTO附件", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringAttachmentDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringAttachmentDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springAttachmentService.save(viewEntity);
		return responseDTO;
	}

	@ApiOperation(value = "修改附件", notes = "根据SpringAttachmentDTO修改附件", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringAttachmentDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringAttachmentDTO viewEntity, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springAttachmentService.update(viewEntity);
		return responseDTO;

	}

	@ApiOperation(value = "删除附件", notes = "根据List<String>对象删除附件", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "附件编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springAttachmentService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "删除附件", notes = "根据List<String>对象删除附件", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "附件编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		ResponseDTO<String> responseDTO = springAttachmentService.setDeleted(ids);
		return responseDTO;
	}

	@ApiOperation(value = "上传附件", notes = "根据MultipartFile上传附件", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "file", dataType = "MultipartFile"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Upload")
	public ResponseDTO<String> upload(MultipartFile file, HttpServletRequest request) {
		ResponseDTO<String> responseDTO = springAttachmentService.upload(file);
		return responseDTO;
	}
}
