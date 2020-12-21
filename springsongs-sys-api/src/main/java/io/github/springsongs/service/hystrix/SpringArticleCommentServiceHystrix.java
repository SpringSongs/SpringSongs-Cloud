package io.github.springsongs.service.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringArticleCommentDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringArticleCommentService;

@Component
public class SpringArticleCommentServiceHystrix implements ISpringArticleCommentService {

	@Override
	public ReponseResultPageDTO<List<SpringArticleCommentDTO>> listByPage(SpringArticleCommentDTO springAritlceQuery,
			int page, int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> get(String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> save(SpringArticleCommentDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> update(SpringArticleCommentDTO viewEntity) {
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
	public ResponseDTO<String> audit(String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
