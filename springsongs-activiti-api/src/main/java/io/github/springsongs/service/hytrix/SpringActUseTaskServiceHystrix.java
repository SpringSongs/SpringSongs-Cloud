package io.github.springsongs.service.hytrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringActUseTaskDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringActUseTaskService;

@Component
public class SpringActUseTaskServiceHystrix implements ISpringActUseTaskService {

	@Override
	public ResponseDTO<List<SpringActUseTaskDTO>> listUserTaskByProcDefKey(String procDefKey) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> initSingleDefinition(String processDefinitionId, String procDefKey) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> initAllDefinition() {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> setUserToTask(String procDefKey) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
