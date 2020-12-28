import request from '@/utils/request'
import qs from 'qs'
export function search(page, size, category) {
  // var data = qs.stringify(data)
  return request({
    url: '/ACTIVITI-SERVICE/SpringProcess/ListByPage?page=' + page + '&size=' + size + '&category=' + category,
    method: 'get'
  })
}

export function listUserTask(data) {
  return request({
    url: '/ACTIVITI-API/SpringActUseTask/ListUserTaskByProcDefKey?procDefKey=' + data,
    method: 'get'
  })
}

export function initSingleDefinition(data) {
  data = qs.stringify(data)
  return request({
    url: '/ACTIVITI-API/SpringActUseTask/InitSingleDefinition',
    method: 'post',
    data
  })
}

export function initAllDefinition(data) {
  data = qs.stringify(data)
  return request({
    url: '/ACTIVITI-API/SpringActUseTask/InitAllDefinition',
    method: 'post',
    data
  })
}

export function setUserToTask(data, procDefKey) {
  // data = qs.stringify(data)
  return request({
    url: '/ACTIVITI-API/SpringActUseTask/SetUserToTask?procDefKey=' + procDefKey,
    method: 'post',
    data
  })
}

export function listUserPage(page, size, data) {
  return request({
    url: '/SpringUser/ListByPage/?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function listRole(page, size, data) {
  return request({
    url: '/ACTIVITI-API/SpringRole/ListByPage?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}
