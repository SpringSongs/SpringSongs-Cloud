package io.github.springsongs.service.hytrix;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.SpringTaskDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringTaskService;

@Component
public class SpringTaskServiceHystrix implements ISpringTaskService {

	@Override
	public ReponseResultPageDTO<List<SpringTaskDTO>> getTodoTasks(String title, String category, int page, int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ReponseResultPageDTO<List<SpringTaskDTO>> getTasksByStarter(String title, String category, int page,
			int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ReponseResultPageDTO<List<SpringTaskDTO>> getFinishTasks(String title, String category, int page, int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

}
