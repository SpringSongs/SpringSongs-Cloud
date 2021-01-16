import request from '@/utils/request'
import qs from 'qs'
export function search(page, sise, data) {
  return request({
    url: '/SYS-API/SpringArticleCategory/ListByPage?page=' + page + '&size=' + sise,
    method: 'post',
    data
  })
}

export function get(id) {
  return request({
    url: '/SYS-API/SpringArticleCategory/Detail?id=' + id,
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: '/SYS-API/SpringArticleCategory/Create',
    method: 'post',
    data
  })
}

export function edit(data) {
  return request({
    url: '/SYS-API/SpringArticleCategory/Edit',
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
    url: '/SYS-API/SpringArticleCategory/SetDeleted',
    method: 'post',
    data
  })
}

export function getCategorysByParent(parentId) {
  return request({
    url: '/SYS-API/SpringArticleCategory/GetCategorysByParent?parentId=' + parentId,
    method: 'get'
  })
}
