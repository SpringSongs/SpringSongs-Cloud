package io.github.springsongs.service.hytrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.domain.SpringActModel;
import io.github.springsongs.dto.Model;
import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringActModelService;

@Component
public class SpringActModelServiceHystrix implements ISpringActModelService {

	@Override
	public ReponseResultPageDTO<List<Model>> listByPage(SpringActModel springActModelQuery, int page, int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> save(SpringActModel viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> edit(SpringActModel viewEntity, String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> delete(List<String> ids) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> deploy(String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public void export(String modelId) {

	}

}
