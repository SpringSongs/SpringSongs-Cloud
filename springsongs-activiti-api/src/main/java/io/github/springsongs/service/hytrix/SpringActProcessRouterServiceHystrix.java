package io.github.springsongs.service.hytrix;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringActProcessRouterDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringActProcessRouterService;

@Component
public class SpringActProcessRouterServiceHystrix implements ISpringActProcessRouterService {

	@Override
	public ResponseDTO<String> save(SpringActProcessRouterDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> edit(SpringActProcessRouterDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<SpringActProcessRouterDTO> findSpringActProcessRouterByProcDefKey(
			String procDefKey) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}



	

}
