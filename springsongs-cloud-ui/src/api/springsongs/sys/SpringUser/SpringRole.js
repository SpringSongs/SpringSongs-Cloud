import request from '@/utils/request'
import qs from 'qs'
export function search(page, size, data) {
  return request({
    url: '/SYS-API/SpringRole/ListByPage?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function get(id) {
  return request({
    url: '/SYS-API/SpringRole/Detail?id=' + id,
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: '/SYS-API/SpringRole/Create',
    method: 'post',
    data
  })
}

export function edit(data) {
  return request({
    url: '/SYS-API/SpringRole/Edit',
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
    url: '/SYS-API/SpringUser/SetDeleted',
    method: 'post',
    data
  })
}

export function listSpringResources(page, size, data) {
  return request({
    url: '/SYS-API/SpringResource/ListByPage?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function listAuthority(roleId) {
  return request({
    url: '/SYS-API/SpringRole/ListAuthority/' + roleId,
    method: 'post'
  })
}

export function setAuthority(roleId, data) {
  data = qs.stringify({
    'moduleIds': data
  }, {
    indices: false
  })
  return request({
    url: '/SYS-API/SpringRole/SetAuthority/' + roleId,
    method: 'post',
    data
  })
}

export function listUserPage(page, size, data) {
  return request({
    url: '/SYS-API/SpringUser/ListByPage/?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function ListUsersByRoleId(roleId, page, size, data) {
  return request({
    url: '/SYS-API/SpringUser/ListByRoleId/' + roleId + '?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function setUsersToRole(roleId, data) {
  data = qs.stringify({
    'ids': data
  }, {
    indices: false
  })
  return request({
    url: '/SYS-API/SpringRole/SetUsers/' + roleId,
    method: 'post',
    data
  })
}
