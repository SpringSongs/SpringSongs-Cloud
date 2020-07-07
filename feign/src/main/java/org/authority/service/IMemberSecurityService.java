package org.authority.service;

import java.util.List;

import org.authority.domain.MemberSecurity;
import org.authority.service.hystrix.MemberSecurityServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTHORITY-SERVICE", fallback = MemberSecurityServiceImpl.class)
public interface IMemberSecurityService {
	@RequestMapping(value = "/BaseBuserLogOn/DeleteByPrimaryKey", method = RequestMethod.POST)
	R deleteByPrimaryKey(@RequestParam("id") String id);

	@RequestMapping(value = "/BaseBuserLogOn/Insert", method = RequestMethod.POST)
	R insert(@RequestBody MemberSecurity record);

	@RequestMapping(value = "/BaseBuserLogOn/SelectByPrimaryKey", method = RequestMethod.POST)
	R selectByPrimaryKey(@RequestParam("id") String id);

	@RequestMapping(value = "/BaseBuserLogOn/UpdateByPrimaryKey", method = RequestMethod.POST)
	R updateByPrimaryKey(@RequestBody MemberSecurity record);

	@RequestMapping(value = "/BaseBuserLogOn/GetAllRecordByPage", method = RequestMethod.POST)
	R getAllRecordByPage(@RequestBody MemberSecurity record, @RequestParam("page") int page,
			@RequestParam("size") int size);

	@RequestMapping(value = "/BaseBuserLogOn/SetDeleted", method = RequestMethod.POST)
	R setDeleted(@RequestParam("ids") List<String> ids);

	@RequestMapping(value = "/BaseBuserLogOn/BatchSaveExcel", method = RequestMethod.POST)
	R batchSaveExcel(@RequestParam("list") List<String[]> list);
}
