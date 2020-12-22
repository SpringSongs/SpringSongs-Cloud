package io.github.springsongs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
public class LoginRequest {
	@NotBlank(message = "请填写帐号")
	@Pattern(regexp = "^[A-Za-z0-9_]+$", message = "帐号请填写数字与大小字母组合")
	@Size(max = 45, min = 1, message = "长度在最小1至45之间")
	private String username;
	@NotBlank(message = "请填写密码")
	@Size(max = 45, min = 1, message = "长度在最小1至45之间")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
