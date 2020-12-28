package io.github.springsongs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringActUseTask;
import io.github.springsongs.domain.SpringActVacation;
import io.github.springsongs.domain.SpringActVacationApprove;
import io.github.springsongs.dto.SpringActVacationApproveDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringActUseTaskMapper;
import io.github.springsongs.mapper.SpringActVacationApproveMapper;
import io.github.springsongs.mapper.SpringActVacationMapper;
import io.github.springsongs.service.ISpringActVacationApproveService;
import io.github.springsongs.service.SpringProcessService;
import io.github.springsongs.util.AuthenUtil;
import io.github.springsongs.utils.ActivitiConstants;
import io.github.springsongs.utils.Constant;

@Service
public class SpringActVacationApproveServiceImpl implements ISpringActVacationApproveService {

	static Logger logger = LoggerFactory.getLogger(SpringActVacationApproveServiceImpl.class);

	@Autowired
	private SpringActVacationApproveMapper springActVacationApproveMapper;

	@Autowired
	private ISpringActVacationApproveService springActVacationApproveService;

	@Autowired
	private SpringActVacationMapper springActVacationMapper;

	@Autowired
	private TaskService taskService;

	@Autowired
	protected RuntimeService runtimeService;

	@Autowired
	private SpringActUseTaskMapper springActUseTaskMapper;

	@Autowired
	private SpringProcessService springProcessService;
	
	@Autowired
	private AuthenUtil authenUtil;

	@Override
	public void deleteByPrimaryKey(String id) {
		
	}

	@Override
	public void insert(SpringActVacationApproveDTO springActVacationApproveDTO) {
		SpringActVacationApprove springActVacationApprove = new SpringActVacationApprove();
		springActVacationApproveDTO.setId(UUID.randomUUID().toString());
		springActVacationApproveDTO.setCreatedUserId(authenUtil.getUser().getId());
		springActVacationApproveDTO.setCreatedBy(authenUtil.getUser().getUserName());
		springActVacationApproveDTO.setCreatedIp(authenUtil.getUser().getIp());
		springActVacationApproveDTO.setCreatedOn(new Date());
		BeanUtils.copyProperties(springActVacationApproveDTO, springActVacationApprove);
		try {
			springActVacationApproveMapper.insert(springActVacationApprove);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public SpringActVacationApproveDTO selectByPrimaryKey(String id) {
		SpringActVacationApprove springActVacationApprove = springActVacationApproveMapper.selectByPrimaryKey(id);
		// Optional.ofNullable(springActVacationApprove).orElseThrow(()->new
		// SpringSongsException(ResultCode.INFO_NOT_FOUND));
		if (null == springActVacationApprove) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringActVacationApproveDTO springActVacationApproveDTO = new SpringActVacationApproveDTO();
		BeanUtils.copyProperties(springActVacationApprove, springActVacationApproveDTO);
		return springActVacationApproveDTO;
	}

	@Override
	public void updateByPrimaryKey(SpringActVacationApproveDTO springActVacationApproveDTO) {
		SpringActVacationApprove springActVacationApprove = springActVacationApproveMapper
				.selectByPrimaryKey(springActVacationApproveDTO.getId());
		// Optional.ofNullable(springActVacationApprove).orElseThrow(()->new
		// SpringSongsException(ResultCode.INFO_NOT_FOUND));
		if (null == springActVacationApprove) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		try {
			springActVacationApproveDTO.setUpdatedUserId(authenUtil.getUser().getId());
			springActVacationApproveDTO.setUpdatedBy(authenUtil.getUser().getUserName());
			springActVacationApproveDTO.setUpdatedIp(authenUtil.getUser().getIp());
			springActVacationApproveDTO.setUpdatedOn(new Date());
			SpringActVacationApprove springActVacationApproveDO = new SpringActVacationApprove();
			BeanUtils.copyProperties(springActVacationApproveDTO, springActVacationApproveDO);
			springActVacationApproveMapper.updateByPrimaryKey(springActVacationApproveDO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public PageInfo<SpringActVacationApproveDTO> getAllRecordByPage(SpringActVacationApproveDTO record,int page,int size) {
		if (size > Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);
		
		List<SpringActVacationApprove> springActVacationApproves = springActVacationApproveMapper.listByPage(record);
		List<SpringActVacationApproveDTO> springActVacationApproveDTOs = new ArrayList<>();
		springActVacationApproves.stream().forEach(springAritlceCategory -> {
			SpringActVacationApproveDTO springActVacationApproveDTO = new SpringActVacationApproveDTO();
			BeanUtils.copyProperties(springAritlceCategory, springActVacationApproveDTO);
			springActVacationApproveDTOs.add(springActVacationApproveDTO);
		});
		PageInfo springActVacationApprovePageInfo = new PageInfo<>(springActVacationApproves);
		springActVacationApprovePageInfo.setList(springActVacationApproveDTOs);
		return springActVacationApprovePageInfo;
	}

	@Override
	public void setDeleted(List<String> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchSaveExcel(List<String[]> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void completeTask(String taskId, String auditStr) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String procInstanceId = task.getProcessInstanceId();
		if (task == null) {
			logger.error("审核任务ID:{}查询到任务为空！", taskId);
			throw new SpringSongsException(ResultCode.TASK_HADED_CONFIG);
		}

		TaskDefinition taskDefinition = null;
		try {
			taskDefinition = springProcessService.getNextTaskInfo(taskId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("audit", auditStr);
		taskService.setVariables(taskId, map);
		taskService.complete(taskId, map);

		if (null != taskDefinition) {
			List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstanceId).list();
			SpringActUseTask springActUseTask = springActUseTaskMapper.findUserTaskByTaskDefKey(taskDefinition.getKey());
			String nextTaskId = "";
			for (Task taskTemp : tasks) {
				if (taskTemp.getTaskDefinitionKey().equals(taskDefinition.getKey())) {
					nextTaskId = taskTemp.getId();
					break;
				}
			}
			if (null != springActUseTask) {
				if (ActivitiConstants.ASSIGNEE.equals(springActUseTask.getTaskType())) {
					taskService.setAssignee(nextTaskId, springActUseTask.getCandidateIds());
				} else if (ActivitiConstants.CANDIDATE_USER.equals(springActUseTask.getTaskType())) {
					String[] candidateUsers = springActUseTask.getCandidateIds().split(",");
					for (String candidateUser : candidateUsers) {
						taskService.addCandidateUser(nextTaskId, candidateUser);
					}
				} else if (ActivitiConstants.CANDIDATE_GROUP.equals(springActUseTask.getTaskType())) {
					String[] candidateUsers = springActUseTask.getCandidateIds().split(",");
					for (String candidateUser : candidateUsers) {
						taskService.addCandidateGroup(nextTaskId, candidateUser);
					}
				}
			}
		}

		if (null == taskDefinition) {
			SpringActVacation springActVacation = springActVacationMapper.findByProcessInstanceId(procInstanceId);
			springActVacation.setProcessStatus(Short.valueOf(auditStr));
			springActVacationMapper.updateByPrimaryKey(springActVacation);
		}
		// springActVacationApproveService.updateByPrimaryKey(record);
//		String excId = task.getExecutionId();
//		ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId).singleResult();
//		String activitiId = execution.getProcessDefinitionKey();
//		execution.getActivity().getActivityBehavior();
	}

	@Transactional
	@Override
	public void completeSpringActVacationApprove(SpringActVacationApproveDTO record, String taskId) {
		record.setCreatedBy(authenUtil.getUser().getUserName());
		record.setCreatedUserId(authenUtil.getUser().getId());
		record.setCreatedIp(authenUtil.getUser().getIp());
		record.setCreatedOn(new Date());
		record.setTrueName(authenUtil.getUser().getUserName());
		record.setUserId(authenUtil.getUser().getId());
		springActVacationApproveService.insert(record);
		springActVacationApproveService.completeTask(taskId, String.valueOf(record.getResult()));
	}

	@Override
	public List<SpringActVacationApproveDTO> findByVacationId(String vacationId) {
		List<SpringActVacationApprove> springActVacationApproveList=springActVacationApproveMapper.findByVacationId(vacationId);
		List<SpringActVacationApproveDTO> springActVacationApproveDTOs=new ArrayList<>();
		springActVacationApproveList.stream().forEach(springActVacationApprove->{
			SpringActVacationApproveDTO springActVacationApproveDTO=new SpringActVacationApproveDTO();
			BeanUtils.copyProperties(springActVacationApprove, springActVacationApproveDTO);
			springActVacationApproveDTOs.add(springActVacationApproveDTO);
		});
		return springActVacationApproveDTOs;
	}

}
