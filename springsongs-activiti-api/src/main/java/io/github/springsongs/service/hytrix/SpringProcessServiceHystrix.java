package io.github.springsongs.service.hytrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.Model;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringProcessService;

@Component
public class SpringProcessServiceHystrix implements ISpringProcessService {

	@Override
	public ReponseResultPageDTO<List<Model>> listByPage(String category, int page, int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> updateState(String state, String procDefId) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public void resourceRead(String procDefId, String proInsId, String resType) {

	}

	@Override
	public ResponseDTO<String> convertToModel(String procDefId) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
