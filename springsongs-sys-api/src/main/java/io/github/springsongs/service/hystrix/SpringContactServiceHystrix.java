package io.github.springsongs.service.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringContactDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringContactService;

@Component
public class SpringContactServiceHystrix implements ISpringContactService {

	@Override
	public ReponseResultPageDTO<List<SpringContactDTO>> listByPage(SpringContactDTO springAritlceQuery, int page, int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<SpringContactDTO> get(String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> save(SpringContactDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> update(SpringContactDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> setDeleted(List<String> ids) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> deleted(List<String> ids) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
