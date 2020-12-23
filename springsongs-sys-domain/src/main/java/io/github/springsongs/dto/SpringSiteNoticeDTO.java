package io.github.springsongs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SpringSiteNoticeDTO{
	private String id;
    public String getId(){
        return  this.id;
    }
    public void setId(String id){
        this.id=id;
    }

    @NotBlank(message="请填写标题")
    @Size(max=45, min=1)
    private String title;
    public String getTitle(){
        return  this.title;
    }
    public void setTitle(String title){
        this.title=title;
    }

    @NotBlank(message="请填写内容")
    @Size(max=65535, min=1)
    private String content;
    public String getContent(){
        return  this.content;
    }
    public void setContent(String content){
        this.content=content;
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

    private int version;
    public int getVersion(){
        return  this.version;
    }
    public void setVersion(int version){
        this.version=version;
    }

}
