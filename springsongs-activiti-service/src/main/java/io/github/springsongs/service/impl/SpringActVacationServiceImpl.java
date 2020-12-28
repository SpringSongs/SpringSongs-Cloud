package io.github.springsongs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringActUseTask;
import io.github.springsongs.domain.SpringActVacation;
import io.github.springsongs.domain.SpringStartTask;
import io.github.springsongs.dto.SpringActProcessRouterDTO;
import io.github.springsongs.dto.SpringActVacationDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringActUseTaskMapper;
import io.github.springsongs.mapper.SpringActVacationMapper;
import io.github.springsongs.service.ISpringActProcessRouterService;
import io.github.springsongs.service.ISpringActVacationApproveService;
import io.github.springsongs.service.ISpringActVacationService;
import io.github.springsongs.util.AuthenUtil;
import io.github.springsongs.utils.ActivitiConstants;
import io.github.springsongs.utils.Constant;

@Service
public class SpringActVacationServiceImpl implements ISpringActVacationService {

	static Logger logger = LoggerFactory.getLogger(SpringActVacationServiceImpl.class);

	@Autowired
	private SpringActVacationMapper springActVacationMapper;

	@Autowired
	private ISpringActVacationService springActVacationService;
	
	

	@Autowired
	private ISpringActVacationApproveService springActVacationApproveService;

	@Autowired
	protected IdentityService identityService;

	@Autowired
	protected RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private SpringActUseTaskMapper springActUseTaskMapper;

	@Autowired
	private ISpringActProcessRouterService springActProcessRouterService;

	@Autowired
	private AuthenUtil authenUtil;
	
	
	@Override
	public void deleteByPrimaryKey(String id) {
		try {
			springActVacationMapper.deleteByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Transactional
	@Override
	public void insert(SpringActVacationDTO record) {
		SpringActVacation springActVacation = new SpringActVacation();
		record.setId(UUID.randomUUID().toString());
		record.setUserId(authenUtil.getUser().getId());
		record.setTrueName(authenUtil.getUser().getUserName());
		record.setCreatedUserId(authenUtil.getUser().getId());
		record.setCreatedBy(authenUtil.getUser().getUserName());
		record.setCreatedIp(authenUtil.getUser().getIp());
		record.setCreatedOn(new Date());
		BeanUtils.copyProperties(record, springActVacation);
		try {
			springActVacationMapper.insert(springActVacation);
			// springActVacationService.startSpringActVacation(springActVacation);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public SpringActVacationDTO selectByPrimaryKey(String id) {
		SpringActVacation springActVacation = null;
		springActVacation = springActVacationMapper.selectByPrimaryKey(id);
		if (null == springActVacation) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringActVacationDTO springActVacationDTO = new SpringActVacationDTO();
		BeanUtils.copyProperties(springActVacation, springActVacationDTO);
		return springActVacationDTO;
	}

	@Override
	public void updateByPrimaryKey(SpringActVacationDTO record) {
		SpringActVacation springActVacation = springActVacationMapper.selectByPrimaryKey(record.getId());
		if (null == springActVacation) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		if (!StringUtils.isEmpty(springActVacation.getProcessInstanceId())) {
			throw new SpringSongsException(ResultCode.TASK_HADED_SUBMIT);
		}
		record.setUpdatedUserId(authenUtil.getUser().getId());
		record.setUpdatedBy(authenUtil.getUser().getUserName());
		record.setUpdatedIp(authenUtil.getUser().getIp());
		record.setUpdatedOn(new Date());
		SpringActVacation springActVacationDO = new SpringActVacation();
		BeanUtils.copyProperties(record, springActVacationDO);
		try {
			springActVacationMapper.updateByPrimaryKey(springActVacationDO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public PageInfo<SpringActVacationDTO> getAllRecordByPage(SpringActVacationDTO record, int page,int size) {
		if (size > Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);

		List<SpringActVacation> springActVacations = springActVacationMapper.listByPage(record);
		List<SpringActVacationDTO> springActVacationDTOs = new ArrayList<>();
		springActVacations.stream().forEach(springActVacation -> {
			SpringActVacationDTO springActVacationDTO = new SpringActVacationDTO();
			BeanUtils.copyProperties(springActVacation, springActVacationDTO);
			springActVacationDTOs.add(springActVacationDTO);
		});
		PageInfo springActVacationPageInfo = new PageInfo<>(springActVacations);
		springActVacationPageInfo.setList(springActVacationDTOs);
		return springActVacationPageInfo;
	}

	@Override
	public void setDeleted(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > 1000) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		for (String id : ids) {
			SpringActVacation springActVacation = springActVacationMapper.selectByPrimaryKey(id);
			if (!StringUtils.isEmpty(springActVacation.getProcDefKey())) {
				throw new SpringSongsException(ResultCode.INFO_CAN_NOT_DELETE);
			}
		}
		try {
			springActVacationMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	public void batchSaveExcel(List<String[]> list) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public String submitSpringActVacation(SpringActVacationDTO vacation) {
		if (!StringUtils.isEmpty(vacation.getProcessInstanceId())) {
			throw new SpringSongsException(ResultCode.TASK_HADED_SUBMIT);
		}
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		identityService.setAuthenticatedUserId(vacation.getUserId().toString());
		SpringActProcessRouterDTO springActProcessRouter = springActProcessRouterService
				.findSpringActProcessRouterByProcDefKey(vacation.getProcDefKey());
		SpringStartTask springStartTask = new SpringStartTask();
		springStartTask.setBusinessId(vacation.getId());
		springStartTask.setRouter(springActProcessRouter.getRouter());
		springStartTask.setStartUserName(vacation.getTrueName());
		springStartTask.setTitle(vacation.getTitle());
		springStartTask.setSubmitTime(vacation.getSubmitTime());
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("entity", springStartTask);
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(vacation.getProcDefKey(),
				springStartTask.getBusinessId(), variables);
		String processInstanceId = processInstance.getId();
		SpringActVacation springActVacationDO = this.springActVacationMapper.selectByPrimaryKey(vacation.getId());
		springActVacationDO.setProcessInstanceId(processInstanceId);
		this.springActVacationMapper.updateByPrimaryKey(springActVacationDO);
		this.identityService.setAuthenticatedUserId(null);
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(vacation.getProcDefKey()).latestVersion().singleResult();
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		task.setCategory(processDefinition.getCategory());
		SpringActUseTask springActUseTask = springActUseTaskMapper.findUserTaskByTaskDefKey(task.getTaskDefinitionKey());
		taskService.saveTask(task);
		if (ActivitiConstants.ASSIGNEE.equals(springActUseTask.getTaskType())) {
			taskService.setAssignee(task.getId(), springActUseTask.getCandidateIds());
			
		} else if (ActivitiConstants.CANDIDATE_USER.equals(springActUseTask.getTaskType())) {
			String[] candidateUsers = springActUseTask.getCandidateIds().split(",");
			for (String candidateUser : candidateUsers) {
				taskService.addCandidateUser(task.getId(), candidateUser);
			}
		} else if (ActivitiConstants.CANDIDATE_GROUP.equals(springActUseTask.getTaskType())) {
			String[] candidateUsers = springActUseTask.getCandidateIds().split(",");
			for (String candidateUser : candidateUsers) {
				taskService.addCandidateGroup(task.getId(), candidateUser);
			}
		}
		return processInstanceId;
	}

}