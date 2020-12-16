package io.github.springsongs.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * 忽略静态文件
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/SpringUser/Invalidate", "/v2/api-docs", "/swagger-resources/**", "/css/**",
				"/img/**", "/js/**", "/**.ico", "/webjars/**", "/", "/jquery-easyui/**", "/error", "/bootstrap/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/Login").loginProcessingUrl("/Login").permitAll().and().logout()
				.logoutUrl("/Logout").logoutSuccessUrl("/Login").permitAll().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.headers().frameOptions().disable();
		http.csrf().disable();
		http.cors();
	}
}
