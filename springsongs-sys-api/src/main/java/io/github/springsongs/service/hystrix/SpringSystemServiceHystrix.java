package io.github.springsongs.service.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringSystemDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringSystemService;

@Component
public class SpringSystemServiceHystrix implements ISpringSystemService {

	@Override
	public ReponseResultPageDTO<List<SpringSystemDTO>> listByPage(SpringSystemDTO springAritlceQuery, int page, int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<SpringSystemDTO> get(String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> save(SpringSystemDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> update(SpringSystemDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> setDeleted(List<String> ids) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<List<SpringSystemDTO>> listAll() {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
