package io.github.springsongs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringLoginLog;
import io.github.springsongs.dto.SpringLoginLogDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringLoginLogMapper;
import io.github.springsongs.service.ISpringLoginLogService;
import io.github.springsongs.util.AuthenUtil;
import io.github.springsongs.utils.Constant;

@Service
public class SpringLoginLogServiceImpl implements ISpringLoginLogService {
	
	static Logger logger = LoggerFactory.getLogger(SpringDictionaryServiceImpl.class);
	
	
	@Autowired
	private SpringLoginLogMapper springLoginLogMapper;
	
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
		springLoginLogMapper.deleteByPrimaryKey(id);

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
	public void insert(SpringLoginLogDTO record) {
		record.setId(UUID.randomUUID().toString());
		record.setCreatedUserId(authenUtil.getUser().getId());
		record.setCreatedBy(authenUtil.getUser().getUserName());
		record.setCreatedIp(authenUtil.getUser().getIp());
		record.setCreatedOn(new Date());
		SpringLoginLog springLoginLog = new SpringLoginLog();
		BeanUtils.copyProperties(record, springLoginLog);
		springLoginLogMapper.insert(springLoginLog);

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
	public SpringLoginLogDTO selectByPrimaryKey(String id) {
		SpringLoginLog springLoginLog = springLoginLogMapper.selectByPrimaryKey(id);
		SpringLoginLogDTO springLoginLogDTO = new SpringLoginLogDTO();
		BeanUtils.copyProperties(springLoginLog, springLoginLogDTO);
		return springLoginLogDTO;
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
	public void updateByPrimaryKey(SpringLoginLogDTO record) {
		SpringLoginLog springLoginLog = new SpringLoginLog();
		BeanUtils.copyProperties(record, springLoginLog);
		springLoginLogMapper.updateByPrimaryKey(springLoginLog);
	}

	/**
	 *
	 * 分页查询
	 * 
	 * @param record
	 * @return Page<BaseLoginLogEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringLoginLogDTO> getAllRecordByPage(SpringLoginLogDTO springLoginLogQuery,int page,int size) {
		
		if (size>Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		
		PageHelper.startPage(page, size);
		
		Page<SpringLoginLog> springLoginLogs = springLoginLogMapper.listByPage(springLoginLogQuery);
		List<SpringLoginLogDTO> springLoginLogDTOs = new ArrayList<>();
		springLoginLogs.stream().forEach(springLoginLog -> {
			SpringLoginLogDTO springLoginLogDTO = new SpringLoginLogDTO();
			BeanUtils.copyProperties(springLoginLog, springLoginLogDTO);
			springLoginLogDTOs.add(springLoginLogDTO);
		});
		PageInfo springLoginLogsPageInfo = new PageInfo<>(springLoginLogs);
		springLoginLogsPageInfo.setList(springLoginLogDTOs);
		return springLoginLogsPageInfo;
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
	public void delete(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > Constant.MAX_ITEM_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		try {
			springLoginLogMapper.delete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
		
	}
}
