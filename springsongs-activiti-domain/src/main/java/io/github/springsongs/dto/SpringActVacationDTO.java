package io.github.springsongs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SpringActVacationDTO {
	 private String id;
     public String getId(){
         return  this.id;
     }
     public void setId(String id){
         this.id=id;
     }

     @Size(max=45, min=0)
     private String processInstanceId;
     public String getProcessInstanceId(){
         return  this.processInstanceId;
     }
     public void setProcessInstanceId(String processInstanceId){
         this.processInstanceId=processInstanceId;
     }

     @Size(max=200, min=0)
     private String procDefKey;
     public String getProcDefKey(){
         return  this.procDefKey;
     }
     public void setProcDefKey(String procDefKey){
         this.procDefKey=procDefKey;
     }

     @Size(max=36, min=0)
     private String userId;
     public String getUserId(){
         return  this.userId;
     }
     public void setUserId(String userId){
         this.userId=userId;
     }

     @Size(max=45, min=0)
     private String trueName;
     public String getTrueName(){
         return  this.trueName;
     }
     public void setTrueName(String trueName){
         this.trueName=trueName;
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

     @NotBlank(message="请填写假期类型")
     @Size(max=45, min=1)
     private String vacationType;
     public String getVacationType(){
         return  this.vacationType;
     }
     public void setVacationType(String vacationType){
         this.vacationType=vacationType;
     }

     @NotBlank(message="请填写请假申请原因")
     @Size(max=45, min=1)
     private String reason;
     public String getReason(){
         return  this.reason;
     }
     public void setReason(String reason){
         this.reason=reason;
     }

     private int time;
     public int getTime(){
         return  this.time;
     }
     public void setTime(int time){
         this.time=time;
     }

     private short processStatus;
     public short getProcessStatus(){
         return  this.processStatus;
     }
     public void setProcessStatus(short processStatus){
         this.processStatus=processStatus;
     }

     private java.util.Date submitTime;
     public java.util.Date getSubmitTime(){
         return  this.submitTime;
     }
     public void setSubmitTime(java.util.Date submitTime){
         this.submitTime=submitTime;
     }

     @NotBlank(message="请填写开始时间")
     @Size(max=45, min=1)
     private String startTime;
     public String getStartTime(){
         return  this.startTime;
     }
     public void setStartTime(String startTime){
         this.startTime=startTime;
     }

     @NotBlank(message="请填写结束时间 ")
     @Size(max=45, min=1)
     private String endTime;
     public String getEndTime(){
         return  this.endTime;
     }
     public void setEndTime(String endTime){
         this.endTime=endTime;
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
