package io.github.springsongs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SpringUserDTO{
	
	private String id;
    public String getId(){
        return  this.id;
    }
    public void setId(String id){
        this.id=id;
    }

    @NotBlank(message="请填写邮箱")
    @Size(max=45, min=1)
    private String email;
    public String getEmail(){
        return  this.email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    @NotBlank(message="请填写手机")
    @Size(max=45, min=1)
    private String mobile;
    public String getMobile(){
        return  this.mobile;
    }
    public void setMobile(String mobile){
        this.mobile=mobile;
    }

    @Size(max=45, min=0)
    private String portrait;
    public String getPortrait(){
        return  this.portrait;
    }
    public void setPortrait(String portrait){
        this.portrait=portrait;
    }

    @NotBlank(message="请填写用户名")
    @Size(max=45, min=1)
    private String userName;
    public String getUserName(){
        return  this.userName;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }

    @NotBlank(message="请填写真实姓名")
    @Size(max=45, min=1)
    private String trueName;
    public String getTrueName(){
        return  this.trueName;
    }
    public void setTrueName(String trueName){
        this.trueName=trueName;
    }

    @Size(max=45, min=0)
    private String resume;
    public String getResume(){
        return  this.resume;
    }
    public void setResume(String resume){
        this.resume=resume;
    }

    @Size(max=36, min=0)
    private String organizationId;
    public String getOrganizationId(){
        return  this.organizationId;
    }
    public void setOrganizationId(String organizationId){
        this.organizationId=organizationId;
    }

    @Size(max=45, min=0)
    private String organizationName;
    public String getOrganizationName(){
        return  this.organizationName;
    }
    public void setOrganizationName(String organizationName){
        this.organizationName=organizationName;
    }

    @Size(max=45, min=0)
    private String titleId;
    public String getTitleId(){
        return  this.titleId;
    }
    public void setTitleId(String titleId){
        this.titleId=titleId;
    }

    @Size(max=45, min=0)
    private String titleName;
    public String getTitleName(){
        return  this.titleName;
    }
    public void setTitleName(String titleName){
        this.titleName=titleName;
    }

    private int loginCount;
    public int getLoginCount(){
        return  this.loginCount;
    }
    public void setLoginCount(int loginCount){
        this.loginCount=loginCount;
    }

    private java.util.Date registerTime;
    public java.util.Date getRegisterTime(){
        return  this.registerTime;
    }
    public void setRegisterTime(java.util.Date registerTime){
        this.registerTime=registerTime;
    }

    @Size(max=45, min=0)
    private String regsiterIp;
    public String getRegsiterIp(){
        return  this.regsiterIp;
    }
    public void setRegsiterIp(String regsiterIp){
        this.regsiterIp=regsiterIp;
    }

    private java.util.Date lastLoginTime;
    public java.util.Date getLastLoginTime(){
        return  this.lastLoginTime;
    }
    public void setLastLoginTime(java.util.Date lastLoginTime){
        this.lastLoginTime=lastLoginTime;
    }

    private boolean status;
    public boolean getStatus(){
        return  this.status;
    }
    public void setStatus(boolean status){
        this.status=status;
    }

    private boolean lockStatus;
    public boolean getLockStatus(){
        return  this.lockStatus;
    }
    public void setLockStatus(boolean lockStatus){
        this.lockStatus=lockStatus;
    }

    private int sortOrder;
    public int getSortOrder(){
        return  this.sortOrder;
    }
    public void setSortOrder(int sortOrder){
        this.sortOrder=sortOrder;
    }

    private boolean enableEdit;
    public boolean getEnableEdit(){
        return  this.enableEdit;
    }
    public void setEnableEdit(boolean enableEdit){
        this.enableEdit=enableEdit;
    }

    private boolean enableDelete;
    public boolean getEnableDelete(){
        return  this.enableDelete;
    }
    public void setEnableDelete(boolean enableDelete){
        this.enableDelete=enableDelete;
    }

    private boolean deletedStatus;
    public boolean getDeletedStatus(){
        return  this.deletedStatus;
    }
    public void setDeletedStatus(boolean deletedStatus){
        this.deletedStatus=deletedStatus;
    }

    @Size(max=36, min=0)
    private String createdUserId;
    public String getCreatedUserId(){
        return  this.createdUserId;
    }
    public void setCreatedUserId(String createdUserId){
        this.createdUserId=createdUserId;
    }

    @Size(max=36, min=0)
    private String createdBy;
    public String getCreatedBy(){
        return  this.createdBy;
    }
    public void setCreatedBy(String createdBy){
        this.createdBy=createdBy;
    }

    private java.util.Date createdOn;
    public java.util.Date getCreatedOn(){
        return  this.createdOn;
    }
    public void setCreatedOn(java.util.Date createdOn){
        this.createdOn=createdOn;
    }

    @Size(max=45, min=0)
    private String createdIp;
    public String getCreatedIp(){
        return  this.createdIp;
    }
    public void setCreatedIp(String createdIp){
        this.createdIp=createdIp;
    }

    @Size(max=36, min=0)
    private String updatedUserId;
    public String getUpdatedUserId(){
        return  this.updatedUserId;
    }
    public void setUpdatedUserId(String updatedUserId){
        this.updatedUserId=updatedUserId;
    }

    @Size(max=45, min=0)
    private String updatedBy;
    public String getUpdatedBy(){
        return  this.updatedBy;
    }
    public void setUpdatedBy(String updatedBy){
        this.updatedBy=updatedBy;
    }

    private java.util.Date updatedOn;
    public java.util.Date getUpdatedOn(){
        return  this.updatedOn;
    }
    public void setUpdatedOn(java.util.Date updatedOn){
        this.updatedOn=updatedOn;
    }

    @Size(max=45, min=0)
    private String updatedIp;
    public String getUpdatedIp(){
        return  this.updatedIp;
    }
    public void setUpdatedIp(String updatedIp){
        this.updatedIp=updatedIp;
    }
    
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
