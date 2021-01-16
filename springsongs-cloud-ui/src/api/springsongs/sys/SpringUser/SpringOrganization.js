import request from '@/utils/request'
import qs from 'qs'
export function search(data) {
  return request({
    url: '/SYS-API/SpringOrganization/ListByPage',
    method: 'post',
    data
  })
}

export function listAllToTree() {
  return request({
    url: '/SYS-API/SpringOrganization/ListAllToTree',
    method: 'get'
  })
}

export function get(id) {
  return request({
    url: '/SYS-API/SpringOrganization/Detail?id=' + id,
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: '/SYS-API/SpringOrganization/Create',
    method: 'post',
    data
  })
}

export function edit(data) {
  return request({
    url: '/SYS-API/SpringOrganization/Edit',
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
    url: '/SYS-API/SpringOrganization/SetDeleted',
    method: 'post',
    data
  })
}

export function listOrganizationsByParent(parentId) {
  parentId = qs.stringify({
    'parentId': parentId
  })
  return request({
    url: '/SYS-API/SpringOrganization/listOrganizationsByParent',
    method: 'get',
    parentId
  })
}
