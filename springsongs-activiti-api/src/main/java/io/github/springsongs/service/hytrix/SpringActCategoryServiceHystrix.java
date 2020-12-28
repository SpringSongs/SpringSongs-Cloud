package io.github.springsongs.service.hytrix;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringActCategoryDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringActCategoryService;

@Component
public class SpringActCategoryServiceHystrix implements ISpringActCategoryService {

	@Override
	public ReponseResultPageDTO<List<SpringActCategoryDTO>> listByPage(SpringActCategoryDTO springActCategoryQuery,
			int page, int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<SpringActCategoryDTO> get(String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> save(SpringActCategoryDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> update(SpringActCategoryDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> setDeleted(List<String> ids) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> deleted( List<String> ids) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<List<SpringActCategoryDTO>> listAll() {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
