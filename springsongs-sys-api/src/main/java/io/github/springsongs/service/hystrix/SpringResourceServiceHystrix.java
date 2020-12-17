package io.github.springsongs.service.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.EasyUiMenuDTO;
import io.github.springsongs.dto.ElementUiTreeDTO;
import io.github.springsongs.dto.MenuDTO;
import io.github.springsongs.dto.MenuRouterDTO;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringResourceDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringResourceService;
@Component
public class SpringResourceServiceHystrix implements ISpringResourceService {

	@Override
	public ReponseResultPageDTO<SpringResourceDTO> listByPage(SpringResourceDTO springAritlceQuery, int page,
			int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<SpringResourceDTO> get(String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> save(SpringResourceDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> update(SpringResourceDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> setDeleted(List<String> ids) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<List<MenuDTO>> getMenus() {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<List<MenuRouterDTO>> getRouters() {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<List<EasyUiMenuDTO>> getEasyUIMenu() {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<List<ElementUiTreeDTO>> getModuleByParentId(String parentId, String systemId) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<List<SpringResourceDTO>> ListAllToTree(String systemId) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
