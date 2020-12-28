package io.github.springsongs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SpringActUseTaskDTO {
	 private String id;
     public String getId(){
         return  this.id;
     }
     public void setId(String id){
         this.id=id;
     }

     @NotBlank(message="请填写流程Key")
     @Size(max=200, min=1)
     private String procDefKey;
     public String getProcDefKey(){
         return  this.procDefKey;
     }
     public void setProcDefKey(String procDefKey){
         this.procDefKey=procDefKey;
     }

     @NotBlank(message="请填写流程名称")
     @Size(max=45, min=1)
     private String procDefName;
     public String getProcDefName(){
         return  this.procDefName;
     }
     public void setProcDefName(String procDefName){
         this.procDefName=procDefName;
     }

     @Size(max=45, min=0)
     private String taskDefKey;
     public String getTaskDefKey(){
         return  this.taskDefKey;
     }
     public void setTaskDefKey(String taskDefKey){
         this.taskDefKey=taskDefKey;
     }

     @Size(max=45, min=0)
     private String taskName;
     public String getTaskName(){
         return  this.taskName;
     }
     public void setTaskName(String taskName){
         this.taskName=taskName;
     }

     @Size(max=45, min=0)
     private String taskType;
     public String getTaskType(){
         return  this.taskType;
     }
     public void setTaskType(String taskType){
         this.taskType=taskType;
     }

     @Size(max=45, min=0)
     private String candidateName;
     public String getCandidateName(){
         return  this.candidateName;
     }
     public void setCandidateName(String candidateName){
         this.candidateName=candidateName;
     }

     @Size(max=45, min=0)
     private String candidateIds;
     public String getCandidateIds(){
         return  this.candidateIds;
     }
     public void setCandidateIds(String candidateIds){
         this.candidateIds=candidateIds;
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
