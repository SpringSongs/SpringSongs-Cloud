package org.authority.service;

import java.util.List;

import org.authority.domain.Module;
import org.authority.domain.ModuleRole;
import org.authority.service.hystrix.ModuleServiceImpl;
import org.authority.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTHORITY-SERVICE", fallback = ModuleServiceImpl.class)
public interface IModuleService {
	@RequestMapping(value = "/BaseModule/DeleteByPrimaryKey", method = RequestMethod.POST)
	R deleteByPrimaryKey(@RequestParam("id") String id);

	@RequestMapping(value = "/BaseModule/Insert", method = RequestMethod.POST)
	R insert(@RequestBody Module record);

	@RequestMapping(value = "/BaseModule/SelectByPrimaryKey", method = RequestMethod.POST)
	R selectByPrimaryKey(@RequestParam("id") String id);

	@RequestMapping(value = "/BaseModule/UpdateByPrimaryKey", method = RequestMethod.POST)
	R updateByPrimaryKey(@RequestBody Module record);

	@RequestMapping(value = "/BaseModule/GetAllRecordByPage", method = RequestMethod.POST)
	R getAllRecordByPage(@RequestBody Module record, @RequestParam("page") int page,
			@RequestParam("size") int size);

	@RequestMapping(value = "/BaseModule/SetDeleted", method = RequestMethod.POST)
	R setDeleted(@RequestParam("ids") List<String> ids);

	@RequestMapping(value = "/BaseModule/BatchSaveExcel", method = RequestMethod.POST)
	R batchSaveExcel(@RequestParam("list") List<String[]> list);

	@RequestMapping(value = "/BaseModule/ListModuleByUserId", method = RequestMethod.POST)
	R listModuleByUserId(@RequestParam("userId") String userId);

	@RequestMapping(value = "/BaseModule/GetModulesByParentId", method = RequestMethod.POST)
	R getModulesByParentId(@RequestParam("parentId") String parentId, @RequestParam("systemId") String systemId);

	@RequestMapping(value = "/BaseModule/ListModulesByRoleId", method = RequestMethod.POST)
	R listModulesByRoleId(@RequestParam("roleId") String roleId);

	@RequestMapping(value = "/BaseModule/SaveModuleToRole", method = RequestMethod.POST)
	R saveModuleToRole(@RequestBody List<ModuleRole> baseModuleRoleEntityList,
			@RequestParam("roleId") String roleId);
}
