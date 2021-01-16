import request from '@/utils/request'
import qs from 'qs'
export function search(page, size, data) {
  return request({
    url: '/JOB-API/SpringJobGroup/ListByPage?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function get(id) {
  return request({
    url: '/JOB-API/SpringJobGroup/Detail?id=' + id,
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: '/JOB-API/SpringJobGroup/Create',
    method: 'post',
    data
  })
}

export function edit(data) {
  return request({
    url: '/JOB-API/SpringJobGroup/Edit',
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
    url: '/JOB-API/SpringJobGroup/SetDeleted',
    method: 'post',
    data
  })
}
