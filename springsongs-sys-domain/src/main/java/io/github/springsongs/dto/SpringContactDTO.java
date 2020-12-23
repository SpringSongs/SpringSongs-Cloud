package io.github.springsongs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SpringContactDTO{
	private String id;
    public String getId(){
        return  this.id;
    }
    public void setId(String id){
        this.id=id;
    }

    @NotBlank(message="请填写公司")
    @Size(max=45, min=1)
    private String company;
    public String getCompany(){
        return  this.company;
    }
    public void setCompany(String company){
        this.company=company;
    }

    @NotBlank(message="请填写职称")
    @Size(max=45, min=1)
    private String title;
    public String getTitle(){
        return  this.title;
    }
    public void setTitle(String title){
        this.title=title;
    }

    @NotBlank(message="请填写名称")
    @Size(max=45, min=1)
    private String username;
    public String getUsername(){
        return  this.username;
    }
    public void setUsername(String username){
        this.username=username;
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

    @Size(max=45, min=0)
    private String web;
    public String getWeb(){
        return  this.web;
    }
    public void setWeb(String web){
        this.web=web;
    }

    @Size(max=45, min=0)
    private String fax;
    public String getFax(){
        return  this.fax;
    }
    public void setFax(String fax){
        this.fax=fax;
    }

    @Size(max=45, min=0)
    private String qq;
    public String getQq(){
        return  this.qq;
    }
    public void setQq(String qq){
        this.qq=qq;
    }

    @Size(max=45, min=0)
    private String webchat;
    public String getWebchat(){
        return  this.webchat;
    }
    public void setWebchat(String webchat){
        this.webchat=webchat;
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
    private String tel;
    public String getTel(){
        return  this.tel;
    }
    public void setTel(String tel){
        this.tel=tel;
    }

    private int sortCode;
    public int getSortCode(){
        return  this.sortCode;
    }
    public void setSortCode(int sortCode){
        this.sortCode=sortCode;
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
