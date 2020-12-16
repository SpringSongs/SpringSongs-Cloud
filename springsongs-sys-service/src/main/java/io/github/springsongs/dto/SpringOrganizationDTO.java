package io.github.springsongs.dto;

import java.util.ArrayList;
import java.util.List;

import io.github.springsongs.domain.SpringOrganization;

public class SpringOrganizationDTO extends SpringOrganization{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4354528935779911864L;
	

	private String text;
	
	public String getText() {
		return this.getTitle();
	}



	private List<SpringOrganizationDTO> children=new ArrayList<>();

	public List<SpringOrganizationDTO> getChildren() {
		return children;
	}

	public void setChildren(List<SpringOrganizationDTO> children) {
		this.children = children;
	}
	
	
	
}
