package org.authority.security.service;

import java.util.List;

import org.authority.domain.LoginLog;
import org.authority.security.service.impl.BaseLoginLogServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTHORITY-SERVICE", fallback =BaseLoginLogServiceImpl.class)
public interface IBaseLoginLogService {
	@RequestMapping(value = "/BaseLoginLog/DeleteByPrimaryKey", method = RequestMethod.POST)
	R deleteByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseLoginLog/Insert", method = RequestMethod.POST)
	R insert(@RequestBody LoginLog record);

	@RequestMapping(value = "/BaseLoginLog/SelectByPrimaryKey", method = RequestMethod.POST)
	R selectByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseLoginLog/UpdateByPrimaryKey", method = RequestMethod.POST)
	R updateByPrimaryKey(@RequestBody LoginLog record);

	@RequestMapping(value = "/BaseLoginLog/GetAllRecordByPage", method = RequestMethod.POST)
	R getAllRecordByPage(@RequestBody LoginLog record, @RequestParam("page")int page,@RequestParam("size")int size);

	@RequestMapping(value = "/BaseLoginLog/SetDeleted", method = RequestMethod.POST)
	R setDeleted(@RequestParam("ids")List<String> ids);
	
	@RequestMapping(value = "/BaseLoginLog/BatchSaveExcel", method = RequestMethod.POST)
	R batchSaveExcel(@RequestParam("list")List<String[]> list);
}
