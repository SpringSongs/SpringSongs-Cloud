package org.authority.service;

import java.util.List;

import org.authority.domain.BusinessCard;
import org.authority.service.hystrix.BusinessCardServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTHORITY-SERVICE", fallback =BusinessCardServiceImpl.class)
public interface IBusinessCardService {

	@RequestMapping(value = "/BaseBusinessCard/DeleteByPrimaryKey", method = RequestMethod.POST)
	R deleteByPrimaryKey(@RequestParam("id") String id);

	@RequestMapping(value = "/BaseBusinessCard/Insert", method = RequestMethod.POST)
	R insert(@RequestBody BusinessCard record);

	@RequestMapping(value = "/BaseBusinessCard/SelectByPrimaryKey", method = RequestMethod.POST)
	R selectByPrimaryKey(@RequestParam("id") String id);

	@RequestMapping(value = "/BaseBusinessCard/UpdateByPrimaryKey", method = RequestMethod.POST)
	R updateByPrimaryKey(@RequestBody BusinessCard record);

	@RequestMapping(value = "/BaseBusinessCard/GetAllRecordByPage", method = RequestMethod.POST)
	R getAllRecordByPage(@RequestBody BusinessCard record, @RequestParam("page") int page,@RequestParam("size") int size);

	@RequestMapping(value = "/BaseBusinessCard/SetDeleted", method = RequestMethod.POST)
	R setDeleted(@RequestParam("ids") List<String> ids);
	
	@RequestMapping(value = "/BaseBusinessCard/BatchSaveExcel", method = RequestMethod.POST)
	R batchSaveExcel(@RequestParam("list") List<String[]> list);
}
