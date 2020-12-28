package io.github.springsongs.task;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.springsongs.domain.AbstractBaseJob;
import io.github.springsongs.dto.SpringJobHistoryDTO;
import io.github.springsongs.service.impl.SpringJobServiceImpl;

public class ShopJob extends AbstractBaseJob {
	static Logger logger = LoggerFactory.getLogger(SpringJobServiceImpl.class);

	@Override
	public void doExecute(JobExecutionContext context, SpringJobHistoryDTO history) {
		String result = "ShopJob 执行时间: " + new Date();
		logger.info(result);
	}

}