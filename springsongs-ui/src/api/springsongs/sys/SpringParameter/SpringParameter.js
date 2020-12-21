import request from '@/utils/request'
import qs from 'qs'
export function search(page, size, data) {
  return request({
    url: '/SYS-API/SpringParameter/ListByPage?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function get(id) {
  return request({
    url: '/SYS-API/SpringParameter/Detail?id=' + id,
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: '/SYS-API/SpringParameter/Create',
    method: 'post',
    data
  })
}

export function edit(data) {
  return request({
    url: '/SYS-API/SpringParameter/Edit/',
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
    url: '/SYS-API/SpringParameter/SetDeleted',
    method: 'post',
    data
  })
}
