package org.authority.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.authority.domain.LoginLog;
import org.authority.dto.UserLogonDto;
import org.authority.security.service.IBaseLoginLogService;
import org.authority.util.HttpUtils;
import org.authority.util.IpKit;
import org.authority.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.alibaba.fastjson.JSON;

@Configuration
@EnableWebSecurity
//@EnableWebFluxSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	// @Autowired
	// private AnthencationProder provider;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private IBaseLoginLogService baseLoginLogService;

//	@Autowired
//	private UrlAccessDecisionManager urlAccessDecisionManager;
//
//	@Autowired
//	private UrlFilterInvocationSecurityMetadaSource urlFilterInvocationSecurityMetadaSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O o) {
				//TODO 在这里处理权限
				//o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadaSource);
				//o.setAccessDecisionManager(urlAccessDecisionManager);
				return o;
			}
		}).antMatchers("/BaseBuser/Invalidate", "/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html**",
				"/css/**", "/img/**", "/js/**").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/Login").permitAll().failureHandler(loginFailureHandler())
				.successHandler(loginSuccessHandler()).and().logout().logoutUrl("/Logout")
				.logoutSuccessUrl("/BaseBuser/Invalidate").permitAll().logoutSuccessHandler(logoutSuccessHandler())
				.deleteCookies("JSESSIONID", "SESSION").and().sessionManagement()
				.invalidSessionUrl("/BaseBuser/Invalidate").sessionFixation().changeSessionId().maximumSessions(1)
				.maxSessionsPreventsLogin(false)// false之后登录踢掉之前登录,true则不允许之后登录
				.expiredSessionStrategy(sessionInformationExpiredStrategy());// 登录被踢掉时的自定义操作;
		http.headers().frameOptions().disable();
		http.csrf().disable().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	}

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() { // 登出处理
		return new LogoutSuccessHandler() {
			@Override
			public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
					Authentication authentication) throws IOException, ServletException {
				logger.info("loginSuccessHandler");
				logger.info("## 请求时间：" + new Date() + "");
				long startTime = System.currentTimeMillis();
				try {
					MyUserPrincipal userDetails = (MyUserPrincipal) authentication.getPrincipal();

					// 记录登录信息
					LoginLog entity = new LoginLog();
					entity.setId(UUID.randomUUID().toString());
					entity.setDescription("注销");
					entity.setCreatedUserId(userDetails.getBaseEntityUser().getId());
					entity.setCreatedBy(userDetails.getBaseEntityUser().getUsername());
					entity.setCreatedIp(IpKit.getRealIp(httpServletRequest));
					entity.setCreatedOn(new Date());
					baseLoginLogService.insert(entity);
					logger.info("USER : " + userDetails.getUsername() + " LOGOUT SUCCESS !  ");
				} catch (Exception e) {
					logger.info("LOGOUT EXCEPTION , e : " + e.getMessage());
				}
				long endTime = System.currentTimeMillis();
				if (HttpUtils.isAjaxRequest(httpServletRequest)) {
					httpServletResponse.setContentType("application/json;charset=utf-8");
					PrintWriter out = null;
					try {
						out = httpServletResponse.getWriter();
						Map<String, Object> map = new HashMap();
						map.put("code", "200");
						map.put("msg", "注销成功");

						out.append(JSON.toJSONString(new R().ok(map)));
					} catch (IOException e) {
						logger.error(e.getMessage());
					} finally {
						if (out != null) {
							out.flush();
							out.close();
						}
					}
				}
				logger.info("## 请求时间：" + (endTime - startTime) + "");
				if (!HttpUtils.isAjaxRequest(httpServletRequest)) {
					httpServletResponse.sendRedirect("/login");
				}
			}
		};
	}

	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { // 登入处理
		return new SavedRequestAwareAuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				MyUserPrincipal userDetails = (MyUserPrincipal) authentication.getPrincipal();

				logger.info("loginSuccessHandler");
				logger.info("## " + userDetails.getUsername() + "");
				logger.info("## 请求时间：" + new Date() + "");
				logger.info("USER : " + userDetails.getUsername() + " LOGIN SUCCESS !  ");
				logger.info("ROLE :" + userDetails.getAuthorities());
				long startTime = System.currentTimeMillis();

				// 记录登录信息
				LoginLog entity = new LoginLog();
				entity.setId(UUID.randomUUID().toString());
				entity.setDescription("登录");
				entity.setCreatedUserId(userDetails.getBaseEntityUser().getId());
				entity.setCreatedBy(userDetails.getBaseEntityUser().getUsername());
				entity.setCreatedIp(IpKit.getRealIp(request));
				entity.setCreatedOn(new Date());
				baseLoginLogService.insert(entity);

				if (HttpUtils.isAjaxRequest(request)) {
					response.setContentType("application/json;charset=utf-8");
					PrintWriter out = null;
					try {
						out = response.getWriter();
						UserLogonDto userLogonDto = new UserLogonDto();
						userLogonDto.setId(userDetails.getBaseEntityUser().getId());
						userLogonDto.setUserName(userDetails.getBaseEntityUser().getUsername());
						Map<String, Object> map = new HashMap();
						map.put("code", "200");
						map.put("msg", "登录成功");
						map.put("data", userLogonDto);
						out.append(JSON.toJSONString(new R().ok(map)));
					} catch (IOException e) {
						logger.error(e.getMessage());
					} finally {
						if (out != null) {
							out.flush();
							out.close();
						}
					}
				}
				long endTime = System.currentTimeMillis();
				logger.info("## 请求时间：" + (endTime - startTime) + "");
				super.onAuthenticationSuccess(request, response, authentication);
			}
		};
	}

	@Bean
	public AuthenticationFailureHandler loginFailureHandler() {
		return new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				logger.info("loginFailureHandler");
				logger.info("## 请求时间：" + new Date() + "");
				long startTime = System.currentTimeMillis();
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				R r = new R();
				if (exception instanceof UsernameNotFoundException) {
					logger.error(exception.getMessage());
					r.put("code", 500);
					r.put("msg", "用户名或者密码不对，登录失败!");
				} else if (exception instanceof BadCredentialsException) {
					logger.error(exception.getMessage());
					r.put("code", 500);
					r.put("msg", "密码不对，登录失败!");
				} else if (exception instanceof LockedException) {
					logger.error(exception.getMessage());
					r.put("code", 500);
					r.put("msg", "账户被锁定，登录失败，请联系管理员!");
				} else if (exception instanceof AccountExpiredException) {
					logger.error(exception.getMessage());
					r.put("code", 500);
					r.put("msg", "账户已过期，登录失败，请联系管理员!");
				} else if (exception instanceof DisabledException) {
					logger.error(exception.getMessage());
					r.put("code", 500);
					r.put("msg", "账户被禁用，登录失败，请联系管理员!");
				} else if (exception instanceof CredentialsExpiredException) {
					logger.error(exception.getMessage());
					r.put("code", 500);
					r.put("msg", "密码凭证已过期，登录失败!");
				} else {
					logger.error("登录失败!");
					r.put("code", 500);
					r.put("msg", "登录失败!");
				}
				out.write(JSON.toJSONString(r));
				out.flush();
				out.close();
				long endTime = System.currentTimeMillis();
				logger.info("## 请求时间：" + (endTime - startTime) + "");
			}
		};
	}

	@Bean
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
		return new SessionInformationExpiredStrategy() {
			@Override
			public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
				event.getResponse().setContentType("application/json;charset=UTF-8");
				event.getResponse().getWriter().write("并发登录!");
			}
		};
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new AccessDeniedHandler() {
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				response.setCharacterEncoding("UTF-8");
				if (HttpUtils.isAjaxRequest(request)) {
					response.setContentType("application/json;charset=utf-8");
					PrintWriter pw = response.getWriter();
					R r = new R();
					r.put("code", HttpServletResponse.SC_FORBIDDEN);
					r.put("msg", "权限不足，请联系管理员");
					pw.write(JSON.toJSONString(r));
					pw.flush();
					pw.close();
				} else {
					response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
				}
			}
		};
	}

	// String idForEncode = "bcrypt";
	// Map encoders = new HashMap<>();
	// encoders.put(idForEncode, new BCryptPasswordEncoder());
	// encoders.put("noop", NoOpPasswordEncoder.getInstance());
	// encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
	// encoders.put("scrypt", new SCryptPasswordEncoder());
	// encoders.put("sha256", new StandardPasswordEncoder());
	//
	// PasswordEncoder passwordEncoder =
	// new DelegatingPasswordEncoder(idForEncode, encoders);
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		// auth.authenticationProvider(provider);
		logger.info(passwordEncoder().encode("qweasd"));
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

}
