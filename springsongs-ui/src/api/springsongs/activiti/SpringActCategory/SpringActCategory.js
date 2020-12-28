import request from '@/utils/request'
import qs from 'qs'
export function search(page, size, data) {
  return request({
    url: '/ACTIVITI-API/SpringActCategory/ListByPage?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function get(id) {
  return request({
    url: '/ACTIVITI-API/SpringActCategory/Detail?id=' + id,
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: '/ACTIVITI-API/SpringActCategory/Create',
    method: 'post',
    data
  })
}

export function edit(data) {
  return request({
    url: '/ACTIVITI-API/SpringActCategory/Edit/',
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
    url: '/ACTIVITI-API/SpringActCategory/SetDeleted',
    method: 'post',
    data
  })
}
