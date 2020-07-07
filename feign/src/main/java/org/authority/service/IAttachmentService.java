package org.authority.service;

import java.util.List;

import org.authority.domain.Attachment;
import org.authority.service.hystrix.BaseAttachmentServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "AUTHORITY-SERVICE", fallback =BaseAttachmentServiceImpl.class)
public interface IAttachmentService {
	
	@RequestMapping(value = "/BaseFile/DeleteByPrimaryKey", method = RequestMethod.POST)
	R deleteByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseFile/Insert", method = RequestMethod.POST)
	R insert(@RequestBody Attachment record);

	@RequestMapping(value = "/BaseFile/SelectByPrimaryKey", method = RequestMethod.POST)
	R selectByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseFile/UpdateByPrimaryKey", method = RequestMethod.POST)
	R updateByPrimaryKey(@RequestBody Attachment record);

	@RequestMapping(value = "/BaseFile/GetAllRecordByPage", method = RequestMethod.POST)
	R getAllRecordByPage(@RequestBody Attachment record, @RequestParam("page")int page,@RequestParam("size")int size);

	@RequestMapping(value = "/BaseFile/SetDeleted", method = RequestMethod.POST)
	R setDeleted(@RequestParam("ids")List<String> ids);
	
	@RequestMapping(value = "/BaseFile/BatchSaveExcel", method = RequestMethod.POST)
	R batchSaveExcel(@RequestParam("list")List<String[]> list);
}
