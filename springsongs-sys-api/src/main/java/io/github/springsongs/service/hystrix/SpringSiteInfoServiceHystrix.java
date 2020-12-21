package io.github.springsongs.service.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringSiteInfoDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringSiteInfoService;
@Component
public class SpringSiteInfoServiceHystrix implements ISpringSiteInfoService {

	@Override
	public ReponseResultPageDTO<List<SpringSiteInfoDTO>> listByPage(SpringSiteInfoDTO springAritlceQuery, int page,
			int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<SpringSiteInfoDTO> get(String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> save(SpringSiteInfoDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> update(SpringSiteInfoDTO viewEntity) {
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
