package org.authority.service;

import java.util.List;

import org.authority.domain.Comment;
import org.authority.service.hystrix.CommentServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTHORITY-SERVICE", fallback =CommentServiceImpl.class)
public interface ICommentService {
	@RequestMapping(value = "/BaseComment/DeleteByPrimaryKey", method = RequestMethod.POST)
	R deleteByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseComment/Insert", method = RequestMethod.POST)
	R insert(@RequestBody Comment record);

	@RequestMapping(value = "/BaseComment/SelectByPrimaryKey", method = RequestMethod.POST)
	R selectByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseComment/UpdateByPrimaryKey", method = RequestMethod.POST)
	R updateByPrimaryKey(@RequestBody Comment record);

	@RequestMapping(value = "/BaseComment/GetAllRecordByPage", method = RequestMethod.POST)
	R getAllRecordByPage(@RequestBody Comment record, @RequestParam("page") int page,@RequestParam("size") int size);

	@RequestMapping(value = "/BaseComment/SetDeleted", method = RequestMethod.POST)
	R setDeleted(@RequestParam("ids")List<String> ids);
	
	@RequestMapping(value = "/BaseComment/BatchSaveExcel", method = RequestMethod.POST)
	R batchSaveExcel(@RequestParam("list")List<String[]> list);
}
