package io.github.springsongs.service.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringDictionaryDetailDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringDictionaryDetailService;

@Component
public class SpringDictionaryDetailServiceHystrix implements ISpringDictionaryDetailService {

	@Override
	public ReponseResultPageDTO<List<SpringDictionaryDetailDTO>> listByPage(SpringDictionaryDetailDTO springAritlceQuery,
			int page, int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<SpringDictionaryDetailDTO> get(String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> save(SpringDictionaryDetailDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> update(SpringDictionaryDetailDTO viewEntity) {
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

	@Override
	public ResponseDTO<List<SpringDictionaryDetailDTO>> listSpringDictionaryDetailByDictionaryCode(
			String dictionaryCode) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
