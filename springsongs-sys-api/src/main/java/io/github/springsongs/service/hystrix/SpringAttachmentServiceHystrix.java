package io.github.springsongs.service.hystrix;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringAttachmentDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringAttachmentService;

@Component
public class SpringAttachmentServiceHystrix implements ISpringAttachmentService {

	@Override
	public ReponseResultPageDTO<List<SpringAttachmentDTO>> listByPage(SpringAttachmentDTO springAritlceQuery, int page,
			int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<SpringAttachmentDTO> get( String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> save(@Valid SpringAttachmentDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> update(@Valid SpringAttachmentDTO viewEntity) {
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
	public ResponseDTO<String> upload(MultipartFile file) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
