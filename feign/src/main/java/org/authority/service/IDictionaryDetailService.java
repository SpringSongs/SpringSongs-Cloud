package org.authority.service;

import java.util.List;

import org.authority.domain.DictionaryDetail;
import org.authority.service.hystrix.DictionaryDetailServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTHORITY-SERVICE", fallback =DictionaryDetailServiceImpl.class)
public interface IDictionaryDetailService {
	@RequestMapping(value = "/BaseDictionaryDetail/DeleteByPrimaryKey", method = RequestMethod.POST)
	R deleteByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseDictionaryDetail/Insert", method = RequestMethod.POST)
	R insert(@RequestBody DictionaryDetail record);

	@RequestMapping(value = "/BaseDictionaryDetail/SelectByPrimaryKey", method = RequestMethod.POST)
	R selectByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseDictionaryDetail/UpdateByPrimaryKey", method = RequestMethod.POST)
	R updateByPrimaryKey(@RequestBody DictionaryDetail record);

	@RequestMapping(value = "/BaseDictionaryDetail/GetAllRecordByPage", method = RequestMethod.POST)
	R getAllRecordByPage(@RequestBody DictionaryDetail record, @RequestParam("page")int page,@RequestParam("size")int size);

	@RequestMapping(value = "/BaseDictionaryDetail/SetDeleted", method = RequestMethod.POST)
	R setDeleted(@RequestParam("ids")List<String> ids);
	
	@RequestMapping(value = "/BaseDictionaryDetail/BatchSaveExcel", method = RequestMethod.POST)
	R batchSaveExcel(@RequestParam("list")List<String[]> list);
}
