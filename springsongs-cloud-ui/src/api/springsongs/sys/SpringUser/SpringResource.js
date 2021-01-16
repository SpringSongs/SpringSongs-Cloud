import request from '@/utils/request'
import qs from 'qs'
export function search(page, size, data) {
  return request({
    url: '/SYS-API/SpringResource/ListByPage?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function get(id) {
  return request({
    url: '/SYS-API/SpringResource/Detail?id=' + id,
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: '/SYS-API/SpringResource/Create',
    method: 'post',
    data
  })
}

export function edit(data) {
  return request({
    url: '/SYS-API/SpringResource/Edit/',
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
    url: '/SYS-API/SpringResource/SetDeleted',
    method: 'post',
    data
  })
}

export function listAllSystem() {
  return request({
    url: '/SYS-API/SpringSystem/ListAll',
    method: 'get'
  })
}

export function ListAllToTree(systemId) {
  return request({
    url: '/SYS-API/SpringResource/ListAllToTree?systemId=' + systemId,
    method: 'get'
  })
}
