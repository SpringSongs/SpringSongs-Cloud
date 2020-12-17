package io.github.springsongs.service.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringOrganizationDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringOrganizationService;

@Component
public class SpringOrganizationServiceHystrix implements ISpringOrganizationService {

	@Override
	public ReponseResultPageDTO<SpringOrganizationDTO> listByPage(SpringOrganizationDTO springAritlceQuery, int page,
			int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<SpringOrganizationDTO> get(String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> save(SpringOrganizationDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> update(SpringOrganizationDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> setDeleted(List<String> ids) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<List<SpringOrganizationDTO>> getOrganizationsByParent(String parentId) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<List<SpringOrganizationDTO>> listAllRecord() {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<List<SpringOrganizationDTO>> ListAllToTree() {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
