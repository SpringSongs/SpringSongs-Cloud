import request from '@/utils/request'
import qs from 'qs'
export function search(page, size, data) {
  return request({
    url: '/JOB-API/SpringJob/ListByPage?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function get(id) {
  return request({
    url: '/JOB-API/SpringJob/Detail/' + id,
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: '/JOB-API/SpringJob/AddTask',
    method: 'post',
    data
  })
}

export function edit(data) {
  return request({
    url: '/JOB-API/SpringJob/UpdateTask',
    method: 'put',
    data
  })
}

export function batchDelete(data) {
  data = qs.stringify({
    'ids': data
  }, {
    indices: false
  })
  return request({
    url: '/JOB-API/SpringJob/SetDeleted',
    method: 'post',
    data
  })
}

export function listAllSpringJobGroupCategory() {
  return request({
    url: '/JOB-API/SpringJobGroup/ListAll',
    method: 'get'
  })
}

export function pause(data) {
  data = qs.stringify(data)
  return request({
    url: '/JOB-API/SpringJob/PauseTask',
    method: 'put',
    data
  })
}

export function resume(data) {
  data = qs.stringify(data)
  return request({
    url: '/JOB-API/SpringJob/ResumeTask',
    method: 'put',
    data
  })
}

export function deleteTask(data) {
  data = qs.stringify(data)
  return request({
    url: '/JOB-API/SpringJob/DeleteTask',
    method: 'post',
    data
  })
}
