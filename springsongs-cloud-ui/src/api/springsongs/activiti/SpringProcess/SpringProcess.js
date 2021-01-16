import request from '@/utils/request'
export function search(page, size, category) {
  // var data = qs.stringify(data)
  return request({
    url: '/ACTIVITI-SERVICE/SpringProcess/ListByPage?page=' + page + '&size=' + size + '&category=' + category,
    method: 'get'
  })
}

export function updateState(state, procDefId) {
  return request({
    url: `/ACTIVITI-API/SpringProcess/ActiveOrSuspend/${state}`,
    method: 'put',
    params: {
      procDefId: procDefId
    }
  })
}

export function converToModel(procDefId) {
  return request({
    url: `/ACTIVITI-API/SpringProcess/Convert/${procDefId}`,
    method: 'put'
  })
}

export function save(data) {
  return request({
    url: '/ACTIVITI-API/SpringActProcessRouter/Create',
    method: 'post',
    data
  })
}

export function edit(data, id) {
  return request({
    url: '/SpringActProcessRouter/Edit',
    method: 'put',
    data
  })
}

export function findSpringActProcessRouterByProcDefKey(procDefKey) {
  return request({
    url: `/ACTIVITI-API/SpringActProcessRouter/FindSpringActProcessRouterByProcDefKey?procDefKey=${procDefKey}`,
    method: 'get'
  })
}
