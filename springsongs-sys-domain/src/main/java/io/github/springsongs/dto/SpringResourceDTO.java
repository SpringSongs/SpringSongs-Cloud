package io.github.springsongs.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SpringResourceDTO{
	
	 private String id;
     public String getId(){
         return  this.id;
     }
     public void setId(String id){
         this.id=id;
     }

     @NotBlank(message="请填写编码")
     @Size(max=200, min=1)
     private String code;
     public String getCode(){
         return  this.code;
     }
     public void setCode(String code){
         this.code=code;
     }

     @NotBlank(message="请填写名称")
     @Size(max=45, min=1)
     private String title;
     public String getTitle(){
         return  this.title;
     }
     public void setTitle(String title){
         this.title=title;
     }

     private boolean menuFlag;
     public boolean getMenuFlag(){
         return  this.menuFlag;
     }
     public void setMenuFlag(boolean menuFlag){
         this.menuFlag=menuFlag;
     }

     @Size(max=45, min=0)
     private String vueIcon;
     public String getVueIcon(){
         return  this.vueIcon;
     }
     public void setVueIcon(String vueIcon){
         this.vueIcon=vueIcon;
     }

     @Size(max=200, min=0)
     private String vueUrl;
     public String getVueUrl(){
         return  this.vueUrl;
     }
     public void setVueUrl(String vueUrl){
         this.vueUrl=vueUrl;
     }

     @Size(max=45, min=0)
     private String angularIcon;
     public String getAngularIcon(){
         return  this.angularIcon;
     }
     public void setAngularIcon(String angularIcon){
         this.angularIcon=angularIcon;
     }

     @Size(max=255, min=0)
     private String angularUrl;
     public String getAngularUrl(){
         return  this.angularUrl;
     }
     public void setAngularUrl(String angularUrl){
         this.angularUrl=angularUrl;
     }

     @Size(max=36, min=0)
     private String parentId;
     public String getParentId(){
         return  this.parentId;
     }
     public void setParentId(String parentId){
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

     private int sortCode;
     public int getSortCode(){
         return  this.sortCode;
     }
     public void setSortCode(int sortCode){
         this.sortCode=sortCode;
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

     private boolean showStatus;
     public boolean getShowStatus(){
         return  this.showStatus;
     }
     public void setShowStatus(boolean showStatus){
         this.showStatus=showStatus;
     }

     @NotBlank(message="请填写系统主键")
     @Size(max=36, min=1)
     private String systemId;
     public String getSystemId(){
         return  this.systemId;
     }
     public void setSystemId(String systemId){
         this.systemId=systemId;
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

	
	private String text;
	
	public String getText() {
		return this.getTitle();
	}

	private List<SpringResourceDTO> children=new ArrayList<>();

	public List<SpringResourceDTO> getChildren() {
		return children;
	}

	public void setChildren(List<SpringResourceDTO> children) {
		this.children = children;
	}


}
