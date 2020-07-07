package org.authority.service;

import java.util.List;

import org.authority.domain.Parameter;
import org.authority.service.hystrix.ParameterServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "AUTHORITY-SERVICE", fallback =ParameterServiceImpl.class)
public interface IParameterService {
	@RequestMapping(value = "/BaseParameter/DeleteByPrimaryKey", method = RequestMethod.POST)
	R deleteByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseParameter/Insert", method = RequestMethod.POST)
	R insert(@RequestBody Parameter record);

	@RequestMapping(value = "/BaseParameter/SelectByPrimaryKey", method = RequestMethod.POST)
	R selectByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseParameter/UpdateByPrimaryKey", method = RequestMethod.POST)
	R updateByPrimaryKey(@RequestBody Parameter record);

	@RequestMapping(value = "/BaseParameter/GetAllRecordByPage", method = RequestMethod.POST)
	R getAllRecordByPage(@RequestBody Parameter record, @RequestParam("page")int page,@RequestParam("size")int size);

	@RequestMapping(value = "/BaseParameter/SetDeleted", method = RequestMethod.POST)
	R setDeleted(@RequestParam("ids")List<String> ids);
	
	@RequestMapping(value = "/BaseParameter/BatchSaveExcel", method = RequestMethod.POST)
	R batchSaveExcel(@RequestParam("list")List<String[]> list);
}
