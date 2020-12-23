package io.github.springsongs.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringSiteMessageDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringSiteMessageService;
import io.github.springsongs.util.AuthenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "站内消息管理")
@RestController
@RequestMapping(value = "/SpringSiteMessage")
public class SpringSiteMessageController{

	private static final Logger logger = LoggerFactory.getLogger(SpringSiteMessageController.class);
	@Autowired
	private ISpringSiteMessageService SpringSiteMessageService;
	
	@Autowired
	private AuthenUtil authenUtil;

	@ApiOperation(value = "获取内容管理分页列表", response = ReponseResultPageDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "searchQuery", dataType = "SpringSiteMessageDTO"),
			@ApiImplicitParam(name = "page", dataType = "int"), @ApiImplicitParam(name = "size", dataType = "int") })
	@PostMapping(value = "/ListByPage")
	public ReponseResultPageDTO<List<SpringSiteMessageDTO>> listByPage(@RequestBody SpringSiteMessageDTO searchQuery, int page,
			int size) {
		PageInfo<SpringSiteMessageDTO> lists = SpringSiteMessageService.getAllRecordByPage(searchQuery, page, size);
		return ReponseResultPageDTO.successed(lists.getList(), lists.getTotal(), ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "获取站内消息", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "String") })
	@PostMapping(value = "/Detail")
	public ResponseDTO<SpringSiteMessageDTO> get(@NotEmpty(message = "id不能为空") String id) {
		SpringSiteMessageDTO entity = SpringSiteMessageService.selectByPrimaryKey(id);
		return ResponseDTO.successed(entity, ResultCode.SELECT_SUCCESSED);
	}

	@ApiOperation(value = "创建站内消息", notes = "根据SpringSiteMessageDTO创建站内消息", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringSiteMessageDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PostMapping(value = "/Create")
	public ResponseDTO<String> save(@RequestBody @Valid SpringSiteMessageDTO viewEntity, HttpServletRequest request) {
		SpringSiteMessageService.insert(viewEntity);
		return ResponseDTO.successed(null, ResultCode.SAVE_SUCCESSED);
	}

	@ApiOperation(value = "修改站内消息", notes = "根据SpringSiteMessageDTO修改站内消息", response = ResponseDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "viewEntity", dataType = "SpringSiteMessageDTO"),
			@ApiImplicitParam(name = "request", dataType = "HttpServletRequest"), })
	@PutMapping(value = "/Edit")
	public ResponseDTO<String> update(@RequestBody @Valid SpringSiteMessageDTO viewEntity, HttpServletRequest request) {
		SpringSiteMessageService.updateByPrimaryKey(viewEntity);
		return ResponseDTO.successed(null, ResultCode.UPDATE_SUCCESSED);
	}

	@ApiOperation(value = "删除站内消息", notes = "根据List<String>对象删除站内消息", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "站内消息编号", required = true)
	@PostMapping(value = "/SetDeleted")
	public ResponseDTO<String> setDeleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		SpringSiteMessageService.setDeleted(ids);
		return ResponseDTO.successed(null, ResultCode.DELETE_SUCCESSED);
	}

	@ApiOperation(value = "删除站内消息", notes = "根据List<String>对象删除站内消息", response = ResponseDTO.class)
	@ApiImplicitParam(dataType = "List<String>", name = "ids", value = "站内消息编号", required = true)
	@PostMapping(value = "/Deleted")
	public ResponseDTO<String> deleted(@RequestParam(value = "ids", required = true) List<String> ids) {
		SpringSiteMessageService.setDeleted(ids);
		return ResponseDTO.successed(null, ResultCode.DELETE_SUCCESSED);
	}

	@ApiOperation(value = "查询未读消息", notes = "根据toUserId对象删除站内消息", response = ResponseDTO.class)
	@GetMapping(value = "/CountNotReadMessageByUserId")
	public ResponseDTO<Integer> countNotReadMessageByUserId() {
		Integer numbers = SpringSiteMessageService.countNotReadMessageByUserId(authenUtil.getUser().getId());
		return ResponseDTO.successed(numbers, ResultCode.SELECT_SUCCESSED);
	}
}
