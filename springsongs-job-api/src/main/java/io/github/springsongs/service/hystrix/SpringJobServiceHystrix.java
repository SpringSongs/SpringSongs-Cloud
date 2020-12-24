package io.github.springsongs.service.hystrix;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import io.github.springsongs.dto.ReponseResultPageDTO;
import io.github.springsongs.dto.ResponseDTO;
import io.github.springsongs.dto.SpringJobDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.service.ISpringJobService;

@Component
public class SpringJobServiceHystrix implements ISpringJobService {

	@Override
	public ReponseResultPageDTO<List<SpringJobDTO>> listByPage(SpringJobDTO springJobQuery, int page, int size) {
		return ReponseResultPageDTO.successed(null, 0, ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<SpringJobDTO> get(String id) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> save(@Valid SpringJobDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> update(@Valid SpringJobDTO viewEntity) {
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
	public ResponseDTO<String> addTask(@Valid SpringJobDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> updateTask(@Valid SpringJobDTO viewEntity) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> pauseTask(String taskClassName, String groupCode) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);	}

	@Override
	public ResponseDTO<String> resumeTask(String taskClassName, String groupCode) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

	@Override
	public ResponseDTO<String> deleteTask(String taskClassName, String groupCode) {
		return ResponseDTO.successed(ResultCode.SERCIE_BUSING);
	}

}
