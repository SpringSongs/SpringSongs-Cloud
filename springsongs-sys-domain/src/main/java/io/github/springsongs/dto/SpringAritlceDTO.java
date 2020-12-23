package io.github.springsongs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SpringAritlceDTO {
	private String id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Size(max = 45, min = 0)
	private String categoryId;

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Size(max = 45, min = 0)
	private String categoryTitle;

	public String getCategoryTitle() {
		return this.categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	@Size(max = 45, min = 0)
	private String color;

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Size(max = 45, min = 0)
	private String tag;

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Size(max = 45, min = 0)
	private String keyword;

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@NotBlank(message = "请填写标题")
	@Size(max = 200, min = 1)
	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Size(max = 500, min = 0)
	private String summary;

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@NotBlank(message = "请填写内容")
	private String contents;

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Size(max = 45, min = 0)
	private String authorId;

	public String getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	@Size(max = 45, min = 0)
	private String author;

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Size(max = 45, min = 0)
	private String authorUrl;

	public String getAuthorUrl() {
		return this.authorUrl;
	}

	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}

	private boolean status;

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Size(max = 45, min = 0)
	private String link;

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	private int readCount;

	public int getReadCount() {
		return this.readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	private int likeCount;

	public int getLikeCount() {
		return this.likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	private int collectCount;

	public int getCollectCount() {
		return this.collectCount;
	}

	public void setCollectCount(int collectCount) {
		this.collectCount = collectCount;
	}

	private int shareCount;

	public int getShareCount() {
		return this.shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	private boolean topStatus;

	public boolean getTopStatus() {
		return this.topStatus;
	}

	public void setTopStatus(boolean topStatus) {
		this.topStatus = topStatus;
	}

	private boolean hotStatus;

	public boolean getHotStatus() {
		return this.hotStatus;
	}

	public void setHotStatus(boolean hotStatus) {
		this.hotStatus = hotStatus;
	}

	private boolean featured;

	public boolean getFeatured() {
		return this.featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	private int commentCount;

	public int getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	private int sortOrder;

	public int getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Size(max = 45, min = 0)
	private String comeFrom;

	public String getComeFrom() {
		return this.comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	@Size(max = 2000, min = 0)
	private String comeFromLink;

	public String getComeFromLink() {
		return this.comeFromLink;
	}

	public void setComeFromLink(String comeFromLink) {
		this.comeFromLink = comeFromLink;
	}

	private boolean deletedStatus;

	public boolean getDeletedStatus() {
		return this.deletedStatus;
	}

	public void setDeletedStatus(boolean deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	@Size(max = 36, min = 0)
	private String createdUserId;

	public String getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	@Size(max = 36, min = 0)
	private String createdBy;

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	private java.util.Date createdOn;

	public java.util.Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(java.util.Date createdOn) {
		this.createdOn = createdOn;
	}

	@Size(max = 45, min = 0)
	private String createdIp;

	public String getCreatedIp() {
		return this.createdIp;
	}

	public void setCreatedIp(String createdIp) {
		this.createdIp = createdIp;
	}

	@Size(max = 36, min = 0)
	private String updatedUserId;

	public String getUpdatedUserId() {
		return this.updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	@Size(max = 45, min = 0)
	private String updatedBy;

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	private java.util.Date updatedOn;

	public java.util.Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(java.util.Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Size(max = 45, min = 0)
	private String updatedIp;

	public String getUpdatedIp() {
		return this.updatedIp;
	}

	public void setUpdatedIp(String updatedIp) {
		this.updatedIp = updatedIp;
	}

	private int version;

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
