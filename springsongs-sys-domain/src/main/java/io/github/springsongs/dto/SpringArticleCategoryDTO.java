package io.github.springsongs.dto;

import java.util.ArrayList;
import java.util.List;

import io.github.springsongs.domain.SpringArticleCategory;

public class SpringArticleCategoryDTO extends SpringArticleCategory {
	

	private String text;
	
	public String getText() {
		return this.getTitle();
	}
	
	
	private List<SpringArticleCategoryDTO> children=new ArrayList<>();

	public List<SpringArticleCategoryDTO> getChildren() {
		return children;
	}

	public void setChildren(List<SpringArticleCategoryDTO> children) {
		this.children = children;
	}
	
}
