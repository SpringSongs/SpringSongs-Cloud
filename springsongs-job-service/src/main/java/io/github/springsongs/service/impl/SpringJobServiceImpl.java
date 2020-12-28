package io.github.springsongs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringBaseJob;
import io.github.springsongs.domain.SpringJob;
import io.github.springsongs.domain.SpringJobGroup;
import io.github.springsongs.dto.SpringJobDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.enumeration.SchedulerStatus;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringJobMapper;
import io.github.springsongs.service.ISpringJobService;
import io.github.springsongs.util.AuthenUtil;
import io.github.springsongs.utils.Constant;

@Service
public class SpringJobServiceImpl implements ISpringJobService {


	static Logger logger = LoggerFactory.getLogger(SpringJobServiceImpl.class);

	@Autowired
	private SpringJobMapper springJobMapper;

	@Autowired
	// @Qualifier("Scheduler")
	private Scheduler scheduler;
	
	@Autowired
	private AuthenUtil authenUtil;

	/**
	 *
	 * 物理删除
	 * 
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void deleteByPrimaryKey(String id) {
		try {
			springJobMapper.deleteByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	/**
	 *
	 * 保存
	 * 
	 * @param record
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void insert(SpringJobDTO record) {
		try {
			record.setId(UUID.randomUUID().toString());
			record.setCreatedUserId(authenUtil.getUser().getId());
			record.setCreatedBy(authenUtil.getUser().getUserName());
			record.setCreatedIp(authenUtil.getUser().getIp());
			record.setCreatedOn(new Date());
			SpringJob springJobDO=new SpringJob();
			BeanUtils.copyProperties(record, springJobDO);
			springJobMapper.insert(springJobDO);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}

	}

	/**
	 *
	 * 获取单项
	 * 
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public SpringJobDTO selectByPrimaryKey(String id) {
		SpringJob springJob = null;
		try {
			springJob = springJobMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringJobDTO springJobDTO = new SpringJobDTO();
		BeanUtils.copyProperties(springJob, springJobDTO);
		return springJobDTO;
	}

	/**
	 *
	 * 更新
	 * 
	 * @param record
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void updateByPrimaryKey(SpringJobDTO springJobDTO) {
		SpringJob entity = springJobMapper.selectByPrimaryKey(springJobDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			entity.setGroupCode(springJobDTO.getGroupCode());
			entity.setGroupTitle(springJobDTO.getGroupTitle());
			entity.setTaskTitle(springJobDTO.getTaskTitle());
			entity.setTaskClassTitle(springJobDTO.getTaskClassTitle());
			entity.setCronExpression(springJobDTO.getCronExpression());
			entity.setStatus(springJobDTO.getStatus());
			try {
				springJobMapper.updateByPrimaryKey(entity);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		}
	}

	/**
	 *
	 * 分页查询
	 * 
	 * @param record
	 * @return Page<SpringJobDTO>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringJobDTO> getAllRecordByPage(SpringJobDTO record,int page,int size) {
		if (size> Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		PageHelper.startPage(page, size);

		List<SpringJob> springJobs = springJobMapper.listByPage(record);
		List<SpringJobDTO> springJobDTOs = new ArrayList<>();
		springJobs.stream().forEach(springJob -> {
			SpringJobDTO springJobDTO = new SpringJobDTO();
			BeanUtils.copyProperties(springJob, springJobDTO);
			springJobDTOs.add(springJobDTO);
		});
		PageInfo pringAritlcesPageInfo = new PageInfo<>(springJobs);
		pringAritlcesPageInfo.setList(springJobDTOs);
		return pringAritlcesPageInfo;
	}

	/**
	 *
	 * 逻辑删除
	 * 
	 * @param record
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void setDeleted(List<String> ids) {

	}

	/**
	 *
	 * Excel批量保存
	 * 
	 * @param list
	 * @return R
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void batchSaveExcel(List<String[]> list) {

	}

	@Override
	@Transactional
	public void addTask(SpringJobDTO record) {
		try {

			scheduler.start();

			JobDetail jobDetail = JobBuilder.newJob(getClass(record.getTaskClassTitle()).getClass())
					.withIdentity(record.getTaskClassTitle(), record.getGroupCode()).build();

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(record.getCronExpression());

			CronTrigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(record.getTaskClassTitle(), record.getGroupCode()).withSchedule(scheduleBuilder)
					.build();

			scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.ADD_TASK_FAIL);
		}
		try {
			SpringJob springJob = new SpringJob();
			record.setId(UUID.randomUUID().toString());
			record.setCreatedUserId(authenUtil.getUser().getId());
			record.setCreatedBy(authenUtil.getUser().getUserName());
			record.setCreatedIp(authenUtil.getUser().getIp());
			record.setCreatedOn(new Date());
			record.setStatus(SchedulerStatus.CREATED.getStatus());
			BeanUtils.copyProperties(record, springJob);
			springJobMapper.insert(springJob);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	@Override
	@Transactional
	public void updateTask(SpringJobDTO springJobDTO) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(springJobDTO.getTaskClassTitle(),
					springJobDTO.getGroupCode());

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(springJobDTO.getCronExpression());

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			scheduler.rescheduleJob(triggerKey, trigger);

		} catch (SchedulerException e) {
			logger.error(e.getMessage());
			throw new SpringSongsException(ResultCode.UPDATE_TASK_FAIL);
		}

		SpringJob entity = springJobMapper.selectByPrimaryKey(springJobDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			springJobDTO.setStatus(SchedulerStatus.UPDATED.getStatus());
			entity.setGroupCode(springJobDTO.getGroupCode());
			entity.setGroupTitle(springJobDTO.getGroupTitle());
			entity.setTaskTitle(springJobDTO.getTaskTitle());
			entity.setTaskClassTitle(springJobDTO.getTaskClassTitle());
			entity.setCronExpression(springJobDTO.getCronExpression());
			entity.setStatus(springJobDTO.getStatus());
			entity.setUpdatedUserId(authenUtil.getUser().getId());
			entity.setUpdatedBy(authenUtil.getUser().getUserName());
			entity.setUpdatedIp(authenUtil.getUser().getIp());
			entity.setUpdatedOn(new Date());
			try {
				springJobMapper.updateByPrimaryKey(entity);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		}
	}

	@Override
	@Transactional
	public void pauseTask(String taskClassName, String groupCode) {
		try {
			scheduler.pauseJob(JobKey.jobKey(taskClassName, groupCode));
		} catch (SchedulerException ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.PAUSE_TASK_FAIL);
		}
		SpringJob entity = springJobMapper.findByGroupCodeAndTaskClassTitle(groupCode, taskClassName);
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			try {
				entity.setStatus(SchedulerStatus.PAUSED.getStatus());
				springJobMapper.updateByPrimaryKey(entity);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		}
	}

	@Override
	@Transactional
	public void deleteTask(String taskClassName, String groupCode) {
		try {
			scheduler.pauseTrigger(TriggerKey.triggerKey(taskClassName, groupCode));
			scheduler.unscheduleJob(TriggerKey.triggerKey(taskClassName, groupCode));
			scheduler.deleteJob(JobKey.jobKey(taskClassName, groupCode));
		} catch (SchedulerException ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.DELETE_TASK_FAIL);
		}
		SpringJob entity = springJobMapper.findByGroupCodeAndTaskClassTitle(groupCode, taskClassName);
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			try {
				entity.setStatus(SchedulerStatus.DELETED.getStatus());
				springJobMapper.updateByPrimaryKey(entity);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		}
	}

	private SpringBaseJob getClass(String classname) throws Exception {
		Class<?> class1 = Class.forName(classname);
		return (SpringBaseJob) class1.newInstance();
	}

	@Override
	@Transactional
	public void resumeTask(String taskClassName, String taskGroupCode) {
		try {
			scheduler.resumeJob(JobKey.jobKey(taskClassName, taskGroupCode));
		} catch (SchedulerException ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.RESUME_TASK_FAIL);
		}
		SpringJob entity = springJobMapper.findByGroupCodeAndTaskClassTitle(taskGroupCode, taskClassName);
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			try {
				entity.setStatus(SchedulerStatus.RESUME.getStatus());
				springJobMapper.updateByPrimaryKey(entity);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		}
	}

}
