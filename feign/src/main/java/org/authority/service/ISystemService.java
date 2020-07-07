package org.authority.service;

import java.util.List;

import org.authority.domain.System;
import org.authority.service.hystrix.SystemServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTHORITY-SERVICE", fallback = SystemServiceImpl.class)
public interface ISystemService {

	@RequestMapping(value = "/BaseSystem/DeleteByPrimaryKey", method = RequestMethod.POST)
	R deleteByPrimaryKey(@RequestParam("id") String id);

	@RequestMapping(value = "/BaseSystem/Insert", method = RequestMethod.POST)
	R insert(@RequestBody System record);

	@RequestMapping(value = "/BaseSystem/SelectByPrimaryKey", method = RequestMethod.POST)
	R selectByPrimaryKey(@RequestParam("id") String id);

	@RequestMapping(value = "/BaseSystem/UpdateByPrimaryKey", method = RequestMethod.POST)
	R updateByPrimaryKey(@RequestBody System record);

	@RequestMapping(value = "/BaseSystem/GetAllRecordByPage", method = RequestMethod.POST)
	R getAllRecordByPage(@RequestBody System record, @RequestParam("page") int page,
			@RequestParam("size") int size);

	@RequestMapping(value = "/BaseSystem/SetDeleted", method = RequestMethod.POST)
	R setDeleted(@RequestParam("ids") List<String> ids);

	@RequestMapping(value = "/BaseSystem/BatchSaveExcel", method = RequestMethod.POST)
	R batchSaveExcel(@RequestParam("list") List<String[]> list);

	@RequestMapping(value = "/BaseSystem/ListAll", method = RequestMethod.POST)
	R listAll();
}
