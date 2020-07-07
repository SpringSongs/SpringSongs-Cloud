package org.authority.aspect;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

@Aspect
@Component
public class LogAspectj {
	private static final Logger logger = LoggerFactory.getLogger(LogAspectj.class);

	@Around("within(@* org.authority.*controller.*) && execution(public * *(..))")
	public Object log(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = null;
		for (int i = 0; i < pjp.getArgs().length; i++) {
			if (pjp.getArgs()[i] instanceof HttpServletRequest) {
				request = (HttpServletRequest) pjp.getArgs()[i];
				logger.info(request.getRequestURL().toString());
			}
			if (!(pjp.getArgs()[i] instanceof HttpServletRequest) && !(pjp.getArgs()[i] instanceof ModelMap)
					&& !(pjp.getArgs()[i] instanceof BindingResult) && !(pjp.getArgs()[i] instanceof MultipartFile)
					&& !(pjp.getArgs()[i] instanceof HttpServletResponse)) {
				logger.info("## {}", JSON.toJSONString(pjp.getArgs()[i]));
			}
		}
		logger.info("## 请求时间：{}", new Date());
		long startTime = System.currentTimeMillis();
		Object object = pjp.proceed();
		long endTime = System.currentTimeMillis();
		logger.info("## 请求耗时：{} 毫秒", (endTime - startTime));
		return object;
	}
}
