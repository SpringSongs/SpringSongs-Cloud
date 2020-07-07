package org.authority.service;

import java.util.List;

import org.authority.domain.ApiSecurity;
import org.authority.service.hystrix.ApiSecurityServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTHORITY-SERVICE", fallback = ApiSecurityServiceImpl.class)
public interface IApiSecurityService {

	@RequestMapping(value = "/BaseApiSecurity/DeleteByPrimaryKey", method = RequestMethod.POST)
	R deleteByPrimaryKey(@RequestParam("id") String id);

	@RequestMapping(value = "/BaseApiSecurity/Insert", method = RequestMethod.POST)
	R insert(@RequestBody ApiSecurity record);

	@RequestMapping(value = "/BaseApiSecurity/SelectByPrimaryKey", method = RequestMethod.POST)
	R selectByPrimaryKey(@RequestParam("id") String id);

	@RequestMapping(value = "/BaseApiSecurity/UpdateByPrimaryKey", method = RequestMethod.POST)
	R updateByPrimaryKey(@RequestBody ApiSecurity record);

	@RequestMapping(value = "/BaseApiSecurity/GetAllRecordByPage", method = RequestMethod.POST)
	R getAllRecordByPage(@RequestBody ApiSecurity record, @RequestParam("page") int page,
			@RequestParam("size") int size);

	@RequestMapping(value = "/BaseApiSecurity/SetDeleted", method = RequestMethod.POST)
	R setDeleted(@RequestParam("ids") List<String> ids);

	@RequestMapping(value = "/BaseApiSecurity/BatchSaveExcel", method = RequestMethod.POST)
	R batchSaveExcel(@RequestParam("ids") List<String[]> list);
}