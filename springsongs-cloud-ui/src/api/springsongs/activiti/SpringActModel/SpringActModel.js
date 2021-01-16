import request from '@/utils/request'
export function search(page, size, data) {
  // var data = qs.stringify(data)
  return request({
    url: '/ACTIVITI-SERVICE/SpringActModel/ListByPage?page=' + page + '&size=' + size,
    method: 'post',
    data
  })
}

export function get(id) {
  return request({
    url: '/ACTIVITI-SERVICE/SpringActModel/Create/' + id,
    method: 'post'
  })
}

export function save(data) {
  return request({
    url: '/ACTIVITI-SERVICE/SpringActModel/Create',
    method: 'post',
    data
  })
}

export function edit(data, id) {
  return request({
    url: '/ACTIVITI-SERVICE/SpringActModel/Edit/' + id,
    method: 'put',
    data
  })
}

export function batchDelete(id) {
  return request({
    url: '/ACTIVITI-SERVICE/SpringActModel/SetDeleted/' + id,
    method: 'post'
  })
}

export function listAllSpringActCategory() {
  return request({
    url: '/ACTIVITI-SERVICE/SpringActCategory/ListAll',
    method: 'get'
  })
}

export function deploy(id) {
  return request({
    url: `/ACTIVITI-SERVICE/SpringActModel/Deploy/${id}`,
    method: 'put'
  })
}

export function exportXML(id) {
  return request({
    url: `/ACTIVITI-SERVICE/SpringActModel/Export/${id}`,
    method: 'get'
  })
}
