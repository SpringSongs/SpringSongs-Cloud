package io.github.springsongs.service.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringSiteMessageDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringSiteMessageService;

@Component
public class SpringSiteMessageServiceHystrix implements ISpringSiteMessageService {

	@Override
	public ReponseResultPageDTO<List<SpringSiteMessageDTO>> listByPage(SpringSiteMessageDTO springAritlceQuery, int page,
			int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<SpringSiteMessageDTO> get(String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> save(SpringSiteMessageDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> update(SpringSiteMessageDTO viewEntity) {
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
	public ResponseDTO<Integer> countNotReadMessageByUserId() {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
