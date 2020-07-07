package org.authority.service;

import java.util.List;

import org.authority.domain.Folder;
import org.authority.service.hystrix.FolderServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "AUTHORITY-SERVICE", fallback =FolderServiceImpl.class)
public interface IFolderService {

	@RequestMapping(value = "/BaseFolder/DeleteByPrimaryKey", method = RequestMethod.POST)
	R deleteByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseFolder/Insert", method = RequestMethod.POST)
	R insert(@RequestBody Folder record);

	@RequestMapping(value = "/BaseFolder/SelectByPrimaryKey", method = RequestMethod.POST)
	R selectByPrimaryKey(@RequestParam("id")String id);

	@RequestMapping(value = "/BaseFolder/UpdateByPrimaryKey", method = RequestMethod.POST)
	R updateByPrimaryKey(@RequestBody Folder record);

	@RequestMapping(value = "/BaseFolder/GetAllRecordByPage", method = RequestMethod.POST)
	R getAllRecordByPage(@RequestBody Folder record, @RequestParam("page")int page,@RequestParam("size") int size);

	@RequestMapping(value = "/BaseFolder/SetDeleted", method = RequestMethod.POST)
	R setDeleted(@RequestParam("ids")List<String> ids);

	@RequestMapping(value = "/BaseFolder/BatchSaveExcel", method = RequestMethod.POST)
	R batchSaveExcel(@RequestParam("list")List<String[]> list);
}
