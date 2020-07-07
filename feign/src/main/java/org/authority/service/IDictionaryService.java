package org.authority.service;

import java.util.List;

import org.authority.domain.Dictionary;
import org.authority.service.hystrix.DictionaryServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTHORITY-SERVICE", fallback =DictionaryServiceImpl.class)
public interface IDictionaryService {

	@RequestMapping(value = "/BaseDictionary/DeleteByPrimaryKey", method = RequestMethod.POST)
	R deleteByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseDictionary/Insert", method = RequestMethod.POST)
	R insert(@RequestBody Dictionary record);

	@RequestMapping(value = "/BaseDictionary/SelectByPrimaryKey", method = RequestMethod.POST)
	R selectByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseDictionary/UpdateByPrimaryKey", method = RequestMethod.POST)
	R updateByPrimaryKey(@RequestBody Dictionary record);

	@RequestMapping(value = "/BaseDictionary/GetAllRecordByPage", method = RequestMethod.POST)
	R getAllRecordByPage(@RequestBody Dictionary record, @RequestParam("page")int page,@RequestParam("size")int size);

	@RequestMapping(value = "/BaseDictionary/SetDeleted", method = RequestMethod.POST)
	R setDeleted(@RequestParam("ids")List<String> ids);
	
	@RequestMapping(value = "/BaseDictionary/BatchSaveExcel", method = RequestMethod.POST)
	R batchSaveExcel(@RequestParam("list")List<String[]> list);
}
