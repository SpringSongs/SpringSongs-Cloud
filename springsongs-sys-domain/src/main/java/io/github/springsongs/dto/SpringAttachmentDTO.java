package io.github.springsongs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SpringAttachmentDTO {
	private String id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotBlank(message = "请填写文件夾主键")
	@Size(max = 36, min = 1)
	private String folderId;

	public String getFolderId() {
		return this.folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	@NotBlank(message = "请填写文件夹名称")
	@Size(max = 45, min = 1)
	private String folderName;

	public String getFolderName() {
		return this.folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	@NotBlank(message = "请填写文件路径")
	@Size(max = 300, min = 1)
	private String path;

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Size(max = 200, min = 0)
	private String description;

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}
