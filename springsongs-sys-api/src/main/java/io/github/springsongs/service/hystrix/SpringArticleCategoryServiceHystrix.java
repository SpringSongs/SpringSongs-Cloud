package io.github.springsongs.service.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ElementUiTreeDTO;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringArticleCategoryDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringArticleCategoryService;

@Component
public class SpringArticleCategoryServiceHystrix implements ISpringArticleCategoryService {

	@Override
	public ReponseResultPageDTO<SpringArticleCategoryDTO> listByPage(SpringArticleCategoryDTO springAritlceQuery,
			int page, int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> get(String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> save(SpringArticleCategoryDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> update(SpringArticleCategoryDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> setDeleted(List<String> ids) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<ElementUiTreeDTO> getModuleByParentId(String parentId) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<SpringArticleCategoryDTO> listAllRecord() {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<SpringArticleCategoryDTO> ListAllToTree() {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
