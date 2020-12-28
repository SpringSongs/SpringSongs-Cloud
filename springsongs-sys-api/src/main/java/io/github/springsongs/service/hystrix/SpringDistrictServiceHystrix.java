package io.github.springsongs.service.hystrix;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringContactDTO;
import io.github.springsongs.dto.SpringDistrictDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringDistrictService;

@Component
public class SpringDistrictServiceHystrix implements ISpringDistrictService {

	@Override
	public ReponseResultPageDTO<List<SpringDistrictDTO>> listByPage(SpringDistrictDTO springAritlceQuery, int page,
			int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<SpringDistrictDTO> get(@NotEmpty(message = "id不能为空") Long id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> save(@Valid SpringDistrictDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> update(@Valid SpringDistrictDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> setDeleted(List<Long> ids) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> deleted(List<Long> ids) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<List<SpringDistrictDTO>> listSpringDistrictByParentId(Long parentId) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
