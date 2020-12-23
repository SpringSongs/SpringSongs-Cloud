package io.github.springsongs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SpringDistrictDTO{
	private long id;
    public long getId(){
        return  this.id;
    }
    public void setId(long id){
        this.id=id;
    }

    @NotBlank(message="请填写名称")
    @Size(max=255, min=1)
    private String name;
    public String getName(){
        return  this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    private int level;
    public int getLevel(){
        return  this.level;
    }
    public void setLevel(int level){
        this.level=level;
    }

    private long parentId;
    public long getParentId(){
        return  this.parentId;
    }
    public void setParentId(long parentId){
        this.parentId=parentId;
    }

    @Size(max=45, min=0)
    private String parentName;
    public String getParentName(){
        return  this.parentName;
    }
    public void setParentName(String parentName){
        this.parentName=parentName;
    }

    private short sortOrder;
    public short getSortOrder(){
        return  this.sortOrder;
    }
    public void setSortOrder(short sortOrder){
        this.sortOrder=sortOrder;
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
}
