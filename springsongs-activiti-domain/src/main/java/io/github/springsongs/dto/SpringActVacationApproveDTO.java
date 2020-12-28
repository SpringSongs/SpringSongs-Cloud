package io.github.springsongs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SpringActVacationApproveDTO {
	 private String id;
     public String getId(){
         return  this.id;
     }
     public void setId(String id){
         this.id=id;
     }

     @NotBlank(message="请填写请假单主键")
     @Size(max=36, min=1)
     private String vacationId;
     public String getVacationId(){
         return  this.vacationId;
     }
     public void setVacationId(String vacationId){
         this.vacationId=vacationId;
     }

     @Size(max=45, min=0)
     private String processInstanceId;
     public String getProcessInstanceId(){
         return  this.processInstanceId;
     }
     public void setProcessInstanceId(String processInstanceId){
         this.processInstanceId=processInstanceId;
     }

     @Size(max=45, min=0)
     private String taskId;
     public String getTaskId(){
         return  this.taskId;
     }
     public void setTaskId(String taskId){
         this.taskId=taskId;
     }

     @NotBlank(message="请填写审批人用户id")
     @Size(max=36, min=1)
     private String userId;
     public String getUserId(){
         return  this.userId;
     }
     public void setUserId(String userId){
         this.userId=userId;
     }

     private short result;
     public short getResult(){
         return  this.result;
     }
     public void setResult(short result){
         this.result=result;
     }

     @NotBlank(message="请填写备注")
     @Size(max=200, min=1)
     private String remark;
     public String getRemark(){
         return  this.remark;
     }
     public void setRemark(String remark){
         this.remark=remark;
     }

     @NotBlank(message="请填写审批人姓名")
     @Size(max=45, min=1)
     private String trueName;
     public String getTrueName(){
         return  this.trueName;
     }
     public void setTrueName(String trueName){
         this.trueName=trueName;
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
