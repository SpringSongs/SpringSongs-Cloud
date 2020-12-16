package io.github.springsongs.dto;

import io.github.springsongs.domain.SpringUser;

public class SpringUserDTO extends SpringUser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1009449152333909065L;

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
