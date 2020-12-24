package io.github.springsongs.service.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringJobHistoryDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringJobHistoryService;

@Component
public class SpringJobHistoryServiceHystrix implements ISpringJobHistoryService {

	@Override
	public ReponseResultPageDTO<List<SpringJobHistoryDTO>> listByPage(SpringJobHistoryDTO springJobHistory, int page,
			int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> setDeleted(List<String> ids) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
