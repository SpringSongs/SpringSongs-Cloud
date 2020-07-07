package org.authority.service;

import java.util.List;

import org.authority.domain.Aritlce;
import org.authority.service.hystrix.AritlceServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTHORITY-SERVICE", fallback = AritlceServiceImpl.class)
public interface IAritlceService {

	@RequestMapping(value = "/BaseAritlce/DeleteByPrimaryKey", method = RequestMethod.POST)
	R deleteByPrimaryKey(@RequestParam("id") String id);

	@RequestMapping(value = "/BaseAritlce/Insert", method = RequestMethod.POST)
	R insert(@RequestBody Aritlce record);

	@RequestMapping(value = "/BaseAritlce/SelectByPrimaryKey", method = RequestMethod.POST)
	R selectByPrimaryKey(@RequestParam("id") String id);

	@RequestMapping(value = "/BaseAritlce/UpdateByPrimaryKey", method = RequestMethod.POST)
	R updateByPrimaryKey(@RequestBody Aritlce record);

	@RequestMapping(value = "/BaseAritlce/GetAllRecordByPage", method = RequestMethod.POST)
	R getAllRecordByPage(@RequestBody Aritlce record, @RequestParam("page") int page,
			@RequestParam("size") int size);

	@RequestMapping(value = "/BaseAritlce/SetDeleted", method = RequestMethod.POST)
	R setDeleted(@RequestParam("ids") List<String> ids);

	@RequestMapping(value = "/BaseAritlce/BatchSaveExcel", method = RequestMethod.POST)
	R batchSaveExcel(@RequestParam("list") List<String[]> list);
	
	@RequestMapping(value = "/BaseAritlce/Audit/{id}", method = RequestMethod.POST)
	R audit(@PathVariable(value = "id", required = true) String id);
}
