import request from '@/utils/request'
import qs from 'qs'
export function search(page, size, data) {
  return request({
    url: '/SYS-API/SpringUser/ListByPage?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function get(id) {
  return request({
    url: '/SYS-API/SpringUser/Detail?id=' + id,
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: '/SYS-API/SpringUser/Create',
    method: 'post',
    data
  })
}

export function edit(data) {
  return request({
    url: '/SYS-API/SpringUser/Edit',
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

export function listOrganizeTree() {
  return request({
    url: '/SYS-API/SpringOrganization/listAllRecord',
    method: 'get'
  })
}

export function updatePwd(data) {
  return request({
    url: '/SYS-API/SpringUser/SetPwd',
    method: 'post',
    data
  })
}

export function listRole(page, size, data) {
  return request({
    url: '/SYS-API/SpringRole/ListByPage?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function ListRoleByUserId(userId, page, size, data) {
  return request({
    url: '/SYS-API/SpringRole/ListByUserId/' + userId + '/?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function setRoleSave(userId, data) {
  data = qs.stringify({
    'ids': data
  }, {
    indices: false
  })
  return request({
    url: '/SYS-API/SpringUser/SetRoles/' + userId,
    method: 'post',
    data
  })
}
