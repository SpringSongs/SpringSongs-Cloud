import request from '@/utils/request'
import qs from 'qs'
export function search(data) {
  return request({
    url: '/SYS-API/SpringContact/ListByPage',
    method: 'post',
    data
  })
}

export function get(id) {
  return request({
    url: '/SYS-API/SpringContact/Detail?id=' + id,
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: '/SYS-API/SpringContact/Create',
    method: 'post',
    data
  })
}

export function edit(data) {
  return request({
    url: '/SYS-API/SpringContact/Edit/',
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
    url: '/SYS-API/SpringContact/SetDeleted',
    method: 'post',
    data
  })
}
