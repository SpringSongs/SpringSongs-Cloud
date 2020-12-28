package io.github.springsongs.service.hytrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringActVacationApproveDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringActVacationApproveService;

@Component
public class SpringActVacationApproveServiceHystrix implements ISpringActVacationApproveService {

	@Override
	public ResponseDTO<String> completeSpringActVacationApprove(SpringActVacationApproveDTO viewEntity, String taskId) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<List<SpringActVacationApproveDTO>> findByVacationId(String vacationId) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
