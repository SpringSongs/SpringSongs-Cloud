package io.github.springsongs.service.hystrix;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.SpringLoginLogDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringLoginLogService;

@Component
public class SpringLoginLogServiceHystrix implements ISpringLoginLogService {

	@Override
	public ReponseResultPageDTO<SpringLoginLogDTO> listByPage(SpringLoginLogDTO springAritlceQuery, int page,
			int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

}
