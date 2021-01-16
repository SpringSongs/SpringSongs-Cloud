import request from '@/utils/request'
import qs from 'qs'
export function search(page, size, data) {
  return request({
    url: '/SYS-API/SpringArticle/ListByPage?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function get(id) {
  return request({
    url: '/SYS-API/SpringArticle/Detail/' + id,
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: '/SYS-API/SpringArticle/Create',
    method: 'post',
    data
  })
}

export function edit(data) {
  return request({
    url: '/SYS-API/SpringArticle/Edit/',
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
    url: '/SYS-API/SpringArticle/SetDeleted',
    method: 'post',
    data
  })
}

export function auditStatus(id) {
  return request({
    url: '/SYS-API/SpringArticle/Audit/' + id,
    method: 'put'
  })
}

export function hotStatus(id) {
  return request({
    url: '/SYS-API/SpringArticle/HotStatus/' + id,
    method: 'put'
  })
}

export function topStatus(id) {
  return request({
    url: '/SYS-API/SpringArticle/TopStatus/' + id,
    method: 'put'
  })
}

export function featured(id) {
  return request({
    url: '/SYS-API/SpringArticle/Featured/' + id,
    method: 'put'
  })
}

export function loadCategoryTreeByParentId(parentId) {
  return request({
    url: '/SYS-API/SpringArticleCategory/GetCategorysByParent?parentId=' + parentId,
    method: 'get'
  })
}

export function listCategoryToTree() {
  return request({
    url: '/SYS-API/SpringArticleCategory/listAllRecord',
    method: 'get'
  })
}
