package io.github.springsongs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SpringJobDTO{
	 private String id;
     public String getId(){
         return  this.id;
     }
     public void setId(String id){
         this.id=id;
     }

     @NotBlank(message="请填写组别编码")
     @Size(max=45, min=1)
     private String groupCode;
     public String getGroupCode(){
         return  this.groupCode;
     }
     public void setGroupCode(String groupCode){
         this.groupCode=groupCode;
     }

     @NotBlank(message="请填写组别名称")
     @Size(max=45, min=1)
     private String groupTitle;
     public String getGroupTitle(){
         return  this.groupTitle;
     }
     public void setGroupTitle(String groupTitle){
         this.groupTitle=groupTitle;
     }

     @NotBlank(message="请填写任务名称")
     @Size(max=45, min=1)
     private String taskTitle;
     public String getTaskTitle(){
         return  this.taskTitle;
     }
     public void setTaskTitle(String taskTitle){
         this.taskTitle=taskTitle;
     }

     @NotBlank(message="请填写任务类")
     @Size(max=100, min=1)
     private String taskClassTitle;
     public String getTaskClassTitle(){
         return  this.taskClassTitle;
     }
     public void setTaskClassTitle(String taskClassTitle){
         this.taskClassTitle=taskClassTitle;
     }

     @NotBlank(message="请填写时间表达式")
     @Size(max=45, min=1)
     private String cronExpression;
     public String getCronExpression(){
         return  this.cronExpression;
     }
     public void setCronExpression(String cronExpression){
         this.cronExpression=cronExpression;
     }

     private int status;
     public int getStatus(){
         return  this.status;
     }
     public void setStatus(int status){
         this.status=status;
     }

     @NotBlank(message="请填写备注")
     @Size(max=500, min=1)
     private String remark;
     public String getRemark(){
         return  this.remark;
     }
     public void setRemark(String remark){
         this.remark=remark;
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
